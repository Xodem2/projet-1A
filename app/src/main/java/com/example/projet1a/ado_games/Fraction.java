package com.example.projet1a.ado_games;

import androidx.annotation.NonNull;

import java.util.Random;

public class Fraction {

    private int num;
    private int den;
    private Random random;

    public Fraction(){
        this.random = new Random();
        this.num = random.nextInt();
        this.den = random.nextInt();
        while (this.den==0){
            this.den = random.nextInt();
        }
        int i=2;
        while (i<Math.min(this.num, this.den)) {
            if (this.num%i==0 && this.den%i==0){
                this.num/=i;
                this.den/=i;
            }
            else{
                i++;
            }
        }

        int mult = random.nextInt(10);
    }

    public int[] getQuestion(){
        int[] retour = new int[2];
        int mult = random.nextInt(10)+1;
        retour[0] = this.num*mult;
        retour[1] = this.den*mult;
        return retour;
    }

    public int[][] getProp(){
        int[][] retour = new int[3][2];
        int mult;
        int[] multi = new int[3];
        for (int i=0; i<3; i++){
            multi[i]=0;
        }
        for (int i=0; i<2; i++){
            boolean test;
            do {
                mult = random.nextInt(20) + 1;
                test = false;
                for (int j = 0; j < i; j++) {
                    test = test || multi[j] == mult;
                }
            } while (test);
            multi[i] = mult;
            retour[i] = new int[2];
            retour[i][0] = this.num*mult;
            retour[i][1] = this.den*mult;
        }
        return retour;
    }

    public boolean verify(@NonNull int[] solve){
        return solve[0]==this.num && solve[1]==this.den;
    }

}
