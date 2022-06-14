package com.example.projet1a;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.projet1a.database.DataBase;
import com.google.android.material.textfield.TextInputEditText;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    private Button joinButton;

    public final static String id="Join";

    private DataBase db;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
//        DataBase.run();

//        this.db = new DataBase();

        this.joinButton = (Button) findViewById(R.id.joinButton);
        this.joinButton.setOnClickListener(this);
//        ((AppCompatEditText) findViewById(R.id.idParty)).setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        DataProvider.getInstance().getDataBaseV2().rejoindre(Integer.parseInt(((TextView) findViewById(R.id.idParty)).getText().toString()));
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

//    @Override
//    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//        System.out.println("tape");
//        try {
//            DataProvider.getInstance().getDataBaseV2().rejoindre(Integer.parseInt(((TextView) findViewById(R.id.idParty)).getText().toString()));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            DataProvider.getInstance().getDataBaseV2().rejoindre(0);
//        }
//        return false;
//    }
}
