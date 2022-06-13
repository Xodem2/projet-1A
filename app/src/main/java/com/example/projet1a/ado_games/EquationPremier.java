package com.example.projet1a.ado_games;

import java.util.Random;

public class EquationPremier {
    private int a;
    private int b;
    private int c;
    private float x;
    private static final int MAXIMUM_RANDOM = 10;


    public EquationPremier(){
        do {
            a = randomNumber();
            b = randomNumber();
            c = randomNumber();
        } while( this.a == 0 || this.b == 0 || (solveEquation() != (int)solveEquation()));
    }

    public float solveEquation(){
        x = (float)(c-b)/a;
        return x;
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
    public int getC() { return c; }
    public int getX(){return (int)solveEquation();}
}
