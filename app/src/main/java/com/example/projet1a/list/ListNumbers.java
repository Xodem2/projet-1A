package com.example.projet1a.list;

import com.example.projet1a.adult.Matrix;

import java.util.ArrayList;
import java.util.Random;

public class ListNumbers {
    private Random random;
    private int length;
    private int lengthMax;
    private ArrayList<Integer> numbers;

    public ListNumbers(int lengthMax) {
        this.random = new Random();
        this.length = 0;
        this.lengthMax = lengthMax;
        this.numbers = new ArrayList<>(lengthMax);
    }

    public void add(int n) {
        assert this.length < this.lengthMax;
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

    public ArrayList<Integer> getNumbers() {
        return this.numbers;
    }

    public int getNumber(int pos) {
        return this.numbers.get(pos);
    }

    public int getLastNumber() {
        return this.getNumber(getLength()-1);
    }

    public boolean contains(int value) {
        return this.numbers.contains(value);
    }

    public int[] conv(){
        int[] retour = new int[this.length];
        for (int i=0; i<this.length; i++){
            retour[i] = this.getNumber(i);
        }
        return retour;
    }

}