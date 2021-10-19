package A2_2020149;
/*
AP Assignment 2
 Rahul Maddula
 2020149
*/

import java.util.*;
import java.text.*;

public class A2 {
    static Scanner scInt = new Scanner(System.in);  // Scanner to take integer inputs
    static Scanner scString = new Scanner(System.in);  // Scanner to take String inputs

    static ArrayList<instructor> instructors = new ArrayList<>();  // Store all objects of instructor class
    static ArrayList<student> students = new ArrayList<>();  // Store all objects of student class
    static ArrayList<slide> slides = new ArrayList<>();  // Store all objects of slide class
    static ArrayList<video> videos = new ArrayList<>();  // Store all objects of video class
    static ArrayList<assignment> assignments = new ArrayList<>();  // Store all objects of assignment class
    static ArrayList<quiz> quizzes = new ArrayList<>();  // Store all objects of quiz class
    static ArrayList<submission> submissions = new ArrayList<>();  // Store all objects of quiz class
    static ArrayList<comment> comments = new ArrayList<>();  // Store all objects of comment class

    static int id_count = 0;

    interface backpack_interface {
        void view_lecture_materials();
        void view_assessments();
        void view_comments();
        void add_comments();
    }

    public static void line() {
        System.out.println("----------------------------------------");
    }

    public static String date_time() {  // Print the local date and time according to the given format (Arabian Standard Time)
        SimpleDateFormat sd = new SimpleDateFormat(
                "E MMM d HH:mm:ss z y");
        Date date = new Date();
        return(sd.format(date));
    }

