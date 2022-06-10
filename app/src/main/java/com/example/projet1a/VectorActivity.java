package com.example.projet1a;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet1a.adult.VectorQuestion;
import com.example.projet1a.profile.PlayerProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VectorActivity extends GameMaster {

    private static final int OPERATION_ADDITION = 1;
    private static final int OPERATION_SUBSTRACTION = 2;
    private static final int OPERATION_SCALAR_PRODUCT = 3;

    private TextView vector1TW;
    private TextView vector2TW;
    private TextView operationTW;

    private VectorQuestion vq;
    private Object answer;
    private Object[] propositions;

    private PlayerProfile player;

    private Random random;

    public final static String id="VectorActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vector);
        super.onCreate(savedInstanceState);

        this.vector1TW = (TextView) findViewById(R.id.vector1ID);
        this.vector2TW = (TextView) findViewById(R.id.vector2ID2);
        this.operationTW = (TextView) findViewById(R.id.vectorOperationID);

        this.vq = new VectorQuestion();
        this.random = new Random();

        this.player = DataProvider.getInstance().getPlayer();

        this.generate();
    }

    @Override
    public void onClick(View v) {
        String userAnswer = "";

        if(v.getId() == this.choix1Button.getId()) userAnswer = (String) this.choix1Button.getText();
        else if(v.getId() == this.choix2Button.getId()) userAnswer = (String) this.choix2Button.getText();
        else if(v.getId() == this.choix3Button.getId()) userAnswer = (String) this.choix3Button.getText();

        super.update(userAnswer.equals(this.answer.toString()));

        this.vq = new VectorQuestion();
        this.generate();
    }

    private int chooseRandomQuestion(){
        int[] choices = {OPERATION_ADDITION, OPERATION_SUBSTRACTION, OPERATION_SCALAR_PRODUCT};
        int choice = this.random.nextInt(choices.length);
        return choices[choice];
    }

    @Override
    public void generate(){
        super.setId(this.id);
        super.generate();
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

        List<Object> list = Arrays.asList(this.propositions);
        Collections.shuffle(list);
        this.propositions = list.toArray();
        String[] prop = new String[3];
        prop[0] = this.propositions[0].toString();
        prop[1] = this.propositions[1].toString();
        prop[2] = this.propositions[2].toString();
        super.setProp(prop);
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