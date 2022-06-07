package com.example.projet1a.ado_games;

import java.util.Random;

public class EquationSecond {
    private int a;
    private int b;
    private int c;
    private int x1;
    private int x2;
    private float delta;
    private static final int MAXIMUM_RANDOM = 10;

    public EquationSecond(){
        do{
            a = randomNumber();
            b = randomNumber();
            c = randomNumber();
        } while((delta() < 0) && (calc_x1() == (int)calc_x1() && calc_x2() == (int)calc_x2()));
    }

    public int  delta(){
        delta = b*b - 4*a*c;
        return (int)delta;
    }

    public int calc_x1(){
        if(delta > 0) {
            x1 = (int) ((-b - Math.sqrt(delta))/2*a);
        }
        else{
            x1 = -b /2*a;
        }
        return x1;
    }

    public int calc_x2(){
        if(delta > 0) {
            x2 = (int) ((-b + Math.sqrt(delta))/2*a);
        }
        else{
            x2 = -b /2*a;
        }
        return x2;
    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(MAXIMUM_RANDOM);
    }

    // Gets functions
    public int getA(){
        return a;
    }
    public int getB(){
        return b;
    }
    public int getC(){
        return c;
    }
}
