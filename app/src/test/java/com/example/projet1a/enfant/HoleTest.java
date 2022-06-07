package com.example.projet1a.enfant;

import junit.framework.TestCase;

public class HoleTest extends TestCase {

    public void testGetA() {
    }

    public void testGetB() {
    }

    public void testGenerate() {
        Hole hole = new Hole(0);
        int a = hole.getA();
        int b = hole.getB();
        hole.generate(0);
        assert hole.getA() != a && hole.getB() != b;
    }

    public void testPlus() {
        Hole hole = new Hole(0);
        System.out.println(hole.getA() + " + " + hole.getB() + " = " + hole.getC());
        assert hole.plus();
        assert !(hole.minus());
        assert !(hole.mult()) || (hole.getA() == hole.getB() && hole.getA() == 2);
        assert !(hole.div());
    }

    public void testMinus() {
        Hole hole = new Hole(1);
        System.out.println(hole.getA() + " - " + hole.getB() + " = " + hole.getC());
        assert !(hole.plus());
        assert hole.minus();
        assert !(hole.mult());
        assert !(hole.div());
    }

    public void testMult() {
        Hole hole = new Hole(2);
        System.out.println(hole.getA() + " * " + hole.getB() + " = " + hole.getC());
        assert !(hole.plus()) || (hole.getA() == hole.getB() && hole.getA() == 2);
        assert !(hole.minus());
        assert hole.mult();
        assert !(hole.div());
    }

    public void testDiv() {
        Hole hole = new Hole(3);
        System.out.println(hole.getA() + " / " + hole.getB() + " = " + hole.getC());
        assert !(hole.plus());
        assert !(hole.minus());
        assert !(hole.mult());
        assert hole.div();
    }
}