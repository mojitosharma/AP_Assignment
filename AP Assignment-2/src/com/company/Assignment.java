package com.company;

import org.xml.sax.SAXNotRecognizedException;

import java.util.HashMap;
import java.util.Map;

public class Assignment implements Submissions {
    private String question;
    private int max_marks;
    private boolean closed;
    private Map<String, String> submission_by_students = new HashMap<String, String>();
    private Map<String, Integer> grades = new HashMap<String, Integer>();
    private Map<String, String> gradedby = new HashMap<>();

    public Assignment(String problem_statement,int max_marks){
        this.question = problem_statement;
        this.max_marks = max_marks;
        closed = false;
    }

    @Override
    public String getQuestion() {
        return question;
    }
    @Override
    public int getMax_marks() {
        return max_marks;
    }
    @Override
    public boolean isClosed() {
        return closed;
    }
    @Override
    public void setClosed(){
        closed = true;
    }
    @Override
    public void view_submission(){
        System.out.printf("Assignment: %s Max Marks: %d\n", this.question, this.max_marks);
    }
    @Override
    public void add_assignment_by_student(String filename, String student_name){
        submission_by_students.put(student_name, filename);
    }
    @Override
    public int isAssignment(){
        return 1;
    }
    @Override
    public int check_Submission_by_student(String name) {
        if(submission_by_students.containsKey(name)){
            return 1;
        }
        return 0;
    }
    @Override
    public String getSubmission_by_student(String name){
        return submission_by_students.get(name);
    }
    @Override
    public int get_grades_of_student(String name){
        if(grades.containsKey(name)){
            return grades.get(name);
        }
        return -1;
    }
    @Override
    public String get_gradedby(String name){
        if(gradedby.containsKey(name)){
            return gradedby.get(name);
        }
        return "";
    }
    @Override
    public Map<String, String> getSubmission_by_students() {
        return submission_by_students;
    }
    @Override
    public void setgrade(String graded_by, String name, int marks){
        grades.put(name, marks);
        gradedby.put(name,graded_by);
    }

}
