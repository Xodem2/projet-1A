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
import com.example.projet1a.profile.PlayerProfile;

public class MoinsActivity extends GameMaster{
    Operation op;

    public final static String id="MoinsActivity";
    public final static String idInd="opération";

    private PlayerProfile player;

    int[] prop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_moins);
        this.pb = findViewById(R.id.progressBarToday);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);

        this.player = DataProvider.getInstance().getPlayer();

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
        super.onClick(v);
        if (v.getId() == this.choix1Button.getId()) {
            super.update(op.minus(this.prop[0]));
            this.generate();
        }
        else if (v.getId() == this.choix2Button.getId()) {
            super.update(op.minus(this.prop[1]));
            this.generate();
        }
        else if (v.getId() == this.choix3Button.getId()) {
            super.update(op.minus(this.prop[2]));
            this.generate();
        }
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rcdiff").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100) {
                this.player.getSuccess().getSuccessById("o100rcdiff").acquire();
                this.showSuccessPopup(this.player.getSuccess().getSuccessById("o100rcdiff").getTitle());
            }
        }
    }
}