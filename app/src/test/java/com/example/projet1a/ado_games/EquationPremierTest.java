package com.example.projet1a.ado_games;

import junit.framework.TestCase;

public class EquationPremierTest extends TestCase {

    public void testSolveEquation() {
        EquationPremier eq = new EquationPremier();
        assertEquals( - eq.getB()/eq.getA(), (int)eq.solveEquation());
    }

    public void testRandomNumber() {
    }
}