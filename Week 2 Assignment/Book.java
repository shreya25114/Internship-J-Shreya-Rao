import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.locks.*;

// ======================== Book Class ========================
class Book {
    private final UUID bookID;
    private final String title;
    private final String author;
    private final String genre;
    private boolean isIssued;
    private Member issuedTo;
    private LocalDate dueDate;
    private final Queue<Member> reservationQueue;

    public Book(String title, String author, String genre) {
        this.bookID = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isIssued = false;
        this.issuedTo = null;
        this.dueDate = null;
        this.reservationQueue = new LinkedList<>();
    }

    public UUID getBookID() { return bookID; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isIssued() { return isIssued; }
    public Member getIssuedTo() { return issuedTo; }
    public LocalDate getDueDate() { return dueDate; }
    public Queue<Member> getReservationQueue() { return reservationQueue; }

    public void issueTo(Member member, int days) {
        this.isIssued = true;
        this.issuedTo = member;
        this.dueDate = LocalDate.now().plusDays(days);
    }

    public void returnBook() {
        this.isIssued = false;
        this.issuedTo = null;
        this.dueDate = null;
    }

    @Override
    public String toString() {
        return String.format("Book[ID=%s, Title=%s, Author=%s, Genre=%s, Issued=%s]",
                bookID, title, author, genre, isIssued ? "Yes" : "No");
    }
}

// ======================== Member (Abstract) ========================
abstract class Member {
    protected final UUID memberID;
    protected final String name;
    protected final String email;
    protected final String phone;
    protected final int maxBooksAllowed;
    protected final List<Book> currentlyIssuedBooks;

    public Member(String name, String email, String phone, int maxBooksAllowed) {
        this.memberID = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.maxBooksAllowed = maxBooksAllowed;
        this.currentlyIssuedBooks = new ArrayList<>();
    }

    public UUID getMemberID() { return memberID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
    public List<Book> getCurrentlyIssuedBooks() { return currentlyIssuedBooks; }

    public abstract int getMaxAllowedDays();
    public abstract String getMemberType();

    @Override
    public String toString() {
        return String.format("%s[ID=%s, Name=%s, Email=%s, Phone=%s]",
                getMemberType(), memberID, name, email, phone);
    }
}

class StudentMember extends Member {
    public StudentMember(String name, String email, String phone) {
        super(name, email, phone, 3);
    }
    public int getMaxAllowedDays() { return 14; }
    public String getMemberType() { return "Student"; }
}

class TeacherMember extends Member {
    public TeacherMember(String name, String email, String phone) {
        super(name, email, phone, 5);
    }
    public int getMaxAllowedDays() { return 30; }
    public String getMemberType() { return "Teacher"; }
}

class GuestMember extends Member {
    public GuestMember(String name, String email, String phone) {
        super(name, email, phone, 1);
    }
    public int getMaxAllowedDays() { return 7; }
    public String getMemberType() { return "Guest"; }
}

// ======================== Librarian ========================
class Librarian extends Member {
    public Librarian(String name, String email, String phone) {
        super(name, email, phone, Integer.MAX_VALUE);
    }
    public int getMaxAllowedDays() { return Integer.MAX_VALUE; }
    public String getMemberType() { return "Librarian"; }
}

// ======================== Library ========================
class Library {
    private final Map<UUID, Book> books;
    private final Map<UUID, Member> members;
    private final ReadWriteLock rwLock;

    public Library() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.rwLock = new ReentrantReadWriteLock();
    }

