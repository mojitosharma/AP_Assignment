package com.company;
//graded submiision
// not showing graded
import java.text.SimpleDateFormat;
import java.util.*;

public class Instructor implements person {
    private String name;
    private ArrayList<Course_page> course_pages = new ArrayList<Course_page>();
    private Course_page current_course;                                          //course that you are currently viewing

    public Instructor(String name){
        this.name = name;
    }
    public String get_name(){
        return this.name;
    }


    public void add_lecture()throws Exception{
        Reader.init(System.in);
        System.out.println("1. Add Lecture Slide\n" + "2. Add Lecture Video");
        int option1 =  Reader.nextint();
        if(option1 == 1 ){
            System.out.printf("Enter topic of slides: ");
            String topic = Reader.nextLine();
            System.out.printf("Enter number of slides: ");
            int no_of_slides = Reader.nextint();
            System.out.printf("Enter content of slides\n");
            ArrayList<String> content = new ArrayList<String>();
            for(int i =0; i< no_of_slides; i++){
                System.out.printf("Content of slide %d: ",i+1);
                String content_temp = Reader.nextLine();
                content.add(content_temp);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String dateInString = sdf.format(new Date());
            Date date = sdf.parse(dateInString);
            Lecture_slides temp = new Lecture_slides(topic,no_of_slides,content,this.name,date);
            current_course.add_lecture_material(temp);
            //current_course.add_lecture_slide(topic, no_of_slides, content, this.name, date);
        }
        else if(option1 == 2){
            System.out.printf("Enter topic of video: ");
            String topic = Reader.nextLine();
            System.out.printf("Enter filename of video: ");
            String filename = Reader.next();
            int i;
            for (i =0; i< filename.length(); i++){
                if(filename.charAt(i) == '.'){
                    break;
                }
            }
            if(i != filename.length()-4 && filename.length() < 5){
                System.out.println("Invalid Input!!");
            }
            else if(filename.charAt(i+1) == 'm' && filename.charAt(i+2) == 'p' && filename.charAt(i+3) == '4'){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                String dateInString = sdf.format(new Date());
                Date date = sdf.parse(dateInString);
                Lecture_videos temp = new Lecture_videos(topic, filename, this.name,date);
                current_course.add_lecture_material(temp);
                //current_course.add_lecture_video(topic, filename, this.name, date);
            }
            else  System.out.println("Invalid Input!!");
        }
        else{
            System.out.println("Invalid Input!!");
        }
    }

    public void add_assignments()throws Exception{
        System.out.printf("1. Add Assignment\n" +
                "2. Add Quiz\n");
        Reader.init(System.in);
        int option1 =  Reader.nextint();
        if(option1 == 1){
            System.out.printf("Enter problem statement: ");
            String problem_statement = Reader.nextLine();
            System.out.printf("Enter max marks: ");
            int max_marks = Reader.nextint();
            Assignment temp = new Assignment(problem_statement, max_marks);
            current_course.add_assessments(temp);
        }
        else if(option1 == 2){
            System.out.printf("Enter quiz question: ");
            String quiz_question = Reader.nextLine();
            Quiz temp = new Quiz(quiz_question);
            current_course.add_assessments(temp);

        }
        else{
            System.out.printf("Invalid Input!!\n");
        }
    }

    public void grade_assignments()throws Exception{
        System.out.println("List of assessments");
        view_assignments();
        System.out.printf("Enter ID of assessment to view submissions: ");
        Reader.init(System.in);
        int id =  Reader.nextint();
        if(id < 0 || id >= current_course.getSubmissions().size()){
            System.out.println("Invalid Input!!");
            return;
        }
        ArrayList<Submissions> submissions = current_course.getSubmissions();
        Map<String, String> submission_by_students =  submissions.get(id).getSubmission_by_students();
        int i = 0, count = 0;
        Set<String> keys = submission_by_students.keySet();
        for (String key : keys) {
            if(submissions.get(id).get_grades_of_student(key) != -1)continue;
            if(i==0) System.out.println("Choose ID from these ungraded submissions");
            System.out.printf("%d. %s\n",i,key);
            i++;
        }
        if(i == 0){
            System.out.println("No Ungraded assignment");
            return;}
        i = 0;
        int student_id =  Reader.nextint();
        for (String key : keys) {
            if(submissions.get(id).get_grades_of_student(key) != -1) {
                i++;
                continue;
            }
            if(student_id == count){
                String student_name = key;
                System.out.printf("Submission: %s\n",submission_by_students.get(student_name));
                System.out.println("-------------------------------");
                System.out.printf("Max Marks: %d\n",submissions.get(id).getMax_marks());
                System.out.printf("Marks scored: ");
                int marks = Reader.nextint();
                if(marks < 0 || marks > submissions.get(id).getMax_marks()){
                    System.out.println("Invalid input!!");
                    return;
                }
                submissions.get(id).setgrade(this.name, student_name, marks);
                return;
            }
            count++; i++;
        }
        System.out.println("Invalid Input!!");
        return;
    }

    public void close_assignments()throws Exception{
        Reader.init(System.in);
        System.out.println("List of Open Assignments:");
        Course_page current_course = this.getCurrent_course();
        ArrayList<Submissions> submissions = current_course.getSubmissions();
        int count = 0;
        for(int i = 0; i< submissions.size(); i++){
            Submissions temp = submissions.get(i);
            if(temp.isClosed() == false) {
                System.out.printf("ID: %d ", count);
                temp.view_submission();
                System.out.printf("----------------\n");
                count++;
            }
        }
        if(submissions.size() == 0){
            System.out.println("No Assignments added.");
        }
        System.out.printf("Enter id of assignment to close: ");
        int id = Reader.nextint();
        if(id < 0 || id > count) {
            System.out.println("Invalid Input!!");
            return;
        }
        count = 0;
        int i;
        for(i = 0; i< submissions.size(); i++){
            Submissions temp = submissions.get(i);
            if(temp.isClosed() == false) {
                if(count == id)break;
                count++;
            }
        }
        current_course.getSubmissions().get(i).setClosed();
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
    public void add_comments()throws Exception{
        Reader.init(System.in);
        System.out.printf("Enter comment: ");
        String comment = Reader.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = sdf.format(new Date());
        Date date = sdf.parse(dateInString);
        current_course.add_comment(comment, this.name, date);
    }
}

