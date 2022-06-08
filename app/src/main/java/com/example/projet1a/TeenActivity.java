package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class TeenActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button VectorButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen);
        this.VectorButton = (Button) findViewById(R.id.button_Vector);
        this.VectorButton.setOnClickListener(this);

    }

@Override
    public void onClick(View v) {
        if(v.getId() == this.VectorButton.getId()) this.showVectorPage();
    }
    private void showVectorPage() {
        Intent vectorActivityIntent = new Intent(this, VectorActivity.class);
        startActivity(vectorActivityIntent);
    }


}