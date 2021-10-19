package com.company;

import java.util.HashMap;
import java.util.Map;

public class Quiz implements Submissions {
    private String question;
    private int max_marks;
    private boolean closed;
    private Map<String, String> submission_by_students = new HashMap<String, String>();
    private Map<String, Integer> grades = new HashMap<String, Integer>();
    private Map<String, String> gradedby = new HashMap<String, String>();

    public Quiz(String problem_statement){
        this.question = problem_statement;
        max_marks = 1;
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
        System.out.printf("Question: %s\n", this.question);
    }
    @Override
    public void add_assignment_by_student(String answer, String student_name){
        submission_by_students.put(student_name, answer);
    }
    @Override
    public int isAssignment(){
        return 0;
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

