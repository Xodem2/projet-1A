package com.example.projet1a;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet1a.profile.PlayerProfile;
import com.example.projet1a.profile.Success;

import java.util.LinkedList;

public class CustomPlayerRankingAdapter extends RecyclerView.Adapter<CustomPlayerRankingAdapter.ViewHolder> {

    private LinkedList<PlayerProfile> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView playerRank;
        private final TextView playerNickname;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.successItemTextFillId);
            playerRank = (TextView) view.findViewById(R.id.playerRankingRankId);
            playerNickname = (TextView) view.findViewById(R.id.playerRankingPlayerNicknameId);
        }

        public TextView getTextView() {
            return textView;
        }

        public TextView getPlayerRank(){
            return playerRank;
        }

        public TextView getPlayerNickname(){
            return playerNickname;
        }
    }


    public CustomPlayerRankingAdapter(LinkedList<PlayerProfile> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.player_ranking, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //Success success = DataProvider.getInstance().getPlayer().getSuccess().getSuccessById(localDataSet[position]);
        PlayerProfile player = localDataSet.get(position);
        viewHolder.getPlayerNickname().setText(player.getNickname() + " (" + String.valueOf(player.getStats().getTotalScore()) + ")");
        viewHolder.getPlayerRank().setText(String.valueOf(position + 1));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}


