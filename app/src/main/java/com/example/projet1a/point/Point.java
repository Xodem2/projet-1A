package com.example.projet1a.point;

public class Point {
    private int score;

    public Point(){
        score=0;
    }

    public Point(Point p){
        score=p.getScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incr(int x){
        score+=x;
    }

    public void decr(int x){
        score-=x;
    }
}
