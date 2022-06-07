package com.example.projet1a.profile;

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

    public void updateSingleplayerScore(int spScore){
        this.singleplayerScore += spScore;
    }

    public void updateMultiplayerScore(int mpScore){
        this.multiplayerScore += mpScore;
    }
}
