package A2_2020149;
/*
AP Assignment 2
 Rahul Maddula
 2020149
*/

import A1_2020149.A1;

import java.lang.reflect.Array;
import java.util.*;

public class A2 {
    static Scanner scInt = new Scanner(System.in);
    static ArrayList<instructor> instructors = new ArrayList<>();  // Store all objects of instructor class
    static ArrayList<student> students = new ArrayList<>();  // Store all objects of student class
    static ArrayList<String> lecture_videos = new ArrayList<>();  // Store all objects of lecture videos
    static ArrayList<String> lecture_materials = new ArrayList<>();  // Store all objects of lecture slides

    interface instructor_interface {
        void add_lecture_material();
        void add_assessments();
        void view_lecture_materials();
        void view_assessments();
        void grade_assessments();
        void close_assessments();
        void view_comments();
        void add_comments();
        void logout();
    }

    interface student_interface {
        void view_lecture_materials();
        void view_assessments();
        void submit_assessments();
        void view_grades();
        void view_comments();
        void add_comments();
        void logout();

    }

    public static void line() {
        System.out.println("----------------------------------------");
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
                    Instructor();
                    valid = true;
                }
                case 2 -> {
                    Student();
                    valid = true;
                }
                case 3 -> {
                    valid = true;
                    System.exit(0);
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

    public static class lectures {
        String slide_topic;
        String video;

    }
    public static class instructor extends lectures implements instructor_interface {
        String instructor_id;

        @Override
        public void add_lecture_material() {

        }

        @Override
        public void add_assessments() {

        }

        @Override
        public void view_lecture_materials() {

        }

        @Override
        public void view_assessments() {

        }

        @Override
        public void grade_assessments() {

        }

        @Override
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
    public static class student implements student_interface {
        String student_id;

        @Override
        public void view_lecture_materials() {

        }

        @Override
        public void view_assessments() {

        }

        @Override
        public void submit_assessments() {

        }

        @Override
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

    public static void Instructor() {
        for (int i = 0; i < instructors.size(); i++) {
            System.out.println(i + " - " + instructors.get(i).instructor_id);
        }
        System.out.print("Enter your choice: ");
        int choice = scInt.nextInt();
        boolean valid = (choice >= 0 && choice < instructors.size());
        while (!valid) {
            System.out.println("Invalid input. Please enter an input from the given options.");
            System.out.print("Enter your choice: ");
            choice = scInt.nextInt();
            valid = (choice >= 0 && choice < instructors.size());
        }
        System.out.println("Welcome " + instructors.get(choice).instructor_id);
        instructor_menu();
        System.out.print("Enter your choice: ");
        choice = scInt.nextInt();
        valid = (choice >= 1 && choice <= 9);
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
