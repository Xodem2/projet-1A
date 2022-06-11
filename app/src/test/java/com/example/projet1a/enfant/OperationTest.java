package com.example.projet1a.enfant;

import com.example.projet1a.list.ListNumbers;

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

    public void testAnsPlus() {
        Operation op = new Operation();
        assert op.getA()+op.getB() == op.ansPlus();
    }

    public void testAnsMinus() {
        Operation op = new Operation();
        assert op.getA()-op.getB() == op.ansMinus();
    }

    public void testAnsMult() {
        Operation op = new Operation();
        assert op.getA()*op.getB() == op.ansMult();
    }

    public void testAnsDiv() {
        Operation op = new Operation();
        assert op.getA()/op.getB() == op.ansDiv();
    }

    public void testPlus() {
        Operation op = new Operation();
        System.out.println(op.getA() + " + " + op.getB() + " = " + op.ansPlus());
        assert op.plus(op.ansPlus());
        assert !(op.plus(op.ansPlus()+1));
    }

    public void testMinus() {
        Operation op = new Operation();
        System.out.println(op.getA() + " - " + op.getB() + " = " + op.ansMinus());
        assert op.minus(op.ansMinus());
        assert !(op.minus(op.ansMinus()+1));
    }

    public void testMult() {
        Operation op = new Operation();
        System.out.println(op.getA() + " * " + op.getB() + " = " + op.ansMult());
        assert op.mult(op.ansMult());
        assert !(op.mult(op.ansMult()+1));
    }

    public void testDiv() {
        Operation op = new Operation();
        System.out.println(op.getA() + " / " + op.getB() + " = " + op.ansDiv());
        assert op.div(op.ansDiv());
        assert !(op.mult(op.ansDiv()+1));
    }

    public void testPropPlus() {
        Operation op = new Operation();
        ListNumbers props = op.propPlus();
        System.out.println(op.getA() + " + " + op.getB() + " = ?");
        int correct = op.ansPlus();
        for(int i = 0; i < op.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(op.plus(prop))) || prop == correct && op.plus(prop);
        }
    }

    public void testPropMinus() {
        Operation op = new Operation();
        ListNumbers props = op.propMinus();
        System.out.println(op.getA() + " - " + op.getB() + " = ?");
        int correct = op.ansMinus();
        for(int i = 0; i < op.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(op.minus(prop))) || prop == correct && op.minus(prop);
        }
    }

    public void testPropMult() {
        Operation op = new Operation();
        ListNumbers props = op.propMult();
        System.out.println(op.getA() + " * " + op.getB() + " = ?");
        int correct = op.ansMult();
        for(int i = 0; i < op.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(op.mult(prop))) || prop == correct && op.mult(prop);
        }
    }

    public void testPropDiv() {
        Operation op = new Operation();
        ListNumbers props = op.propDiv();
        System.out.println(op.getA() + " / " + op.getB() + " = ?");
        int correct = op.ansDiv();
        for(int i = 0; i < op.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(op.div(prop))) || prop == correct && op.div(prop);
        }
    }

    public void test() {
        Operation op = new Operation(31, 18);
        ListNumbers props = op.propMinus();
        System.out.println(op.getA() + " - " + op.getB() + " = ?");
        int correct = op.ansMinus();
        for(int i = 0; i < op.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(op.minus(prop))) || prop == correct && op.minus(prop);
        }
    }
}