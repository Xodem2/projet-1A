package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestGameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_game);

        this.button = (Button) findViewById(R.id.testGameButtonId);
        this.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.button.getId()) this.doPlay();
    }

    private void doPlay() {
        DataProvider.getInstance().getMyFirebaseHelper().updateGame(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
    }
}