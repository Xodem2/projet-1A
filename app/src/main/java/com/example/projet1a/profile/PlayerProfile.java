package com.example.projet1a.profile;

import java.util.UUID;

public class PlayerProfile {

    private String id;
    private String nickname;
    private PlayerStatistics stats;

    public PlayerProfile(){
        this.generateID();
        this.stats = new PlayerStatistics(0, 0);
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
