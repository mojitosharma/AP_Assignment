package com.company;

public class Player {
    private final String name;
    private int floor_no, total_points;
    private Floor floor;
    public Player(String name){
        this.name = name;
        this.floor_no = -1;
        this.total_points = 0;
    }

    public void jump(){
        this.total_points = this.total_points + floor.getFloor_value();
    }
    public void roll_dice(Dice d){
        d.roll();
    }

    //getter
    public int getTotal_points() {
        return total_points;
    }
    public int get_floor_no() {
        return floor_no;
    }
    public String getName() {
        return name;
    }
    //setter
    public void setFloor(Floor floor) {
        this.floor = floor;
    }
    public void set_floor_no(int face_value){
        floor_no += face_value;
    }
    public void add_Total_points(int temp) {
        this.total_points = total_points + temp;
    }
    public void go_to_floor(int temp){
        floor_no = temp;
    }

    @Override
    public String toString() {
        return "Player position Floor-"+this.floor_no+"\n"+this.name +
                " has reached "+floor.getFloor_type()+"\nTotal points "+this.total_points ;
    }
}