    public static void login_menu() {
        line();
        System.out.println("""
                1. Enter as Instructor
                2. Enter as Student
                3. Exit""");
        line();
        System.out.print("Enter your choice: ");
        int choice = scInt.nextInt();
        boolean valid = false;
        while (!valid) {
            switch (choice) {
                case 1 -> {
                    for (int i = 0; i < instructors.size(); i++) {
                        System.out.println(i + " - " + instructors.get(i).instructor_id);
                    }
                    System.out.print("Enter your choice: ");
                    int choice2 = scInt.nextInt();
                    boolean valid2 = (choice2 >= 0 && choice2 < instructors.size());
                    while (!valid2) {
                        System.out.println("Invalid input. Please enter an input from the given options.");
                        System.out.print("Enter your choice: ");
                        choice2 = scInt.nextInt();
                        valid2 = (choice2 >= 0 && choice2 < instructors.size());
                    }
                    valid = true;
                    Instructor(choice2);
                }
                case 2 -> {
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println(i + " - " + students.get(i).student_id);
                    }
                    System.out.print("Enter your choice: ");
                    int choice2 = scInt.nextInt();
                    boolean valid2 = (choice2 >= 0 && choice2 < students.size());
                    while (!valid2) {
                        System.out.println("Invalid input. Please enter an input from the given options.");
                        System.out.print("Enter your choice: ");
                        choice2 = scInt.nextInt();
                        valid2 = (choice2 >= 0 && choice2 < students.size());
                    }
                    valid = true;
                    Student(choice2);
                }
                case 3 -> {
                    valid = true;
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid input. Please enter an input from the given options");
                    System.out.print("Enter your choice: ");
                    choice = scInt.nextInt();
                }
            }
        }
    }

    public static void instructor_menu() {
        line();
        System.out.println("""
                1. Add Lecture material
                2. Add Assessments
                3. View Lecture materials
                4. View Assessments
                5. Grade Assessments
                6. Close Assessment
                7. View Comments
                8. Add Comments
                9. Logout""");
        line();
    }

    public static void student_menu() {
        line();
        System.out.println("""
                1. View Lecture materials
                2. View Assessments
                3. Submit Assessment
                4. View Grades
                5. View Comments
                6. Add Comments
                7. Logout""");
        line();
    }

    public static class slide {
        String instructor_id, slide_topic, date;
        ArrayList<String> content = new ArrayList<>();
    }

    public static class video {
        String instructor_id, video_topic, video_file, date;
    }

    public static class assignment {
        String instructor_id, problem_statement, date;
        int assignment_id, max_marks, status = 0;  // status 0 - open, status 1 - closed
        ArrayList<String> pending_assignment = new ArrayList<>();  // List of student IDs who didn't submit the assignment
    }

    public static class quiz {
        String instructor_id, quiz_question, answer, date;
        int quiz_id, status = 0;  // status 0 - open, status 1 - closed
        ArrayList<String> pending_quiz = new ArrayList<>();  // List of student IDs who didn't submit the quiz
    }

    public static class submission {
        String student_id, instructor_id, assignment_filename, quiz_answer;
        int assignment_id, marks = 0, quiz_id, status = 0;  // By default, a status 0 - not submitted, status 1 - submitted & ungraded, status 2 - graded
    }

    public static class comment {
        String student_id, instructor_id, comment, date;
    }

    public static class instructor implements backpack_interface {
        String instructor_id;

        public void add_lecture_material() {
            System.out.println("""
                    1. Add Lecture Slide
                    2. Add Lecture Video""");
            System.out.print("Enter your choice: ");
            int choice = scInt.nextInt();
            boolean valid = (choice >= 1 && choice <= 9);
            while (!valid) {
                System.out.println("Invalid input. Please enter an input from the given options");
                System.out.print("Enter your choice: ");
                choice = scInt.nextInt();
                valid = (choice == 1 || choice == 2);
            }
            if (choice == 1) {
                slide s = new slide();  // Uses-A a relationship
                System.out.print("Enter the topic of slides: ");
                String slide_topic = scString.nextLine();
                System.out.print("Enter the number of slides: ");
                int n = scInt.nextInt();
                if (n > 0) {
                    s.instructor_id = instructor_id;
                    s.slide_topic = slide_topic;
                    for(int i = 0; i < n; i++) {
                        System.out.print("Content for slide " + (i + 1) + ": ");
                        String content = scString.nextLine();
                        s.content.add(content);
                    }
                    s.date = date_time();
                    slides.add(s);
                }
                else
                    System.out.println("You chose not to add any lecture slides.");
            }
            else if (choice == 2) {
                video v = new video();  // Uses-A a relationship
                v.instructor_id = instructor_id;
                System.out.print("Enter the topic of video: ");
                v.video_topic = scString.nextLine();
                System.out.print("Enter the filename of video: ");
                String video_file = scString.nextLine();
                scInt.nextLine();
                while (video_file.contains(" ") || !video_file.toLowerCase().endsWith(".mp4")) {
                    System.out.print("Incorrect file format. Please enter a single file name with '.mp4' extension.\nEnter the filename of video: ");
                    video_file = scInt.nextLine();
                }
                v.date = date_time();
                v.video_file = video_file;
                videos.add(v);
            }
            else
                System.out.println("Invalid input. Please enter an input from the given options");
        }

        public void add_assessments() {
            System.out.println("""
                    1. Add Assignment
                    2. Add Quiz""");
            System.out.print("Enter your choice: ");
            int choice = scInt.nextInt();
            boolean valid = (choice >= 1 && choice <= 9);
            while (!valid) {
                System.out.println("Invalid input. Please enter an input from the given options");
                System.out.print("Enter your choice: ");
                choice = scInt.nextInt();
                valid = (choice == 1 || choice == 2);
            }
            if (choice == 1) {
                assignment a = new assignment();
                a.assignment_id = ++id_count;
                a.instructor_id = instructor_id;
                System.out.print("Enter the problem statement: ");
                a.problem_statement = scString.nextLine();
                System.out.print("Enter max marks: ");
                a.max_marks = scInt.nextInt();
                a.date = date_time();
                for (A2.student student : students) {  // Add all students IDs to the pending list of added assignment
                    a.pending_assignment.add(student.student_id);
                }
                assignments.add(a);
            }
            else if (choice == 2) {
                quiz q = new quiz();
                q.quiz_id = ++id_count;
                q.instructor_id = instructor_id;
                System.out.print("Enter the quiz question: ");
                q.quiz_question = scString.nextLine();
                q.date = date_time();
                for (A2.student student : students) {  // Add all students IDs to the pending list of added quiz
                    q.pending_quiz.add(student.student_id);
                }
                quizzes.add(q);
            }
            else
                System.out.println("Invalid input. Please enter an input from the given options");
        }

        @Override
        public void view_lecture_materials() {
            if (slides.size() == 0 && videos.size() == 0) {
                System.out.println("No lecture materials available.");
                System.out.println();
            }
            else {
                line();
                for (A2.slide slide : slides) {
                    System.out.println("Title: " + slide.slide_topic);
                    for (int i = 0; i < slide.content.size(); i++) {
                        System.out.println("Slide " + (i + 1) + ": " + slide.content.get(i));
                    }
                    System.out.println("Number of slides: " + slide.content.size());
                    System.out.println("Date of upload: " + slide.date);
                    System.out.println("Uploaded by: " + slide.instructor_id);
                    line();
                }
                System.out.println();
                line();
                for (A2.video video : videos) {
                    System.out.println("Title of video: " + video.video_topic);
                    System.out.println("Video file: " + video.video_file);
                    System.out.println("Date of upload: " + video.date);
                    System.out.println("Uploaded by: " + video.instructor_id);
                }
            }
        }

        @Override
        public void view_assessments() {
            if (assignments.size() == 0 && quizzes.size() == 0) {
                System.out.println("No assessments available.");
                System.out.println();
            }
            else {
                for (A2.assignment assignment : assignments) {
                    System.out.println("Assignment: " + assignment.problem_statement);
                    System.out.println("Max marks: " + assignment.max_marks);
                    if (assignment.status == 0)
                        System.out.println("Status: Open");
                    else
                        System.out.println("Status: Closed");
                    System.out.println("Date of upload: " + assignment.date);
                    System.out.println("Uploaded by: " + assignment.instructor_id);
                    line();
                }
                System.out.println();
                line();
                for (A2.quiz quiz : quizzes) {
                    System.out.println("Question: " + quiz.quiz_question);
                    System.out.println("Max marks: 1");
                    if (quiz.status == 0)
                        System.out.println("Status: Open");
                    else
                        System.out.println("Status: Closed");
                    System.out.println("Date of upload: " + quiz.date);
                    System.out.println("Uploaded by: " + quiz.instructor_id);
                    line();
                }
            }
        }

        public void grade_assessments() {
            if (assignments.size() == 0 && quizzes.size() == 0) {
                System.out.println("No assessments available.");
                System.out.println();
            }
            else {
                int i, j;
                line();
                for (i = 0; i < assignments.size(); i++) {
                    System.out.println("ID: " + i);
                    System.out.println("        Assignment: " + assignments.get(i).problem_statement);
                    System.out.println("        Max marks: " + assignments.get(i).max_marks);
                    line();
                }
                System.out.println();
                line();
                for (j = i; j < quizzes.size() + i; j++) {
                    System.out.println("ID: " + j);
                    System.out.println("        Question: " + quizzes.get(j - i).quiz_question);
                    line();
                }
                System.out.print("Enter the ID of assessment to view submission: ");
                int choice = scInt.nextInt();
                boolean present = false;
                if (choice >= assignments.size()) {
                    choice = choice - i;
                    for (int k = 0; k < students.size(); k++) {
                        for (A2.submission submission : submissions) {
                            if (submission.quiz_id == quizzes.get(choice).quiz_id && submission.status == 1 && submission.student_id.equals(students.get(k).student_id)) {
                                System.out.println(k + ". " + students.get(k).student_id);
                                present = true;
                                break;
                            }
                        }
                    }

                    if (present) {
                        System.out.print("Choose student ID from these ungraded submissions: ");
                        int choice2 = scInt.nextInt();
                        int submission_index = -1;
                        for (int k = 0; k < submissions.size(); k++) {
                            if (submissions.get(k).quiz_id == quizzes.get(choice).quiz_id && submissions.get(k).status == 1 && submissions.get(k).student_id.equals(students.get(choice2).student_id)) {
                                System.out.println("Submission: " + submissions.get(k).quiz_answer);
                                submission_index = k;
                                line();
                                break;
                            }
                        }
                        System.out.println("Max marks: 1");
                        System.out.print("Marks scored: ");
                        int marks = scInt.nextInt();
                        while (marks > 1) {
                            System.out.println("Marks cannot exceed max marks.");
                            System.out.print("Marks scored: ");
                            marks = scInt.nextInt();
                        }
                        submissions.get(submission_index).marks = marks;
                        submissions.get(submission_index).instructor_id = instructor_id;
                        submissions.get(submission_index).status = 2;  // Graded status
                    }
                    else
                        System.out.println("No ungraded submissions available.");
                }
                else {
                    for (int k = 0; k < students.size(); k++) {
                        for (A2.submission submission : submissions) {
                            if (submission.assignment_id == assignments.get(choice).assignment_id && submission.status == 1 && submission.student_id.equals(students.get(k).student_id)) {
                                System.out.println(k + ". " + students.get(k).student_id);
                                present = true;
                                break;
                            }
                        }
                    }
                    if (present) {
                        System.out.print("Choose student ID from these ungraded submissions: ");
                        int choice2 = scInt.nextInt();
                        int submission_index = -1;
                        for (int k = 0; k < submissions.size(); k++) {
                            if (submissions.get(k).assignment_id == assignments.get(choice).assignment_id && submissions.get(k).status == 1 && submissions.get(k).student_id.equals(students.get(choice2).student_id)) {
                                System.out.println("Submission: " + submissions.get(k).assignment_filename);
                                line();
                                submission_index = k;
                                break;
                            }
                        }
                        System.out.println("Max marks: " + assignments.get(choice).max_marks);
                        System.out.print("Marks scored: ");
                        int marks = scInt.nextInt();
                        while (marks > assignments.get(choice).max_marks) {
                            System.out.println("Marks cannot exceed max marks.");
                            System.out.print("Marks scored: ");
                            marks = scInt.nextInt();
                        }
                        submissions.get(submission_index).marks = marks;
                        submissions.get(submission_index).instructor_id = instructor_id;
                        submissions.get(submission_index).status = 2;  // Graded status
                    }
                    else
                        System.out.println("No ungraded submissions available.");
                }
            }
        }

        public void close_assessments() {
            if (assignments.size() == 0 && quizzes.size() == 0) {
                System.out.println("No assessments available.");
                System.out.println();
            }
            else {
                boolean present = false;
                int i, j;
                System.out.println("List of open assessments");
                line();
                for (i = 0; i < assignments.size(); i++) {
                    if (assignments.get(i).status == 0) {
                        present = true;
                        System.out.println("ID: " + i);
                        System.out.println("        Assignment: " + assignments.get(i).problem_statement);
                        System.out.println("        Max marks: " + assignments.get(i).max_marks);
                        line();
                    }
                }
                System.out.println();
                line();
                for (j = i; j < quizzes.size() + i; j++) {
                    if (quizzes.get(j - i).status == 0) {
                        present = true;
                        System.out.println("ID: " + j);
                        System.out.println("        Question: " + quizzes.get(j - i).quiz_question);
                        line();
                    }
                }
                if (present) {
                    System.out.print("Enter the ID of assessment to close: ");
                    int choice = scInt.nextInt();
                    if (choice >= assignments.size()) {
                        choice = choice - i;
                        quizzes.get(choice).status = 1;  // closed quiz
                    }
                    else{
                        assignments.get(choice).status = 1;  // closed assignment
                    }

                }
                else
                    System.out.println("No open assessments available.");
            }
        }

        @Override
        public void view_comments() {
            for (A2.comment comment : comments) {
                line();
                if (comment.instructor_id == null) {
                    System.out.println(comment.comment + " - " + comment.student_id);
                }
                else {
                    System.out.println(comment.comment + " - " + comment.instructor_id);
                }
                System.out.println(comment.date);
            }
        }

        @Override
        public void add_comments() {
            comment c = new comment();
            c.instructor_id = instructor_id;
            c.student_id = null;
            System.out.print("Enter the comment: " );
            c.comment = scString.nextLine();
            c.date = date_time();
            comments.add(c);
        }

    }
    public static class student implements backpack_interface {
        String student_id;

        @Override
        public void view_lecture_materials() {
            if (slides.size() == 0 && videos.size() == 0) {
                System.out.println("No lecture materials available.");
                System.out.println();
            }
            else {
                for (A2.slide slide : slides) {
                    System.out.println("Title: " + slide.slide_topic);
                    for (int i = 0; i < slide.content.size(); i++) {
                        System.out.println("Slide " + (i + 1) + ": " + slide.content.get(i));
                    }
                    System.out.println("Number of slides: " + slide.content.size());
                    System.out.println("Date of upload: " + slide.date);
                    System.out.println("Uploaded by: " + slide.instructor_id);
                    line();
                }
                System.out.println();
                line();
                for (A2.video video : videos) {
                    System.out.println("Title of video: " + video.video_topic);
                    System.out.println("Video file: " + video.video_file);
                    System.out.println("Date of upload: " + video.date);
                    System.out.println("Uploaded by: " + video.instructor_id);
                }
            }
        }

        @Override
        public void view_assessments() {
            if (assignments.size() == 0 && quizzes.size() == 0) {
                System.out.println("No assessments available.");
                System.out.println();
            }
            else {
                for (A2.assignment assignment : assignments) {
                    System.out.println("Assignment: " + assignment.problem_statement);
                    System.out.println("Max marks: " + assignment.max_marks);
                    if (assignment.status == 0)
                        System.out.println("Status: Open");
                    else
                        System.out.println("Status: Closed");
                    System.out.println("Date of upload: " + assignment.date);
                    System.out.println("Uploaded by: " + assignment.instructor_id);
                    line();
                }
                System.out.println();
                line();
                for (A2.quiz quiz : quizzes) {
                    System.out.println("Question: " + quiz.quiz_question);
                    System.out.println("Max marks: 1");
                    if (quiz.status == 0)
                        System.out.println("Status: Open");
                    else
                        System.out.println("Status: Closed");
                    System.out.println("Date of upload: " + quiz.date);
                    System.out.println("Uploaded by: " + quiz.instructor_id);
                    line();
                }
            }
        }

        public void submit_assessments() {
            if (assignments.size() == 0 && quizzes.size() == 0) {
                System.out.println("No assessments available.");
                System.out.println();
            }
            else {
                boolean present = false;
                int i, j;
                System.out.println("Pending assessments");
                line();
                for (i = 0; i < assignments.size(); i++) {
                    if (assignments.get(i).status == 0 && assignments.get(i).pending_assignment.contains(student_id)) {
                        present = true;
                        System.out.println("ID: " + i);
                        System.out.println("        Assignment: " + assignments.get(i).problem_statement);
                        System.out.println("        Max marks: " + assignments.get(i).max_marks);
                        line();
                    }
                }
                System.out.println();
                line();
                for (j = i; j < quizzes.size() + i; j++) {
                    if (quizzes.get(j - i).status == 0 && quizzes.get(j - i).pending_quiz.contains(student_id)) {
                        present = true;
                        System.out.println("ID: " + j);
                        System.out.println("        Question: " + quizzes.get(j - i).quiz_question);
                        line();
                    }
                }
                if (present) {
                    System.out.print("Enter the ID of assessment: ");
                    int choice = scInt.nextInt();
                    if (choice >= assignments.size()) {
                        choice = choice - i;
                        submission s = new submission();   // Uses-A relationship
                        s.student_id = student_id;
                        System.out.print(quizzes.get(choice).quiz_question + " ");
                        System.out.print("Enter a one-word answer: ");
                        String quiz_answer = scString.nextLine();
                        scInt.nextLine();
                        while (quiz_answer.contains(" ")) {
                            System.out.print("Invalid answer.\nEnter a one-word answer: ");
                            quiz_answer = scInt.nextLine();
                        }
                        s.quiz_id = quizzes.get(choice).quiz_id;
                        s.quiz_answer = quiz_answer;
                        s.status = 1;  // Submission status 1 represents submitted & ungraded
                        s.assignment_id = 0;  // Assignment ID is 0 when submission is an quiz
                        s.assignment_filename = null;  // Assignment filename is null when submission is an quiz
                        submissions.add(s);
                        quizzes.get(choice).pending_quiz.remove(student_id);

                    } else {
                        submission s = new submission();   // Uses-A relationship
                        s.student_id = student_id;
                        System.out.print("Enter the filename of the assignment: ");
                        String filename = scString.nextLine().toLowerCase();
                        scInt.nextLine();
                        while (filename.contains(" ") || !filename.toLowerCase().endsWith(".zip")) {
                            System.out.print("Incorrect file format. Please enter a single file name with '.zip' extension.\nEnter the filename of the assignment: ");
                            filename = scInt.nextLine().toLowerCase();
                        }
                        s.assignment_id = assignments.get(choice).assignment_id;
                        s.assignment_filename = filename;
                        s.status = 1;  // Submission status 1 represents submitted & ungraded
                        s.quiz_id = 0;  // Quiz ID is 0 when submission is an assignment
                        s.quiz_answer = null;  // Quiz answer is null when submission is an assignment
                        System.out.println("Filename: " + s.assignment_filename);
                        submissions.add(s);
                        assignments.get(choice).pending_assignment.remove(student_id);
                    }
                }
                else
                    System.out.println("Hurray! No pending assessments!");
            }
        }

        public void view_grades() {
            System.out.println("Graded Submissions");
            boolean graded_present = false, ungraded_present = false;
            line();
            for (A2.submission submission : submissions) {
                if (submission.student_id.equals(student_id) && submission.status == 2) {
                    if (submission.quiz_id == 0) {
                        System.out.println("Submission: " + submission.assignment_filename);
                        System.out.println("Marks scored: " + submission.marks);
                        System.out.println("Graded by: " + submission.instructor_id);
                        line();
                    }
                    else if (submission.assignment_id == 0) {
                        System.out.println("Submission: " + submission.quiz_answer);
                        System.out.println("Marks scored: " + submission.marks);
                        System.out.println("Graded by: " + submission.instructor_id);
                        line();
                    }
                    graded_present = true;
                }
            }
            if (!graded_present) {
                System.out.println("No graded submissions available.");
            }
            System.out.println();
            System.out.println("Ungraded Submissions");
            line();
            for (A2.submission submission : submissions) {
                if (submission.student_id.equals(student_id) && submission.status == 1) {
                    if (submission.quiz_id == 0)
                        System.out.println("Submission: " + submission.assignment_filename);
                    else if (submission.assignment_id == 0)
                        System.out.println("Submission: " + submission.quiz_answer);
                    ungraded_present = true;
                }
            }
            if (!ungraded_present) {
                System.out.println("No ungraded submissions available.");
            }
        }

        @Override
        public void view_comments() {
            for (A2.comment comment : comments) {
                line();
                if (comment.instructor_id == null) {
                    System.out.println(comment.comment + " - " + comment.student_id);
                }
                else {
                    System.out.println(comment.comment + " - " + comment.instructor_id);
                }
                System.out.println(comment.date);
            }
        }

        @Override
        public void add_comments() {
            comment c = new comment();
            c.student_id = student_id;
            c.instructor_id = null;
            System.out.print("Enter the comment: " );
            c.comment = scString.nextLine();
            c.date = date_time();
            comments.add(c);
        }
    }

    public static void Instructor(int choice) {
        System.out.println("Welcome " + instructors.get(choice).instructor_id);
        instructor_menu();
        System.out.print("Enter your choice: ");
        int choice2 = scInt.nextInt();
        boolean valid = (choice2 >= 1 && choice2 <= 9);
        while (!valid) {
            System.out.println("Invalid input. Please enter an input from the given options");
            System.out.print("Enter your choice: ");
            choice2 = scInt.nextInt();
            valid = (choice2 >= 1 && choice2 <= 9);
        }
        switch  (choice2) {
            case 1 -> {
                instructors.get(choice).add_lecture_material();
            }
            case 2 -> {
                instructors.get(choice).add_assessments();
            }
            case 3 -> {
                instructors.get(choice).view_lecture_materials();
            }
            case 4 -> {
                instructors.get(choice).view_assessments();
            }
            case 5 -> {
                instructors.get(choice).grade_assessments();
            }
            case 6 -> {
                instructors.get(choice).close_assessments();
            }
            case 7 -> {
                instructors.get(choice).view_comments();
            }
            case 8 -> {
                instructors.get(choice).add_comments();
            }
            case 9 -> {
                System.out.println("Welcome to Backpack!");
                login_menu();
            }
        }
        Instructor(choice);
    }

    public static void Student(int choice) {
        System.out.println("Welcome " + students.get(choice).student_id);
        student_menu();
        System.out.print("Enter your choice: ");
        int choice2 = scInt.nextInt();
        boolean valid = (choice2 >= 1 && choice2 <= 7);
        while (!valid) {
            System.out.println("Invalid input. Please enter an input from the given options");
            System.out.print("Enter your choice: ");
            choice2 = scInt.nextInt();
            valid = (choice2 >= 1 && choice2 <= 7);
        }
        switch  (choice2) {
            case 1 -> {
                students.get(choice).view_lecture_materials();
            }
            case 2 -> {
                students.get(choice).view_assessments();
            }
            case 3 -> {
                students.get(choice).submit_assessments();
            }
            case 4 -> {
                students.get(choice).view_grades();
            }
            case 5 -> {
                students.get(choice).view_comments();
            }
            case 6 -> {
                students.get(choice).add_comments();
            }
            case 7 -> {
                System.out.println("Welcome to Backpack!");
                login_menu();
            }
        }
        Student(choice);
    }
    public static void main (String[] args) {
        instructor I0 = new instructor();
        I0.instructor_id = "I0";
        instructors.add(I0);
        instructor I1 = new instructor();
        I1.instructor_id = "I1";
        instructors.add(I1);

        student S0 = new student();
        S0.student_id = "S0";
        students.add(S0);
        student S1 = new student();
        S1.student_id = "S1";
        students.add(S1);
        student S2 = new student();
        S2.student_id = "S2";
        students.add(S2);

        // Init
        System.out.println("Welcome to Backpack!");
        login_menu();
    }
}
