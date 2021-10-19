package com.company;

import java.util.Map;

public interface Submissions {
    public String getQuestion();
    public int getMax_marks();
    public void setClosed();
    public boolean isClosed();
    public void view_submission();
    public void add_assignment_by_student(String filename, String student_name);
    public int isAssignment();
    public int check_Submission_by_student(String name);
    public String getSubmission_by_student(String name);
    public int get_grades_of_student(String name);
    public String get_gradedby(String name);
    public Map<String, String> getSubmission_by_students();
    public void setgrade(String gradedby, String name, int marks);
}
