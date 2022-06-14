package com.example.projet1a.database;

import com.example.projet1a.DataProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class DataBase {

    private DataProvider profil;
    FirebaseDatabase dataBase;
    DatabaseReference reference_player;
    DatabaseReference game_table;
    DatabaseReference reference_game;
    static int id_game=0;
    static int place=0;
    static ArrayList<Integer> game_on = new ArrayList<>();
    private static boolean est_libre=false;

    public static void run(){
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("gamedata").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                String key;
                while (it.hasNext()){
                    key = it.next().getKey();
                    if (Integer.valueOf(key)>=id_game){
                        id_game = Integer.valueOf(key)+1;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("gamedata").child("0").setValue("go1");
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("gamedata").child("0").setValue("go2");
    }

    public static boolean test_id(String id){
        DataBase.est_libre = true;
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("userdata").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                String key;
                while (it.hasNext()){
                    key = it.next().getKey();
                    if (key==id){
                        DataBase.est_libre = false;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        return DataBase.est_libre;
    }

    public DataBase(){
        this.profil = DataProvider.getInstance();
        this.dataBase = FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/");
        this.reference_player = dataBase.getReference("userdata").child(this.profil.getPlayer().getID());
        this.game_table = dataBase.getReference("gamedata");
        this.id_game = 0;
    }

    public void update_player(int score, String activity){
        reference_player.child(activity).setValue(score);
    }

    public void update_player(String name){
        reference_player.child("name").setValue(name);
    }

    public int create_private_game(String name_game){
        DataBase.run();
        this.reference_game = this.game_table.child(String.valueOf(id_game));
        this.reference_game.child("etat").setValue("debut");
        this.reference_game.child("prof").setValue(this.profil.getPlayer().getID());
        this.reference_game.child("game").setValue(name_game);
        return id_game;
    }

    public boolean join_private_game(int id_game, String id_player){
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("gamedata").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.child(String.valueOf(id_game)).getChildren().iterator();
                String key;
                while (it.hasNext()){
                    key = it.next().getKey();
                    try {
                        if (Integer.valueOf(key) >= DataBase.place) {
                            DataBase.place = Integer.valueOf(key) + 1;
                        }
                    }
                    catch (Exception e){
                        System.out.println("new pb");
                        System.out.println(key);
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("gamedata").child(String.valueOf(id_game)).setValue("go1");
        FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("gamedata").child(String.valueOf(id_game)).setValue("go2");

        if (DataBase.place!=0) {
            this.reference_game = this.game_table.child(String.valueOf(id_game));
            this.reference_game.child("prof").setValue(this.profil.getPlayer().getID());
            this.reference_game.child(String.valueOf(DataBase.place)).setValue(id_player);
            DataBase.place=0;
            return true;
        }
        return false;
    }
}
