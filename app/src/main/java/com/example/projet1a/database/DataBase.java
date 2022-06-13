package com.example.projet1a.database;

import com.example.projet1a.DataProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DataBase {

    private DataProvider profil;
    FirebaseDatabase dataBase;
    DatabaseReference reference_player;
    DatabaseReference game_table;
    DatabaseReference reference_game;

    public static boolean test_id(String id){
        return FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("userdata").child(id) == null;
    }

    public DataBase(){
        this.profil = DataProvider.getInstance();
        this.dataBase = FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/");
        this.reference_player = dataBase.getReference("userdata").child(this.profil.getPlayer().getID());
        this.game_table = dataBase.getReference("gamedata");
    }

    public void update_player(int score, String activity){
        reference_player.child(activity).setValue(score);
    }

    public void update_player(String name){
        reference_player.child("name").setValue(name);
    }

    public void create_private_game(){
        int id_partie=0;
        while (game_table.child(String.valueOf(id_partie))!=null){
            id_partie+=1;
        }
        this.reference_game = game_table.child(String.valueOf(id_partie));
        this.reference_game.setValue("etat", "debut");
        this.reference_game.setValue("prof", this.profil.getPlayer().getID());
    }

}
