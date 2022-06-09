package com.example.projet1a.profile;

import android.os.Parcel;
import android.os.Parcelable;

public class PlayerStatistics implements Parcelable {

    private int singleplayerScore;
    private int multiplayerScore;
    private int totalScore;

    public PlayerStatistics(int spScore, int mpScore){
        this.singleplayerScore = spScore;
        this.multiplayerScore = mpScore;
        this.updateTotalScore();
    }

    protected PlayerStatistics(Parcel in) {
        singleplayerScore = in.readInt();
        multiplayerScore = in.readInt();
        totalScore = in.readInt();
    }

    public static final Creator<PlayerStatistics> CREATOR = new Creator<PlayerStatistics>() {
        @Override
        public PlayerStatistics createFromParcel(Parcel in) {
            return new PlayerStatistics(in);
        }

        @Override
        public PlayerStatistics[] newArray(int size) {
            return new PlayerStatistics[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(singleplayerScore);
        dest.writeInt(multiplayerScore);
        dest.writeInt(totalScore);
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
