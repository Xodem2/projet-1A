package com.example.projet1a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    int gameId;
    private EditText editTextTextPersonName;
    private TextView roomtext;
    private ImageButton launchButton;

    public final static String id="Room";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //this.launchButton = (ImageButton) findViewById(R.id.launchButton);
        //this.launchButton.setOnClickListener(this);

        this.roomtext = (TextView) findViewById(R.id.room_id);

        MainMultiplayerActivity main = new MainMultiplayerActivity();
        this.roomtext.setText("id de la room: " + main.getIdSession() );
    }

    @Override
    public void onClick(View v) {
        //if (v.getId() == this.launchButton.getId()) this.showFractionPage();
    }

    public void showWaitingRoom(){
        Intent waitingRoomActivityIntent = new Intent(this, RoomActivity.class);
        startActivity(waitingRoomActivityIntent);
    }
}
