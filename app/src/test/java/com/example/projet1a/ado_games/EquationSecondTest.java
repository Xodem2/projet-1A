package com.example.projet1a.ado_games;

import junit.framework.TestCase;

public class EquationSecondTest extends TestCase {

    public void testEqST() {
        EquationSecond eq = new EquationSecond();
        assertEquals(eq.getB()*eq.getB() - 4*eq.getA()*eq.getC(), eq.delta());
        System.out.println(eq.calc_x1());
        System.out.println(eq.calc_x2());
    }
}