package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projet1a.profile.PlayerProfile;

public class GameStatsActivity extends AppCompatActivity {

    private PlayerProfile player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);

        this.player = DataProvider.getInstance().getPlayer();

        String[] gameId = new String[this.player.getStats().getGameStats().size()];
        for(int i = 0; i < this.player.getStats().getGameStats().size(); i++){
            gameId[i] = this.player.getStats().getGameStats().valueAt(i).getId();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gameStatsPageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomGameStatsAdapter customAdapter = new CustomGameStatsAdapter(gameId);
        recyclerView.setAdapter(customAdapter);
    }
}