package com.example.projet1a.database;

import com.example.projet1a.DataProvider;
import com.example.projet1a.ProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class DataBase {

    private DataProvider profil;
    FirebaseDatabase database;
    DatabaseReference reference_player;

    public static boolean test_id(String id){
        return FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/").getReference("userdata").child(id) == null;
    }

    public DataBase(){
        this.profil = DataProvider.getInstance();
        this.database = FirebaseDatabase.getInstance("https://einstein-6af82-default-rtdb.europe-west1.firebasedatabase.app/");
        this.reference_player = database.getReference("userdata").child(this.profil.getPlayer().getID());
    }

    public void update_player(int score, String activity){
        reference_player.child(activity).setValue(score);
    }

    public void lecture(){


//        reference.child("score").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.getResult().exists())
//                    score = (int) ((long) task.getResult().getValue());
//                else
//                    score = 0;
//            }
    }
}
