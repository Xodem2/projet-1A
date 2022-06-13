package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet1a.adult.Matrix;
import com.example.projet1a.adult.MatrixOperation;

import java.util.ArrayList;
import java.util.Random;

public class MatricesActivity extends GameMaster {
    public final static String id="MatriceActivity";

    MatrixOperation m;
    private TextView pg1;
    private TextView m1_11;
    private TextView m1_12;
    private TextView m1_21;
    private TextView m1_22;
    private TextView pd1;

    private TextView pg2;
    private TextView m2_11;
    private TextView m2_12;
    private TextView m2_21;
    private TextView m2_22;
    private TextView pd2;

    private TextView pg3;
    private TextView c1_11;
    private TextView c1_12;
    private TextView c1_21;
    private TextView c1_22;
    private TextView pd3;
    private TextView det1;

    private TextView pg4;
    private TextView c2_11;
    private TextView c2_12;
    private TextView c2_21;
    private TextView c2_22;
    private TextView pd4;
    private TextView det2;

    private TextView pg5;
    private TextView c3_11;
    private TextView c3_12;
    private TextView c3_21;
    private TextView c3_22;
    private TextView pd5;
    private TextView det3;

    int operation;
    Random random;

    int correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_matrices);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        this.m = new MatrixOperation(2);

        this.pg1 = (TextView) findViewById(R.id.pg1);
        this.m1_11 = (TextView) findViewById(R.id.m1_11);
        this.m1_12 = (TextView) findViewById(R.id.m1_12);
        this.m1_21 = (TextView) findViewById(R.id.m1_21);
        this.m1_22 = (TextView) findViewById(R.id.m1_22);
        this.pd1 = (TextView) findViewById(R.id.pd1);

        this.pg2 = (TextView) findViewById(R.id.pg2);
        this.m2_11 = (TextView) findViewById(R.id.m2_11);
        this.m2_12 = (TextView) findViewById(R.id.m2_12);
        this.m2_21 = (TextView) findViewById(R.id.m2_21);
        this.m2_22 = (TextView) findViewById(R.id.m2_22);
        this.pd2 = (TextView) findViewById(R.id.pd2);

        this.pg3 = (TextView) findViewById(R.id.pg3);
        this.c1_11 = (TextView) findViewById(R.id.c1_11);
        this.c1_12 = (TextView) findViewById(R.id.c1_12);
        this.c1_21 = (TextView) findViewById(R.id.c1_21);
        this.c1_22 = (TextView) findViewById(R.id.c1_22);
        this.pd3 = (TextView) findViewById(R.id.pd3);
        this.det1 = (TextView) findViewById(R.id.det1);

        this.pg4 = (TextView) findViewById(R.id.pg4);
        this.c2_11 = (TextView) findViewById(R.id.c2_11);
        this.c2_12 = (TextView) findViewById(R.id.c2_12);
        this.c2_21 = (TextView) findViewById(R.id.c2_21);
        this.c2_22 = (TextView) findViewById(R.id.c2_22);
        this.pd4 = (TextView) findViewById(R.id.pd4);
        this.det2 = (TextView) findViewById(R.id.det2);

        this.pg5 = (TextView) findViewById(R.id.pg5);
        this.c3_11 = (TextView) findViewById(R.id.c3_11);
        this.c3_12 = (TextView) findViewById(R.id.c3_12);
        this.c3_21 = (TextView) findViewById(R.id.c3_21);
        this.c3_22 = (TextView) findViewById(R.id.c3_22);
        this.pd5 = (TextView) findViewById(R.id.pd5);
        this.det3 = (TextView) findViewById(R.id.det3);

        this.random = new Random();

        generate();
    }

    @Override
    public void generate(){
        super.generate();
        this.m.generate();
        this.operation = random.nextInt(5);

        this.det1.setText("");
        this.det2.setText("");
        this.det3.setText("");

        if (this.operation==0){
//            somme
            ArrayList<Matrix> prop = this.m.propSum();
            this.correct = this.m.getCorrect();
            this.pg1.setText("(");
            this.m1_11.setText(String.valueOf(this.m.getM1().get(0, 0)));
            this.m1_12.setText(String.valueOf(this.m.getM1().get(0, 1)));
            this.m1_21.setText(String.valueOf(this.m.getM1().get(1, 0)));
            this.m1_22.setText(String.valueOf(this.m.getM1().get(1, 1)));
            this.pd1.setText(")");

            ((TextView) findViewById(R.id.operation)).setText("+");

            this.pg2.setText("(");
            this.m2_11.setText(String.valueOf(this.m.getM2().get(0, 0)));
            this.m2_12.setText(String.valueOf(this.m.getM2().get(0, 1)));
            this.m2_21.setText(String.valueOf(this.m.getM2().get(1, 0)));
            this.m2_22.setText(String.valueOf(this.m.getM2().get(1, 1)));
            this.pd2.setText(")");

//            Choix 1
            this.pg3.setText("(");
            this.c1_11.setText(String.valueOf(prop.get(0).get(0, 0)));
            this.c1_12.setText(String.valueOf(prop.get(0).get(0, 1)));
            this.c1_21.setText(String.valueOf(prop.get(0).get(1, 0)));
            this.c1_22.setText(String.valueOf(prop.get(0).get(1, 1)));
            this.pd3.setText(")");

//            Choix 2
            this.pg4.setText("(");
            this.c2_11.setText(String.valueOf(prop.get(1).get(0, 0)));
            this.c2_12.setText(String.valueOf(prop.get(1).get(0, 1)));
            this.c2_21.setText(String.valueOf(prop.get(1).get(1, 0)));
            this.c2_22.setText(String.valueOf(prop.get(1).get(1, 1)));
            this.pd4.setText(")");

//            Choix 3
            this.pg5.setText("(");
            this.c3_11.setText(String.valueOf(prop.get(2).get(0, 0)));
            this.c3_12.setText(String.valueOf(prop.get(2).get(0, 1)));
            this.c3_21.setText(String.valueOf(prop.get(2).get(1, 0)));
            this.c3_22.setText(String.valueOf(prop.get(2).get(1, 1)));
            this.pd5.setText(")");
        }
        else if (this.operation==1){
//            différence
            ArrayList<Matrix> prop = this.m.propMinus();
            this.correct = this.m.getCorrect();
            this.pg1.setText("(");
            this.m1_11.setText(String.valueOf(this.m.getM1().get(0, 0)));
            this.m1_12.setText(String.valueOf(this.m.getM1().get(0, 1)));
            this.m1_21.setText(String.valueOf(this.m.getM1().get(1, 0)));
            this.m1_22.setText(String.valueOf(this.m.getM1().get(1, 1)));
            this.pd1.setText(")");

            ((TextView) findViewById(R.id.operation)).setText("-");

            this.pg2.setText("(");
            this.m2_11.setText(String.valueOf(this.m.getM2().get(0, 0)));
            this.m2_12.setText(String.valueOf(this.m.getM2().get(0, 1)));
            this.m2_21.setText(String.valueOf(this.m.getM2().get(1, 0)));
            this.m2_22.setText(String.valueOf(this.m.getM2().get(1, 1)));
            this.pd2.setText(")");

//            Choix 1
            this.pg3.setText("(");
            this.c1_11.setText(String.valueOf(prop.get(0).get(0, 0)));
            this.c1_12.setText(String.valueOf(prop.get(0).get(0, 1)));
            this.c1_21.setText(String.valueOf(prop.get(0).get(1, 0)));
            this.c1_22.setText(String.valueOf(prop.get(0).get(1, 1)));
            this.pd3.setText(")");

//            Choix 2
            this.pg4.setText("(");
            this.c2_11.setText(String.valueOf(prop.get(1).get(0, 0)));
            this.c2_12.setText(String.valueOf(prop.get(1).get(0, 1)));
            this.c2_21.setText(String.valueOf(prop.get(1).get(1, 0)));
            this.c2_22.setText(String.valueOf(prop.get(1).get(1, 1)));
            this.pd4.setText(")");

//            Choix 3
            this.pg5.setText("(");
            this.c3_11.setText(String.valueOf(prop.get(2).get(0, 0)));
            this.c3_12.setText(String.valueOf(prop.get(2).get(0, 1)));
            this.c3_21.setText(String.valueOf(prop.get(2).get(1, 0)));
            this.c3_22.setText(String.valueOf(prop.get(2).get(1, 1)));
            this.pd5.setText(")");
        }
        else if (this.operation==2){
//            produit
            ArrayList<Matrix> prop = this.m.propProd();
            this.correct = this.m.getCorrect();
            this.pg1.setText("(");
            this.m1_11.setText(String.valueOf(this.m.getM1().get(0, 0)));
            this.m1_12.setText(String.valueOf(this.m.getM1().get(0, 1)));
            this.m1_21.setText(String.valueOf(this.m.getM1().get(1, 0)));
            this.m1_22.setText(String.valueOf(this.m.getM1().get(1, 1)));
            this.pd1.setText(")");

            ((TextView) findViewById(R.id.operation)).setText("*");

            this.pg2.setText("(");
            this.m2_11.setText(String.valueOf(this.m.getM2().get(0, 0)));
            this.m2_12.setText(String.valueOf(this.m.getM2().get(0, 1)));
            this.m2_21.setText(String.valueOf(this.m.getM2().get(1, 0)));
            this.m2_22.setText(String.valueOf(this.m.getM2().get(1, 1)));
            this.pd2.setText(")");

//            Choix 1
            this.pg3.setText("(");
            this.c1_11.setText(String.valueOf(prop.get(0).get(0, 0)));
            this.c1_12.setText(String.valueOf(prop.get(0).get(0, 1)));
            this.c1_21.setText(String.valueOf(prop.get(0).get(1, 0)));
            this.c1_22.setText(String.valueOf(prop.get(0).get(1, 1)));
            this.pd3.setText(")");

//            Choix 2
            this.pg4.setText("(");
            this.c2_11.setText(String.valueOf(prop.get(1).get(0, 0)));
            this.c2_12.setText(String.valueOf(prop.get(1).get(0, 1)));
            this.c2_21.setText(String.valueOf(prop.get(1).get(1, 0)));
            this.c2_22.setText(String.valueOf(prop.get(1).get(1, 1)));
            this.pd4.setText(")");

//            Choix 3
            this.pg5.setText("(");
            this.c3_11.setText(String.valueOf(prop.get(2).get(0, 0)));
            this.c3_12.setText(String.valueOf(prop.get(2).get(0, 1)));
            this.c3_21.setText(String.valueOf(prop.get(2).get(1, 0)));
            this.c3_22.setText(String.valueOf(prop.get(2).get(1, 1)));
            this.pd5.setText(")");
        }
        else if (this.operation==3){
//            determinant
            this.pg1.setText("");
            this.m1_11.setText("");
            this.m1_12.setText("");
            this.m1_21.setText("");
            this.m1_22.setText("");
            this.pd1.setText("");

            ((TextView) findViewById(R.id.operation)).setText("det");

            this.pg2.setText("(");
            this.m2_11.setText(String.valueOf(this.m.getM2().get(0, 0)));
            this.m2_12.setText(String.valueOf(this.m.getM2().get(0, 1)));
            this.m2_21.setText(String.valueOf(this.m.getM2().get(1, 0)));
            this.m2_22.setText(String.valueOf(this.m.getM2().get(1, 1)));
            this.pd2.setText(")");


//            Choix 1
            this.pg3.setText("");
            this.c1_11.setText("");
            this.c1_12.setText("");
            this.c1_21.setText("");
            this.c1_22.setText("");
            this.pd3.setText("");

//            Choix 2
            this.pg4.setText("");
            this.c2_11.setText("");
            this.c2_12.setText("");
            this.c2_21.setText("");
            this.c2_22.setText("");
            this.pd4.setText("");

//            Choix 3
            this.pg5.setText("");
            this.c3_11.setText("");
            this.c3_12.setText("");
            this.c3_21.setText("");
            this.c3_22.setText("");
            this.pd5.setText("");

            int[] prop = this.m.getM2().propDet().conv();
            if (this.m.getM2().det(prop[0])){
                this.correct = 0;
            }
            else if (this.m.getM2().det(prop[1])){
                this.correct = 1;
            }
            else{
                this.correct = 2;
            }
            this.det1.setText(String.valueOf(prop[0]));
            this.det2.setText(String.valueOf(prop[1]));
            this.det3.setText(String.valueOf(prop[2]));
        }
        else{
//            transpose
            ArrayList<Matrix> prop = this.m.getM2().propTrans();
            this.correct = this.m.getCorrect();
            this.pg1.setText("");
            this.m1_11.setText("");
            this.m1_12.setText("");
            this.m1_21.setText("");
            this.m1_22.setText("");
            this.pd1.setText("");

            ((TextView) findViewById(R.id.operation)).setText("transpose");

            this.pg2.setText("(");
            this.m2_11.setText(String.valueOf(this.m.getM2().get(0, 0)));
            this.m2_12.setText(String.valueOf(this.m.getM2().get(0, 1)));
            this.m2_21.setText(String.valueOf(this.m.getM2().get(1, 0)));
            this.m2_22.setText(String.valueOf(this.m.getM2().get(1, 1)));
            this.pd2.setText(")");


//            Choix 1
            this.pg3.setText("(");
            this.c1_11.setText(String.valueOf(prop.get(0).get(0, 0)));
            this.c1_12.setText(String.valueOf(prop.get(0).get(0, 1)));
            this.c1_21.setText(String.valueOf(prop.get(0).get(1, 0)));
            this.c1_22.setText(String.valueOf(prop.get(0).get(1, 1)));
            this.pd3.setText(")");

//            Choix 2
            this.pg4.setText("(");
            this.c2_11.setText(String.valueOf(prop.get(1).get(0, 0)));
            this.c2_12.setText(String.valueOf(prop.get(1).get(0, 1)));
            this.c2_21.setText(String.valueOf(prop.get(1).get(1, 0)));
            this.c2_22.setText(String.valueOf(prop.get(1).get(1, 1)));
            this.pd4.setText(")");

//            Choix 3
            this.pg5.setText("(");
            this.c3_11.setText(String.valueOf(prop.get(2).get(0, 0)));
            this.c3_12.setText(String.valueOf(prop.get(2).get(0, 1)));
            this.c3_21.setText(String.valueOf(prop.get(2).get(1, 0)));
            this.c3_22.setText(String.valueOf(prop.get(2).get(1, 1)));
            this.pd5.setText(")");
        }
    }

    @Override
    public void onClick(View v){
        super.onClick(v);
        if (v.getId()==this.choix1Button.getId()){
            super.update(this.correct==0);
        }
        else if (v.getId()==this.choix2Button.getId()){
            super.update(this.correct==1);
        }
        else{
            super.update(this.correct==2);
        }
        this.generate();
    }
}