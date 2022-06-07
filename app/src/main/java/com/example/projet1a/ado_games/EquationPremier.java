package com.example.projet1a.ado_games;

import java.util.Random;

public class EquationPremier {
    private int a;
    private int b;
    private float x;
    private static final int MAXIMUM_RANDOM = 10;


    public EquationPremier(){
        do {
            a = randomNumber();
            b = randomNumber();
        } while(solveEquation() != (int)solveEquation());
    }

    public int solveEquation(){
        x = (int)(-b / a);
        return (int)x;
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
    public float getX(){
        return x;
    }
}
