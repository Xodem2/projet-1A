package com.example.projet1a.ado_games;

import com.example.projet1a.list.ListNumbers;

import java.util.ArrayList;
import java.util.Random;

public class Suite extends ListNumbers {
    private int a;
    private int b;
    private int c;
    private int lengthMaxGenerate;
    private Random random;
    private static final int nMax = 9;
    public Suite() {
        super(5);
        lengthMaxGenerate = 4;
        this.random = this.getRandom();
        this.generate();
    }

    public String toString() {
        return this.getA() + " n²+" + this.getB() + " n+" + this.getC();
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

    public int generateTerm() {
        int s;
        if(this.random.nextInt(2) == 0)
            s = 1;
        else
            s = -1;
        return s*(this.random.nextInt(nMax)+1);
    }

    public void generate() {
        this.clear();
        this.a = generateTerm();
        this.b = generateTerm();
        this.c = generateTerm();
        for(int i = 0; i < lengthMaxGenerate; i++) {
            this.add(this.a*i*i+this.b*i+this.c);
        }
    }

    public boolean input(int n) {
        int length = getLength();
        int value = this.a*length*length+this.b*length+this.c;
        if(n == value) {
            this.add(n);
            return true;
        }
        return false;
    }
}
