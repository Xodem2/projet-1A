package com.example.projet1a;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FractionActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private TextView num;
    private TextView den;
    Rect r;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        r = new Rect();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraction);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.num = (TextView) findViewById(R.id.num);
        this.den = (TextView) findViewById(R.id.den);
        this.progressBar.setMinHeight(Integer.parseInt("10"));
        this.progressBar.setMaxHeight(Integer.parseInt("10"));
        Rect rectNum = new Rect();
        String textNum = (String) this.num.getText();
        this.num.getPaint().getTextBounds(textNum, 0, textNum.length(), rectNum);
        Rect rectDen = new Rect();
        String textDen = (String) this.den.getText();
        this.num.getPaint().getTextBounds(textDen, 0, textDen.length(), rectDen);
        this.progressBar.setMinWidth((int)(Math.max(rectNum.width(), rectDen.width())*1.1));
        this.progressBar.setMaxWidth((int)(Math.max(rectNum.width(), rectDen.width())*1.1));
        this.progressBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}