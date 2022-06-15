package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.projet1a.profile.PlayerProfile;

public class MainMultiplayerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton FractionButton;
    private ImageButton Equation1Button;
    private ImageButton Equation2Button;
    private ImageButton pythagoreButton;

    private ImageButton sommeButton;
    private ImageButton diffButton;
    private ImageButton multButton;
    private ImageButton divButton;

    private ImageButton vectorButton;
    private ImageButton matriceButton;
    private ImageButton sqrtButton;
    private ImageButton trigoButton;
    MediaPlayer mMediaPlayer;
    int mCurrentVideoPosition;
    private VideoView adultback;

    private int id_session;
    private PlayerProfile player;

    private String idGame ;

    @Override
    protected void onResume() {
        super.onResume();
        DataProvider.getInstance().getPlayer().setIsFinished(-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_multiplayer);
        DataProvider.getInstance().getPlayer().setIsFinished(-1);

        this.FractionButton = (ImageButton) findViewById(R.id.main_multiplayer_fraction);
        this.FractionButton.setOnClickListener(this);

        this.Equation1Button = (ImageButton)  findViewById(R.id.main_multiplayer_equation1);
        this.Equation1Button.setOnClickListener(this);

        this.Equation2Button = (ImageButton)  findViewById(R.id.main_multiplayer_equation2);
        this.Equation2Button.setOnClickListener(this);

        this.pythagoreButton = (ImageButton)  findViewById(R.id.main_multiplayer_pythagore);
        this.pythagoreButton.setOnClickListener(this);

        this.sommeButton = (ImageButton) findViewById(R.id.main_multiplayer_somme);
        this.sommeButton.setOnClickListener(this);

        this.diffButton = (ImageButton) findViewById(R.id.main_multiplayer_diff);
        this.diffButton.setOnClickListener(this);

        this.multButton = (ImageButton) findViewById(R.id.main_multiplayer_mult);
        this.multButton.setOnClickListener(this);

        this.divButton = (ImageButton) findViewById(R.id.main_multiplayer_division);
        this.divButton.setOnClickListener(this);

        this.player = DataProvider.getInstance().getPlayer();

        this.vectorButton = (ImageButton) findViewById(R.id.main_multiplayer_vector);
        this.vectorButton.setOnClickListener(this);

        this.matriceButton = (ImageButton) findViewById(R.id.main_multiplayer_matrice);
        this.matriceButton.setOnClickListener(this);

        this.sqrtButton = (ImageButton) findViewById(R.id.main_multiplayer_sqrt);
        this.sqrtButton.setOnClickListener(this);

        this.trigoButton = (ImageButton) findViewById(R.id.main_multiplayer_trigo);
        this.trigoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.FractionButton.getId()) this.showFractionPage();
        else if(v.getId() == this.Equation1Button.getId()) this.showEquation1Page();
        else if(v.getId() == this.Equation2Button.getId()) this.showEquation2Page();
        else if(v.getId() == this.pythagoreButton.getId()) this.showPythagorePage();
        else if(v.getId() == this.sommeButton.getId()) this.showSommePage();
        else if(v.getId() == this.diffButton.getId()) this.showDiffPage();
        else if(v.getId() == this.multButton.getId()) this.showMultPage();
        else if(v.getId() == this.divButton.getId()) this.showDivPage();
        else if(v.getId() == this.vectorButton.getId()) this.showVectorActivity();
        else if(v.getId() == this.matriceButton.getId()) this.showMatriceActivity();
        else if(v.getId() == this.sqrtButton.getId()) this.showSqrtActivity();
        else if(v.getId() == this.trigoButton.getId()) this.showTrigoActivity();
//        sqrtButton
//        trigoButton
    }

    private void showVectorActivity() {
        this.idGame = VectorActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showMatriceActivity() {
        this.idGame = MatricesActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showFractionPage(){
        this.idGame = FractionActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showEquation1Page(){
        this.idGame = Equation1Activity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showEquation2Page(){
        this.idGame = Equation2Activity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showPythagorePage(){
        this.idGame = PythagoreActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }


    private void showSommePage() {
        this.idGame = SommeActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showDiffPage() {
        this.idGame = MoinsActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showMultPage() {
        this.idGame = MultActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    private void showDivPage() {
        this.idGame = DivisionActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    public void launchActivity(){
        Intent roomActivityIntent = new Intent(this, RoomActivity.class);
        startActivity(roomActivityIntent);
    }

    public void showSqrtActivity(){
        this.idGame = SqrActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    public void showTrigoActivity(){
        this.idGame = TrigoActivity.id;
        DataProvider.getInstance().getMyFirebaseHelper().createGame(this.idGame);
        this.launchActivity();
    }

    public String getIdGame(){
        return this.idGame;
    }
    public int getIdSession(){ return this.id_session; }
}