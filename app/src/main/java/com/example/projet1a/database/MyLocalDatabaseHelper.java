package com.example.projet1a.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.projet1a.profile.GameStats;
import com.example.projet1a.profile.PlayerProfile;
import com.example.projet1a.profile.PlayerStatistics;

public class MyLocalDatabaseHelper extends SQLiteOpenHelper {

    // TODO : updatePlayer() mehtod

    // db settings
    private static final String DATABASE_NAME = "player.db";
    private static final int DATABASE_VERSION = 1;

    // table : player
    private static final String TABLE_PLAYER_NAME = "Player";
    private static final String TABLE_PLAYER_COLUMN_ID = "_playerId"; // primary key
    private static final String TABLE_PLAYER_COLUMN_NICKNAME = "_playerNickname";
    private static final String TABLE_PLAYER_COLUMN_AGE = "_playerAge";

    // table : player stats
    private static final String TABLE_PLAYER_STATS_NAME = "PlayerStats";
    private static final String TABLE_PLAYER_STATS_COLUMN_SPSCORE = "_playerStatsSpscore";
    private static final String TABLE_PLAYER_STATS_COLUMN_MPSCORE = "_playerStatsMpscore";
    private static final String TABLE_PLAYER_STATS_COLUMN_TOTALSCORE = "_playerStatsTotalscore";
    private static final String TABLE_PLAYER_STATS_COLUMN_PLAYERID = "_playerId"; // primary key

    // table : game stats
    private static final String TABLE_GAMESTATS_NAME = "GameStats";
    private static final String TABLE_GAMESTATS_COLUMN_GAMEID = "_gameStatsGameId"; // primary key (1)
    private static final String TABLE_GAMESTATS_COLUMN_TOTALCORRECT = "_gameStatsTotalCorrect";
    private static final String TABLE_GAMESTATS_COLUMN_TOTALANSWERED = "_gameStatsTotalAnswered";
    private static final String TABLE_GAMESTATS_COLUMN_ANSWERSINAROW = "_gameStatsCorrectInARow";
    private static final String TABLE_GAMESTATS_COLUMN_PLAYERID = "_gameStatsplayerId"; // primary key (2)

    private Context context;

    public MyLocalDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryPlayer =
                "CREATE TABLE " + TABLE_PLAYER_NAME
                + " (" + TABLE_PLAYER_COLUMN_ID + " VARCHAR(256) PRIMARY KEY, "
                + TABLE_PLAYER_COLUMN_NICKNAME + " VARCHAR(256), "
                + TABLE_PLAYER_COLUMN_AGE + " INTEGER);";

        String queryPlayerStats =
                "CREATE TABLE " + TABLE_PLAYER_STATS_NAME
                + " (" + TABLE_PLAYER_STATS_COLUMN_SPSCORE + " INTEGER, "
                + TABLE_PLAYER_STATS_COLUMN_MPSCORE + " INTEGER, "
                + TABLE_PLAYER_STATS_COLUMN_TOTALSCORE + " INTEGER, "
                + TABLE_PLAYER_STATS_COLUMN_PLAYERID + " VARCHAR(256) PRIMARY KEY);";

        String queryGameStats =
                "CREATE TABLE " + TABLE_GAMESTATS_NAME
                + " (" + TABLE_GAMESTATS_COLUMN_GAMEID + " VARCHAR(256), "
                + TABLE_GAMESTATS_COLUMN_TOTALCORRECT + " INTEGER, "
                + TABLE_GAMESTATS_COLUMN_TOTALANSWERED + " INTEGER, "
                + TABLE_GAMESTATS_COLUMN_ANSWERSINAROW + " INTEGER, "
                + TABLE_GAMESTATS_COLUMN_PLAYERID + " VARCHAR(256), "
                + "PRIMARY KEY("+ TABLE_GAMESTATS_COLUMN_PLAYERID + ", " + TABLE_GAMESTATS_COLUMN_GAMEID + "));";

        db.execSQL(queryPlayer);
        db.execSQL(queryPlayerStats);
        db.execSQL(queryGameStats);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_STATS_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMESTATS_NAME);
        onCreate(db);
    }

    public void addPlayer(PlayerProfile player){
        if(player == null) return;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // player
        cv.put(TABLE_PLAYER_COLUMN_ID, player.getID());
        cv.put(TABLE_PLAYER_COLUMN_NICKNAME, player.getNickname());
        cv.put(TABLE_PLAYER_COLUMN_AGE, player.getAge());
        db.insert(TABLE_PLAYER_NAME, null, cv);

        // stats
        PlayerStatistics stats = player.getStats();
        if(stats == null) return;
        cv = new ContentValues();
        cv.put(TABLE_PLAYER_STATS_COLUMN_SPSCORE, stats.getSingleplayerScore());
        cv.put(TABLE_PLAYER_STATS_COLUMN_MPSCORE, stats.getMultiplayerScore());
        cv.put(TABLE_PLAYER_STATS_COLUMN_TOTALSCORE, stats.getTotalScore());
        cv.put(TABLE_PLAYER_STATS_COLUMN_PLAYERID, player.getID());
        db.insert(TABLE_PLAYER_STATS_NAME, null, cv);

        // game stats
        ArrayMap<String, GameStats> gStats = stats.getGameStats();
        if(gStats == null) return;
        for(int i = 0; i < gStats.size(); i++){
            cv = new ContentValues();
            GameStats gStat = gStats.valueAt(i);
            cv.put(TABLE_GAMESTATS_COLUMN_GAMEID, gStat.getId());
            cv.put(TABLE_GAMESTATS_COLUMN_TOTALCORRECT, gStat.getTotalCorrects());
            cv.put(TABLE_GAMESTATS_COLUMN_TOTALANSWERED, gStat.getTotalAnswered());
            cv.put(TABLE_GAMESTATS_COLUMN_ANSWERSINAROW, gStat.getCorrectsInARow());
            cv.put(TABLE_GAMESTATS_COLUMN_PLAYERID, player.getID());
            db.insert(TABLE_GAMESTATS_NAME, null, cv);
        }
    }

    public PlayerProfile loadPlayer(){
        SQLiteDatabase db = this.getReadableDatabase();

        PlayerProfile player = new PlayerProfile();

        // player
        String queryPlayer = "SELECT * FROM " + TABLE_PLAYER_NAME;
        Cursor cursorPlayer = db.rawQuery(queryPlayer, null);
        int idIndex = cursorPlayer.getColumnIndex(TABLE_PLAYER_COLUMN_ID);
        int nicknameIndex = cursorPlayer.getColumnIndex(TABLE_PLAYER_COLUMN_NICKNAME);
        int ageIndex = cursorPlayer.getColumnIndex(TABLE_PLAYER_COLUMN_AGE);
        if(!cursorPlayer.moveToFirst()) return null;
        String playerId = cursorPlayer.getString(idIndex);
        String nickname = cursorPlayer.getString(nicknameIndex);
        int playerAge = cursorPlayer.getInt(ageIndex);
        player.setId(playerId);
        player.setNickname(nickname);
        player.setAge(playerAge);

        // TODO : stats + game stats

        return player;
    }

}
