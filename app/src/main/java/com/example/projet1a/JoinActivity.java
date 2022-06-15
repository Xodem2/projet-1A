package com.example.projet1a;

import android.content.Intent;
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

    public void join(String gameId) {
        DataProvider.getInstance().getMyFirebaseHelper().joinGame(gameId);
        DataProvider.getInstance().getMyFirebaseHelper().startGame(gameId);
        String id_game = gameId.split("-")[0];
        DataProvider.getInstance().getPlayer().setIsFinished(10);
        if (id_game.equals(SommeActivity.id)) {
            startActivity(new Intent(this, SommeActivity.class));
        }
        else if (id_game.equals(DivisionActivity.id)) {
            startActivity(new Intent(this, DivisionActivity.class));
        }
        else if (id_game.equals(Equation1Activity.id)) {
            startActivity(new Intent(this, Equation1Activity.class));
        }
        else if (id_game.equals(Equation2Activity.id)) {
            startActivity(new Intent(this, Equation2Activity.class));
        }
        else if (id_game.equals(FractionActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(MatricesActivity.id)) {
            startActivity(new Intent(this, MatricesActivity.class));
        }
        else if (id_game.equals(MoinsActivity.id)) {
            startActivity(new Intent(this, MoinsActivity.class));
        }
        else if (id_game.equals(MultActivity.id)) {
            startActivity(new Intent(this, MultActivity.class));
        }
        else if (id_game.equals(PythagoreActivity.id)) {
            startActivity(new Intent(this, PythagoreActivity.class));
        }
        else if (id_game.equals(SqrActivity.id)) {
            startActivity(new Intent(this, SqrActivity.class));
        }
        else if (id_game.equals(ThalesActivity.id)) {
            startActivity(new Intent(this, ThalesActivity.class));
        }
        else if (id_game.equals(TrigoActivity.id)) {
            startActivity(new Intent(this, TrigoActivity.class));
        }
        else if (id_game.equals(VectorActivity.id)) {
            startActivity(new Intent(this, VectorActivity.class));
        }
//        startActivity(new Intent(this, TestGameActivity.class));
    }
}
