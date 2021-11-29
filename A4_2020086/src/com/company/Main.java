package com.company;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        try {
            Game g = new Game();
            g.play();
        } catch (NullPointerException var2) {
            System.out.println("Tried to Access NULL pointer");
        } catch (Exception var3) {
            System.out.println("Something went wrong Please Try again");
        }

    }
}
