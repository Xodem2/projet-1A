package com.example.projet1a.enfant;

import java.util.ArrayList;
import java.util.Random;

public class ListNumber {
    private Random random = new Random();
    private int length = 0;
    private int lengthMax = 10;
    private int nMax = 50;
    private ArrayList<Integer> numbers = new ArrayList<Integer>(lengthMax);
    public void ListNumber() {

    }

    public void add(int n) {
        this.numbers.add(n);
        length++;
    }

    public void addEven() {
        assert length < nMax;
        int n = this.random.nextInt(nMax/2-1)*2+2;
        while(numbers.contains(n)) {
            n = this.random.nextInt(nMax/2-1)*2+2;
        }
        this.add(n);
    }

    public void addOdd() {
        assert length < nMax;
        int n = this.random.nextInt(nMax/2)*2+1;
        while(numbers.contains(n)) {
            n = this.random.nextInt(nMax/2)*2+1;
        }
        this.add(n);
    }

    public int getLength() {
        return length;
    }

    public int getNumber(int pos) {
        return this.numbers.get(pos);
    }

    public int getLastNumber() {
        return this.getNumber(getLength()-1);
    }

    public void generateEven1Odd() {
        numbers.clear();
        length = 0;
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
        numbers.clear();
        length = 0;
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
