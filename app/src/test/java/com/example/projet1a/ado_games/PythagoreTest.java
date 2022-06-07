package com.example.projet1a.ado_games;

import junit.framework.TestCase;

public class PythagoreTest extends TestCase {

    public void testSolvePythagore() {
        Pythagore py = new Pythagore();
        System.out.println(py.getA());
        System.out.println(py.getB());
        System.out.println(py.solvePythagore());
        assertEquals((int)py.solvePythagore(), (int)Math.sqrt(py.getA()*py.getA() + py.getB()*py.getB()));
    }
}