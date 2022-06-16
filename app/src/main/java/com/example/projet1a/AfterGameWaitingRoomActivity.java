package com.example.projet1a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projet1a.database.MyFirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AfterGameWaitingRoomActivity extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    private TextView status;
    private TextView player1Nickname;
    private TextView player1Score;
    private TextView player2Nickname;
    private TextView player2Score;
    private ImageButton quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game_waiting_room);

        this.status = (TextView) findViewById(R.id.afterGameWaitingRoomStatusFillId);
        this.status.setText("En attente ...");
        this.player1Nickname = (TextView) findViewById(R.id.afterGameWaitingRoomPlayer1FillId);
        this.player2Nickname = (TextView) findViewById(R.id.afterGameWaitingRoomPlayer2FillId);
        this.player1Score = (TextView) findViewById(R.id.afterGameWaitingRoomP1ScoreFillId);
        this.player2Score = (TextView) findViewById(R.id.afterGameWaitingRoomP2ScoreFillId);
        this.quitButton = (ImageButton) findViewById(R.id.afterGameWaitingRoomQuitButtonId);
        this.quitButton.setOnClickListener(this);
        DataProvider.getInstance().getMyFirebaseHelper().getFinishedGameStatusReference().addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String gameId = DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn();
        if(DataProvider.getInstance().getMyFirebaseHelper().player1Finished(gameId) && DataProvider.getInstance().getMyFirebaseHelper().player2Finished(gameId)) {

            // set value
            this.status.setText("Partie terminée !");
            int p1Score = DataProvider.getInstance().getMyFirebaseHelper().getPlayer1Score(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
            int p2Score = DataProvider.getInstance().getMyFirebaseHelper().getPlayer2Score(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
            this.player1Score.setText(String.valueOf(p1Score));
            this.player2Score.setText(String.valueOf(p2Score));

            // send request
            DataProvider.getInstance().getMyFirebaseHelper().getFinishedGameStatusReference().setValue(1);
            DataProvider.getInstance().getMyFirebaseHelper().getFinishedGameStatusReference().child("1").removeValue();

            // add score to player and save it
            String player1Id = DataProvider.getInstance().getMyFirebaseHelper().getPlayer1Id(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
            String player2Id = DataProvider.getInstance().getMyFirebaseHelper().getPlayer2Id(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
            String playerId = DataProvider.getInstance().getPlayer().getID();
            if(playerId.equals(player1Id)) DataProvider.getInstance().getPlayer().getStats().updateMultiplayerScore(p1Score);
            else if(playerId.equals(player2Id)) DataProvider.getInstance().getPlayer().getStats().updateMultiplayerScore(p2Score);
            DataProvider.getInstance().getMyLocalDatabase().savePlayer(DataProvider.getInstance().getPlayer());
            DataProvider.getInstance().getMyFirebaseHelper().savePlayer(DataProvider.getInstance().getPlayer());

            // set nicknames
            String player1Nickname = DataProvider.getInstance().getMyFirebaseHelper().getPlayer1Nickname(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
            String player2Nickname = DataProvider.getInstance().getMyFirebaseHelper().getPlayer2Nickname(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
            this.player1Nickname.setText(player1Nickname);
            this.player2Nickname.setText(player2Nickname);

            // set win / defeat in status
            if(playerId.equals(player1Id)){
                if(p1Score > p2Score) this.status.setText("Victoire !");
                else if(p1Score < p2Score) this.status.setText("Défaite !");
                else this.status.setText("Egalité !");
            }
            else if(playerId.equals(player2Id)){
                if(p2Score > p1Score) this.status.setText("Victoire !");
                else if(p2Score < p1Score) this.status.setText("Défaite !");
                else this.status.setText("Egalité !");
            }

            // allow player to leave game
            this.quitButton.setOnClickListener(this);

            // delete game in database
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.quitButton.getId()) {
            if (player1Score.getText().toString().equals("p1Score")){
                DataProvider.getInstance().getMyFirebaseHelper().getFinishedGameStatusReference().addValueEventListener(this);
            }
            else {
                this.doQuit();
            }
        }
    }

    private void doQuit() {
        startActivity(new Intent(this, MainActivity.class));
    }
}