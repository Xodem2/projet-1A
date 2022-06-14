package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.projet1a.database.DataBase;
import com.example.projet1a.enfant.Operation;
import com.example.projet1a.point.Point;
import com.example.projet1a.profile.PlayerProfile;

import java.util.Arrays;

public class SommeActivity extends GameMaster{

    Operation op;

    int[] prop;
    private PlayerProfile player;

    public final static String id="SommeActivity";
    public final static String idInd="opération";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_somme);
        super.onCreate(savedInstanceState);
        super.setId(this.id);
        super.setIdInd(this.idInd);

        this.player = DataProvider.getInstance().getPlayer();

        ((TextView) findViewById(R.id.prop1)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop2)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop3)).setText(String.valueOf(""));

        this.op = new Operation();
        generate();
    }

    public void generate(){
        super.generate();
        this.op.generate();
        ((TextView) findViewById(R.id.op1)).setText(String.valueOf(this.op.getA()));
        ((TextView) findViewById(R.id.op2)).setText(String.valueOf(this.op.getB()));
        this.prop = this.op.propPlus().conv();
        super.setProp(prop);
    }

    @Override
    public void onClick(View v){
        super.onClick(v);
        if (v.getId() == this.choix1Button.getId()) {
            super.update(op.plus(this.prop[0]));
            this.generate();
        }
        else if (v.getId() == this.choix2Button.getId()) {
            super.update(op.plus(this.prop[1]));
            this.generate();
        }
        else if (v.getId() == this.choix3Button.getId()) {
            super.update(op.plus(this.prop[2]));
            this.generate();
        }
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        if(!this.player.getSuccess().getSuccessById("o100rcsommes").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100)
                this.player.getSuccess().getSuccessById("o100rcsommes").acquire();
        }
    }
}