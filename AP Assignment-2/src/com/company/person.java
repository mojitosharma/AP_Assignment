package com.company;

import java.util.ArrayList;

public interface person {
    public void setCurrent_course(Course_page current_course);
    public Course_page getCurrent_course();
    public void add_course(Course_page c);
    public ArrayList<Course_page> get_courses();
    public void view_lecture_materials();
    public void view_assignments();
    public void view_comments();
    public void add_comments()throws Exception;
}
