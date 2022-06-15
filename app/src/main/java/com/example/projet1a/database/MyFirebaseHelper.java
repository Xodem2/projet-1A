package com.example.projet1a.database;

import android.icu.text.Edits;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.projet1a.DataProvider;
import com.example.projet1a.profile.PlayerProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.LinkedList;

public class MyFirebaseHelper implements ValueEventListener {

    private static final String URL = "https://myfirebase-2e044-default-rtdb.europe-west1.firebasedatabase.app/";

    private static final int TOTAL_QUESTIONS = 10;

    // games
    private static final String NODE_GAME = "_game";
    private static final String NODE_GAME_GAMEID = "_gameId";
    private static final String NODE_GAME_GAMEID_P1ID = "_player1Id";
    private static final String NODE_GAME_GAMEID_P2ID = "_player2Id";
    private static final String NODE_GAME_GAMEID_REMAININGP1 = "_remainingP1";
    private static final String NODE_GAME_GAMEID_REMAININGP2 = "_remainingP2";

    // players
    private static final String NODE_PLAYER = "_player";
    private static final String NODE_PLAYERID = "_playerId";
    private static final String NODE_PLAYERNICKNAME = "_playerNickname";
    private static final String NODE_PLAYERAGE = "_playerAge";
    private static final String NODE_PLAYERSPSCORE = "_playerSpScore";
    private static final String NODE_PLAYERMPSCORE = "_playerMpScore";
    private static final String NODE_PLAYERTOTALSCORE = "_playerTotalScore";

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReferenceGame;
    private DatabaseReference databaseReferencePlayer;

    private DataSnapshot gameSnapshot;
    private DataSnapshot playerSnapshot;

