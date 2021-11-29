package com.company;

import java.util.ArrayList;

public class Bucket {
    private ArrayList<Soft_toy> soft_toys_won = new ArrayList();

    public Bucket() {
    }

    public void add_toy(Soft_toy t) {
        this.soft_toys_won.add(t);
    }

    public ArrayList<Soft_toy> getSoft_toys_won() {
        return this.soft_toys_won;
    }
}