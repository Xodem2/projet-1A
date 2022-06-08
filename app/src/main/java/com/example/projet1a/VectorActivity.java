package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet1a.adult.VectorQuestion;

import java.util.ArrayList;

public class VectorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView vector1TW;
    private TextView vector2TW;
    private TextView answerTW;
    private Button vectorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);

        this.vector1TW = (TextView) findViewById(R.id.vector1ID);
        this.vector2TW = (TextView) findViewById(R.id.vector2ID);
        this.answerTW = (TextView) findViewById(R.id.vectorAnswerID);
        this.vectorButton = (Button) findViewById(R.id.vectorButton);

        this.vectorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.vectorButton.getId()) this.doVectorButton();
    }

    private void doVectorButton() {
        VectorQuestion vq = new VectorQuestion();
        ArrayList<Integer>[] vectors = vq.getVectors();
        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());

        vq.scalarProduct();
        this.answerTW.setText(vq.getResult().toString());
    }
}