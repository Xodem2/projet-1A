package com.example.projet1a.ado_games;

import junit.framework.TestCase;
import java.lang.Math;

public class TrigoTest extends TestCase {

    public void testSin1() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec BC = " + trigo.getSeg() + " cm (hypothénuse) et angle ABC = " + trigo.getAngle() + "°. Calculer AC (côté opposé à l'angle).");
        System.out.println("AC = sin(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + trigo.ansSin1() + " cm");
        assert trigo.sin1(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
        assert !trigo.sin1(Math.round((long) (Math.cos(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
    }

    public void testSin2() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec AC = " + trigo.getSeg() + " cm  (côté opposé à l'angle) et angle ABC = " + trigo.getAngle() + "°. Calculer BC (l'hypothénuse).");
        System.out.println("BC = " + trigo.getSeg() + "/sin(" + trigo.getAngle() + "°) = " + trigo.ansSin2() + " cm");
        assert trigo.sin2(Math.round((long) (trigo.getSeg()/Math.sin(Math.toRadians(trigo.getAngle())))));
        assert !trigo.sin2(Math.round((long) (trigo.getSeg()/Math.cos(Math.toRadians(trigo.getAngle())))));
    }

    public void testCos1() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec BC = " + trigo.getSeg() + " cm (hypothénuse) et angle ABC = " + trigo.getAngle() + "°. Calculer AB (côté adjacent à l'angle).");
        System.out.println("AC = cos(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + trigo.ansCos1() + " cm");
        assert trigo.cos1(Math.round((long) (Math.cos(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
        assert !trigo.cos1(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
    }

    public void testCos2() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec AB = " + trigo.getSeg() + " cm (côté adjacent à l'angle) et angle ABC = " + trigo.getAngle() + "°. Calculer BC (hypothénuse).");
        System.out.println("BC = " + trigo.getSeg() + "/cos(" + trigo.getAngle() + "°) = " + trigo.ansCos2() + " cm");
        assert trigo.cos2(Math.round((long) (trigo.getSeg()/Math.cos(Math.toRadians(trigo.getAngle())))));
        assert !trigo.cos2(Math.round((long) (trigo.getSeg()/Math.sin(Math.toRadians(trigo.getAngle())))));
    }

    public void testTan1() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec AB = " + trigo.getSeg() + " cm (côté adjacent à l'angle) et angle ABC = " + trigo.getAngle() + "°. Calculer AC (côté opposé à l'angle).");
        System.out.println("AC = tan(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + trigo.ansTan1() + " cm");
        assert trigo.tan1(Math.round((long) (Math.tan(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
        assert !trigo.tan1(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
    }

    public void testTan2() {
        Trigo trigo = new Trigo();
        System.out.println("Soit un triangle rectangle en A avec AC = " + trigo.getSeg() + " cm (côté opposé à l'angle) et angle ABC = " + trigo.getAngle() + "°. Calculer AB (côté adjacent à l'angle).");
        System.out.println("AB = " + trigo.getSeg() + "/tan(" + trigo.getAngle() + "°) = " + trigo.ansTan2() + " cm");
        assert trigo.tan2(Math.round((long) (trigo.getSeg()/Math.tan(Math.toRadians(trigo.getAngle())))));
        assert !trigo.tan2(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
    }
}