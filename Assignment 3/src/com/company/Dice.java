package com.company;
public class Dice {
    private int face_value;
    public void roll() {
        double temp = Math.random();
        //to increase randomness
//        if(temp <= 0.25)set_face_value(2);
//        else if(temp <= 0.50)set_face_value(1);
//        else if(temp <= 0.75)set_face_value(2);
//        else if(temp <= 1)set_face_value(1);
        int curr_faceValue = (Math.random() <= 0.5) ? 1 : 2;
        set_face_value(curr_faceValue);
    }
    //getter
    public int get_face_value() {
        return face_value;
    }
    //setter
    private void set_face_value (int value) {
        if (value <= 2)
            face_value = value;
    }
    public String toString() {
        return "Dice gave " + face_value;
    }
}
