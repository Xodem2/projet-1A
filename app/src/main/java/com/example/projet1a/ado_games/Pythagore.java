package com.example.projet1a.ado_games;

import java.util.Random;

public class Pythagore {

    private int a;
    private int b;
    private double c;
    private static final int MAXIMUM_RANDOM = 10;

    public Pythagore(){
        do {
            this.a = randomNumber();
            this.b = randomNumber();
        } while((solvePythagore() != (int)solvePythagore()) || (this.a == 0) || (this.b == 0));
    }

    public double solvePythagore() {
        c = Math.sqrt(a*a + b*b);
        return c;
    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(MAXIMUM_RANDOM);
    }

    //Get functions for global variable
    public int getA(){
        return a;
    }
    public int getB(){
        return b;
    }
    public double getC(){
        return c;
    }
    public boolean getSmaller(){
        if( this.a < this.b){
            return true;
        }
        return false;
    }
}
