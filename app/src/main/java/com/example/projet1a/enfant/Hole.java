package com.example.projet1a.enfant;

import java.util.Random;

public class Hole {
    private int a;
    private int b;
    private int c;
    private Random random;
    private int nMax = 50;
    public Hole(int opChoice) {
        this.random = new Random();
        this.generate(opChoice);
    }
    public int getA() {
        return this.a;
    }
    public int getB() {
        return this.b;
    }
    public int getC() {
        return this.c;
    }
    public void generate(int opChoice) {
        this.a = this.random.nextInt(nMax-1)+1;
        this.b = this.random.nextInt(nMax-1)+1;
        if(opChoice == 0)
            c = this.a+this.b;
        else if(opChoice == 1)
            c = this.a-this.b;
        else if(opChoice == 2)
            c = this.a*this.b;
        else
            c = this.a/this.b;
    }
    public boolean plus() {
        return c == this.a+this.b;
    }
    public boolean minus() {
        return c == this.a-this.b;
    }
    public boolean mult() {
        return c == this.a*this.b;
    }
    public boolean div() {
        return c == this.a/this.b;
    }
}
