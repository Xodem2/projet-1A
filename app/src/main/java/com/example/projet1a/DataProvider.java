package com.example.projet1a;

import com.example.projet1a.database.MyLocalDatabaseHelper;
import com.example.projet1a.profile.PlayerProfile;


/*
according to :
https://stackoverflow.com/questions/36548479/updating-a-parcelable-object-that-was-delivered-from-previous-activity/36549059#36549059
 */

public class DataProvider {

    private PlayerProfile player;
    private MyLocalDatabaseHelper myLocalDatabase;

    private static DataProvider ourInstance = new DataProvider();

    private DataProvider(){

    }

    public static DataProvider getInstance() {
        return ourInstance;
    }

    public void setPlayer(PlayerProfile player){
        this.player = player;
    }

    public PlayerProfile getPlayer(){
        return this.player;
    }

    public void setMyLocalDatabase(MyLocalDatabaseHelper db){
        this.myLocalDatabase = db;
    }

    public MyLocalDatabaseHelper getMyLocalDatabase(){
        return this.myLocalDatabase;
    }

}