    public MyFirebaseHelper(){
        this.firebaseDatabase = FirebaseDatabase.getInstance(URL);
        this.databaseReferenceGame = this.firebaseDatabase.getReference(NODE_GAME);
        this.databaseReferencePlayer = this.firebaseDatabase.getReference(NODE_PLAYER);

        this.firebaseDatabase.getReference().addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        Iterator<DataSnapshot> snapshotIterator = snapshot.getChildren().iterator();
        DataSnapshot snapshot1;
        while(snapshotIterator.hasNext()){
            snapshot1 = snapshotIterator.next();
            if(snapshot1.getKey().equals(NODE_GAME)) this.gameSnapshot = snapshot1;
            else if(snapshot1.getKey().equals(NODE_PLAYER)) this.playerSnapshot = snapshot1;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    private void deleteGamesCreatedByPlayer(){
        // delete other games created by player
        if(this.gameSnapshot != null) {
            Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
            DataSnapshot game;
            LinkedList<DataSnapshot> nodeToDelete = new LinkedList<>();
            while (games.hasNext()) {
                game = games.next();
                Iterator<DataSnapshot> gameIterator = game.getChildren().iterator();
                DataSnapshot gameNode;
                while (gameIterator.hasNext()) {
                    gameNode = gameIterator.next();
                    if (gameNode.getKey().equals(NODE_GAME_GAMEID_P1ID) && gameNode.getValue().equals(DataProvider.getInstance().getPlayer().getID())) {
                        nodeToDelete.add(game);
                    }
                }
            }
            for (DataSnapshot node : nodeToDelete) node.getRef().removeValue();
        }
    }

    public void createGame(String gameId){
        this.deleteGamesCreatedByPlayer();
        this.databaseReferenceGame.child(this.generateUniqueGameId(gameId)).child(NODE_GAME_GAMEID_P1ID).setValue(
                DataProvider.getInstance().getPlayer().getID()
        );
    }

    public void joinGame(String gameId){
        // remove game joined by player
        if(this.gameSnapshot != null) {
            this.deleteGamesCreatedByPlayer();
            Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
            DataSnapshot game;
            while (games.hasNext()) {
                game = games.next();
                Iterator<DataSnapshot> gameIterator = game.getChildren().iterator();
                DataSnapshot gameNode;
                while (gameIterator.hasNext()) {
                    gameNode = gameIterator.next();
                    if (gameNode.getKey().equals(NODE_GAME_GAMEID_P2ID) && gameNode.getValue().equals(DataProvider.getInstance().getPlayer().getID()))
                        gameNode.getRef().removeValue();
                }
            }
        }
        this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_P2ID).setValue(
                DataProvider.getInstance().getPlayer().getID()
        );
    }

    public void startGame(String gameId){
        // set total questions to precised value
        this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).setValue(TOTAL_QUESTIONS);
        this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).setValue(TOTAL_QUESTIONS);
    }

    public void updateGame(String gameId){
        // called when user answers a queston
        // TODO : finish updateGame

        String p1Id = "";
        String p2Id = "";
        int remainingP1 = TOTAL_QUESTIONS;
        int remainingP2 = TOTAL_QUESTIONS;
        Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
        DataSnapshot game;
        while(games.hasNext()){
            game = games.next();
            Iterator<DataSnapshot> gameIterator = game.getChildren().iterator();
            DataSnapshot gameNode;
            while(gameIterator.hasNext()){
                gameNode = gameIterator.next();
                if(gameNode.getKey().equals(NODE_GAME_GAMEID_P1ID)) p1Id = gameNode.getValue().toString();
                else if(gameNode.getKey().equals(NODE_GAME_GAMEID_P2ID)) p2Id = gameNode.getValue().toString();
                else if(gameNode.getKey().equals(NODE_GAME_GAMEID_REMAININGP1)) remainingP1 = Integer.parseInt(gameNode.getValue().toString());
                else if(gameNode.getKey().equals(NODE_GAME_GAMEID_REMAININGP1)) remainingP2 = Integer.parseInt(gameNode.getValue().toString());
            }
        }

        if(DataProvider.getInstance().getPlayer().getID().equals(p1Id)) remainingP1--;
        else if(DataProvider.getInstance().getPlayer().getID().equals(p2Id)) remainingP2--;

        Log.v("localDB", String.valueOf(remainingP2));

        this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).setValue(remainingP1);
        this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).setValue(remainingP2);
    }

    public String generateUniqueGameId(String gameId){
        // TODO : fix to be able to make more than 10 games
        // return gameID + - + number
        // example : VectorActivity-133
        //         : VectorActivity-135
        if(this.gameSnapshot == null) return gameId + "-" + 1;
        Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
        DataSnapshot game;
        int number = 1;
        while(games.hasNext()){
            game = games.next();
            if(game.getKey().split("-")[0].equals(gameId)){
                if(game.getKey().equals(gameId + "-" + number)) number++;
            }
        }
        return gameId + "-" + number;
    }

    public void savePlayer(PlayerProfile player){
        this.databaseReferencePlayer.child(player.getID()).child(NODE_PLAYERNICKNAME).setValue(player.getNickname());
        this.databaseReferencePlayer.child(player.getID()).child(NODE_PLAYERAGE).setValue(player.getAge());
        this.databaseReferencePlayer.child(player.getID()).child(NODE_PLAYERSPSCORE).setValue(player.getStats().getSingleplayerScore());
        this.databaseReferencePlayer.child(player.getID()).child(NODE_PLAYERMPSCORE).setValue(player.getStats().getMultiplayerScore());
        this.databaseReferencePlayer.child(player.getID()).child(NODE_PLAYERTOTALSCORE).setValue(player.getStats().getTotalScore());
    }

    public String getGameIdWherePlayerIn(){
        Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
        DataSnapshot game;
        while(games.hasNext()){
            game = games.next();
            Iterator<DataSnapshot> gameIterator = game.getChildren().iterator();
            DataSnapshot gameNode;
            while(gameIterator.hasNext()){
                gameNode = gameIterator.next();
                if(gameNode.getKey().equals(NODE_GAME_GAMEID_P1ID) || gameNode.getKey().equals(NODE_GAME_GAMEID_P2ID)){
                    if(gameNode.getValue().equals(DataProvider.getInstance().getPlayer().getID())) return game.getKey();
                }
            }
        }
        return "should not appear";
    }

    public String[] getPlayersNickname(){
        // TODO : fix this
        // current player name
        String[] nickname = new String[2];
        if(DataProvider.getInstance().getPlayer().getNickname() != null) nickname[0] = DataProvider.getInstance().getPlayer().getNickname();
        else nickname[0] = "player";

        // opponent id
        Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
        DataSnapshot game;
        String opponentId = "";
        while(games.hasNext()){
            game = games.next();
            Iterator<DataSnapshot> gameIterator = game.getChildren().iterator();
            DataSnapshot gameNode;
            while(gameIterator.hasNext()){
                gameNode = gameIterator.next();
                if(gameNode.getKey().equals(NODE_GAME_GAMEID_P1ID) || gameNode.getKey().equals(NODE_GAME_GAMEID_P2ID)){
                    if(!gameNode.getValue().toString().equals(DataProvider.getInstance().getPlayer().getID())) opponentId = gameNode.getValue().toString();
                }
            }
        }

        // iterator nickname
        Iterator<DataSnapshot> players = this.playerSnapshot.getChildren().iterator();
        DataSnapshot player;
        while(players.hasNext()){
            player = players.next();
            if(player.getKey().equals(opponentId)){
                Iterator<DataSnapshot> playerInfo = player.getChildren().iterator();
                DataSnapshot info;
                while(playerInfo.hasNext()){
                    info = playerInfo.next();
                    if(info.getKey().equals(NODE_PLAYERNICKNAME)) nickname[1] = info.getValue().toString();
                    else nickname[1] = "player";
                }
            }
        }

        return nickname;
    }
}
