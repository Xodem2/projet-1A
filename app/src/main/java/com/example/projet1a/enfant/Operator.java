package com.example.projet1a.enfant;

import java.util.Random;

public class Operator {
    private int a;
    private int b;
    private int c;
    private float x;
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
        if (rdOp ==1){
            c = a - b;
        }
        if (rdOp ==2){
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
}
