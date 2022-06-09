package com.example.projet1a.enfant;

import junit.framework.TestCase;

public class ListIntruderTest extends TestCase {
    public void testAddEven() {
        ListIntruder listIntruder = new ListIntruder();
        listIntruder.clear();
        listIntruder.addEven();
        assert listIntruder.getLastNumber()%2 == 0;
    }

    public void testAddOdd() {
        ListIntruder listIntruder = new ListIntruder();
        listIntruder.clear();
        listIntruder.addOdd();
        assert listIntruder.getLastNumber()%2 != 0;
    }

    public void testGenerateEven1Odd() {
        boolean test;
        ListIntruder listIntruder = new ListIntruder();
        for(int tests = 0; tests < 5; tests++) {
            System.out.println("Test " + tests + " pair.");
            test = false;
            listIntruder.generateEven1Odd();
            for (int i = 0; i < listIntruder.getLength(); i++) {
                System.out.println(i + " : " + listIntruder.getNumber(i));
                if (listIntruder.getNumber(i) % 2 != 0) {
                    System.out.println("intrus");
                    if (test) {
                        test = false;
                        break;
                    }
                    test = true;
                }
            }
            assert test;
        }
    }

    public void testGenerateOdd1Even() {
        boolean test;
        ListIntruder listIntruder = new ListIntruder();
        for(int tests = 0; tests < 5; tests++) {
            System.out.println("Test " + tests + " impair.");
            test = false;
            listIntruder.generateOdd1Even();
            for (int i = 0; i < listIntruder.getLength(); i++) {
                System.out.println(i + " : " + listIntruder.getNumber(i));
                if (listIntruder.getNumber(i) % 2 == 0) {
                    System.out.println("intrus");
                    if (test) {
                        test = false;
                        break;
                    }
                    test = true;
                }
            }
            assert test;
        }
    }
}