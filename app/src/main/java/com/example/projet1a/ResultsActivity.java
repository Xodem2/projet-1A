package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projet1a.enfant.Operation;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {

    int[] playersId = new int[10];
    String[] playersName = new String[10];
    int[] playersScore = new int[10];

    public final static String id="Results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        playersName[0] = "Test1";
        playersScore[0] = 19;
        ((TextView) findViewById(R.id.textView)).setText(playersName[0]);
        ((TextView) findViewById(R.id.textView2)).setText(String.valueOf(playersScore[0]));
    }

    @Override
    public void onClick(View v) {
    }
}
