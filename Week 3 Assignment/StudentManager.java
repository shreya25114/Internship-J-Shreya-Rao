import java.io.*;
import java.util.*;


public class StudentManager {
private ArrayList<Student> students;
private HashMap<Integer, Student> studentMap;
private TreeSet<Student> studentSet; // sorted by name then id


public StudentManager() {
this.students = new ArrayList<>();
this.studentMap = new HashMap<>();
this.studentSet = new TreeSet<>(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER)
.thenComparingInt(Student::getId));
}


public synchronized void addStudent(Student student) throws IllegalArgumentException {
if (student == null) throw new IllegalArgumentException("Student cannot be null");
if (student.getId() <= 0) throw new IllegalArgumentException("ID must be positive");
if (student.getName() == null || student.getName().isEmpty())
throw new IllegalArgumentException("Name cannot be empty");
if (student.getAge() <= 0) throw new IllegalArgumentException("Age must be positive");
if (studentMap.containsKey(student.getId()))
throw new IllegalArgumentException("A student with this ID already exists: " + student.getId());


students.add(student);
studentMap.put(student.getId(), student);
studentSet.add(student);
}


public synchronized boolean removeStudent(int id) {
Student removed = studentMap.remove(id);
if (removed == null) return false;
students.remove(removed);
studentSet.remove(removed);
return true;
}


public synchronized void updateStudent(int id, String name, int age, String grade, String address)
throws NoSuchElementException, IllegalArgumentException {
Student s = studentMap.get(id);
if (s == null) throw new NoSuchElementException("Student with ID " + id + " not found.");
if (name != null && !name.trim().isEmpty()) s.setName(name);
if (age > 0) s.setAge(age);
if (grade != null) s.setGrade(grade);
if (address != null) s.setAddress(address);


// Rebalance TreeSet because name might have changed
studentSet.remove(s);
studentSet.add(s);
}


public synchronized Student searchStudent(int id) {
return studentMap.get(id);
}


public synchronized void displayAllStudents() {
if (studentSet.isEmpty()) {
System.out.println("No students to display.");
return;
}
System.out.println("--- All students (sorted by name, then ID) ---");
}