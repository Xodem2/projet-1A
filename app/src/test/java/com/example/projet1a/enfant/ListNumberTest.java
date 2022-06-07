package com.example.projet1a.enfant;

import junit.framework.TestCase;

public class ListNumberTest extends TestCase {

    public void testListNumber() {
    }

    public void testAdd() {
        for(int tests = 0; tests < 5; tests++) {
            int n = tests;
            ListNumber listNumber = new ListNumber();
            listNumber.add(n);
            assert listNumber.getLastNumber() == n;
        }
    }

    public void testAddEven() {
        ListNumber listNumber = new ListNumber();
        listNumber.addEven();
        assert listNumber.getLastNumber()%2 == 0;
    }

    public void testAddOdd() {
        ListNumber listNumber = new ListNumber();
        listNumber.addOdd();
        assert listNumber.getLastNumber()%2 != 0;
    }

    public void testGenerateEven1Odd() {
        boolean test;
        ListNumber listNumber = new ListNumber();
        for(int tests = 0; tests < 5; tests++) {
            System.out.println("Test " + tests + " pair.");
            test = false;
            listNumber.generateEven1Odd();
            for (int i = 0; i < listNumber.getLength(); i++) {
                System.out.println(listNumber.getNumber(i));
                if (listNumber.getNumber(i) % 2 != 0) {
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
        ListNumber listNumber = new ListNumber();
        for(int tests = 0; tests < 5; tests++) {
            System.out.println("Test " + tests + " impair.");
            test = false;
            listNumber.generateOdd1Even();
            for (int i = 0; i < listNumber.getLength(); i++) {
                System.out.println(listNumber.getNumber(i));
                if (listNumber.getNumber(i) % 2 == 0) {
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

    public void testGetLength() {
        ListNumber listNumber = new ListNumber();
        listNumber.add(2);
        assert listNumber.getLength() == 1;
    }

    public void testGetNumber() {
        int n = 2;
        ListNumber listNumber = new ListNumber();
        listNumber.add(n);
        assert listNumber.getNumber(0) == n;
    }

    public void testGetLastNumber() {
        for(int tests = 0; tests < 5; tests++) {
            int n = tests;
            ListNumber listNumber = new ListNumber();
            listNumber.add(n);
            assert listNumber.getLastNumber() == n;
        }
    }

}