package com.example.projet1a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.projet1a.profile.PlayerProfile;

public class SuccessActivity extends AppCompatActivity {

    private PlayerProfile player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        this.player = DataProvider.getInstance().getPlayer();

        String[] successId = new String[this.player.getSuccess().getAllSuccess().size()];
        for(int i = 0; i < this.player.getSuccess().getAllSuccess().size(); i++){
            successId[i] = this.player.getSuccess().getAllSuccess().valueAt(i).getId();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.successPageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomAdapter customAdapter = new CustomAdapter(successId);
        recyclerView.setAdapter(customAdapter);
    }
}