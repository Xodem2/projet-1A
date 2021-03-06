package com.example.projet1a;

import androidx.arch.core.executor.DefaultTaskExecutor;

import com.example.projet1a.database.DataBaseV2;
import com.example.projet1a.database.MyFirebaseHelper;
import com.example.projet1a.database.MyLocalDatabaseHelper;
import com.example.projet1a.profile.PlayerProfile;


/*
according to :
https://stackoverflow.com/questions/36548479/updating-a-parcelable-object-that-was-delivered-from-previous-activity/36549059#36549059
 */

public class DataProvider {

    private PlayerProfile player;
    private MyLocalDatabaseHelper myLocalDatabase;
    private MyFirebaseHelper myFirebaseHelper;
    private DataBaseV2 dataBaseV2;


    private static DataProvider ourInstance = new DataProvider();

    private DataProvider(){

    }

    public static DataProvider getInstance() {
        return ourInstance;
    }

    public static void renew(){
        DataProvider.ourInstance = new DataProvider();
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

    public DataBaseV2 getDataBaseV2() {
        return dataBaseV2;
    }

    public void setDataBaseV2(DataBaseV2 dataBaseV2) {
        this.dataBaseV2 = dataBaseV2;
    }

    public void setMyFirebaseHelper(MyFirebaseHelper firebaseHelper){
        this.myFirebaseHelper = firebaseHelper;
    }

    public MyFirebaseHelper getMyFirebaseHelper(){
        return this.myFirebaseHelper;
    }

}
