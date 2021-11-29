package com.company;

interface Cloneable {
}

public class Soft_toy implements Cloneable {
    private String name;

    Soft_toy(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Soft_toy clone() {
        Soft_toy copy = new Soft_toy(this.name);
        return copy;
    }
}

