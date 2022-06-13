package com.example.projet1a.profile;

public class PlayerLevel {

    private final static double INCREASE_RATE = 2;
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

    public void setLevel(int lvl){
        this.level = lvl;
    }

    public void update(int score){
        if(score > this.currentXp) this.currentXp = score;
        if(this.currentXp > this.neededXp) {
            this.increaseLevel();
            this.neededXp = (int) INCREASE_RATE * this.neededXp;
            this.currentXp = 0;
        }
    }

    public int getCurrentXp(){
        return this.currentXp;
    }

    public void setCurrentXp(int currentXp){
        this.currentXp = currentXp;
    }

    public int getNeededXp(){
        return this.neededXp;
    }

    public void setNeededXp(int neededXp){
        this.neededXp = neededXp;
    }

}
