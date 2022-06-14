package com.example.projet1a;

import android.graphics.Color;
import android.util.ArrayMap;
import android.util.Log;
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
        private final TextView rate;
        private final TextView maxCorrectInARow;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            gameTitle = (TextView) view.findViewById(R.id.gameStatsItemGameTitleFillId);
            correctAnswer = (TextView) view.findViewById(R.id.gameStatsItemCorrectAnswerIFilld);
            totalAnswer = (TextView) view.findViewById(R.id.gameStatsItemTotalAnswerIFilld);
            correctInARow = (TextView) view.findViewById(R.id.gameStatsItemInARowFillId);
            rate = (TextView) view.findViewById(R.id.gameStatsItemRateFillId);
            maxCorrectInARow = (TextView) view.findViewById(R.id.gameStatsItemMaxInARowFillId);
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

        public TextView getRate(){
            return rate;
        }

        public TextView getMaxCorrectInARow(){
            return maxCorrectInARow;
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
        viewHolder.getGameTitle().setText(this.translateTitle(gameStat.getId()));
        viewHolder.getMaxCorrectInARow().setText(String.valueOf(gameStat.getMaxCorrectsInARow()));

        int rate = (int)( (float) gameStat.getTotalCorrects() * 100 / gameStat.getTotalAnswered());
        if(rate < 40) viewHolder.getRate().setTextColor(Color.parseColor("#ff0000"));
        else if(40 <= rate && rate <= 60) viewHolder.getRate().setTextColor(Color.parseColor("#ffa500"));
        else if(rate > 60) viewHolder.getRate().setTextColor(Color.parseColor("#00ff00"));
        viewHolder.getRate().setText(String.valueOf(rate) + "%");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }

    private String translateTitle(String gameTitle){
        String translated = gameTitle;

        if(gameTitle.equals(DivisionActivity.id)) translated = "Divisions";
        else if(gameTitle.equals(Equation1Activity.id)) translated = "équations 1er degré";
        else if(gameTitle.equals(Equation2Activity.id)) translated = "équations 2eme degré";
        else if(gameTitle.equals(FractionActivity.id)) translated = "Fractions";
        else if(gameTitle.equals(MatricesActivity.id)) translated = "Matrices";
        else if(gameTitle.equals(MoinsActivity.id)) translated = "Différences";
        else if(gameTitle.equals(MultActivity.id)) translated = "Multiplications";
        else if(gameTitle.equals(PythagoreActivity.id)) translated = "Pythagore";
        else if(gameTitle.equals(SommeActivity.id)) translated = "Sommes";
        else if(gameTitle.equals(ThalesActivity.id)) translated = "Thales";
        else if(gameTitle.equals(VectorActivity.id)) translated = "Calcul vectoriel";
        return translated;
    }
}




