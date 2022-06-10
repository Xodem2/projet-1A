package com.example.projet1a.adult;

import com.example.projet1a.list.ListNumbers;

import junit.framework.TestCase;

import java.util.ArrayList;

public class MatrixTest extends TestCase {
    public void testIs() {
        Matrix M = new Matrix(2, 2);
        Matrix N = new Matrix(2, 2);
        M.set(0, 0, 1);
        M.set(0, 1, 2);
        M.set(1, 0, 3);
        M.set(1, 1, 4);
        N.set(0, 0, 1);
        N.set(0, 1, 2);
        N.set(1, 0, 3);
        N.set(1, 1, 4);
        assert M.is(N);
        M.set(0, 0, 2);
        assert !M.is(N);
    }

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
        System.out.print(M.toString());
        System.out.print(N.toString());
        System.out.print(P.toString());
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
        System.out.print(M.toString());
        System.out.print(N.toString());
        System.out.print(P.toString());
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
        System.out.print(M.toString());
        System.out.print(tM.toString());
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
        assert M.detAns() == 0;
    }

    public void testPropDet() {
        Matrix matrix = new Matrix();
        ListNumbers props = matrix.propDet();
        System.out.println("det\n" + matrix.toString() + " = ?\n");
        int correct = matrix.detAns();
        for(int i = 0; i < matrix.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(matrix.det(prop))) || (prop == correct && matrix.det(prop));
        }
    }

    public void testGenerate() {
        Matrix M = new Matrix(3, 2);
        System.out.println(M.toString());
    }

}