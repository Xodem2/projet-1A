package com.example.projet1a.profile;

public class PlayerLevel {

    private final static double INCREASE_RATE = 1.5;
    private final static int XP_LEVEL_1 = 100;

    private int level;
    private int neededXp;
    private int currentXp;

    public PlayerLevel() {
        this.level = 0;
        this.neededXp = XP_LEVEL_1;
        this.currentXp = 0;
    }

    private void increaseLevel(){
        this.level++;
    }

    public int getLevel(){
        return this.level;
    }

    public void update(int score){
        this.currentXp = score;
        if(this.currentXp > this.neededXp) {
            this.increaseLevel();
            this.neededXp = (int) INCREASE_RATE * this.neededXp;
        }
    }

}
