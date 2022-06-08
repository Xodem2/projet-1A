package com.example.projet1a.adult;

import junit.framework.TestCase;

public class MatrixTest extends TestCase {
    public void testSum() {
        Matrix M = new Matrix(2, 2);
        M.set(0, 0, 1);
        M.set(0, 1, 2);
        M.set(1, 0, 3);
        M.set(1, 1, 4);
        Matrix N = new Matrix(2, 2);
        N.set(0, 0, 7);
        N.set(0, 1, 8);
        N.set(1, 0, 9);
        N.set(1, 1, -1);
        Matrix P = M.sum(N);
        System.out.println(P.get(0, 0) + " " + P.get(0, 1));
        System.out.println(P.get(1, 0) + " " + P.get(1, 1));
        assert P.get(0, 0) == 8 && P.get(0, 1) == 10 && P.get(1, 0) == 12 && P.get(1, 1) == 3;
    }

    public void testProd() {
        Matrix M = new Matrix(2, 3);
        M.set(0, 0, 1);
        M.set(0, 1, 2);
        M.set(0, 2, 3);
        M.set(1, 0, 4);
        M.set(1, 1, 5);
        M.set(1, 2, 6);
        Matrix N = new Matrix(3, 2);
        N.set(0, 0, 7);
        N.set(0, 1, 8);
        N.set(1, 0, 9);
        N.set(1, 1, -1);
        N.set(2, 0, -2);
        N.set(2, 1, -3);
        Matrix P = M.prod(N);
        System.out.println(P.get(0, 0) + " " + P.get(0, 1));
        System.out.println(P.get(1, 0) + " " + P.get(1, 1));
        assert P.get(0, 0) == 19 && P.get(0, 1) == -3 && P.get(1, 0) == 61 && P.get(1, 1) == 9;
    }

    public void testT() {
        Matrix M = new Matrix(2, 3);
        M.set(0, 0, 1);
        M.set(0, 1, 2);
        M.set(0, 2, 3);
        M.set(1, 0, 4);
        M.set(1, 1, 5);
        M.set(1, 2, 6);
        Matrix tM = M.t();
        System.out.println(tM.get(0, 0) + " " + tM.get(0, 1));
        System.out.println(tM.get(1, 0) + " " + tM.get(1, 1));
        System.out.println(tM.get(2, 0) + " " + tM.get(2, 1));
        assert tM.get(0, 0) == 1 && tM.get(0, 1) == 4 && tM.get(1, 0) == 2 && tM.get(1, 1) == 5 && tM.get(2, 0) == 3 && tM.get(2, 1) == 6;
    }

    public void testDet() {
        Matrix M = new Matrix(3, 3);
        M.set(0, 0, 1);
        M.set(0, 1, 2);
        M.set(0, 2, 3);
        M.set(1, 0, 4);
        M.set(1, 1, 5);
        M.set(1, 2, 6);
        M.set(2, 0, 7);
        M.set(2, 1, 8);
        M.set(2, 2, 9);
        assert M.det() == 0;
    }

    public void testGenerate() {
        Matrix M = new Matrix(3, 2);
        System.out.println(M.get(0, 0) + " " + M.get(0, 1));
        System.out.println(M.get(1, 0) + " " + M.get(1, 1));
        System.out.println(M.get(2, 0) + " " + M.get(2, 1));
    }
}