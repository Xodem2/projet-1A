package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet1a.adult.VectorQuestion;

import java.util.ArrayList;
import java.util.Random;

public class VectorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int OPERATION_ADDITION = 1;
    private static final int OPERATION_SUBSTRACTION = 2;
    private static final int OPERATION_SCALAR_PRODUT = 3;

    private Button choice1Button;
    private Button choice2Button;
    private Button choice3Button;

    private TextView vector1TW;
    private TextView vector2TW;
    private TextView operationTW;

    private VectorQuestion vq;
    private Object answer;
    private Object[] propositions;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector);

        this.vector1TW = (TextView) findViewById(R.id.vector1ID);
        this.vector2TW = (TextView) findViewById(R.id.vector2ID2);
        this.operationTW = (TextView) findViewById(R.id.vectorOperationID);

        this.choice1Button = (Button) findViewById(R.id.choice1ID);
        this.choice2Button = (Button) findViewById(R.id.choice2ID);
        this.choice3Button = (Button) findViewById(R.id.choice3ID);

        this.choice1Button.setOnClickListener(this);
        this.choice2Button.setOnClickListener(this);
        this.choice3Button.setOnClickListener(this);

        this.vq = new VectorQuestion();
        this.random = new Random();

        this.generateQuestion();
    }

    @Override
    public void onClick(View v) {
        // TODO : on click
        this.vq = new VectorQuestion();
        this.generateQuestion();
    }

    private int chooseRandomQuestion(){
        int[] choices = {OPERATION_ADDITION, OPERATION_SUBSTRACTION, OPERATION_SCALAR_PRODUT};
        int choice = this.random.nextInt(choices.length);
        return choices[choice];
    }

    private void generateQuestion(){
        int operation = this.chooseRandomQuestion();

        switch(operation){
            case OPERATION_ADDITION:
                this.doAddition();
                break;
            case OPERATION_SUBSTRACTION:
                this.doSubstraction();
                break;
            case OPERATION_SCALAR_PRODUT:
                this.doScalarProduct();
                break;
            default:
                // should never go there
                break;
        }
    }

    private void doAddition(){
        this.vq.addition();
        this.answer = this.vq.getResult();
        ArrayList<Integer>[] vectors = this.vq.getVectors();

        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());
        this.operationTW.setText("+");

        this.generateVectorsPropositions();
    }

    private void doSubstraction(){
        this.vq.substraction();
        this.answer = vq.getResult();
        ArrayList<Integer>[] vectors = this.vq.getVectors();

        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());
        this.operationTW.setText("-");

        this.generateVectorsPropositions();
    }

    private void doScalarProduct(){
        this.vq.scalarProduct();
        this.answer = vq.getResult();
        ArrayList<Integer>[] vectors = this.vq.getVectors();

        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());
        this.operationTW.setText(".");

        this.generateScalarPropositions();
    }

    private void generateVectorsPropositions(){

    }

    private void generateScalarPropositions(){

    }

}