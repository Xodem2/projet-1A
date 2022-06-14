package com.example.projet1a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projet1a.ado_games.Square_Sqrt;
import com.example.projet1a.profile.PlayerProfile;

import java.util.Random;

public class SqrActivity extends GameMaster{

    Square_Sqrt sqr;
    Random random;

    int[] prop;
    private PlayerProfile player;

    public final static String id="SqrActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sqr);
        super.onCreate(savedInstanceState);
        super.setId(this.id);

        this.player = DataProvider.getInstance().getPlayer();

        ((TextView) findViewById(R.id.prop1)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop2)).setText(String.valueOf(""));
        ((TextView) findViewById(R.id.prop3)).setText(String.valueOf(""));

        this.sqr = new Square_Sqrt();
        this.random = new Random();
        generate();
    }

    public void generate(){
        super.generate();
        if(this.random.nextInt(2) == 0) {
            super.setIdInd("sqrt");
            this.sqr.sqrt();
            ((TextView) findViewById(R.id.sqrN)).setText("√"+String.valueOf(this.sqr.getN()));
            this.prop = this.sqr.propSqrt().conv();
        }
        else {
            super.setIdInd("sqr");
            this.sqr.square();
            if(this.sqr.getM() < 0) {
                ((TextView) findViewById(R.id.sqrN)).setText(String.valueOf("(" + this.sqr.getM() + ")²"));
            }
            else {
                ((TextView) findViewById(R.id.sqrN)).setText(String.valueOf(this.sqr.getM() + "²"));
            }
            this.prop = this.sqr.propSqr().conv();
        }
        super.setProp(prop);
    }

    @Override
    public void onClick(View v){
        super.onClick(v);
        if (v.getId() == this.choix1Button.getId()) {
            super.update(this.sqr.fct(this.prop[0]));
            this.generate();
        }
        else if (v.getId() == this.choix2Button.getId()) {
            super.update(this.sqr.fct(this.prop[1]));
            this.generate();
        }
        else if (v.getId() == this.choix3Button.getId()) {
            super.update(this.sqr.fct(this.prop[2]));
            this.generate();
        }
    }

    @Override
    public void checkSuccess(){
        super.checkSuccess();
        /* if(!this.player.getSuccess().getSuccessById("o100rcsqr").isAcquired()){
            if(this.player.getStats().getGameStatsById(id).getTotalCorrects() >= 100) {
                this.player.getSuccess().getSuccessById("o100rcsqr").acquire();
                this.showSuccessPopup(this.player.getSuccess().getSuccessById("o100rcsqr").getTitle());
            }
        } */
    }
}