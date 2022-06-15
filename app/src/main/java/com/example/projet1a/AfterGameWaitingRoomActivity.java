package com.example.projet1a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projet1a.database.MyFirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AfterGameWaitingRoomActivity extends AppCompatActivity implements ValueEventListener {

    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game_waiting_room);

        this.status = (TextView) findViewById(R.id.afterGameWaitingRoomStatusFillId);
        this.status.setText("Waiting for opponent to finish.");

        DataProvider.getInstance().getMyFirebaseHelper().getFinishedGameStatusReference().addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(DataProvider.getInstance().getMyFirebaseHelper().playerFinished(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn())
        && DataProvider.getInstance().getMyFirebaseHelper().opponentFinished(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn()))
            this.status.setText("Game is finished !");
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}