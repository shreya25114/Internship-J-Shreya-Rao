Project: Advanced Student Management System (Java)
Files:
- Student.java
- StudentManager.java
- Main.java


Description:
Console-based Student Management System implementing CRUD operations,
persistent storage using ObjectInputStream/ObjectOutputStream,
and advanced collection usage (ArrayList, HashMap, TreeSet).


How to compile & run:
1. Ensure you have Java 8+ installed.
2. Save the three .java files in the same directory.
3. From that directory run:
javac *.java
java Main


Data persistence:
- The program reads/writes to 'students.dat' in the working directory.
- On exit (option 6) it saves the current student list to the file.


Validation & error handling:
- ID must be positive and unique.
- Age must be positive.
- Name cannot be empty when adding a student.
- Input parsing errors are handled and user-friendly messages shown.


Testing suggestion:
- Start program and choose option 1 five times to add 5 sample students.
- Test options 2-5 to remove, update, search, and display.
- Exit with option 6 to persist data; rerun to confirm data loads.


Notes & extensions (optional):
- You may add more fields (email, phone), filtering, CSV import/export,
or a GUI front-end.
*/
