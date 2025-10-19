import java.util.InputMismatchException;
boolean ok = manager.removeStudent(id);
if (ok) System.out.println("Student removed.");
else System.out.println("No student with ID " + id + " found.");
} catch (NumberFormatException e) {
System.out.println("Invalid ID input.");
}
}


private static void updateStudentFlow(StudentManager manager, Scanner sc) {
try {
System.out.print("Enter ID to update: ");
int id = Integer.parseInt(sc.nextLine().trim());


System.out.print("Enter new name (leave blank to keep current): ");
String name = sc.nextLine();


System.out.print("Enter new age (0 to keep current): ");
int age = Integer.parseInt(sc.nextLine().trim());


System.out.print("Enter new grade (leave blank to keep current): ");
String grade = sc.nextLine();


System.out.print("Enter new address (leave blank to keep current): ");
String address = sc.nextLine();


// convert blanks to nulls where appropriate
String nameParam = name.trim().isEmpty() ? null : name.trim();
int ageParam = age; // caller checks >0
String gradeParam = grade.trim().isEmpty() ? null : grade.trim();
String addressParam = address.trim().isEmpty() ? null : address.trim();


manager.updateStudent(id, nameParam, ageParam, gradeParam, addressParam);
System.out.println("Student updated.");
} catch (NumberFormatException e) {
System.out.println("Invalid number input.");
} catch (NoSuchElementException e) {
System.out.println(e.getMessage());
} catch (IllegalArgumentException e) {
System.out.println("Update failed: " + e.getMessage());
}
}


private static void searchStudentFlow(StudentManager manager, Scanner sc) {
try {
System.out.print("Enter ID to search: ");
int id = Integer.parseInt(sc.nextLine().trim());
Student s = manager.searchStudent(id);
if (s == null) System.out.println("Student not found.");
else System.out.println(s);
} catch (NumberFormatException e) {
System.out.println("Invalid ID input.");
}
}
}