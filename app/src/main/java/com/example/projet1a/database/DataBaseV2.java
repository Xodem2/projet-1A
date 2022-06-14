package com.example.projet1a.database;

import androidx.annotation.NonNull;

import com.example.projet1a.DataProvider;
import com.example.projet1a.VectorActivity;
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
    int id_vecteur;
    public static final String lien="https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/";

    int id_game;
    int rejoint;
    String game;

    public DataBaseV2(){
        this.player = DataProvider.getInstance().getPlayer();
        this.dataBase = FirebaseDatabase.getInstance(lien);

//        table des joueurs
        this.reference_player = this.dataBase.getReference("userdata/"+this.player.getID());

//        table des parties
        this.game_table = this.dataBase.getReference("gamedata/");

        this.id_game=1;
        this.id_vecteur = 0;

        this.game_table.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterator<DataSnapshot> children = snapshot.getChildren().iterator();
                while (children.hasNext()){
                    DataSnapshot next = children.next();
                    int key = getGameId(next);
                    if (id_game<=key){
                        id_game = key+1;
                    }
                    if (getGameId(next)==rejoint) {
                        Iterator<DataSnapshot> data_game = next.getChildren().iterator();
                        while (data_game.hasNext()) {
                            DataSnapshot data = data_game.next();
                            if (data.getKey().equals("name")) {
                                game = data.getValue(String.class);
                            }
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        partie des vecteurs

//        this.game_table.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
        this.game_table.child("0").setValue("go0");
        this.game_table.child("0").setValue("go1");
    }

    public int getGameId(DataSnapshot dataSnapshot){
        return Integer.valueOf(dataSnapshot.getKey());
    }

    public int create_private_game(String name_game){
        DatabaseReference reference_game = this.game_table.child(String.valueOf(this.id_game));
        reference_game.child("name").setValue(name_game);
        return this.id_game;

    }

    public void rejoindre(int numbre_game){
        if (numbre_game!=this.rejoint) {
            this.rejoint = numbre_game;
            game = "remise a zero";
        }
        System.out.println(game);
    }
}
