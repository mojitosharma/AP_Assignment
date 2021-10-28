package com.company;

public class Sankesandladders {
    Floor floors[] = new Floor[14];
    private Dice d;
    private Player p;
    private int last_3_throw[] = new int[3];
    private int count;

    private void initialization()throws Exception{
        //initialization
        floors[0] = new Floor("Empty floor", 0);
        floors[1] = new Floor("Empty floor", 1);
        floors[2] = new Ladder_floor("Elevator floor", 2);
        floors[3] = new Floor("Empty floor", 3);
        floors[4] = new Floor("Empty floor", 4);
        floors[5] = new Snake_floor("Normal snake", 5);
        floors[6] = new Floor("Empty floor", 6);
        floors[7] = new Floor("Empty floor", 7);
        floors[8] = new Ladder_floor("Ladder floor", 8);
        floors[9] = new Floor("Empty floor", 9);
        floors[10] = new Floor("Empty floor", 10);
        floors[11] = new Snake_floor("King cobra snake", 11);
        floors[12] = new Floor("Empty floor", 12);
        floors[13] = new Floor("Empty floor", 13);

        count = 0;
        last_3_throw[0]  = last_3_throw[1] = last_3_throw[2] = -1;

        d = new Dice();
        System.out.println("Enter the player name and hit enter  ");
        Reader.init(System.in);
        String name = Reader.nextLine();
        p = new Player(name);
        System.out.println("The game setup is ready");
    }

    private int check_last_3_throw(int temp){
        last_3_throw[count] = temp;
        count++;
        if(count == 3)count = 0;
        if(last_3_throw[0] == last_3_throw[1] && last_3_throw[0] == last_3_throw[2]){
            count = 0;                              //If three consecutive terms then reset count and values
            last_3_throw[0]  = last_3_throw[1] = last_3_throw[2] = -1;
            return 1;
        }
        else{
            return -1;
        }
    }

    public void play()throws Exception{
        initialization();
        Reader.init(System.in);
        System.out.printf("Hit enter to roll the dice ");
        String input = Reader.nextLine();
        p.roll_dice(d);
        while(d.get_face_value() != 1){
            System.out.println(d);
            System.out.println("Game cannot start until you get 1");
            System.out.printf("Hit enter to roll the dice ");
            input = Reader.nextLine();
            p.roll_dice(d);
            if(check_last_3_throw(d.get_face_value()) == 1){
                p.add_Total_points(2);
                System.out.println("Three consecutive "+d.get_face_value()+"'s 2 point bonus");
            }
        }
        p.set_floor_no(d.get_face_value());
        p.setFloor(floors[p.get_floor_no()]);
        p.jump();
        this.print_data();

        while(p.get_floor_no() < 12){
            System.out.printf("Hit enter to roll the dice ");
            input = Reader.nextLine();
            this.roll();
        }

        if(p.get_floor_no() == 12){
            while(p.get_floor_no() != 13) {
                System.out.printf("Hit enter to roll the dice ");
                input = Reader.nextLine();
                p.roll_dice(d);
                if(d.get_face_value() == 2){
                    System.out.println(d);
                    System.out.println("Player cannot move");
                }
                else{
                    p.set_floor_no(d.get_face_value());
                    p.setFloor(floors[p.get_floor_no()]);
                    p.jump();
                    this.print_data();
                }
                if(check_last_3_throw(d.get_face_value()) == 1){
                    p.add_Total_points(2);
                    System.out.println("Three consecutive "+d.get_face_value()+"'s 2 point bonus");
                }
            }
        }
        System.out.println("Game over");
        System.out.println(p.getName()+ " accumulated "+p.getTotal_points()+" points");
    }

    private void roll(){
        p.roll_dice(d);
        p.set_floor_no(d.get_face_value());
        p.setFloor(floors[p.get_floor_no()]);
        p.jump();
        this.print_data();
        if(p.get_floor_no() == 2 || p.get_floor_no() == 5 || p.get_floor_no() == 8 || p.get_floor_no() == 11 ){
            int temp = floors[p.get_floor_no()].get_goes_to_floor();
            p.go_to_floor(temp);
            p.setFloor(floors[temp]);
            p.jump();
            System.out.println(p);
        }
        if(check_last_3_throw(d.get_face_value()) == 1){
            p.add_Total_points(2);
            System.out.println("Three consecutive "+d.get_face_value()+"'s 2 point bonus");
        }
    }
    public void print_data(){
        System.out.println(d);
        System.out.println(p);
    }

    public static void main(String[] args) throws Exception {
        new Sankesandladders().play();
    }
}
