package com.company;
import java.util.ArrayList;
import java.util.Date;

public class Course_page {
    private String course_name;
    private ArrayList<Instructor> instructors = new ArrayList<Instructor>();
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Lecture_material> lecture_materials = new ArrayList<Lecture_material>();
    private ArrayList<Submissions> submissions = new ArrayList<Submissions>();
    private ArrayList<Comment> comments = new ArrayList<Comment>();

    public void add_instructor(Instructor i){
        instructors.add(i);
    }
    public void add_student(Student s){
        students.add(s);
    }

    public void add_lecture_material( Lecture_material temp){
        lecture_materials.add(temp);
    }
    public void add_lecture_slide (String topic,int no_of_slides, ArrayList<String> content, String uplodedby, Date date){
        Lecture_slides temp = new Lecture_slides(topic,no_of_slides,content,uplodedby,date);
        lecture_materials.add(temp);
    }
    public void add_lecture_video (String topic, String filename, String uplodedby, Date date){
        Lecture_videos temp = new Lecture_videos(topic, filename, uplodedby,date);
        lecture_materials.add(temp);
    }
    public void add_assessments(Submissions temp){
        submissions.add(temp);
    }
    public void add_comment(String comment,String commented_by, Date date){
        comments.add(new Comment(comment,commented_by, date));
    }

    //getter
    public ArrayList<Lecture_material> getLecture_materials() {
        return lecture_materials;
    }
    public String get_course_name(){
        return course_name;
    }
    public ArrayList<Submissions> getSubmissions() {
        return submissions;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }
}


