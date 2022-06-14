package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projet1a.database.DataBase;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    private Button joinButton;

    public final static String id="Join";

    private DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
//        DataBase.run();

//        this.db = new DataBase();

        this.joinButton = (Button) findViewById(R.id.joinButton);
        this.joinButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.joinButton.getId()) this.join(((TextView) findViewById(R.id.idParty)).getText().toString());
    }

    public void join(String game) {
//        this.db.join_private_game(-1, "");
//        if (this.db.join_private_game(Integer.valueOf(game), "test")){
//            System.out.println(game);
//        }
//        else{
//            System.out.println("non");
//        }
    }
}
