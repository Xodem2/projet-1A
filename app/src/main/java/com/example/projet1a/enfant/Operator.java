package com.example.projet1a.enfant;

import com.example.projet1a.list.ListNumbers;

import java.util.Random;

public class Operator {
    private int a;
    private int b;
    private int c;
    private int rdOp;
    private static final int MAXIMUM_RANDOM = 10;
    private static final int MAXIMUM_RANDOM2 = 3;


    public Operator(){

        rdOp=randomNumber2();
        System.out.println("rdOp");
            a = randomNumber();
            b = randomNumber();
        rdOp=randomNumber2();
        if (rdOp ==0){
            c = a + b;
        }
        else if (rdOp ==1){
            c = a - b;
        }
        else {
            c = a*b;
        }
    }


    public int randomNumber2(){
        Random random = new Random();
        return random.nextInt(MAXIMUM_RANDOM2);
    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(MAXIMUM_RANDOM);
    }

    //Get functions for global variables
    public int getA(){
        return a;
    }
    public int getB(){
        return b;
    }
    public int getC() {
        return c;
    }
    public int getX(){
        return (int)rdOp;
    }

    public String getOp(int n) {
        if(n == 0) {
            return "+";
        }
        else if(n == 1) {
            return "-";
        }
        else {
            return "x";
        }
    }

    public int getOpInv(String op) {
        if(op == "+") {
            return 0;
        }
        else if(op == "-") {
            return 1;
        }
        else {
            return 2;
        }
    }

    public boolean op(int n) {
        if(n == 0) {
            return a+b == c;
        }
        else if(n == 1) {
            return a-b == c;
        }
        else {
            return a*b == c;
        }
    }

    public ListNumbers prop() {
        ListNumbers choices = new ListNumbers(MAXIMUM_RANDOM2);
        int correctProp = getX();
        int correctPropPos = randomNumber2();
        for (int i = 0; i < MAXIMUM_RANDOM2; i++) {
            if (i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    falseProp = randomNumber2();
                } while (choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }
}
