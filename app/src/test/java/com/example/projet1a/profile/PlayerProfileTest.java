package com.example.projet1a.profile;

import junit.framework.TestCase;

import org.junit.Test;

public class PlayerProfileTest extends TestCase {

    public void testGetID() {
        PlayerProfile player1 = new PlayerProfile();
        PlayerProfile player2 = new PlayerProfile();
        assertNotSame(player1.getID(), player2.getID());
    }

    public void testSetNickname() {
        PlayerProfile player = new PlayerProfile();
        String nickname = "abc";
        player.setNickname("abc");
        assertEquals(player.getNickname(), nickname);
    }

    public void testGetNickname() {
        testSetNickname();
    }

    public void testGetStats() {
        PlayerProfile player = new PlayerProfile();
        assertEquals(player.getStats().getSingleplayerScore(), 0);
    }
}