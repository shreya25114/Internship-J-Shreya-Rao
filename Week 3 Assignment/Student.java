import java.io.Serializable;
import java.util.Objects;


public class Student implements Serializable, Comparable<Student> {
private static final long serialVersionUID = 1L;


private int id;
private String name;
private int age;
private String grade;
private String address;


public Student() { }


public Student(int id, String name, int age, String grade, String address) {
this.id = id;
this.name = name == null ? "" : name.trim();
this.age = age;
this.grade = grade == null ? "" : grade.trim();
this.address = address == null ? "" : address.trim();
}


public int getId() { return id; }
public void setId(int id) { this.id = id; }


public String getName() { return name; }
public void setName(String name) { this.name = name == null ? "" : name.trim(); }


public int getAge() { return age; }
public void setAge(int age) { this.age = age; }


public String getGrade() { return grade; }
public void setGrade(String grade) { this.grade = grade == null ? "" : grade.trim(); }


public String getAddress() { return address; }
public void setAddress(String address) { this.address = address == null ? "" : address.trim(); }


@Override
public String toString() {
return String.format("Student{id=%d, name='%s', age=%d, grade='%s', address='%s'}",
id, name, age, grade, address);
}


@Override
public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof Student)) return false;
Student student = (Student) o;
return id == student.id;
}


@Override
public int hashCode() {
return Objects.hash(id);
}


@Override
public int compareTo(Student other) {
// default natural ordering by id
return Integer.compare(this.id, other.id);
}
}