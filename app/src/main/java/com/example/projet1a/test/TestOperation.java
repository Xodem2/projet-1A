package com.example.projet1a.test;
import com.example.projet1a.enfant.Operation;
import java.io.IOException;

public class TestOperation {
    public static void main(String[] args) {
        Operation op = new Operation();
        System.out.println(op.getA() + "+" + op.getB() + " = ");
        System.out.println(op.plus(op.getA() + op.getB()));
    }
}
