package com.example.projet1a.enfant;
import java.util.Random;

public class Operation {
    private int a;
    private int b;
    private Random random;
    private int nMax = 50;
    public Operation() {
        this.random = new Random();
        this.generate();
    }
    public int getA() {
        return this.a;
    }
    public int getB() {
        return this.b;
    }
    public void generate() {
        this.a = this.random.nextInt(nMax-1)+1;
        this.b = this.random.nextInt(nMax-1)+1;
    }
    public boolean plus(int ans) {
        return ans == this.a+this.b;
    }
    public boolean minus(int ans) {
        return ans == this.a-this.b;
    }
    public boolean mult(int ans) {
        return ans == this.a*this.b;
    }
    public boolean div(int ans) {
        return ans == this.a/this.b;
    }
}
