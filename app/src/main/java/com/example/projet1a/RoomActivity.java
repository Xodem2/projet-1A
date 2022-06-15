package com.example.projet1a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class RoomActivity extends AppCompatActivity implements View.OnClickListener {

    int gameId;
    private EditText editTextTextPersonName;
    private TextView roomtext;
    private TextView player1Nickname;
    private TextView player2Nickname;
    private Button startButton;
    private ImageButton launchButton;

    public final static String id="Room";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        //this.launchButton = (ImageButton) findViewById(R.id.launchButton);
        //this.launchButton.setOnClickListener(this);

        this.roomtext = (TextView) findViewById(R.id.room_id);
        this.roomtext.setText(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());

        this.player1Nickname = (TextView) findViewById(R.id.activityRoomPlayer1NicknameFillId);
        this.player2Nickname = (TextView) findViewById(R.id.activityRoomPlayer2NicknameFillId);
        String[] playerNicknames = DataProvider.getInstance().getMyFirebaseHelper().getPlayersNickname();
        this.player1Nickname.setText(playerNicknames[0]);
        this.player2Nickname.setText(playerNicknames[1]);

        this.startButton = (Button) findViewById(R.id.activityRoomStartButtonId);
        this.startButton.setOnClickListener(this);

        DataProvider.getInstance().getPlayer().setIsFinished(10);
    }

    @Override
    public void onClick(View v) {
        //if (v.getId() == this.launchButton.getId()) this.showFractionPage();
        if(v.getId() == this.startButton.getId()) this.doStart();
    }

    private void doStart() {
        DataProvider.getInstance().getMyFirebaseHelper().startGame(DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn());
        String id_game = DataProvider.getInstance().getMyFirebaseHelper().getGameIdWherePlayerIn().split("-")[0];
        if (id_game.equals(SommeActivity.id)) {
            startActivity(new Intent(this, SommeActivity.class));
        }
        else if (id_game.equals(DivisionActivity.id)) {
            startActivity(new Intent(this, DivisionActivity.class));
        }
        else if (id_game.equals(Equation1Activity.id)) {
            startActivity(new Intent(this, Equation1Activity.class));
        }
        else if (id_game.equals(Equation2Activity.id)) {
            startActivity(new Intent(this, Equation2Activity.class));
        }
        else if (id_game.equals(FractionActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(MatricesActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(MoinsActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(MultActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(PythagoreActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(SqrActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(ThalesActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(TrigoActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
        else if (id_game.equals(VectorActivity.id)) {
            startActivity(new Intent(this, FractionActivity.class));
        }
    }

    public void showWaitingRoom(){
        Intent waitingRoomActivityIntent = new Intent(this, RoomActivity.class);
        startActivity(waitingRoomActivityIntent);
    }
}
