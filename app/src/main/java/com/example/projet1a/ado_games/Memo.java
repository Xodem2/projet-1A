package com.example.projet1a.ado_games;

import java.util.Random;

public class Memo {
    private Random random;
    private int n = 0;
    private int a = 0;
    private static final int nMax = 99;
    public Memo() {
        this.random = new Random();
        generate();
    }

    public String toString() {
        return this.n+"+"+this.a;
    }

    public int getA() {
        return a;
    }

    public int getN() {
        return n;
    }

    public int getNMax() {
        return nMax;
    }

    public int ans() {
        return a+n;
    }

    public void generate() {
        this.a = this.random.nextInt(nMax+1);
    }

    public boolean input(int n) {
        if(n == this.ans()) {
            this.n = n;
            generate();
            return true;
        }
        else {
            return false;
        }
    }
}
