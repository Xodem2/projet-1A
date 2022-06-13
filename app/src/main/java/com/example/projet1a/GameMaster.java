package com.example.projet1a;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet1a.database.DataBase;
import com.example.projet1a.point.Point;
import com.example.projet1a.profile.GameStats;
import com.example.projet1a.profile.PlayerProfile;

public class GameMaster extends AppCompatActivity implements View.OnClickListener {

    Button choix1Button;
    Button choix2Button;
    Button choix3Button;
    Point score;
    GameStats stats;
    int delta_point;
    ProgressBar pb;
    int[] currentProgress;

    private PlayerProfile player;

    private String id;

    DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.player = DataProvider.getInstance().getPlayer();
        this.pb = findViewById(R.id.progressBarToday);

        this.db = new DataBase();

        // for eg: if countdown is to go for 30 seconds
        this.pb.setMax(500);

        // the progress in our progressbar decreases with the decrement
        // in the remaining time for countdown to be over
        this.pb.setProgress(500);

        this.currentProgress = new int[1];
        this.currentProgress[0] = 500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentProgress[0] -= 1;
                pb.setProgress(currentProgress[0]);
                if(currentProgress[0] != 0){
                    new Handler().postDelayed(this, 10);
                }
                else{
                    currentProgress[0] = pb.getProgress();
                    new Handler().postDelayed(this, 10);
                }
            }
        }, 1000);

        this.choix1Button = (Button) findViewById(R.id.choice1ID);
        this.choix1Button.setOnClickListener(this);
        this.choix2Button = (Button) findViewById(R.id.choice2ID);
        this.choix2Button.setOnClickListener(this);
        this.choix3Button = (Button) findViewById(R.id.choice3ID);
        this.choix3Button.setOnClickListener(this);

        this.score = new Point();
        this.delta_point = 1;
        this.score.setSensibility(this.delta_point);

        ((TextView) findViewById(R.id.delta)).setText("");
    }

    public void setId(String id) {
        this.id = id;
        this.stats = this.player.getStats().getGameStatsById(id);
        if (this.stats==null){
            this.player.getStats().addGameStats(id);
        }
        this.stats = this.player.getStats().getGameStatsById(id);
    }

    public void generate(){
        int maxi = 250;

        if (this.stats.getTotalAnswered()==0){
            maxi+=250;
        }
        else{
            maxi+=500*(1-((double)this.stats.getTotalCorrects())/((double)this.stats.getTotalAnswered()))-1;
        }
        this.currentProgress[0] = maxi;
        this.pb.setMax(maxi);
        ((TextView) findViewById(R.id.Score)).setText(String.valueOf(this.score.getScore()));
    }

    @Override
    public void onClick(View v) {
    }

    public void update(boolean correct){
        if (this.currentProgress[0]>0) {
            if (correct){
                this.score.incr();
                ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
            }
            else{
                this.score.decr();
                ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
            }
        }
        else{
            if (correct){
                this.score.incr();
                ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                ((TextView) findViewById(R.id.delta)).setText("+0");
            }
            else{
                this.score.decr();
                ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
            }
        }
        this.stats.update(correct);
        if (correct) this.player.getStats().updateSingleplayerScore(this.score.getSensibility());
        else this.player.getStats().updateSingleplayerScore(-this.score.getSensibility());
        this.player.getLevel().update(this.player.getStats().getTotalScore());
        DataProvider.getInstance().getMyLocalDatabase().savePlayer(this.player);
        this.db.update_player(this.player.getStats().getTotalScore(), "total");
    }

    public void setProp(@NonNull String[] prop){
        this.choix1Button.setText(prop[0]);
        this.choix2Button.setText(prop[1]);
        this.choix3Button.setText(prop[2]);
    }

    public void setProp(int[] prop){
        this.choix1Button.setText(String.valueOf(prop[0]));
        this.choix2Button.setText(String.valueOf(prop[1]));
        this.choix3Button.setText(String.valueOf(prop[2]));
    }
}
