package com.example.projet1a.ado_games;

import com.example.projet1a.adult.Trigo;
import com.example.projet1a.enfant.Operation;
import com.example.projet1a.list.ListNumbers;

import junit.framework.TestCase;

public class Square_SqrtTest extends TestCase {

    public void testSolveSqrt() {
        Square_Sqrt sqr_sqrt = new Square_Sqrt();
        sqr_sqrt.square();
        assert sqr_sqrt.solveSqrt() == Math.sqrt(sqr_sqrt.getN());
    }

    public void testSolveSquare() {
        Square_Sqrt sqr_sqrt = new Square_Sqrt();
        sqr_sqrt.sqrt();
        assert sqr_sqrt.solveSquare() == Math.sqrt(sqr_sqrt.getM());
    }

    public void testPropSqr() {
        Square_Sqrt sqr_sqrt = new Square_Sqrt();
        sqr_sqrt.square();
        ListNumbers props = sqr_sqrt.propSqr();
        System.out.println(sqr_sqrt.getM() + "^2 = ?");
        int correct = sqr_sqrt.solveSquare();
        for(int i = 0; i < sqr_sqrt.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(sqr_sqrt.boolSqr(prop))) || prop == correct && sqr_sqrt.boolSqr(prop);
        }
    }

    public void testPropSqrt() {
        Square_Sqrt sqr_sqrt = new Square_Sqrt();
        sqr_sqrt.sqrt();
        ListNumbers props = sqr_sqrt.propSqrt();
        System.out.println("√" + sqr_sqrt.getN() + " = ?");
        int correct = sqr_sqrt.solveSqrt();
        for(int i = 0; i < sqr_sqrt.getNProps(); i++) {
            int prop = props.getNumber(i);
            System.out.println(prop);
            assert (prop != correct && !(sqr_sqrt.boolSqrt(prop))) || prop == correct && sqr_sqrt.boolSqrt(prop);
        }
    }

    public void testFct() {
        Square_Sqrt sqr_sqrt = new Square_Sqrt();
        sqr_sqrt.square();
        int correct = sqr_sqrt.solveSquare();
        assert sqr_sqrt.fct(correct);
        sqr_sqrt.sqrt();
        correct = sqr_sqrt.solveSqrt();
        assert sqr_sqrt.fct(correct);
    }
}