    // === Add Book ===
    public void addBook(Book book) {
        rwLock.writeLock().lock();
        try {
            if (books.containsKey(book.getBookID())) {
                throw new IllegalArgumentException("Book with ID already exists!");
            }
            books.put(book.getBookID(), book);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // === Remove Book ===
    public void removeBook(Book book) {
        rwLock.writeLock().lock();
        try {
            if (book.isIssued()) {
                throw new IllegalStateException("Cannot remove issued book!");
            }
            books.remove(book.getBookID());
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // === Register Member ===
    public void registerMember(Member member) {
        rwLock.writeLock().lock();
        try {
            for (Member m : members.values()) {
                if (m.getEmail().equals(member.getEmail()) ||
                    m.getPhone().equals(member.getPhone())) {
                    throw new IllegalArgumentException("Member already exists!");
                }
            }
            members.put(member.getMemberID(), member);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // === Issue Book ===
    public void issueBook(Book book, Member member) {
        rwLock.writeLock().lock();
        try {
            if (book.isIssued()) {
                throw new IllegalStateException("Book already issued!");
            }
            if (member.getCurrentlyIssuedBooks().size() >= member.getMaxBooksAllowed()) {
                throw new IllegalStateException("Member reached max book limit!");
            }
            book.issueTo(member, member.getMaxAllowedDays());
            member.getCurrentlyIssuedBooks().add(book);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // === Return Book ===
    public void returnBook(Book book, Member member) {
        rwLock.writeLock().lock();
        try {
            if (!book.isIssued() || !member.getCurrentlyIssuedBooks().contains(book)) {
                throw new IllegalStateException("Invalid return attempt!");
            }
            member.getCurrentlyIssuedBooks().remove(book);
            book.returnBook();

            if (!book.getReservationQueue().isEmpty()) {
                Member next = book.getReservationQueue().poll();
                issueBook(book, next);
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // === Reserve Book ===
    public void reserveBook(Book book, Member member) {
        rwLock.writeLock().lock();
        try {
            if (!book.isIssued()) {
                throw new IllegalStateException("Book is available. No need to reserve.");
            }
            book.getReservationQueue().add(member);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    // === Search Books ===
    public List<Book> searchBooks(String keyword) {
        rwLock.readLock().lock();
        try {
            List<Book> results = new ArrayList<>();
            for (Book b : books.values()) {
                if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    b.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                    b.getGenre().toLowerCase().contains(keyword.toLowerCase())) {
                    results.add(b);
                }
            }
            return results;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    // === View Issued Books ===
    public void viewIssuedBooks(Member member) {
        rwLock.readLock().lock();
        try {
            for (Book b : member.getCurrentlyIssuedBooks()) {
                long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), b.getDueDate());
                System.out.printf("Book: %s, Due Date: %s, Days Left: %d%n",
                        b.getTitle(), b.getDueDate(), daysLeft);
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    // === View Overdue Books (Librarian only) ===
    public List<Book> viewOverdueBooks(Librarian librarian) {
        rwLock.readLock().lock();
        try {
            List<Book> overdue = new ArrayList<>();
            for (Book b : books.values()) {
                if (b.isIssued() && b.getDueDate().isBefore(LocalDate.now())) {
                    overdue.add(b);
                }
            }
            return overdue;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    // === Safe Getters ===
    public Collection<Member> getAllMembers() {
        rwLock.readLock().lock();
        try {
            return new ArrayList<>(members.values());
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public Collection<Book> getAllBooks() {
        rwLock.readLock().lock();
        try {
            return new ArrayList<>(books.values());
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

// ======================== LibraryApp (Demo) ========================
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Alice", "alice@lib.com", "12345");
        StudentMember student = new StudentMember("Bob", "bob@uni.com", "54321");
        TeacherMember teacher = new TeacherMember("Charlie", "charlie@uni.com", "67890");

        library.registerMember(librarian);
        library.registerMember(student);
        library.registerMember(teacher);

        Book book1 = new Book("Java Programming", "James Gosling", "Education");
        Book book2 = new Book("Design Patterns", "GoF", "Education");
        library.addBook(book1);
        library.addBook(book2);

        library.issueBook(book1, student);
        library.issueBook(book2, teacher);

        System.out.println("Issued Books for Bob:");
        library.viewIssuedBooks(student);

        System.out.println("\nAll Members:");
        for (Member m : library.getAllMembers()) {
            System.out.println(m);
        }

        System.out.println("\nAll Books:");
        for (Book b : library.getAllBooks()) {
            System.out.println(b);
        }

        System.out.println("\nReturning Book1 from Bob...");
        library.returnBook(book1, student);

        System.out.println("\nAll Books after return:");
        for (Book b : library.getAllBooks()) {
            System.out.println(b);
        }
    }
}
