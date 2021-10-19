package com.company;
import java.util.Date;

public class Comment {
    private String comment_text;
    private Date date;
    private String commented_by;
    public Comment(String text, String commented_by, Date date){
        this.comment_text = text;
        this.date = date;
        this.commented_by = commented_by;
    }
    //getter
    public Date getDate() {
        return date;
    }
    public String getComment_text() {
        return comment_text;
    }
    public String getCommented_by(){
        return commented_by;
    }
}
