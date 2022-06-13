package com.example.projet1a;

import android.graphics.Color;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projet1a.profile.GameStats;
import com.example.projet1a.profile.Success;

import org.w3c.dom.Text;

public class CustomGameStatsAdapter extends RecyclerView.Adapter<CustomGameStatsAdapter.ViewHolder> {

    private String[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView gameTitle;
        private final TextView correctAnswer;
        private final TextView totalAnswer;
        private final TextView correctInARow;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            gameTitle = (TextView) view.findViewById(R.id.gameStatsItemGameTitleFillId);
            correctAnswer = (TextView) view.findViewById(R.id.gameStatsItemCorrectAnswerIFilld);
            totalAnswer = (TextView) view.findViewById(R.id.gameStatsItemTotalAnswerIFilld);
            correctInARow = (TextView) view.findViewById(R.id.gameStatsItemInARowFillId);
        }

        public TextView getCorrectAnswer(){
            return correctAnswer;
        }

        public TextView getTotalAnswer(){
            return totalAnswer;
        }

        public TextView getCorrectInARow(){
            return correctInARow;
        }

        public TextView getGameTitle(){
            return gameTitle;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomGameStatsAdapter(String[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.game_stats_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        GameStats gameStat = DataProvider.getInstance().getPlayer().getStats().getGameStatsById(localDataSet[position]);
        viewHolder.getCorrectAnswer().setText(String.valueOf(gameStat.getTotalCorrects()));
        viewHolder.getTotalAnswer().setText(String.valueOf(gameStat.getTotalAnswered()));
        viewHolder.getCorrectInARow().setText(String.valueOf(gameStat.getCorrectsInARow()));
        viewHolder.getGameTitle().setText(gameStat.getId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}




