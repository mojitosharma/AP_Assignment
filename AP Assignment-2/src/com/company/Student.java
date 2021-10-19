package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class Student implements person {
    private String name;
    private Course_page current_course;                                          //course that you are currently viewing
    private ArrayList<Course_page> course_pages = new ArrayList<Course_page>();

    public Student(String name){
        this.name = name;
    }
    public String get_name(){
        return this.name;
    }

    public void submit_assignment()throws Exception{
        ArrayList<Submissions> submissions = current_course.getSubmissions();
        int count = 0;
        for(int i = 0; i< submissions.size(); i++){
            if(submissions.get(i).check_Submission_by_student(this.name) == 1 || submissions.get(i).isClosed() == true){
                continue;
            }
            else{
                if(count == 0){
                    System.out.println("Pending assessments");
                }
                Submissions temp = submissions.get(i);
                System.out.printf("ID: %d ",count);
                temp.view_submission();
                count++;
            }
        }
        if(count == 0){
            System.out.println("No pending assessments");
            return;
        }
        System.out.printf("Enter ID of assessment: ");
        Reader.init(System.in);
        int temp = Reader.nextint();
        int i,id = 0;
        for(i = 0; i< submissions.size(); i++){
            if(submissions.get(i).check_Submission_by_student(this.name) == 1 || submissions.get(i).isClosed() == true){
                continue;
            }
            else{
                if(temp == id){
                    int flag = submissions.get(i).isAssignment();
                    if(flag  == 1){
                        System.out.printf("Enter filename of assignment: ");
                        String filename = Reader.next();
                        int j;
                        for (j =0; j< filename.length(); j++){
                            if(filename.charAt(j) == '.'){
                                break;
                            }
                        }
                        if(j != filename.length()-4 && filename.length() < 5){
                            System.out.printf("Invalid Input!!\n");
                        }
                        else if(filename.charAt(j+1) == 'z' && filename.charAt(j+2) == 'i' && filename.charAt(j+3) == 'p'){
                            submissions.get(i).add_assignment_by_student(filename, name);
                        }
                    }
                    else{
                        System.out.printf("%s ",submissions.get(i).getQuestion());
                        String answer = Reader.next();
                        submissions.get(i).add_assignment_by_student(answer, name);
                    }
                    return;
                }
                id++;
            }
        }
            System.out.println("Invalid ID!!");
    }

    public void view_grades(){
        ArrayList<Submissions> submissions = current_course.getSubmissions();
        System.out.println("Graded submissions");
        int count = 0;
        for(int i = 0; i< submissions.size(); i++){
            if(submissions.get(i).check_Submission_by_student(this.name) == 1){
                if(submissions.get(i).get_grades_of_student(this.name) != -1){
                    count++;
                    System.out.printf("Submission: %s\n" + "Marks scored: %d\n" +
                            "Graded by: %s\n",submissions.get(i).getSubmission_by_student(this.name),
                            submissions.get(i).get_grades_of_student(this.name),submissions.get(i).get_gradedby(this.name)
                            );
                }
            }
        }
        if(count == 0) System.out.println("");
        count = 0;
        for(int i = 0; i< submissions.size(); i++){
            if(submissions.get(i).check_Submission_by_student(this.name) == 1){
                if(submissions.get(i).get_grades_of_student(this.name) == -1){
                    if(count == 0 ) System.out.println("Ungraded submissions");
                    count++;
                    System.out.printf("Submission: %s\n",submissions.get(i).getSubmission_by_student(this.name));
                }
            }
        }
        if(count == 0) System.out.println("");
        System.out.println("-----------------");
    }

    @Override
    public void view_lecture_materials(){
        Course_page current_course = this.getCurrent_course();
        ArrayList<Lecture_material> lecture_materials = current_course.getLecture_materials();
        System.out.printf("\n\n");
        for(int i = 0; i< lecture_materials.size(); i++){
            lecture_materials.get(i).print_material();
            System.out.printf("\n");
        }
        if(lecture_materials.size() == 0){
            System.out.println("No lecture Material added");
        }
    }
    @Override
    public void setCurrent_course(Course_page current_course){
        this.current_course = current_course;
    }
    @Override
    public Course_page getCurrent_course() {
        return current_course;
    }
    @Override
    public void add_course(Course_page c){
        course_pages.add(c); }
    @Override
    public ArrayList<Course_page> get_courses(){
        return course_pages;
    }
    @Override
    public void view_assignments(){
        Course_page current_course = this.getCurrent_course();
        ArrayList<Submissions> submissions = current_course.getSubmissions();
        for(int i = 0; i< submissions.size(); i++){
            Submissions temp = submissions.get(i);
            System.out.printf("ID: %d ",i);
            temp.view_submission();
            System.out.printf("----------------\n");
        }
        if(submissions.size() == 0){
            System.out.println("No Assignments added.");
        }
    }
    @Override
    public void view_comments(){
        ArrayList<Comment> comments = current_course.getComments();
        Comment temp;
        if(comments.size() == 0){
            System.out.println("No comments yet!");
            return;
        }
        for (int i =0 ; i< comments.size(); i++){
            temp = comments.get(i);
            System.out.printf("%s - %s\n%s\n\n",temp.getComment_text(),temp.getCommented_by(),temp.getDate());
        }
    }
    @Override
    public void add_comments() throws ParseException {
        System.out.printf("Enter comment: ");
        Scanner sc= new Scanner(System.in);
        String comment = sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = sdf.format(new Date());
        Date date = sdf.parse(dateInString);
        current_course.add_comment(comment, this.name, date);
    }
}
