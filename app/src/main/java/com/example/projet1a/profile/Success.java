package com.example.projet1a.profile;

public class Success {

    private String id;
    private String title;
    private boolean acquired;

    public Success(String id, String title){
        this.id = id;
        this.title = title;
        this.acquired = false;
    }

    public void acquire(){
        this.acquired = true;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public boolean isAcquired(){
        return this.acquired;
    }
}
