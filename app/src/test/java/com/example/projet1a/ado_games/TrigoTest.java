package com.example.projet1a.ado_games;

import junit.framework.TestCase;
import java.lang.Math;

public class TrigoTest extends TestCase {

    public void testSin1() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec BC = " + trigo.getSeg() + " cm (hypothénuse) et angle ABC = " + trigo.getAngle() + "°. Calculer AC.");
        System.out.println("AC = sin(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + trigo.ansSin1() + " cm");
        assert trigo.sin1(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
    }

    public void testSin2() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec AC = " + trigo.getSeg() + " cm et angle ABC = " + trigo.getAngle() + "°. Calculer BC (l'hypothénuse).");
        System.out.println("BC = " + trigo.getSeg() + "/sin(" + trigo.getAngle() + "°) = " + trigo.ansSin2() + " cm");
        assert trigo.sin2(Math.round((long) (trigo.getSeg()/Math.sin(Math.toRadians(trigo.getAngle())))));
    }

    public void testCos1() {
    }

    public void testCos2() {
    }

    public void testTan1() {
    }

    public void testTan2() {
    }
}