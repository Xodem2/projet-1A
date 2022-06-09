package com.example.projet1a.ado_games;

import java.util.Random;
import java.lang.Math;

public class Trigo {
    private int[] segs = new int[3];
    private int nMax = 999;
    int seg;
    int angle;
    int rSin1;
    int rSin2;
    int rCos1;
    int rCos2;
    int rTan1;
    int rTan2;
    private Random random = new Random();
    public Trigo() {
        generate();
    }

    public void generate() {
        seg = random.nextInt(nMax - 1) + 1;
        angle = random.nextInt(89)+1;
        rSin1 = Math.round((long) (Math.sin(Math.toRadians(angle))*seg));
        rSin2 = Math.round((long) (seg/Math.sin(Math.toRadians(angle))));
        rCos1 = Math.round((long) (Math.cos(Math.toRadians(angle))*seg));
        rCos2 = Math.round((long) (seg/Math.cos(Math.toRadians(angle))));
        rTan1 = Math.round((long) (Math.tan(Math.toRadians(angle))*seg));
        rTan2 = Math.round((long) (seg/Math.tan(Math.toRadians(angle))));
    }

    public int getSeg() {
        return this.seg;
    }

    public int getAngle() {
        return this.angle;
    }

    public int ansSin1() {
        return rSin1;
    }

    public int ansSin2() {
        return rSin2;
    }

    public int ansCos1() {
        return rCos1;
    }

    public int ansCos2() {
        return rCos2;
    }

    public int ansTan1() {
        return rTan1;
    }

    public int ansTan2() {
        return rTan2;
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
}
