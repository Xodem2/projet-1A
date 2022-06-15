package com.example.projet1a;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
    androidx.appcompat.widget.AppCompatButton buttonInd;
    Point score;
    GameStats stats;
    int delta_point;
    ProgressBar pb;
    int[] currentProgress;

    private PlayerProfile player;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView backgame;

    private String id;
    private String idInd;

    DataBase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.player = DataProvider.getInstance().getPlayer();
        this.pb = findViewById(R.id.progressBarToday);

//        this.db = new DataBase();
        this.player.setIsFinished(10);
        // for eg: if countdown is to go for 30 seconds
        this.pb.setMax(500);

        // the progress in our progressbar decreases with the decrement
        // in the remaining time for countdown to be over
        this.pb.setProgress(500);

        this.currentProgress = new int[1];
        this.currentProgress[0] = this.pb.getProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentProgress[0] -= 1;
                pb.setProgress(currentProgress[0]);
                new Handler().postDelayed(this, 10);
            }
        }, 0);

        this.choix1Button = (Button) findViewById(R.id.choice1ID);
        this.choix1Button.setOnClickListener(this);
        this.choix2Button = (Button) findViewById(R.id.choice2ID);
        this.choix2Button.setOnClickListener(this);
        this.choix3Button = (Button) findViewById(R.id.choice3ID);
        this.choix3Button.setOnClickListener(this);
        this.buttonInd = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.buttonInd);
        this.buttonInd.setOnClickListener(this);

        this.score = new Point();
        this.delta_point = 1;
        this.score.setSensibility(this.delta_point);

        ((TextView) findViewById(R.id.delta)).setText("");



        backgame = (VideoView) findViewById(R.id.backgame);
        String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.backgame;
        Uri uri = Uri.parse(uriPath);
        backgame.setVideoURI(uri);
        backgame.start();
        backgame.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                // We want our video to play over and over so we set looping to true.
                mMediaPlayer.setLooping(true);
                // We then seek to the current position if it has been set and play the video.
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });
    }

    public void setId(String id) {
        this.id = id;
        this.stats = this.player.getStats().getGameStatsById(id);
        if (this.stats==null){
            this.player.getStats().addGameStats(id);
        }
        this.stats = this.player.getStats().getGameStatsById(id);
    }

    public void setIdInd(String id) {
        this.idInd = id;
    }

    public String getIdInd() {
        return this.idInd;
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
        if (v.getId() == this.buttonInd.getId()) {
            ((TextView) findViewById(R.id.textInd)).setText((new Indices()).getInd(this.getIdInd()));
        }
        else {
            ((TextView) findViewById(R.id.textInd)).setText("");
        }
    }

    public void update(boolean correct){
        if(this.player.getIsFinished() == -1){
            if (this.currentProgress[0]>0) {
                if (correct){
                    this.score.incr();
                    this.player.incNumberOfSucces();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                    this.player.getStats().updateSingleplayerScore(this.score.getSensibility());
                }
                else{
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                    this.player.getStats().updateSingleplayerScore(-this.score.getSensibility());
                }
                this.stats.update(correct);
                this.player.getLevel().update(this.player.getStats().getTotalScore());
            }
            else{
                if (correct){
                    this.score.setSensibility(0);
                    this.score.incr();
                    this.score.setSensibility(1);
                    this.player.incNumberOfSucces();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                    this.player.getStats().updateSingleplayerScore(0);
                }
                else{
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                    this.player.getStats().updateSingleplayerScore(-this.score.getSensibility());
                }
            }
        }
        else{
            if (this.currentProgress[0]>0) {
                this.player.decIsFinished();
                if (correct){
                    this.score.incr();
                    this.player.incNumberOfSucces();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+" + String.valueOf(this.score.getSensibility()));
                    this.player.getStats().updateSingleplayerScore(-this.score.getSensibility());
                }
                else{
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                    this.player.getStats().updateSingleplayerScore(-this.score.getSensibility());
                }
                this.stats.update(correct);
                this.player.getLevel().update(this.player.getStats().getTotalScore());
            }
            else{
                if (correct){
                    this.score.setSensibility(0);
                    this.score.incr();
                    this.score.setSensibility(1);
                    this.player.incNumberOfSucces();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#00ff00"));
                    ((TextView) findViewById(R.id.delta)).setText("+0");
                    this.player.getStats().updateSingleplayerScore(0);
                }
                else{
                    this.score.decr();
                    ((TextView) findViewById(R.id.delta)).setTextColor(Color.parseColor("#ff0000"));
                    ((TextView) findViewById(R.id.delta)).setText("-" + String.valueOf(this.score.getSensibility()));
                    this.player.getStats().updateSingleplayerScore(-this.score.getSensibility());
                }
            }
        }
        this.stats.update(correct);
        this.player.getLevel().update(this.player.getStats().getTotalScore());

        this.checkSuccess();

        DataProvider.getInstance().getMyLocalDatabase().savePlayer(this.player);
        DataProvider.getInstance().getMyFirebaseHelper().savePlayer(this.player);
//        this.db.update_player(this.player.getStats().getTotalScore(), "total");

        if(this.player.getIsFinished() == 0){
            System.out.println("envoie");
            this.player.decIsFinished();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        }
    }

    public void checkSuccess(){
        if(!player.getSuccess().getSuccessById("o10rcda").isAcquired()) {
            ArrayMap<String, GameStats> gameStats = this.player.getStats().getGameStats();
            for (int i = 0; i < gameStats.size(); i++) {
                if (gameStats.valueAt(i).getCorrectsInARow() >= 10) {
                    this.player.getSuccess().getSuccessById("o10rcda").acquire();
                    this.showSuccessPopup(this.player.getSuccess().getSuccessById("o10rcda").getTitle());
                }
            }
        }
    }

    public void showSuccessPopup(String successTitle){
        Toast customToast = new Toast(this);
        View view = getLayoutInflater().inflate(R.layout.toast_layout, null);
        ((TextView)view.findViewById(R.id.toastLayoutItemSuccessTitleId)).setText(successTitle);
        customToast.setGravity(Gravity.CENTER, 0, 0);
        customToast.setView(view);
        customToast.show();
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
