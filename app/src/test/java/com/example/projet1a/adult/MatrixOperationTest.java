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
        M1.print();
        M2.print();
        M1.sum(M2).print();
        op.ansSum().print();
        assert op.sum(M1.sum(M2));
    }

    public void testProd() {
        MatrixOperation op = new MatrixOperation();
        Matrix M1 = op.getM1();
        Matrix M2 = op.getM2();
        M1.print();
        M2.print();
        M1.prod(M2).print();
        op.ansProd().print();
        assert op.prod(M1.prod(M2));
        assert !op.prod(M2.prod(M1));
    }

    public void testPropSum() {
        MatrixOperation op = new MatrixOperation();
        ArrayList<Matrix> props = op.propSum();
        op.getM1().print();
        System.out.println(" + ");
        op.getM2().print();
        System.out.println(" = ? ");
        Matrix correct = op.ansSum();
        for (int i = 0; i < op.getNProps(); i++) {
            Matrix prop = props.get(i);
            prop.print();
            assert (!prop.is(correct) && !(op.sum(prop))) || (prop.is(correct) && op.sum(prop));
        }
    }

    public void testPropProd() {
        MatrixOperation op = new MatrixOperation();
        ArrayList<Matrix> props = op.propProd();
        op.getM1().print();
        System.out.println(" * ");
        op.getM2().print();
        System.out.println(" = ? ");
        Matrix correct = op.ansProd();
        for(int i = 0; i < op.getNProps(); i++) {
            Matrix prop = props.get(i);
            prop.print();
            assert (!prop.is(correct) && !(op.prod(prop))) || (prop.is(correct) && op.prod(prop));
        }
    }
}