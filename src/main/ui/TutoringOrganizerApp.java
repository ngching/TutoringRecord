package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.Lesson;
import model.Student;
import model.TutoringRecord;

// A tutoring organizer appliation that allows users to record their students and their tutoring
// hours with each students
public class TutoringOrganizerApp {

    private Student selectedStudent;
    private TutoringRecord students;
    private Scanner input;

    // EFFECTS: runs the tutoring organizer application
    public TutoringOrganizerApp() {
        runOrganizer();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // code based on TellerApp
    private void runOrganizer() {
        boolean keepRunning = true;
        String command = null;

        init();
        printDivider();
        System.out.println("Welcome to the Tutoring Organizer app!\n");

        while (keepRunning) {
            displayMainMenu();
            command = input.nextLine();
            command = command.toLowerCase();
            printDivider();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processMainMenuCommand(command);
            }
        }

        System.out.println("Thanks for using the Tutoring Organizer app!");
        System.out.println("Wish you successful lessons ahead.");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: initializes the applcation with starting values (empty list of
    // students)
    private void init() {
        this.students = new TutoringRecord();
        this.selectedStudent = null;
        this.input = new Scanner(System.in);
    }

    // EFFECTS: displays a list of commands the user could do in the main menu
    private void displayMainMenu() {
        System.out.println("Please select from:");
        System.out.println("\ta: Add a student");
        System.out.println("\ts: View all students by their names");
        System.out.println("\tl: View all lessons for all students in the list");
        System.out.println("\tq: Quit application");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: processes the command in the main menu according to the input
    // command
    private void processMainMenuCommand(String command) {
        if (command.equals("a")) {
            addNewStudent();
        } else if (command.equals("s")) {
            displayStudentsNames();
        } else if (command.equals("l")) {
            displayAllLessons();
        } else {
            System.out.println("ERROR: Invalid input option. Please try again.");
            printDivider();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new student to the list of students
    private void addNewStudent() {
        System.out.println("Please enter the student's name:");
        String name = this.input.nextLine();

        System.out.println("\nPlease enter the student's gender ('m' for male, 'f' for female):");
        String genderString = this.input.nextLine();
        genderString = genderString.toLowerCase();
        boolean isMale = genderString.equals("m");

        System.out.println("\nPlease enter the student's grade:");
        int grade = this.input.nextInt();
        this.input.nextLine();

        System.out.println("\nPlease enter the subject(s) that the student needs tutoring in:");
        String subjects = this.input.nextLine();

        Student student = new Student(name, isMale, grade, subjects);
        students.addStudent(student);
        System.out.println("\nNew student '" + name + "' was successfully added!");
        printDivider();
    }

    // EFFECTS: displays all students by their names at once
    private void displayStudentsNames() {
        ArrayList<String> name = students.getStudentsNames();

        if (name.isEmpty()) {
            System.out.println("ERROR: No student found. Try adding a student first!");
            printDivider();
        } else {
            displayNamesAndHandleStudentListMenu();
        }
    }

    // EFFECTS: displays all lessons for every students
    private void displayAllLessons() {
        if (students.getStudentsNames().isEmpty()) {
            System.out.println("ERROR: No student found. Try adding a student first!");
            printDivider();
        } else {
            for (Student s : students.getStudents()) {
                System.out.println("Lessons with " + s.getName() + ":\n");
                displayLessons(s);
                printDivider();
            }
        }
    }

    // EFFECTS: displays the lessons of the given student in a readable way
    private void displayLessons(Student student) {
        ArrayList<Lesson> lessonslist = student.getLessons();
        int listsize = lessonslist.size();

        for (int i = 0; i < listsize; i++) {
            Lesson output = lessonslist.get(i);
            LocalDateTime starting = output.getStarting();
            LocalDateTime ending = output.getEnding();
            String subject = output.getSubject();
            Boolean isPaid = output.getPaymentStatus();

            if (isPaid) {
                System.out.println(
                        "\t#" + (i + 1) + " " + starting + " to " + ending + " ~ " + subject + " -> PAID");
            } else {
                System.out.println(
                        "\t#" + (i + 1) + " " + starting + " to " + ending + " ~ " + subject + " -> UNPAID");
            }
        }
        System.out.println("");
    }

    // MODIFIES: this
    // EFFECTS: displays students names, displays and handles the student list menu
    // according to the input command
    private void displayNamesAndHandleStudentListMenu() {
        boolean stillOnStudentListMenu = true;
        String command;

        while (stillOnStudentListMenu) {
            displayNames();
            displayStudentListMenu();

            command = this.input.nextLine();
            printDivider();
            if (command.equals("m")) {
                stillOnStudentListMenu = false;
            } else {
                processStudentListMenuCommand(command);
            }

            if (students.isEmpty()) {
                stillOnStudentListMenu = false;
                System.out.println("No more students in the list...\n");
            }
        }
        System.out.println("Returning back to the main menu...\n");
    }

    // EFFECTS: displays all students names
    private void displayNames() {
        ArrayList<String> names = students.getStudentsNames();
        int listsize = names.size();
        System.out.println("Name of student(s):\n");

        for (int i = 0; i < listsize; i++) {
            String firstName = names.get(i);
            System.out.println("\t#" + (i + 1) + " " + firstName + "\n");
        }
        printDivider();
    }

    // EFFECTS: displays a list of commands the user could do in the student list
    // menu
    private void displayStudentListMenu() {
        System.out.println("Please select from:");
        System.out.println("\tr: Remove a student");
        System.out.println("\ts: Select a student and view the details of the student");
        System.out.println("\tm: Return to the main menu");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: processes the command in the student list menu according to the
    // input command
    private void processStudentListMenuCommand(String command) {
        if (command.equals("r")) {
            removeStudent();
        } else if (command.equals("s")) {
            selectStudent();
        } else {
            System.out.println("ERROR: Invalid input option. Please try again.");
            printDivider();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a student from the list of students
    private void removeStudent() {
        System.out.println("Please enter the number (#) of the student to be removed:");
        int removeStudentIndex = this.input.nextInt();
        this.input.nextLine();

        if (removeStudentIndex > students.getSize()) {
            System.out.println("\nERROR: Invalid number. Please enter a number (#) from the student name list.");
        } else {
            removeStudentIndex--;
            Student remove = students.get(removeStudentIndex);
            students.removeStudent(remove);
            System.out.println("\nRemoved student '" + remove.getName() + "'.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: selects a student from the list of students and shows the details
    private void selectStudent() {
        System.out.println("Please enter the number (#) of the selected student to view his/her details:");
        int selectedStudentIndex = this.input.nextInt();
        this.input.nextLine();

        if (selectedStudentIndex > students.getSize()) {
            System.out.println("\nERROR: Invalid number. Please enter a number (#) from the student name list.");
            printDivider();
        } else {
            selectedStudentIndex--;
            this.selectedStudent = students.get(selectedStudentIndex);
            showDetailsAndHandleStudentLessonsMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: shows the details of the selected student, displays and handles the
    // student lessons menu according to the input command
    private void showDetailsAndHandleStudentLessonsMenu() {
        boolean stillOnStudentLessonMenu = true;
        String command;

        while (stillOnStudentLessonMenu) {
            showSelectedStudentDetails();
            displayStudentLessonsMenu();

            command = this.input.nextLine();
            printDivider();
            if (command.equals("sl")) {
                stillOnStudentLessonMenu = false;
            } else {
                processStudentLessonsMenuCommand(command);
            }
        }
        System.out.println("Returning back to the student list menu...\n");
    }

    // EFFECTS: displays the details of the selected student
    private void showSelectedStudentDetails() {
        System.out.println("Name:                 " + selectedStudent.getName());
        System.out.println("Gender:               " + selectedStudent.getGender());
        System.out.println("Grade:                " + selectedStudent.getGrade());
        System.out.println("Tutoring subject(s):  " + selectedStudent.getSubjects());
        System.out.println("Lessons: ");
        displayLessons(selectedStudent);
        printDivider();
    }

    // EFFECTS: displays a list of commands the user could do in the student's
    // lessons menu
    private void displayStudentLessonsMenu() {
        System.out.println("Please select an option:");
        System.out.println("\ta: Add a lesson with the student");
        System.out.println("\tp: Select a lesson and mark it as paid");
        System.out.println("\tu: Select a lesson and mark it as unpaid");
        System.out.println("\tsl: Return to the student list menu");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: processes the command in the student's lessons menu according to the
    // input command
    private void processStudentLessonsMenuCommand(String command) {
        if (command.equals("a")) {
            addLessonWithStudent();
        } else if (command.equals("p")) {
            markLessonAsPaid();
        } else if (command.equals("u")) {
            markLessonAsUnpaid();
        } else {
            System.out.println("ERROR: Invalid input option. Please try again.");
            printDivider();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a lesson with the selected student
    @SuppressWarnings("methodlength")
    private void addLessonWithStudent() {
        System.out.print("Please enter the year of the lesson took place: ");
        int year = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the month of the lesson took place: ");
        int month = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the day of the lesson took place: ");
        int day = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the starting hour of the lesson took place: ");
        int startingHour = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the starting minute of the lesson took place: ");
        int startingMinute = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the ending hour of the lesson took place: ");
        int endingHour = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the ending minute of the lesson took place: ");
        int endingMinute = this.input.nextInt();
        this.input.nextLine();

        System.out.print("Please enter the subject taught in the lesson: ");
        String subject = this.input.nextLine();

        LocalDateTime starting = LocalDateTime.of(year, month, day, startingHour, startingMinute);
        LocalDateTime ending = LocalDateTime.of(year, month, day, endingHour, endingMinute);
        Lesson add = new Lesson(starting, ending, subject);
        selectedStudent.addLesson(add);
        System.out.println(
                "\nNew lesson with " + selectedStudent.getName() + " on " + starting + " was successfully added!");
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: marks the selected lesson as paid
    private void markLessonAsPaid() {
        System.out.println("Please enter the lesson number (#) to mark it as paid:");
        int index = this.input.nextInt();
        this.input.nextLine();
        ArrayList<Lesson> lessons = selectedStudent.getLessons();

        if (lessons.isEmpty()) {
            System.out.println("\nERROR: No lesson found. Try adding a lesson first!");
        } else if (index > lessons.size()) {
            System.out.println("\nERROR: Invalid number. Please enter a number (#) from the student lessons list.");
        } else {
            index--;
            Lesson lesson = lessons.get(index);
            lesson.markAsPaid();
            System.out.println("\nLesson #" + (index + 1) + " with " + selectedStudent.getName()
                    + " was successfully marked as paid!");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: marks the selected lesson as unpaid
    private void markLessonAsUnpaid() {
        System.out.println("Please enter the lesson number (#) to mark it as unpaid:");
        int index = this.input.nextInt();
        this.input.nextLine();
        ArrayList<Lesson> lessons = selectedStudent.getLessons();

        if (lessons.isEmpty()) {
            System.out.println("\nERROR: No lesson found. Try adding a lesson first!");
        } else if (index > lessons.size()) {
            System.out.println("\nERROR: Invalid number. Please enter a number (#) from the student lessons list.");
        } else {
            index--;
            Lesson lesson = lessons.get(index);
            lesson.markAsUnpaid();
            System.out.println("\nLesson #" + (index + 1) + " with " + selectedStudent.getName()
                    + " was successfully marked as unpaid!");
        }
        printDivider();
    }

    // EFFECTS: prints out a divider (a line of equals signs) to make things clear
    private void printDivider() {
        System.out.print("===============================================");
        System.out.print("====================================================\n");
    }
}
