package com.company;

public class Ladder {
    protected Floor f;
    protected int goes_to;
    protected int points;
    protected String ladder_type;
    public Ladder(Floor f, int current_floor_no){
        this.f = f;
        this.goes_to = 4 + current_floor_no;
        this.ladder_type = "Ladder floor";
        this.points = 2;
    }
    //getter
    public int getPoints() {
        return points;
    }
    public String getLadder_type(){
        return ladder_type;
    }
    public int getGoes_to() {
        return goes_to;
    }
    //setter
    protected void setPoints(int n){
        this.points = n;
    }

}

class Elevator extends Ladder{
    public Elevator(Floor f,int current_floor_no){
        super(f,current_floor_no);
        this.f = f;
        this.goes_to = 8 + current_floor_no;
        this.points = 4;
        ladder_type = "Elevator floor";
    }
}