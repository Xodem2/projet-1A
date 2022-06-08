package com.example.projet1a.profile;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class PlayerProfile implements Parcelable {

    private String id;
    private String nickname;
    private int age;
    private final PlayerStatistics stats;

    public PlayerProfile(){
        this.generateID();
        this.stats = new PlayerStatistics(0, 0);
    }

    protected PlayerProfile(Parcel in) {
        id = in.readString();
        nickname = in.readString();
        age = in.readInt();
        stats = in.readParcelable(PlayerStatistics.class.getClassLoader());
    }

    public static final Creator<PlayerProfile> CREATOR = new Creator<PlayerProfile>() {
        @Override
        public PlayerProfile createFromParcel(Parcel in) {
            return new PlayerProfile(in);
        }

        @Override
        public PlayerProfile[] newArray(int size) {
            return new PlayerProfile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nickname);
        dest.writeInt(age);
        dest.writeParcelable(stats, flags);
    }

    private void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    private void generateID() {
        // generate random unique ID
        this.id = UUID.randomUUID().toString();
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
