package com.example.projet1a;

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

    public final static String id="Room";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        this.roomtext = (TextView) findViewById(R.id.room_id);

        MainMultiplayerActivity main = new MainMultiplayerActivity();
        this.roomtext.setText("id de la room: " + main.getIdSession() );
    }

    @Override
    public void onClick(View v) {
    }
}
