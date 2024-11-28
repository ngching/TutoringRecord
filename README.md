# My Personal Project - Tutoring Organizer App

## Project Description

*What* will the application do?  
The application will help tutors keep track of their tutoring record, including the tutoring hours with different students and the details of each student.

*Who* will use it?  
The main target users for this application is tutors, who could have an arbitary number of tutoring hours with different students. This application is particularly useful for tutors who have a lot of students.

*Why* is this project of interest to me?  
As a part-time tutor who work with several students at the same time, I find it challenging to track my tutoring hours with each student clearly. Hence, I want to design an application that allows tutors to organize their tutoring hours in a proper way.


## User Stories

*How* users could use this application:
- As a user, I want to be able to add a student to my tutoring record and specify the student's name, gender, grade, and tutoring subject(s)
- As a user, I want to be able to view the list of students in my tutoring record
- As a user, I want to be able to remove a student from my tutoring record
- As a user, I want to be able to mark a student's lesson as paid in my record
- As a user, I want to be able to select a student in my record, and view the student's tutoring record (past lessons) in detail
- As a user, I want to have the option to save my tutoring record to file on the main menu
- As a user, I want to have the option to load my tutoring record from file on the main menu

## Instructions for End User

*How* users could interact with the GUI of this application:
- You can add students to the tutoring record by clicking the button "Add a student", which brings you to another screen to entering some information about the student
- You can generate the first required action related to the user story "adding multiple students to a tutoring record", which is removing a student from the tutoring record by clicking the button "View all students", which brings you to another screen displaying all students names. Then, you could select a student and click on the button "Remove" to remove the selected student
- You can generate the second required action related to the user story "adding multiple students to a tutoring record", which is marking a student's lesson as paid/unpaid, by being in the screen where displaying all informations (including lessons (if any)) of the selected student. Then, select the lesson wanted to be marked as paid/unpaid and press the button "Mark Lesson As Paid" / "Mark Lesson As Unpaid" to mark that selected lesson of the selected student as paid/unpaid.
- You can locate my visual component by starting the application and having the splash screen with an image showing "Loading..." and some symbols
- You can save the state of my application by clicking the button "Save the record"
- You can reload the state of my application by clicking the button "Load the record"

## Phase 4: Task 2

A representative sample of the events that occur when running the program:
```
Event Log: 

Wed Nov 27 10:23:02 PST 2024
Added student: Mary

Wed Nov 27 10:23:23 PST 2024
Added lesson with Mary: on 2024-10-30

Wed Nov 27 10:23:39 PST 2024
Marked as paid: lesson on 2024-10-30

Wed Nov 27 10:23:41 PST 2024
Marked as unpaid: lesson on 2024-10-30

Wed Nov 27 02:23:47 PST 2024
Removed student: Mary
```

## Phase 4: Task 3

Reflection on the design:
If I had more time to work on the project, I would refactor the GUI by introducing an abstract class. The classes `AddStudent` and `AddLesson` have similar method behaviours, which focus on creating a panel that includes several text fields to add a student to lesson to the record. Currently, there are several duplicate codes in these two classes. For instance, both classes extend JPanel and implement ActionListener, share similar fields (a JPanel mainP, a CardLayout cl, a Student, and some JTextFields). They also includes similar visual components and same buttons which have slightly different actions. With the similarities in structures and methods between the two classes, these generic concepts could be refactored into an abstract class.

This refactoring eliminates redundancy and enhances code readability, making the code easier to understand and maintain. In addition, the UML class diagram would be updated to reflect this new structure, which results in a clearer diagram to show the relationships between classes in this application.