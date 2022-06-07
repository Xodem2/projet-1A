package com.example.projet1a.point;

import androidx.annotation.NonNull;

public class Point {
    private int score;
    private int sensibility;

    public Point(){
        this.score=0;
        this.sensibility=1;
    }

    public Point(@NonNull Point p){
        this.score=p.getScore();
        this.sensibility=p.getSensibility();
    }

    public int getScore() {
        return this.score;
    }

    public int getSensibility() {
        return this.sensibility;
    }

    public void setSensibility(int sensibility) {
        this.sensibility = sensibility;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incr(int x){
        this.score+=x;
    }

    public void decr(int x){
        this.score-=x;
    }

    public void incr(){
        this.score+=this.sensibility;
    }

    public void decr(){
        this.score-=this.sensibility;
    }
}
