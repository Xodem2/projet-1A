package com.example.projet1a.database;

import androidx.annotation.NonNull;

import com.example.projet1a.DataProvider;
import com.example.projet1a.profile.PlayerProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class DataBaseV2 {
    private PlayerProfile player;
    FirebaseDatabase dataBase;
    DatabaseReference reference_player;
    DatabaseReference game_table;
    int[] id_vecteur;
    public static final String lien="https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/";

    int id_game;

    public DataBaseV2(){
        this.player = DataProvider.getInstance().getPlayer();
        this.dataBase = FirebaseDatabase.getInstance(lien);

//        table des joueurs
        this.reference_player = this.dataBase.getReference("userdata/"+this.player.getID());

//        table des parties
        this.game_table = this.dataBase.getReference("gamedata/");

        this.id_game=1;

        this.game_table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> children = snapshot.getChildren().iterator();
                while (children.hasNext()){
                    DataSnapshot next = children.next();
                    int key = Integer.valueOf(next.getKey());
                    if (id_game<=key){
                        id_game = key+1;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        partie des vecteurs

        this.id_vecteur = new int[1];
        this.game_table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> game = snapshot.getChildren().iterator();
                while (game.hasNext()){
                    DataSnapshot next = game.next();
                    int key = Integer.valueOf(next.getKey());
                    Iterator<DataSnapshot> data = next.getChildren().iterator();
                    while (data.hasNext()){
                        DataSnapshot data_data = data.next();
                        if (data_data.getKey()=="id1"){
                            id_vecteur[0] = key;
                        }
                        else if(data_data.getKey()=="id2"){
                            id_vecteur[0] = 0;
                        }
                    }
                }
                System.out.println(id_vecteur[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        this.game_table.child("0").setValue("go0");
        this.game_table.child("0").setValue("go1");
    }

    public int create_private_game(String name_game){
        if (this.id_vecteur[0]==0) {
            DatabaseReference reference_game = this.game_table.child(String.valueOf(this.id_game));
            reference_game.child("id1").setValue(this.player.getID());
//        reference_game.child("id2").setValue(0);
            reference_game.child("name").setValue(name_game);
            return this.id_game;
        }
        DatabaseReference reference_game = this.game_table.child(String.valueOf(this.id_vecteur[0]));
        reference_game.child("id2").setValue(this.player.getID());
        return this.id_vecteur[0];

    }

    public void rejoindre(String name_game){

    }
}
