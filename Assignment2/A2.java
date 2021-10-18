package A2_2020149;
/*
AP Assignment 2
 Rahul Maddula
 2020149
*/

import A1_2020149.A1;

import java.lang.reflect.Array;
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


    interface backpack_interface {
        void view_lecture_materials();
        void view_assessments();
        void view_comments();
        void add_comments();
        void logout();
    }

    public static void line() {
        System.out.println("----------------------------------------");
    }

    public static String date_time() {
        SimpleDateFormat sd = new SimpleDateFormat(
                "yyyy.MM.dd G 'at' HH:mm:ss z");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
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
                    Student();
                    valid = true;
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
        String instructor_id, problem_statement;
        int max_marks;
    }

    public static class quiz {
        String instructor_id, quiz_question, answer;
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
                while (video_file.contains(" ") || !video_file.endsWith(".mp4")) {
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
                a.instructor_id = instructor_id;
                System.out.print("Enter the problem statement: ");
                a.problem_statement = scString.nextLine();
                System.out.print("Enter max marks: ");
                a.max_marks = scInt.nextInt();
                assignments.add(a);
            }
            else if (choice == 2) {
                quiz q = new quiz();
                q.instructor_id = instructor_id;
                System.out.print("Enter the quiz question: ");
                q.quiz_question = scString.nextLine();
                System.out.print("Enter the one-word answer: ");
                String answer = scString.nextLine();
                scInt.nextLine();
                while (answer.contains(" ")) {
                    System.out.print("Incorrect file format. Please enter a single word answer.\nEnter the one-word answer: ");
                    answer = scInt.nextLine();
                }
                q.answer = answer;
                quizzes.add(q);
            }
            else
                System.out.println("Invalid input. Please enter an input from the given options");
        }

        @Override
        public void view_lecture_materials() {
            

        }

        @Override
        public void view_assessments() {

        }

        public void grade_assessments() {

        }

        public void close_assessments() {

        }

        @Override
        public void view_comments() {

        }

        @Override
        public void add_comments() {

        }

        @Override
        public void logout() {

        }
    }
    public static class student implements backpack_interface {
        String student_id;

        @Override
        public void view_lecture_materials() {

        }

        @Override
        public void view_assessments() {

        }

        public void submit_assessments() {

        }

        public void view_grades() {

        }

        @Override
        public void view_comments() {

        }

        @Override
        public void add_comments() {

        }

        @Override
        public void logout() {

        }
    }

    public static void Instructor(int choice) {
        System.out.println("Welcome " + instructors.get(choice).instructor_id);
        instructor_menu();
        System.out.print("Enter your choice: ");
        choice = scInt.nextInt();
        boolean valid = (choice >= 1 && choice <= 9);
        while (!valid) {
            System.out.println("Invalid input. Please enter an input from the given options");
            System.out.print("Enter your choice: ");
            choice = scInt.nextInt();
            valid = (choice >= 1 && choice <= 9);
        }
        switch  (choice) {
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

    public static void Student() {
        for (int i = 0; i < students.size(); i++) {
            System.out.print(i + " - " + students.get(i).student_id);
        }
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
