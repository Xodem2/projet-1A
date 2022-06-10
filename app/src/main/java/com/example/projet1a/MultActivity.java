package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projet1a.enfant.Operation;
import com.example.projet1a.point.Point;

public class MultActivity extends GameMaster{
    Operation op;

    int[] prop;
    public final static String id="MultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mult);
        super.onCreate(savedInstanceState);

        ((TextView) findViewById(R.id.prop1_mult)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop2_mult)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop3_mult)).setText(String.valueOf(""));

        this.op = new Operation();
        generate();
    }

    @Override
    public void generate(){
        super.setId(this.id);
        super.generate();
        this.op.generate();
        ((TextView) findViewById(R.id.op1_mult)).setText(String.valueOf(this.op.getA()));
        ((TextView) findViewById(R.id.op2_mult)).setText(String.valueOf(this.op.getB()));
        this.prop = this.op.propMult().conv();
        super.setProp(prop);
    }

    @Override
    public void onClick(View v){
        if (v.getId() == this.choix1Button.getId()) {
            super.update(op.mult(this.prop[0]));
        }
        else if (v.getId() == this.choix2Button.getId()){
            super.update(op.mult(this.prop[1]));
        }
        else{
            super.update(op.mult(this.prop[2]));
        }
        this.generate();
    }
}