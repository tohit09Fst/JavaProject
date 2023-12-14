import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class NegativeMarksException extends Exception {
    public NegativeMarksException(String message) {
        super(message);
    }
}
class InvalidRollNumberException extends Exception {
    public InvalidRollNumberException(String message) {
        super(message);
    }
}

class Student {
    private int rollNumber;
    private String name;
    private int age;
    private double marks;

    public Student(int rollNumber, String name, int age, double marks) throws NegativeMarksException, InvalidRollNumberException {
        if (marks < 0) {
            throw new NegativeMarksException("Marks cannot be negative.");
        }
        if (rollNumber <= 0) {
            throw new InvalidRollNumberException("Invalid roll number.");
        }

        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }
}

public class StudentDatabase {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getRollNumber() == student.getRollNumber()) {
                System.out.println("Student with the same roll number already exists.");
                return;
            }
        }
        students.add(student);
    }

    public void viewStudents() {
        for (Student student : students) {
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Marks: " + student.getMarks());
            System.out.println();
        }
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null; // Student not found
    }

    public double calculateAverageMarks() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double totalMarks = 0.0;
        for (Student student : students) {
            totalMarks += student.getMarks();
        }

        return totalMarks / students.size();
    }

    public static void main(String[] args) {
        StudentDatabase database = new StudentDatabase();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Database Management Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Calculate Average Marks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    scanner.nextLine(); 

                    try {
                        Student newStudent = new Student(rollNumber, name, age, marks);
                        database.addStudent(newStudent);
                    } catch (NegativeMarksException | InvalidRollNumberException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    database.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Roll Number to Search: ");
                    int searchRollNumber = scanner.nextInt();
                    Student foundStudent = database.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student Found:");
                        System.out.println("Roll Number: " + foundStudent.getRollNumber());
                        System.out.println("Name: " + foundStudent.getName());
                        System.out.println("Age: " + foundStudent.getAge());
                        System.out.println("Marks: " + foundStudent.getMarks());
                    } else {
                        System.out.println("Student not found with the given Roll Number.");
                    }
                    break;

                case 4:

                    double averageMarks = database.calculateAverageMarks();
                    System.out.println("Average Marks of all students: " + averageMarks);
                    break;

                case 5:
                    System.out.println("Exiting the program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
