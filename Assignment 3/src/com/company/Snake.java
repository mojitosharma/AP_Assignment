package com.company;

public class Snake {
    protected Floor f;
    protected int goes_to;
    protected int points;
    protected String snake_type;
    public Snake(Floor f, int current_floor_no){
        this.f = f;
        this.goes_to = current_floor_no -4;
        this.points = (-2);
        snake_type = "Normal snake";
    }
    //getter
    public int getPoints() {
        return points;
    }
    public String get_snake_type() {
        return snake_type;
    }
    public int getGoes_to() {
        return goes_to;
    }
}

class king_cobra extends Snake{
    public king_cobra(Floor f, int current_floor_no){
        super(f,current_floor_no);
        snake_type = "King cobra snake";
        this.goes_to = current_floor_no - 8;
        this.points = (-4);
    }
}
