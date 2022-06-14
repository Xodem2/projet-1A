package com.example.projet1a.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalBDDmulti extends SQLiteOpenHelper {

    // db settings
    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 1;

    // table : partie
    private static final String TABLE_PARTIE_NAME = "partie";
    private static final String TABLE_PARTIE_COLUMN_GAME_ID = "_gameId"; // primary key
    private static final String TABLE_PARTIE_COLUMN_GAME = "_game";
    private static final String TABLE_PARTIE_COLUMN_JOIGNABLE = "_joignable";

    // table : joue
    private static final String TABLE_JOUE_NAME = "joue";
    private static final String TABLE_JOUE_COLUMN_GAME_ID = "_gameId";
    private static final String TABLE_JOUE_COLUMN_PLAYER_ID = "_playerId";
    private static final String TABLE_JOUE_COLUMN_SCORE = "_score";
    private static final String TABLE_JOUE_COLUMN_TERMINE = "_termine";

    private Context context;

    public LocalBDDmulti(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTablePartie(db);
        createTableJoue(db);
    }

    private void createTablePartie(SQLiteDatabase db){
        String queryPlayer =
                "CREATE TABLE " + TABLE_PARTIE_NAME
                        + " (" + TABLE_PARTIE_COLUMN_GAME_ID + " INT PRIMARY KEY, "
                        + TABLE_PARTIE_COLUMN_GAME + " VARCHAR(256),"
                        + TABLE_PARTIE_COLUMN_JOIGNABLE + " INT);";
        db.execSQL(queryPlayer);
    }

    private void createTableJoue(SQLiteDatabase db){
        String queryPlayer =
                "CREATE TABLE " + TABLE_JOUE_NAME
                        + " (" + TABLE_JOUE_COLUMN_GAME_ID + " INT REFERENCES "+TABLE_PARTIE_NAME+"("+TABLE_PARTIE_COLUMN_GAME_ID+") ON DELETE CASCADE, "
                        + TABLE_JOUE_COLUMN_PLAYER_ID + " VARCHAR(256), "
                        + TABLE_JOUE_COLUMN_SCORE + " INT, "
                        + TABLE_JOUE_COLUMN_TERMINE + " boolean, PRIMARY KEY("+TABLE_JOUE_COLUMN_GAME_ID+", "+TABLE_JOUE_COLUMN_PLAYER_ID+"));";
        db.execSQL(queryPlayer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOUE_NAME);
        onCreate(db);
    }

    public int find_game(String idPersonne, String nameGame){
        SQLiteDatabase db = this.getReadableDatabase();
        String queryPartie = "SELECT * FROM " + TABLE_PARTIE_NAME + " WHERE " + TABLE_PARTIE_COLUMN_JOIGNABLE + " = false;";
        Cursor cursorPartie = db.rawQuery(queryPartie, null);

        while(cursorPartie.moveToNext()){
            int gameIdIndex = cursorPartie.getColumnIndex(TABLE_PARTIE_COLUMN_GAME_ID);
            int gameNameIndex = cursorPartie.getColumnIndex(TABLE_PARTIE_COLUMN_GAME);
            int joignableIndex = cursorPartie.getColumnIndex(TABLE_PARTIE_COLUMN_JOIGNABLE);

            int gameId = cursorPartie.getInt(gameIdIndex);
            String gameName = cursorPartie.getString(gameNameIndex);
            int joignable = cursorPartie.getInt(joignableIndex);
            if (joignable==1 && gameName==nameGame){
//                il faut maintenant verifier que l'on est pas dans la game
                String queryJoue = "SELECT * FROM " + TABLE_JOUE_NAME + " WHERE " + TABLE_JOUE_COLUMN_GAME_ID + "="+gameId+" and "+TABLE_JOUE_COLUMN_PLAYER_ID+"=="+idPersonne+";";
                Cursor cursorJeu = db.rawQuery(queryPartie, null);
                boolean not_exist = true;
                while(cursorJeu.moveToNext()) {
                    not_exist = false;
                }
                if (not_exist){
                    return gameId;
                }
            }
        }
        return -1;
    }

    public int new_id_game(){
        int retour = 1;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryPartie = "SELECT COUNT("+TABLE_PARTIE_COLUMN_GAME_ID+") FROM " + TABLE_PARTIE_NAME + ";";
        Cursor cursorPartie = db.rawQuery(queryPartie, null);
        while(cursorPartie.moveToNext()){
            int gameIdIndex = cursorPartie.getColumnIndex("COUNT("+TABLE_PARTIE_COLUMN_GAME_ID+")");
            return cursorPartie.getInt(gameIdIndex);
        }
        return retour;
    }

    public void update(String requete){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(requete);
    }

    public String add_partie(String nomGame){
        int id = new_id_game();
        String requete = "ADD INTO "+TABLE_PARTIE_NAME+" VALUES("+String.valueOf(id)+", "+nomGame+", 0);";
//        TODO verifier que c'est bien 0 pour pouvoir rejoindre
        return requete;
    }

    public String add_partie(String nomGame, int idPartie){
        String requete = "ADD INTO "+TABLE_PARTIE_NAME+" VALUES("+String.valueOf(idPartie)+", "+nomGame+", 0);";
//        TODO verifier que c'est bien 0 pour pouvoir rejoindre
        return requete;
    }
}














