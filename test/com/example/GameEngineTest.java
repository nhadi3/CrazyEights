package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GameEngineTest {

    private List<Card> testCards = new ArrayList<>();
    private static List<Player> testPlayerList = new ArrayList<>();
    private Player testPlayer = new Player();
    private static List<Card> deckOfCards = Card.getDeck();

    @Before
    public void setUp() {
        testCards = deckOfCards.subList(0,5);
        testPlayer.init(1, null);
        testPlayer.receiveInitialCards(testCards);
    }

    @Test
    public void getCorrectWinner() {
        testPlayerList.get(2).addScore(205);
        assertEquals(testPlayerList.get(2), GameEngine.getWinnerOfGame());
    }

    @Test
    public void correctlyCreatePlayerList() {
        testPlayerList = new ArrayList<>(Arrays.asList(Player.getPlayers()));
        List<Player> playerList = new ArrayList<>(GameEngine.createPlayerList());
        assertEquals(testPlayerList, playerList);
    }
}
