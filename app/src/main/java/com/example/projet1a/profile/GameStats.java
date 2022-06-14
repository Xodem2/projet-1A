package com.example.projet1a.profile;

public class GameStats {

    private String gameId;
    private int totalCorrects;
    private int totalAnswered;
    private int correctsInARow;
    private int maxCorrectsInARow;

    public GameStats(String gameId){
        this.gameId = gameId;
        this.totalCorrects = 0;
        this.totalAnswered = 0;
        this.correctsInARow = 0;
        this.maxCorrectsInARow = 0;
    }

    public GameStats(String gameId, int totalCorrects, int totalAnswered, int correctsInARow){
        this(gameId);
        this.totalCorrects = totalCorrects;
        this.totalAnswered = totalAnswered;
        this.correctsInARow = correctsInARow;
    }

    public GameStats(String gameId, int totalCorrects, int totalAnswered, int correctsInARow, int maxCorrectsInARow){
        this(gameId, totalCorrects, totalAnswered, correctsInARow);
        this.maxCorrectsInARow = maxCorrectsInARow;
    }

    public String getId(){
        return this.gameId;
    }

    public void incrTotalCorrects(){
        this.totalCorrects++;
    }

    public void incrTotalAnswered(){
        this.totalAnswered++;
    }

    public void incrCorrectsInARow(){
        this.correctsInARow++;
    }

    public void resetCorrectsInARow(){
        this.correctsInARow = 0;
    }

    public int getTotalCorrects() {
        return totalCorrects;
    }

    public int getTotalAnswered() {
        return totalAnswered;
    }

    public int getCorrectsInARow() {
        return correctsInARow;
    }

    public int getMaxCorrectsInARow(){
        return this.maxCorrectsInARow;
    }

    public void update(boolean correct){
        if(correct){
            this.incrCorrectsInARow();
            this.incrTotalCorrects();
            if(this.correctsInARow > this.maxCorrectsInARow) this.maxCorrectsInARow = this.correctsInARow;
        }
        else {
            this.resetCorrectsInARow();
        }
        this.incrTotalAnswered();
    }
}
