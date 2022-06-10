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

public class MoinsActivity extends GameMaster{
    Operation op;

    int[] prop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_moins);this.pb = findViewById(R.id.progressBarToday);
        super.onCreate(savedInstanceState);

        ((TextView) findViewById(R.id.prop1_moins)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop2_moins)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop3_moins)).setText(String.valueOf(""));

        this.op = new Operation();
        generate();
    }
    public void generate(){
        super.generate();
        this.op.generate();
        ((TextView) findViewById(R.id.op1_moins)).setText(String.valueOf(this.op.getA()));
        ((TextView) findViewById(R.id.op2_moins)).setText(String.valueOf(this.op.getB()));
        this.prop = this.op.propMinus().conv();
        super.setProp(this.prop);
    }

    @Override
    public void onClick(View v){
        if (v.getId() == this.choix1Button.getId()) {
            super.update(op.minus(this.prop[0]));
        }
        else if (v.getId() == this.choix2Button.getId()){
            super.update(op.minus(this.prop[1]));
        }
        else{
            super.update(op.minus(this.prop[2]));
        }
        this.generate();
    }
}