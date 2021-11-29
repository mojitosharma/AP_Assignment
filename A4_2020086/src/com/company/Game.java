package com.company;
import java.util.ArrayList;

class Invalidoption extends Exception {
    public Invalidoption(String str) {
        super(str);
    }
}
public class Game {
    private Carpet c;
    private Calculator<Object> cal = new com.company.Calculator();
    private int chance = 0;
    private com.company.Bucket b = new com.company.Bucket();

    public Game() {
    }

    private void Initialize() {
        String[] soft_toys_type = new String[]{"Mickey Mouse", "Thor", "Minion", "Groot", "Bear", "Iron Man", "Super Man", "Black Widow", "Hulk", "Bunny", "Flash", "Spider-man", "Star Lord", "Arrow", "Thanos", "HawkEye", "War Machine", "Ant-man", "Winter Solider", "Caption America"};
        this.c = new Carpet(soft_toys_type);
    }

    private int hop() {
        return (int)(Math.random() * 21.0D + 1.0D);
    }

    private boolean checkanswer(String option) throws Exception {
        if (option.equals("integer")) {
            try {
                int tempInt = Reader.nextint();
                return this.cal.check(tempInt);
            } catch (NumberFormatException var3) {
                System.out.println("Invalid option Type, Try again");
                return this.checkanswer(option);
            }
        } else if (option.equals("string")) {
            String str_temp = Reader.nextLine();
            return this.cal.check(str_temp);
        } else {
            return false;
        }
    }

    private boolean askquestion() throws Exception {
        System.out.printf("Question answer round. integer or strings?\n");
        Reader.init(System.in);
        String option = Reader.nextLine();
        boolean answer = false;

        try {
            if (option.equals("integer")) {
                int t1 = (int)(Math.random() * 100.0D) * 10;
                int t2 = (int)(Math.random() * 100.0D) * t1 * 10;
                this.cal.set_arg(t1, t2);
                System.out.println(this.cal);
                answer = this.checkanswer(option);
            } else {
                if (!option.equals("string")) {
                    throw new Invalidoption("Invalid Option! Please Select a valid option");
                }

                String t1 = "";
                String t2 = "";
                String st = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

                int i;
                int t;
                for(i = 0; i < 4; ++i) {
                    t = (int)(Math.random() * 53.0D);
                    t1 = t1 + st.charAt(t);
                }

                for(i = 0; i < 4; ++i) {
                    t = (int)(Math.random() * 53.0D);
                    t2 = t2 + st.charAt(t);
                }

                this.cal.set_arg(t1, t2);
                System.out.println(this.cal);
                answer = this.checkanswer(option);
            }
        } catch (Invalidoption var8) {
            System.out.println(var8.getMessage());
            return this.askquestion();
        } catch (IndexOutOfBoundsException var9) {
            System.out.println("Something went wrong please try again");
            this.askquestion();
        }

        return answer;
    }

    public void play() throws Exception {
        this.Initialize();
        System.out.printf("Hit enter to initialize the game");
        String[] chance_Str = new String[]{"first", "second", "third", "fourth", "fifth"};
        Reader.init(System.in);
        String temp = Reader.nextLine();
        System.out.printf("Game is ready\n");

        try {
            while(this.chance < 5) {
                System.out.printf("Hit enter for your %s hop", chance_Str[this.chance]);
                ++this.chance;
                temp = Reader.nextLine();
                int pos = this.hop();

                Soft_toy won;
                try {
                    won = this.c.getSoft_toy(pos);
                    System.out.printf("You landed on tile %d\n", pos);
                } catch (ArrayIndexOutOfBoundsException var6) {
                    System.out.printf("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!\n");
                    continue;
                }

                boolean ans = true;
                if (pos % 2 == 1) {
                    ans = this.askquestion();
                }

                if (!ans) {
                    System.out.printf("Incorrect answer\nYou did not win any soft toy\n");
                } else {
                    this.b.add_toy(won);
                    System.out.println("You won a " + won.getName() + " soft toy");
                }
            }

            System.out.printf("Game Over\nSoft toys won by you are:\n");
            ArrayList<Soft_toy> st = this.b.getSoft_toys_won();

            for(int i = 0; i < st.size(); ++i) {
                System.out.printf("%s, ", ((Soft_toy)st.get(i)).getName());
            }
        } catch (Exception var7) {
            System.out.println("Something Went Wrong Please Restart the game");
        }

    }
}
