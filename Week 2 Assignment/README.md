# Library Management System (Java OOP Project)

## Overview
This project is a **full-fledged Library Management System** implemented in **Java**, showcasing advanced **Object-Oriented Programming (OOP)** principles:
- Abstraction  
- Encapsulation  
- Inheritance  
- Polymorphism  
- Interfaces  
- Exception Handling  
- Concurrency with ReadWriteLock  
- Modular Design  

It manages **books, members, staff, and library operations** such as issuing, returning, searching, and reserving books.

---

## Features
- **Book Management**
  - Add / Remove books  
  - Search books by title, author, or genre  
  - Track availability & reservations  

- **Member Management**
  - Register Students, Teachers, Guests, and Librarians  
  - Enforce book limits per member type  
  - Track issued books with due dates  

- **Library Operations**
  - Issue and return books  
  - Reserve books if unavailable  
  - View overdue books (Librarian only)  
  - View all issued books with remaining days  

- **Concurrency**
  - Thread-safe operations with `ReadWriteLock`  

---

## Class Design
### Book
- Attributes: `bookID`, `title`, `author`, `genre`, `isIssued`, `issuedTo`, `dueDate`, `reservationQueue`

### Member (Abstract)
- Attributes: `memberID`, `name`, `email`, `phone`, `maxBooksAllowed`, `currentlyIssuedBooks`
- Subclasses:
  - `StudentMember` → 3 books, 14 days  
  - `TeacherMember` → 5 books, 30 days  
  - `GuestMember` → 1 book, 7 days  
  - `Librarian` → unlimited books, can manage catalog  

### Library
- Stores all `Books` and `Members`  
- Manages all operations (issue, return, reserve, search, etc.)  

---

## Technologies Used
- **Java 17+**  
- **Collections Framework** (`List`, `Map`, `Queue`)  
- **java.time API** (`LocalDate`)  
- **Concurrency** (`ReentrantReadWriteLock`)  

---

## Getting Started

### Clone the Repository
```bash
git clone https://github.com/your-username/library-management-system.git
cd library-management-system
