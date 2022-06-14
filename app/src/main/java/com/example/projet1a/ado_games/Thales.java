package com.example.projet1a.ado_games;

import java.util.Random;

public class Thales {

    public int AD;
    public int AB;
    public int AE;
    public int AC;
    public int DE;
    public int BC;
    private static final int MAXIMUM_RANDOM = 10;

    public Thales(){
        do{
            AD = randomNumber();
            AB = randomNumber();
            AE = randomNumber();
            AC = randomNumber();
            DE = randomNumber();
            BC = randomNumber();
        }while(this.AD == 0 || this.AB == 0 || this.AE == 0 || this.AC == 0 || this.DE == 0 || this.BC == 0 || !isInt(AD, AB) || !isInt(AE, AC) || !isInt(DE, BC) || solveThales() == false);
    }

    public boolean solveThales(){
        if((float)(AD/AB) == (float)(AE/AC) && (float)(AE/AC) == (float)(DE/BC)){return true;}
        else{ return false; }
    }

    public boolean isInt(int x, int y){
        if( x/y == (int)x/y) {return true;}
        else{ return false; }
    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(MAXIMUM_RANDOM);
    }

    //Get functions for global variable
    public int getAB() {
        return AB;
    }
    public int getAD() {
        return AD;
    }
    public int getAC() {
        return AC;
    }
    public int getAE() {
        return AE;
    }
    public int getBC() {
        return BC;
    }
    public int getDE() {
        return DE;
    }
    public int get1(){ return this.AD/this.AB; }
    public int get2(){ return AE/AC; }
    public int get3(){ return DE/BC; }
    public boolean getBool(){ return solveThales(); }
}
