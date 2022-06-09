package com.example.projet1a.enfant;

import com.example.projet1a.list.ListNumbers;

import java.util.ArrayList;
import java.util.Random;

public class ListIntruder extends ListNumbers {
    private Random random;
    private int nMax = 99;
    public ListIntruder() {
        super(6);
        this.random = this.getRandom();
        int choice = this.random.nextInt(2);
        if(choice == 0)
            generateEven1Odd();
        else
            generateOdd1Even();
    }

    public void addEven() {
        int n = this.random.nextInt(nMax/2-1)*2+2;
        while(getNumbers().contains(n)) {
            n = this.random.nextInt(nMax/2-1)*2+2;
        }
        this.add(n);
    }

    public void addOdd() {
        int n = this.random.nextInt(nMax/2)*2+1;
        while(getNumbers().contains(n)) {
            n = this.random.nextInt(nMax/2)*2+1;
        }
        this.add(n);
    }

    public void generateEven1Odd() {
        this.clear();
        int lengthMax = getLengthMax();
        int intruderPos = this.random.nextInt(lengthMax);
        for(int i = 0; i < lengthMax; i++) {
            if(i == intruderPos) {
                addOdd();
            }
            else {
                addEven();
            }
        }
    }

    public void generateOdd1Even() {
        this.clear();
        int lengthMax = getLengthMax();
        int intruderPos = this.random.nextInt(lengthMax);
        for(int i = 0; i < lengthMax; i++) {
            if(i == intruderPos) {
                addEven();
            }
            else {
                addOdd();
            }
        }
    }
}
