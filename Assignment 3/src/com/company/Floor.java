package com.company;

public class Floor {
    protected String floor_type;
    protected int floor_value;
    protected int floor_no;

    public Floor(String floor_type, int floor_no){
        this.floor_type = floor_type;
        this.floor_value = 1;
        this.floor_no = floor_no;
    }
    //getter
    public String getFloor_type(){
        return floor_type;
    }
    public int getFloor_value() {
        return floor_value;
    }
    public int getFloor_no() {
        return floor_no;
    }
    public int get_goes_to_floor(){
        return -1;
    }
}


class Snake_floor extends Floor{
    private Snake s;
    public Snake_floor(String floor_type, int floor_no){
        super(floor_type, floor_no);
        if( floor_type == "Normal snake"){
            s = new Snake(this, this.floor_no);
        }
        else if(floor_type == "King cobra snake"){
            s = new king_cobra(this, this.floor_no);
        }
    }
    @Override
    public int getFloor_value() {
        return s.getPoints();
    }
    public int get_goes_to_floor(){
        return s.getGoes_to();
    }
}

class Ladder_floor extends Floor{
    private Ladder l;
    public Ladder_floor(String floor_type, int floor_no){
        super(floor_type, floor_no);
        if( floor_type == "Ladder floor"){
            l = new Ladder(this, this.floor_no);
        }
        else if(floor_type == "Elevator floor"){
            l = new Elevator(this, this.floor_no);
        }
    }
    @Override
    public int getFloor_value() {
        return l.getPoints();
    }
    public int get_goes_to_floor(){
        return l.getGoes_to();
    }
}

