package com.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

    private List<Card> testCards = new ArrayList<>();
    private Player testPlayer = new Player();
    private static List<Card> deckOfCards = Card.getDeck();

    @Before
    public void setUp() {
        testCards = deckOfCards.subList(0,5);
        testPlayer.init(1, null);
        testPlayer.receiveInitialCards(testCards);
    }

    //Group of tests for get methods.
    @Test
    public void getCorrectCardToPlay() {
        testPlayer.getCardToPlay(deckOfCards.get(6));
        assertEquals(deckOfCards.get(0), testPlayer.playCard());
    }

    @Test
    public void getCorrectInitialCards() {
        assertEquals(testCards, testPlayer.getMyCards());
    }

    @Test
    public void getCorrectGreatestSuitInHand() {
        assertEquals(Card.Suit.DIAMONDS, testPlayer.getGreatestSuit());
    }

    @Test
    public void getCorrectPlayerId() {
        assertEquals(1, testPlayer.getPlayerId());
    }

    @Test
    public void correctlyResetTheGame() {
        testPlayer.reset();
        List<Card> empty = new ArrayList<>();
        assertEquals(empty, testPlayer.getMyCards());
    }

    //Group of tests to determine whether player should draw or not.
    @Test
    public void correctlyDetermineIfPlayerShouldDrawCard() {
        assertTrue(testPlayer.shouldDrawCard(deckOfCards.get(21), deckOfCards.get(21).getSuit()));
    }

    @Test
    public void correctlyDetermineIfPlayerShouldNotDrawCardWithSuit() {
        assertFalse(testPlayer.shouldDrawCard(deckOfCards.get(6), deckOfCards.get(6).getSuit()));
    }

    @Test
    public void correctlyDetermineIfPlayerShouldNotDrawCardWithRank() {
        assertFalse(testPlayer.shouldDrawCard(deckOfCards.get(14), deckOfCards.get(14).getSuit()));
    }

    @Test
    public void correctlyDetermineIfPlayerShouldNotDrawCardWithEight() {
        testPlayer.receiveCard(deckOfCards.get(7));
        assertFalse(testPlayer.shouldDrawCard(deckOfCards.get(20), deckOfCards.get(20).getSuit()));
    }
}
