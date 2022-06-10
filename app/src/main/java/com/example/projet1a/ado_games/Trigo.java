package com.example.projet1a.ado_games;

import com.example.projet1a.list.ListNumbers;

import java.util.Random;
import java.lang.Math;

public class Trigo {
    private int nMax = 999;
    private int nProps = 3;
    int seg;
    int angle;
    int nAngles = 3;
    private int[] angles = new int[nAngles];
    private Random random = new Random();
    public Trigo() {
        generate();
    }

    public void generate() {
        seg = random.nextInt(nMax-1)+1;
        angles[0] = 30;
        angles[1] = 45;
        angles[2] = 60;
        angle = angles[random.nextInt(nAngles)];
    }

    public int getSeg() {
        return this.seg;
    }

    public int getAngle() {
        return this.angle;
    }

    public int ansSin1() {
        double fct = Math.sin(Math.toRadians(angle));
        return Math.round((long) (fct*seg));
    }

    public int ansSin2() {
        double fct = Math.sin(Math.toRadians(angle));
        return Math.round((long) (seg/fct));
    }

    public int ansCos1() {
        double fct = Math.cos(Math.toRadians(angle));
        return Math.round((long) (fct*seg));
    }

    public int ansCos2() {
        double fct = Math.cos(Math.toRadians(angle));
        return Math.round((long) (seg/fct));
    }

    public int ansTan1() {
        double fct = Math.tan(Math.toRadians(angle));
        return Math.round((long) (fct*seg));
    }

    public int ansTan2() {
        double fct = Math.tan(Math.toRadians(angle));
        return Math.round((long) (seg/fct));
    }

    public boolean sin1(int ans) {
        return ans == ansSin1();
    }

    public boolean sin2(int ans) {
        return ans == ansSin2();
    }

    public boolean cos1(int ans) {
        return ans == ansCos1();
    }

    public boolean cos2(int ans) {
        return ans == ansCos2();
    }

    public boolean tan1(int ans) {
        return ans == ansTan1();
    }

    public boolean tan2(int ans) {
        return ans == ansTan2();
    }

    public ListNumbers prop(int correctProp) {
        ListNumbers choices = new ListNumbers(this.nProps);
        int correctPropPos = this.random.nextInt(this.nProps);
        for(int i = 0; i < nProps; i++) {
            if(i == correctPropPos)
                choices.add(correctProp);
            else {
                int falseProp;
                do {
                    int r = this.random.nextInt(6);
                    if(r == 0) {
                        falseProp = ansSin1();
                    }
                    else if(r == 1) {
                        falseProp = ansSin2();
                    }
                    else if(r == 2) {
                        falseProp = ansCos1();
                    }
                    else if(r == 3) {
                        falseProp = ansCos2();
                    }
                    else if(r == 4) {
                        falseProp = ansTan1();
                    }
                    else {
                        falseProp = ansTan2();
                    }
                } while(choices.contains(falseProp) || falseProp == correctProp);
                choices.add(falseProp);
            }
        }
        return choices;
    }

    public int getNProps() {
        return this.nProps;
    }
}
