package com.example.projet1a.ado_games;

import java.util.Random;

public class Square_Sqrt {
    private int n;
    private int m;
    private int p;
    private int q;
    private static final int MAXIMUM_RANDOM = 13;

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(MAXIMUM_RANDOM);
    }

    public void sqrt(){
        do {
            n = randomNumber();
        } while( (solveSqrt() != (int)solveSqrt()));
    }

    public void square(){
        do {
            m = randomNumber();
        } while( (solveSquare() != (int)solveSquare()));
    }

    public int solveSqrt(){
        p = (int)Math.sqrt(n);
        return p;
    }

    public int solveSquare(){
        q = (int)Math.pow(m,2);
        return q;
    }
}
