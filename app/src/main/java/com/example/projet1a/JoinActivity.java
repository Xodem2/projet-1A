package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    int gameId;
    private EditText editTextTextPersonName;
    private Button joinButton;

    public final static String id="Join";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        this.joinButton = (Button) findViewById(R.id.joinButton);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.joinButton.getId()) this.join(this.editTextTextPersonName.getText().toString());
    }

    public void join(String game) {

    }
}
