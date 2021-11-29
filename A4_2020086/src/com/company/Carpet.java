package com.company;

public class Carpet {
    private Soft_toy[] soft_toy = new Soft_toy[20];

    Carpet(String[] name) {
        try {
            for(int i = 0; i < name.length; ++i) {
                this.soft_toy[i] = new Soft_toy(name[i]);
            }
        } catch (IndexOutOfBoundsException var3) {
            System.out.println("All Tiles not filled\n");
        } catch (Exception var4) {
            System.out.println("Something Went Wrong!\n");
        }

    }

    public Soft_toy getSoft_toy(int i) {
        return this.soft_toy[i].clone();
    }
}
