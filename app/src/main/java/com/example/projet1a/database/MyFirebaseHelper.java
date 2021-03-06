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
    private static final String NODE_GAME_GAMEID_SCOREP1 = "_scoreP1";
    private static final String NODE_GAME_GAMEID_SCOREP2 = "_scoreP2";

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

    private void removePlayerInExistingGame(){
        // remove player in existing joined game
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
    }

    public void createGame(String gameId){
        this.deleteGamesCreatedByPlayer();
        this.removePlayerInExistingGame();
        this.databaseReferenceGame.child(this.generateUniqueGameId(gameId)).child(NODE_GAME_GAMEID_P1ID).setValue(
                DataProvider.getInstance().getPlayer().getID()
        );
    }

    public void joinGame(String gameId){
        this.removePlayerInExistingGame();
        this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_P2ID).setValue(
                DataProvider.getInstance().getPlayer().getID()
        );
    }

    public void startGame(String gameId){
        // set total questions to precised value
        String playerId = DataProvider.getInstance().getPlayer().getID();
        String p1Id = "";
        String p2Id = "";
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).exists())
            p1Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).getValue().toString();
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).exists())
            p2Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).getValue().toString();

        Log.v("localDB", "p1Id = " + p1Id);
        Log.v("localDB", "p2Id = " + p2Id);

        int remainingP1 = TOTAL_QUESTIONS;
        int remainingP2 = TOTAL_QUESTIONS;

        if(playerId.equals(p1Id)){ // player is p1
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).setValue(remainingP1);
            if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).exists())
                this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).setValue(
                        Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).getValue().toString())
                );
            else
                this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).setValue(remainingP2);
        }
        else if(playerId.equals(p2Id)) { // player is p2
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).setValue(TOTAL_QUESTIONS);
            if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).exists())
                this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).setValue(
                        Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).getValue().toString())
                );
            else
                this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).setValue(remainingP1);
        }
        else{ // should never happen
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).setValue(remainingP1);
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).setValue(remainingP2);
        }
    }

    public void updateGame(String gameId){
        // called when user answers a queston

        String p1Id = "";
        String p2Id = "";
        int remainingP1 = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).getValue().toString());
        int remainingP2 = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).getValue().toString());
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

    public boolean playerFinished(String gameId){
        // return true if player has remaining questions equals to 0

        // test with remaining
        boolean finished = false;
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

        if(DataProvider.getInstance().getPlayer().getID().equals(p1Id)){
            if(remainingP1 <= 1) finished = true;
        }
        else if(DataProvider.getInstance().getPlayer().getID().equals(p2Id)){
            if(remainingP2 <= 1) finished = true;
        }

        // test with score
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).exists()
        && this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).exists()){
            finished = true;
        }

        return finished;
    }

    public boolean player1Finished(String gameId){
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).exists()) return true;
        return false;
    }

    public boolean player2Finished(String gameId){
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).exists()) return true;
        return false;
    }

    public boolean opponentFinished(String gameId){
        // return true if player has remaining questions equals to 0

        // test using remaining
        boolean finished = false;
        String p1Id = "";
        String p2Id = "";
        int remainingP1 = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP1).getValue().toString());
        int remainingP2 = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_REMAININGP2).getValue().toString());
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

        if(this.getOpponentId(gameId).equals(p1Id)){
            if(remainingP1 <= 1) finished = true;
        }
        else if(this.getOpponentId(gameId).equals(p2Id)){
            if(remainingP2 <= 1) finished = true;
        }

        // test with score
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).exists()
                && this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).exists()){
            finished = true;
        }

        return finished;
    }

    public String getOpponentId(String gameId){
        String p1Id = "";
        String p2Id = "";
        Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
        DataSnapshot game;
        while(games.hasNext()) {
            game = games.next();
            Iterator<DataSnapshot> gameIterator = game.getChildren().iterator();
            DataSnapshot gameNode;
            while (gameIterator.hasNext()) {
                gameNode = gameIterator.next();
                if (gameNode.getKey().equals(NODE_GAME_GAMEID_P1ID)) p1Id = gameNode.getValue().toString();
                else if (gameNode.getKey().equals(NODE_GAME_GAMEID_P2ID)) p2Id = gameNode.getValue().toString();
            }
        }

        String opponentId = "";

        if(p1Id.equals(DataProvider.getInstance().getPlayer().getID())) opponentId = p2Id;
        else if(p2Id.equals(DataProvider.getInstance().getPlayer().getID())) opponentId = p1Id;

        return opponentId;
    }

    public DatabaseReference getFinishedGameStatusReference(){
        return this.firebaseDatabase.getReference(NODE_GAME);
    }

    public void updateScore(int playerScore){
        String gameId = this.getGameIdWherePlayerIn();
        String playerId = DataProvider.getInstance().getPlayer().getID();

        String p1Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).getValue().toString();
        String p2Id = "";
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).exists())
            p2Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).getValue().toString();

        if(playerId.equals(p1Id)){ // player is p1
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).setValue(playerScore);
        }
        else if(playerId.equals(p2Id)){ // player is p2
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).setValue(playerScore);
        }
    }

    public int getPlayerScore(String gameId){
        int playerScore = 0;
        String playerId = DataProvider.getInstance().getPlayer().getID();
        String p1Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).getValue().toString();
        String p2Id = "";
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).exists())
            p2Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).getValue().toString();

        if(playerId.equals(p1Id)){ // player is p1
            if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).exists())
                playerScore = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).getValue().toString());
        }
        else if(playerId.equals(p2Id)){ // player is p2
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).setValue(playerScore);
            if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).exists())
                playerScore = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).getValue().toString());
        }
        return playerScore;
    }

    public int getOpponentScore(String gameId){
        int opponentScore = 0;
        String playerId = DataProvider.getInstance().getPlayer().getID();
        String p1Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).getValue().toString();
        String p2Id = "";
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).exists())
            p2Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).getValue().toString();

        if(!playerId.equals(p1Id)){ // opponent is p1
            if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).exists())
                opponentScore = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).getValue().toString());
        }
        else if(!playerId.equals(p2Id)){ // player is p2
            this.databaseReferenceGame.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).setValue(opponentScore);
            if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).exists())
                opponentScore = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).getValue().toString());
        }
        return opponentScore;
    }

    public int getPlayer1Score(String gameId){
        int p1Score = 0;
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).exists())
            p1Score = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP1).getValue().toString());
        return p1Score;
    }

    public int getPlayer2Score(String gameId){
        int p2Score = 0;
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).exists())
            p2Score = Integer.parseInt(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_SCOREP2).getValue().toString());
        return p2Score;
    }

    public void deleteGame(String gameId){
        if(this.gameSnapshot.child(gameId).exists()){
            Iterator<DataSnapshot> games = this.gameSnapshot.getChildren().iterator();
            DataSnapshot game;
            while(games.hasNext()){
                game = games.next();
                if(game.getKey().equals(gameId))
                    game.getRef().removeValue();
            }
        }
    }

    public String getPlayer1Id(String gameId){
        String p1Id = "";
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).exists())
            p1Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P1ID).getValue().toString();
        return p1Id;
    }

    public String getPlayer2Id(String gameId){
        String p2Id = "";
        if(this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).exists())
            p2Id = this.gameSnapshot.child(gameId).child(NODE_GAME_GAMEID_P2ID).getValue().toString();
        return p2Id;
    }

    public String getPlayer1Nickname(String gameId){
        String p1Nickname = "";
        String p1Id = this.getPlayer1Id(gameId);
        if(this.playerSnapshot.child(p1Id).child(NODE_PLAYERNICKNAME).exists())
            p1Nickname = this.playerSnapshot.child(p1Id).child(NODE_PLAYERNICKNAME).getValue().toString();
        else p1Nickname = "player1";
        return p1Nickname;
    }

    public String getPlayer2Nickname(String gameId){
        String p2Nickname = "";
        String p2Id = this.getPlayer2Id(gameId);
        if(this.playerSnapshot.child(p2Id).child(NODE_PLAYERNICKNAME).exists())
            p2Nickname = this.playerSnapshot.child(p2Id).child(NODE_PLAYERNICKNAME).getValue().toString();
        else p2Nickname = "player2";
        return p2Nickname;
    }

    public LinkedList<PlayerProfile> getPlayers(){
        if(this.playerSnapshot == null) return null;

        LinkedList<PlayerProfile> playersList = new LinkedList<>();
        Iterator<DataSnapshot> players = this.playerSnapshot.getChildren().iterator();
        DataSnapshot player;
        while(players.hasNext()){
            player = players.next();
            Iterator<DataSnapshot> playerInfo = player.getChildren().iterator();
            DataSnapshot info;
            String id = "";
            String nickname = "player";
            int spScore = 0;
            int mpScore = 0;
            PlayerProfile playerToAdd = new PlayerProfile();
            if(player.getKey().equals(NODE_PLAYERID)) id = player.getValue().toString();
            while(playerInfo.hasNext()){
                info = playerInfo.next();
                if(info.getKey().equals(NODE_PLAYERNICKNAME))
                    nickname = info.getValue().toString();
                else if(info.getKey().equals(NODE_PLAYERMPSCORE))
                    mpScore = Integer.parseInt(info.getValue().toString());
                else if(info.getKey().equals(NODE_PLAYERSPSCORE))
                    spScore = Integer.parseInt(info.getValue().toString());
            }
            playerToAdd.setId(id);
            playerToAdd.setNickname(nickname);
            playerToAdd.getStats().updateSingleplayerScore(spScore);
            playerToAdd.getStats().updateMultiplayerScore(mpScore);
            playersList.add(playerToAdd);

        }
        return playersList;
    }

}
