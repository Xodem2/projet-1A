package com.example.projet1a.profile;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.projet1a.database.DataBase;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class PlayerProfile {

    private String id;
    private String nickname;
    private int age;
    private PlayerLevel level;
    private PlayerSuccess success;
    private final PlayerStatistics stats;
    private int isFinished;
    private int numberOfSucceses;

    public PlayerProfile(){
        this.generateID();
        this.level = new PlayerLevel();
        this.stats = new PlayerStatistics(0, 0);
        this.success = new PlayerSuccess();
        this.isFinished = -1;
        this.numberOfSucceses = 0;
    }

    public PlayerProfile(boolean loadingFromDB){
        if(!loadingFromDB) this.generateID();
        this.level = new PlayerLevel();
        this.stats = new PlayerStatistics(0, 0);
        this.success = new PlayerSuccess();
    }

    public void setId(String id){
        this.id = id;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    private void generateID() {
        // generate random unique ID
//        do {
//            this.id = UUID.randomUUID().toString();
//        } while (!DataBase.test_id(this.id));
        this.id = UUID.randomUUID().toString();
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("userdata").child(id).setValue(0);
    }

    public void setIsFinished(int number){ this.isFinished = number;}

    public void incNumberOfSucces(){ this.numberOfSucceses += 1; }

    public void decIsFinished(){ this.isFinished -= 1;}

    public int getIsFinished(){ return this.isFinished;}

    public PlayerLevel getLevel(){
        return this.level;
    }

    public PlayerSuccess getSuccess(){
        return this.success;
    }

    public String getID(){
        return this.id;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getNickname(){
        return this.nickname;
    }

    public PlayerStatistics getStats(){
        return this.stats;
    }

}
