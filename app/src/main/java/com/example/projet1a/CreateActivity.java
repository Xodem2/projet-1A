package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet1a.database.DataBase;

import org.w3c.dom.Text;

public class CreateActivity extends AppCompatActivity  implements View.OnClickListener {

    private TextView text;

    private DataBase db;
    private int id_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        DataBase.run();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

//        this.db = new DataBase();

        this.text = (TextView) findViewById(R.id.idParty);
        this.text.setText(String.valueOf(this.id_game));
        ((Button) findViewById(R.id.button7)).setOnClickListener(this);
        this.text.setText("");
//        this.id_game = this.db.create_private_game("Test");
        if (this.id_game!=0) {
            this.text.setText(String.valueOf(this.id_game));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        this.id_game = this.db.create_private_game("Test");
        if (this.id_game!=0) {
            this.text.setText(String.valueOf(this.id_game));
        }
    }


    @Override
    public void onClick(View v) {
//        this.id_game = this.db.create_private_game("Test");
        if (this.id_game!=0) {
            this.text.setText(String.valueOf(this.id_game));
        }
    }
}