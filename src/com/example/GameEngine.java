package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameEngine {

    /** The number of players playing the game */
    private static final int NUMBER_OF_PLAYERS = 4;

    /** The score a player must reach to win the game */
    private static final int SCORE_REQUIRED_TO_WIN = 200;

    private static List<Player> playerList;

    public static void main(String [] arguments) {
        playerList = createPlayerList();
        System.out.print("The winner of the game is Player " + getWinnerOfGame());
    }

    public static List<Player> createPlayerList() {
        return new ArrayList<>(Arrays.asList(Player.getPlayers()));
    }

    public static Player getWinnerOfGame() {
        Player winner = new Player();
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (playerList.get(i).getTotalScore() >= SCORE_REQUIRED_TO_WIN) {
                winner = playerList.get(i);
            }
        }
        return winner;
    }
}