package com.example.projet1a.ado_games;

import com.example.projet1a.adult.Trigo;
import com.example.projet1a.list.ListNumbers;

import junit.framework.TestCase;
import java.lang.Math;

public class TrigoTest extends TestCase {

    public void testSin1() {
        Trigo trigo = new Trigo();
        assert trigo.sin1(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
        assert !trigo.sin1(Math.round((long) (Math.sin(Math.toRadians(trigo.getAngle()))*trigo.getSeg()))+1);
    }

    public void testSin2() {
        Trigo trigo = new Trigo();
        assert trigo.sin2(Math.round((long) (trigo.getSeg()/Math.sin(Math.toRadians(trigo.getAngle())))));
        assert !trigo.sin2(Math.round((long) (trigo.getSeg()/Math.sin(Math.toRadians(trigo.getAngle()))))+1);
    }

    public void testCos1() {
        Trigo trigo = new Trigo();
        assert trigo.cos1(Math.round((long) (Math.cos(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
        assert !trigo.cos1(Math.round((long) (Math.cos(Math.toRadians(trigo.getAngle()))*trigo.getSeg()))+1);
    }

    public void testCos2() {
        Trigo trigo = new Trigo();
        assert trigo.cos2(Math.round((long) (trigo.getSeg()/Math.cos(Math.toRadians(trigo.getAngle())))));
        assert !trigo.cos2(Math.round((long) (trigo.getSeg()/Math.cos(Math.toRadians(trigo.getAngle()))))+1);
    }

    public void testTan1() {
        Trigo trigo = new Trigo();
        assert trigo.tan1(Math.round((long) (Math.tan(Math.toRadians(trigo.getAngle()))*trigo.getSeg())));
        assert !trigo.tan1(Math.round((long) (Math.tan(Math.toRadians(trigo.getAngle()))*trigo.getSeg()))+1);
    }

    public void testTan2() {
        Trigo trigo = new Trigo();
        assert trigo.tan2(Math.round((long) (trigo.getSeg()/Math.tan(Math.toRadians(trigo.getAngle())))));
        assert !trigo.tan2(Math.round((long) (trigo.getSeg()/Math.tan(Math.toRadians(trigo.getAngle()))))+1);
    }

    public void testPropSin1() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansSin1();
        ListNumbers props = trigo.prop(correct);
        System.out.println("Soit un triangle rectangle en A avec BC = " + trigo.getSeg() + " cm (hypothénuse) et angle ABC = " + trigo.getAngle() + "°. Calculer AC (côté opposé à l'angle ABC) et choisir la valeur la plus proche en cm.");
        for(int i = 0; i < trigo.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(trigo.sin1(prop))) || prop == correct && trigo.sin1(prop);
        }
        System.out.println("AC = sin(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + correct + " cm");
    }

    public void testPropSin2() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansSin2();
        ListNumbers props = trigo.prop(correct);
        System.out.println("Soit un triangle rectangle en A avec AC = " + trigo.getSeg() + " cm (côté opposé à l'angle ABC) et angle ABC = " + trigo.getAngle() + "°. Calculer BC (l'hypothénuse) et choisir la valeur la plus proche en cm.");
        for(int i = 0; i < trigo.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(trigo.sin2(prop))) || prop == correct && trigo.sin2(prop);
        }
        System.out.println("BC = " + trigo.getSeg() + "/sin(" + trigo.getAngle() + "°) = " + correct + " cm");
    }

    public void testPropCos1() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansCos1();
        ListNumbers props = trigo.prop(correct);
        System.out.println("Soit un triangle rectangle en A avec BC = " + trigo.getSeg() + " cm (hypothénuse) et angle ABC = " + trigo.getAngle() + "°. Calculer AB (côté adjacent à l'angle ABC) et choisir la valeur la plus proche en cm.");
        for(int i = 0; i < trigo.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(trigo.cos1(prop))) || prop == correct && trigo.cos1(prop);
        }
        System.out.println("AC = cos(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + correct + " cm");
    }

    public void testPropCos2() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansCos2();
        ListNumbers props = trigo.prop(correct);
        System.out.println("Soit un triangle rectangle en A avec AB = " + trigo.getSeg() + " cm (côté adjacent à l'angle ABC) et angle ABC = " + trigo.getAngle() + "°. Calculer BC (hypothénuse) et choisir la valeur la plus proche en cm.");
        for(int i = 0; i < trigo.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(trigo.cos2(prop))) || prop == correct && trigo.cos2(prop);
        }
        System.out.println("BC = " + trigo.getSeg() + "/cos(" + trigo.getAngle() + "°) = " + correct + " cm");
    }

    public void testPropTan1() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansTan1();
        ListNumbers props = trigo.prop(correct);
        System.out.println("Soit un triangle rectangle en A avec AB = " + trigo.getSeg() + " cm (côté adjacent à l'angle ABC) et angle ABC = " + trigo.getAngle() + "°. Calculer AC (côté opposé à l'angle ABC) et choisir la valeur la plus proche en cm.");
        for(int i = 0; i < trigo.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(trigo.tan1(prop))) || prop == correct && trigo.tan1(prop);
        }
        System.out.println("AC = tan(" + trigo.getAngle() + "°)*" + trigo.getSeg() + " = " + trigo.ansTan1() + " cm");
    }

    public void testPropTan2() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansTan2();
        ListNumbers props = trigo.prop(correct);
        System.out.println("Soit un triangle rectangle en A avec AC = " + trigo.getSeg() + " cm (côté opposé à l'angle ABC) et angle ABC = " + trigo.getAngle() + "°. Calculer AB (côté adjacent à l'angle ABC) et choisir la valeur la plus proche en cm.");
        for(int i = 0; i < trigo.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(trigo.tan2(prop))) || prop == correct && trigo.tan2(prop);
        }
        System.out.println("AB = " + trigo.getSeg() + "/tan(" + trigo.getAngle() + "°) = " + trigo.ansTan2() + " cm");
    }

    public void testAnsFct() {
        Trigo trigo = new Trigo();
        int correct = trigo.ansFct();
        assert trigo.fct(correct) && !trigo.fct(Math.round((long) (Math.cos(Math.toRadians(trigo.getAngle()))*trigo.getSeg()))+1);
    }

}