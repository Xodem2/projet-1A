package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projet1a.profile.PlayerProfile;

import java.util.Comparator;
import java.util.LinkedList;

public class PlayerRankingActivity extends AppCompatActivity {

    private LinkedList<PlayerProfile> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_ranking);

        this.playerList = DataProvider.getInstance().getMyFirebaseHelper().getPlayers();
        if(this.playerList != null) this.showRanking();
    }

    @Override
    protected void onResume(){
        super.onResume();

        this.playerList = DataProvider.getInstance().getMyFirebaseHelper().getPlayers();
        if(this.playerList != null) this.showRanking();
    }

    public void showRanking(){
        this.sortByTotalScore();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plagerRankingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomPlayerRankingAdapter customAdapter = new CustomPlayerRankingAdapter(this.playerList);
        recyclerView.setAdapter(customAdapter);
    }

    private void sortByTotalScore(){
        // desc sort
        this.playerList.sort(new Comparator<PlayerProfile>() {
            @Override
            public int compare(PlayerProfile o1, PlayerProfile o2) {
                return - (o1.getStats().getTotalScore() - o2.getStats().getTotalScore());
            }
        });
    }
}