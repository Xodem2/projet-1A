package com.example.projet1a.adult;

import com.example.projet1a.enfant.Operation;
import com.example.projet1a.list.ListNumbers;

import junit.framework.TestCase;

import java.util.ArrayList;

public class MatrixOperationTest extends TestCase {

    public void testMatrixOperation() {
    }

    public void testGenerate() {
    }

    public void testAnsSum() {
        MatrixOperation op = new MatrixOperation();
        Matrix M1 = op.getM1();
        Matrix M2 = op.getM2();
        assert op.ansSum().is(M1.sum(M2));
    }

    public void testAnsProd() {
        MatrixOperation op = new MatrixOperation();
        Matrix M1 = op.getM1();
        Matrix M2 = op.getM2();
        assert op.ansProd().is(M1.prod(M2));
    }

    public void testSum() {
        MatrixOperation op = new MatrixOperation();
        Matrix M1 = op.getM1();
        Matrix M2 = op.getM2();
        System.out.print(M1.toString());
        System.out.print(M2.toString());
        System.out.print(M1.sum(M2).toString());
        System.out.print(op.ansSum().toString());
        assert op.sum(M1.sum(M2));
    }

    public void testProd() {
        MatrixOperation op = new MatrixOperation();
        Matrix M1 = op.getM1();
        Matrix M2 = op.getM2();
        System.out.print(M1.toString());
        System.out.print(M2.toString());
        System.out.print(M1.sum(M2).toString());
        System.out.print(op.ansSum().toString());
        assert op.prod(M1.prod(M2));
        assert !op.prod(M2.prod(M1));
    }

    public void testPropSum() {
        for(int t = 0; t < 100; t++) {
            MatrixOperation op = new MatrixOperation();
            ArrayList<Matrix> props = op.propSum();
            System.out.print(op.getM1().toString());
            System.out.println(" + ");
            System.out.print(op.getM2().toString());
            System.out.println(" = ? ");
            Matrix correct = op.ansSum();
            for (int i = 0; i < op.getNProps(); i++) {
                Matrix prop = props.get(i);
                System.out.print(prop.toString());
                assert (!prop.is(correct) && !(op.sum(prop))) || (prop.is(correct) && op.sum(prop));
            }
        }
    }

    public void testPropMinus() {
        for(int t = 0; t < 100; t++) {
            MatrixOperation op = new MatrixOperation();
            ArrayList<Matrix> props = op.propMinus();
            System.out.print(op.getM1().toString());
            System.out.println(" - ");
            System.out.print(op.getM2().toString());
            System.out.println(" = ? ");
            Matrix correct = op.ansMinus();
            for (int i = 0; i < op.getNProps(); i++) {
                Matrix prop = props.get(i);
                System.out.print(prop.toString());
                assert (!prop.is(correct) && !(op.minus(prop))) || (prop.is(correct) && op.minus(prop));
            }
        }
    }

    public void testPropProd() {
        for(int t = 0; t < 100; t++) {
            MatrixOperation op = new MatrixOperation();
            ArrayList<Matrix> props = op.propProd();
            System.out.print(op.getM1().toString());
            System.out.println(" * ");
            System.out.print(op.getM2().toString());
            System.out.println(" = ? ");
            Matrix correct = op.ansProd();
            for (int i = 0; i < op.getNProps(); i++) {
                Matrix prop = props.get(i);
                System.out.print(prop.toString());
                assert (!prop.is(correct) && !(op.prod(prop))) || (prop.is(correct) && op.prod(prop));
            }
        }
    }
}