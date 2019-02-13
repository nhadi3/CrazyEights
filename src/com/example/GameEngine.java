package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameEngine {

    /** The starting number of cards in a player's hand. */
    private static final int NUMBER_OF_CARDS_IN_STARTING_HAND = 5;

    /** The number of players playing the game */
    private static final int NUMBER_OF_PLAYERS = 4;

    /** The score a player must reach to win the game */
    private static final int SCORE_REQUIRED_TO_WIN = 200;

    /** The index of the top card of the deck */
    private static final int INDEX_OF_TOP_CARD_OF_DECK = 0;

    private static List<Player> playerList;
    private static Card topPileCard;
    private static boolean gameEnded = false;
    private static List<Card> deckOfCards;

    /**
     * Main method that runs the game and prints the winner of the game.
     */
    public static void main(String [] arguments) {
        playerList = createPlayerList();
        System.out.print("The winner of the game is Player " + getWinnerOfGame());
    }

    /**
     * Creates a player list.
     * @return a list of players
     */
    public static List<Player> createPlayerList() {
        return new ArrayList<>(Arrays.asList(Player.getPlayers()));
    }

    /**
     * Gives the players their starting hand.
     */
    public static void giveInitialCardsToPlayers() {
        deckOfCards = Card.getDeck();
        Collections.shuffle(deckOfCards);

        for (int i = 0; i < playerList.size(); i++) {
            List<Card> temp = new ArrayList<>();
            for (int j = 0; j < NUMBER_OF_CARDS_IN_STARTING_HAND; j++) {
                temp.add(deckOfCards.get(INDEX_OF_TOP_CARD_OF_DECK));
                deckOfCards.remove(INDEX_OF_TOP_CARD_OF_DECK);
            }
            playerList.get(i).receiveInitialCards(temp);
            temp.clear();
        }
    }

    /**
     * Gets the starting top pile card.
     * @return the starting card for the game
     */
    public static Card getStartingTopPileCard() {
        if (deckOfCards.get(INDEX_OF_TOP_CARD_OF_DECK).getRank() == Card.Rank.EIGHT) {
            deckOfCards.add(deckOfCards.get(INDEX_OF_TOP_CARD_OF_DECK));
            deckOfCards.remove(INDEX_OF_TOP_CARD_OF_DECK);
        }

        topPileCard = deckOfCards.get(INDEX_OF_TOP_CARD_OF_DECK);
        deckOfCards.remove(INDEX_OF_TOP_CARD_OF_DECK);
        return topPileCard;
    }

    /**
     * Adds the score for each player in the case of a tie.
     */
    public static void addTiedMatchScore() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            for (int j = 0; j < NUMBER_OF_PLAYERS; j++) {
                for (int k = 0; k < playerList.get(j).getMyCards().size(); k++) {
                    if (i != j) {
                        playerList.get(i).addScore(playerList.get(j).getMyCards().get(k).getPointValue());
                    }
                }
            }
        }
    }

    /**
     * Adds score to the player who won the match.
     * @param winnerOfMatch - the winner of the match
     */
    public static void addMatchScore(Player winnerOfMatch) {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (winnerOfMatch != playerList.get(i)) {
                for (int j = 0; j < playerList.get(i).getMyCards().size(); j++) {
                    winnerOfMatch.addScore(playerList.get(i).getMyCards().get(j).getPointValue());
                }
            }
        }
    }

    /**
     * Gets the winner, the player with the most points, of the game.
     * @return the winner of the game
     */
    public static Player getWinnerOfGame() {
        Player winner = new Player();

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (playerList.get(i).getTotalScore() >= SCORE_REQUIRED_TO_WIN) {
                winner = playerList.get(i);
            }
        }

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (playerList.get(i).getTotalScore() > winner.getTotalScore()) {
                winner = playerList.get(i);
            }
        }

        return winner;
    }
}