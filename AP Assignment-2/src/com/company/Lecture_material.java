package com.company;

import java.util.ArrayList;
import java.util.Date;

public interface Lecture_material {
    public void print_material();
}


class Lecture_slides implements Lecture_material{
    private String topic;
    private int no_of_slides;
    private ArrayList<String> content;
    private String uplodedby;
    private Date date;

    public Lecture_slides(String topic,int no_of_slides, ArrayList<String> content, String uplodedby, Date date){
        this.topic = topic;
        this.no_of_slides =  no_of_slides;
        this.content = content;
        this.uplodedby = uplodedby;
        this.date = date;
    }
    //getter
    public Date getDate() {
        return date;
    }
    public String getTopic() {
        return topic;
    }
    public String getUplodedby() {
        return uplodedby;
    }
    public ArrayList<String> getContent() {
        return content;
    }
    public int getNo_of_slides() {
        return no_of_slides;
    }
    @Override
    public void print_material(){
        System.out.printf("Title: %s\n",this.getTopic());
        ArrayList<String> t = this.getContent();
        for(int j = 0; j< this.getNo_of_slides(); j++){
            System.out.printf("Slide %d: %s\n",j+1,t.get(j));
        }
        System.out.printf("Number of slides: %d\n",this.getNo_of_slides());
        System.out.printf("Date of upload: ");
        System.out.println(this.getDate());
        System.out.printf("Uploaded by: %s\n",this.getUplodedby());
        System.out.printf("\n");
    }
}


class Lecture_videos  implements Lecture_material{
    private String topic;
    private String uplodedby, filename;
    private Date date;

    public Lecture_videos(String topic, String filename, String uplodedby, Date date){
        this.topic = topic;
        this.filename =  filename;
        this.uplodedby = uplodedby;
        this.date = date;
    }

    //getter
    public Date getDate() {
        return date;
    }
    public String getFilename() {
        return filename;
    }
    public String getTopic() {
        return topic;
    }
    public String getUplodedby() {
        return uplodedby;
    }
    @Override
    public void print_material(){;
        System.out.printf("Title: %s\n",this.getTopic());
        System.out.printf("Video file: %s\n",this.getFilename());
        System.out.printf("Date of upload: ");
        System.out.println(this.getDate());
        System.out.printf("Uploaded by: %s\n",this.getUplodedby());
        System.out.printf("\n");
    }
}