package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projet1a.adult.VectorQuestion;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VectorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int OPERATION_ADDITION = 1;
    private static final int OPERATION_SUBSTRACTION = 2;
    private static final int OPERATION_SCALAR_PRODUCT = 3;

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
        String userAnswer = "";

        if(v.getId() == this.choice1Button.getId()) userAnswer = (String) this.choice1Button.getText();
        else if(v.getId() == this.choice2Button.getId()) userAnswer = (String) this.choice2Button.getText();
        else if(v.getId() == this.choice3Button.getId()) userAnswer = (String) this.choice3Button.getText();

        // TODO : count score
        if(userAnswer.equals(this.answer.toString())) System.out.println("correct");

        this.vq = new VectorQuestion();
        this.generateQuestion();
    }

    private int chooseRandomQuestion(){
        int[] choices = {OPERATION_ADDITION, OPERATION_SUBSTRACTION, OPERATION_SCALAR_PRODUCT};
        int choice = this.random.nextInt(choices.length);
        return choices[choice];
    }

    private void generateQuestion(){
        int operation = this.chooseRandomQuestion();

        if(operation == OPERATION_ADDITION){
            this.doAddition();
            this.generateVectorsPropositions();
        }
        else if(operation == OPERATION_SUBSTRACTION){
            this.doSubstraction();
            this.generateVectorsPropositions();
        }
        else if(operation == OPERATION_SCALAR_PRODUCT){
            this.doScalarProduct();
            this.generateScalarPropositions();
        }

        // TODO : shuffle propositions
        List<Object> list = Arrays.asList(this.propositions);
        Collections.shuffle(list);
        this.propositions = list.toArray();

        this.choice1Button.setText(this.propositions[0].toString());
        this.choice2Button.setText(this.propositions[1].toString());
        this.choice3Button.setText(this.propositions[2].toString());
    }

    private void doAddition(){
        this.vq.addition();
        this.answer = this.vq.getResult();
        ArrayList<Integer>[] vectors = this.vq.getVectors();

        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());
        this.operationTW.setText("+");
    }

    private void doSubstraction(){
        this.vq.substraction();
        this.answer = vq.getResult();
        ArrayList<Integer>[] vectors = this.vq.getVectors();

        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());
        this.operationTW.setText("-");
    }

    private void doScalarProduct(){
        this.vq.scalarProduct();
        this.answer = vq.getResult();
        ArrayList<Integer>[] vectors = this.vq.getVectors();

        this.vector1TW.setText(vectors[0].toString());
        this.vector2TW.setText(vectors[1].toString());
        this.operationTW.setText(".");
    }

    private void generateVectorsPropositions(){
        ArrayList<Integer> correctAnswer = (ArrayList<Integer>) this.vq.getResult();
        int dimension = correctAnswer.size();

        ArrayList<Integer> incorrectAnswer1;
        ArrayList<Integer> incorrectAnswer2;
        do {
            incorrectAnswer1 = new ArrayList<>();
            incorrectAnswer2 = new ArrayList<>();
            for (int i = 0; i < dimension; i++) {
                incorrectAnswer1.add(correctAnswer.get(i) + this.random.nextInt(1 - (-1) + 1) + (-1) * this.random.nextInt(3));
                incorrectAnswer2.add(correctAnswer.get(i) + this.random.nextInt(1 - (-1) + 1) + (-1) * this.random.nextInt(3));
            }
        }while(compareVectors(correctAnswer, incorrectAnswer1) || compareVectors(correctAnswer,incorrectAnswer2) || compareVectors(incorrectAnswer1, incorrectAnswer2));

        this.propositions = new Object[3];
        this.propositions[0] = correctAnswer;
        this.propositions[1] = incorrectAnswer1;
        this.propositions[2] = incorrectAnswer2;
    }

    private void generateScalarPropositions(){
        int correctAnswer = (int) this.answer;

        int incorrectAnswer1 = correctAnswer;
        int incorrectAnswer2 = correctAnswer;
        do {
            incorrectAnswer1 = correctAnswer + this.random.nextInt(12) - this.random.nextInt(7);
            incorrectAnswer2 = correctAnswer + this.random.nextInt(8) - this.random.nextInt(12);
        } while((incorrectAnswer1 == correctAnswer) || incorrectAnswer2 == correctAnswer);

        this.propositions = new Object[3];
        this.propositions[0] = correctAnswer;
        this.propositions[1] = incorrectAnswer1;
        this.propositions[2] = incorrectAnswer2;
    }

    private boolean compareVectors(ArrayList<Integer> v1, ArrayList<Integer> v2){
        // return true if same vectors
        if(v1.size() != v2.size()) return false;

        for(int i = 0; i < v1.size(); i++)
            if(!v1.get(i).equals(v2.get(i))) return false;

        return true;
    }

}