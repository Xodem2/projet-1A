package com.example.projet1a.ado_games;

import com.example.projet1a.list.ListNumbers;

import java.util.Random;

public class Square_Sqrt {
    private int n;
    private int m;
    private int p;
    private int q;
    private String type;
    private static final int nProps = 3;
    private static final int MAXIMUM_RANDOM = 13;
    private static final int[] sqrValues = new int[100];
    Random random;

    public Square_Sqrt(){
        int v = 0;
        while(v < MAXIMUM_RANDOM) {
            sqrValues[v] = (int) Math.pow(v, 2);
            v++;
        }
        random = new Random();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getNProps() {
        return nProps;
    }

    public boolean boolSqr(int n) {
        return n == solveSquare();
    }

    public boolean boolSqrt(int n) {
        return n == solveSqrt();
    }

    public void sqrt(){
        type = "sqrt";
        n = sqrValues[random.nextInt(MAXIMUM_RANDOM)];
    }

    public void square(){
        type = "sqr";
        m = random.nextInt(2*MAXIMUM_RANDOM)-MAXIMUM_RANDOM;
    }

    public int solveSqrt(){
        p = (int)Math.sqrt(n);
        return p;
    }

    public int solveSquare(){
        q = (int)Math.pow(m,2);
        return q;
    }

    public ListNumbers propSqr() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = solveSquare();
        int correctPropPos = this.random.nextInt(this.nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    falseProp = correctProp;
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        falseProp = (int)Math.sqrt(m);
                    }
                    else if(r == 1) {
                        falseProp = 2*m;
                    }
                    else if(r == 2 && m < 0) {
                        falseProp = -correctProp;
                    }
                    else if(r == 2) {
                        falseProp = falseProp+this.random.nextInt(2)+1;
                    }
                    else {
                        falseProp = falseProp-this.random.nextInt(2)-1;
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public ListNumbers propSqrt() {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctProp = solveSqrt();
        int correctPropPos = this.random.nextInt(this.nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    falseProp = correctProp;
                    int r = this.random.nextInt(4);
                    if(r == 0) {
                        falseProp = (int)Math.pow(n, 2);
                    }
                    else if(r == 1) {
                        falseProp = n/2;
                    }
                    else if(r == 2) {
                        falseProp = falseProp+this.random.nextInt(2)+1;
                    }
                    else {
                        falseProp = falseProp-this.random.nextInt(2)-1;
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public boolean fct(int nFct) {
        return (type == "sqrt" && solveSqrt() == nFct) || (type == "sqr" && solveSquare() == nFct);
    }
}
