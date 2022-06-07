package com.example.projet1a.ado_games;

import junit.framework.TestCase;

public class SuiteTest extends TestCase {

    public void testAdd() {
    }

    public void testGenerateTerm() {
    }

    public void testGenerate() {
        Suite suite = new Suite();
        for (int i = 0; i < suite.getLength(); i++) {
            int value = suite.getA()*i*i+suite.getB()*i+suite.getC();
            System.out.println(i + " : " + suite.getNumber(i));
            assert value == suite.getNumber(i);
        }
    }

    public void testInput() {
        Suite suite = new Suite();
        for (int i = 0; i < suite.getLength(); i++) {
            System.out.println(i + " : " + suite.getNumber(i));
        }
        System.out.println("Réponse : ");
        for (int i = suite.getLength(); i < suite.getLengthMax(); i++) {
            int value = suite.getA()*i*i+suite.getB()*i+suite.getC();
            System.out.println(i + " : " + value);
            assert suite.input(value);
        }
    }
}