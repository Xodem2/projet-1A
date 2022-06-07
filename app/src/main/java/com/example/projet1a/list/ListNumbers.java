package com.example.projet1a.list;

import java.util.ArrayList;
import java.util.Random;

public class ListNumbers {
    private Random random;
    private int length;
    private int lengthMax;
    private int nMax;
    private ArrayList<Integer> numbers;
    public ListNumbers() {
        random = new Random();
        length = 0;
        lengthMax = 10;
        nMax = 50;
        numbers = new ArrayList<>(lengthMax);
    }

    public void add(int n) {
        this.numbers.add(n);
        length++;
    }

    public void clear() {
        this.numbers.clear();
        length = 0;
    }

    public Random getRandom() {
        return random;
    }

    public int getLength() {
        return length;
    }

    public int getLengthMax() {
        return lengthMax;
    }

    public void setLengthMax(int n) {
        lengthMax = n;
    }

    public int getNMax() {
        return nMax;
    }

    public void setNMax(int n) {
        nMax = n;
    }

    public ArrayList<Integer> getNumbers() {
        return this.numbers;
    }

    public int getNumber(int pos) {
        return this.numbers.get(pos);
    }

    public int getLastNumber() {
        return this.getNumber(getLength()-1);
    }
}