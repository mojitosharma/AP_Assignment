package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

public class BACKPACK {
    private static ArrayList<Instructor> all_instructors = new ArrayList<Instructor>();        //list of all instructors registered on backpack platform
    private static  ArrayList<Student> all_students = new ArrayList<Student>();                 //list of all students registered on backpack platform

    public static void initialize()throws Exception{
        Course_page ap = new Course_page();
        Instructor temp = new Instructor("I0");
        add_instructor(ap,temp);
        temp = new Instructor("I1");
        add_instructor(ap,temp);

        Student temp1 = new Student("S0");
        add_student(ap,temp1);
        temp1 = new Student("S1");
        add_student(ap,temp1);
        temp1 = new Student("S2");
        add_student(ap,temp1);

    while(true){
        System.out.println("Welcome to Backpack\n" +
                "1. Enter as instructor\n" +
                "2. Enter as student\n" +
                "3. Exit");
        Reader.init(System.in);
        int option = Reader.nextint();
        if(option == 1){
            for(int i = 0; i< all_instructors.size(); i++){
                System.out.printf("%d %s\n",i,all_instructors.get(i).get_name());
            }
            System.out.printf("Choose id: ");
            int id = Reader.nextint();
            if(id >= 0 && id < all_instructors.size()){
                instructor_execution(all_instructors.get(id));
            }
            else{
                System.out.printf("Invalid Input!!\n");
            }

        }
        else if(option == 2){
            for(int i = 0; i< all_students.size(); i++){
                System.out.printf("%d %s\n",i,all_students.get(i).get_name());
            }
            System.out.printf("Choose id: ");
            int id = Reader.nextint();
            if(id >= 0 && id < all_students.size()){
                student_execution(all_students.get(id));
            }
            else{
                System.out.printf("Invalid Input!!\n");
            }
        }
        else if(option == 3){
            return;
        }
        else{
            System.out.println("Invalid Input");
        }
    }
    }

    private static void add_instructor(Course_page c, Instructor i){
        i.add_course(c);
        c.add_instructor(i);
        all_instructors.add(i);
        i.setCurrent_course(c);
    }

    private static void add_student(Course_page c, Student s){
        s.add_course(c);
        c.add_student(s);
        all_students.add(s);
        s.setCurrent_course(c);
    }

    private static void instructor_execution(Instructor instructor)throws Exception{
        while(true){
            System.out.printf("Welcome %s\n",instructor.get_name());
            System.out.println("INSTRUCTOR MENU\n" +
                    "1. Add class material\n" +
                    "2. Add assessments\n" +
                    "3. View lecture materials\n" +
                    "4. View assessments\n" +
                    "5. Grade assessments\n" +
                    "6. Close assessment\n" +
                    "7. View comments\n" +
                    "8. Add comments\n" +
                    "9. Logout");
            Reader.init(System.in);
            int option =  Reader.nextint();
            if(option == 1){
                instructor.add_lecture();
            }
            else if(option == 2){
                instructor.add_assignments();
            }
            else if(option == 3){
                instructor.view_lecture_materials();
            }
            else if(option == 4){
                instructor.view_assignments();
            }
            else if(option == 5){
                instructor.grade_assignments();
            }
            else if(option == 6){
                instructor.close_assignments();
            }
            else if(option == 7){
                instructor.view_comments();
            }
            else if(option == 8){
                instructor.add_comments();
            }
            else if(option == 9){
                return;
            }
            else{
                System.out.println("Invalid option!!");
            }
        }
    }

    private static void student_execution(Student student)throws Exception{
        while(true) {
            System.out.printf("Welcome %s\n",student.get_name());
            System.out.println("STUDENT MENU\n" +
                    "1. View lecture materials\n" +
                    "2. View assessments\n" +
                    "3. Submit assessment\n" +
                    "4. View grades\n" +
                    "5. View comments\n" +
                    "6. Add comments\n" +
                    "7. Logout");
            Reader.init(System.in);
            int option =  Reader.nextint();
            if(option == 1){
                student.view_lecture_materials();
            }
            else if(option == 2){
                student.view_assignments();
            }
            else if(option == 3){
                student.submit_assignment();
            }
            else if(option == 4){
                student.view_grades();
            }
            else if(option == 5){
                student.view_comments();
            }
            else if(option == 6){
                student.add_comments();
            }
            else if(option == 7){
                return;
            }
            else {
                System.out.println("Invalid option!!");
            }
        }
    }
    public static void main(String[] args)throws Exception {
        initialize();
    }
}
