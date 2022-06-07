package com.example.projet1a.enfant;

import junit.framework.TestCase;

import java.util.Random;

public class OperationTest extends TestCase {

    public OperationTest() {
    }

    public void testGetA() {
    }

    public void testGetB() {
    }

    public void testGenerate() {
        Operation op = new Operation();
        int a = op.getA();
        int b = op.getB();
        op.generate();
        assert op.getA() != a && op.getB() != b;
    }

    public void testPlus() {
        Operation op = new Operation();
        System.out.println(op.getA() + " + " + op.getB() + " = " + (op.getA()+op.getB()));
        assert op.plus(op.getA()+op.getB());
        assert !(op.plus(op.getA()+op.getB()+1));
    }

    public void testMinus() {
        Operation op = new Operation();
        System.out.println(op.getA() + " - " + op.getB() + " = " + (op.getA()-op.getB()));
        assert op.minus(op.getA()-op.getB());
        assert !(op.minus(op.getA()-op.getB()+1));
    }

    public void testMult() {
        Operation op = new Operation();
        System.out.println(op.getA() + " * " + op.getB() + " = " + (op.getA()*op.getB()));
        assert op.mult(op.getA()*op.getB());
        assert !(op.mult(op.getA()*op.getB()+1));
    }

    public void testDiv() {
        Operation op = new Operation();
        System.out.println(op.getA() + " / " + op.getB() + " = " + (op.getA()/op.getB()));
        assert op.div(op.getA()/op.getB());
        assert !(op.mult(op.getA()/op.getB()+1));
    }
}