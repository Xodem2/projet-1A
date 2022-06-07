package com.example.projet1a.point;

import junit.framework.TestCase;

public class PointTest extends TestCase {

    public void testGetScore() {
        Point p = new Point();
        assert p.getScore()==0;
        for (int i=-10; i<11; i++){
            p.setScore(i);
            assert p.getScore()==i;
        }
    }

    public void testGetSensibility() {
        Point p = new Point();
        assert p.getSensibility()==1;
        for (int i=-10; i<11; i++){
            p.setSensibility(i);
            assert p.getSensibility()==i;
        }
    }

    public void testSetSensibility() {
        Point p = new Point();
        assert p.getSensibility()==1;
        for (int i=-10; i<11; i++){
            p.setSensibility(i);
            assert p.getSensibility()==i;
        }
    }

    public void testSetScore() {
        Point p = new Point();
        assert p.getScore()==0;
        for (int i=-10; i<11; i++){
            p.setScore(i);
            assert p.getScore()==i;
        }
    }

    public void testIncr() {
        Point p = new Point();
        int j=0;
        for (int i=0; i<21; i++){
            p.incr();
            j+=p.getSensibility();
            assert p.getScore()==j;
        }
    }

    public void testDecr() {
        Point p = new Point();
        int j=0;
        for (int i=0; i<21; i++){
            p.decr();
            j-=p.getSensibility();
            assert p.getScore()==j;
        }
    }

    public void testTestIncr() {
        Point p = new Point();
        int j=0;
        for (int i=0; i<21; i++){
            p.incr(i);
            j+=i;
            assert p.getScore()==j;
        }
    }

    public void testTestDecr() {
        Point p = new Point();
        int j=0;
        for (int i=0; i<21; i++){
            p.decr(i);
            j-=i;
            assert p.getScore()==j;
        }
    }
}