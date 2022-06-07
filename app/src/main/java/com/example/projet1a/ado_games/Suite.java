package com.example.projet1a.ado_games;

import com.example.projet1a.list.ListNumbers;

import java.util.ArrayList;
import java.util.Random;

public class Suite extends ListNumbers {
    int a;
    int b;
    int c;
    int lengthMaxGenerate;
    Random random;
    public Suite() {
        setLengthMax(6);
        lengthMaxGenerate = 4;
        setNMax(10);
        this.random = this.getRandom();
        this.generate();
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
        int nMax = getNMax();
        if(this.random.nextInt(2) == 0)
            s = 1;
        else
            s = -1;
        return s*(this.random.nextInt(nMax-1)+1);
    }

    public void generate() {
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