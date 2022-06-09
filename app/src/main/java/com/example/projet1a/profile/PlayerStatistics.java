package com.example.projet1a.profile;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerStatistics {

    private int singleplayerScore;
    private int multiplayerScore;
    private int totalScore;

    public PlayerStatistics(int spScore, int mpScore){
        this.singleplayerScore = spScore;
        this.multiplayerScore = mpScore;
        this.updateTotalScore();
    }

    private void updateTotalScore(){
        this.totalScore = this.singleplayerScore + this.multiplayerScore;
    }

    public void updateSingleplayerScore(int scoreToAdd){
        this.singleplayerScore += scoreToAdd;
        this.updateTotalScore();
    }

    public void updateMultiplayerScore(int scoreToAdd){
        this.multiplayerScore += scoreToAdd;
        this.updateTotalScore();
    }

    public int getSingleplayerScore(){
        return this.singleplayerScore;
    }

    public int getMultiplayerScore(){
        return this.multiplayerScore;
    }

    public int getTotalScore(){
        return this.totalScore;
    }

}
