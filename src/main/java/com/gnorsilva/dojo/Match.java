package com.gnorsilva.dojo;

class Match {
    private final MatchScoreBoard matchScoreBoard;
    private int playerOnePoints;
    private int playerTwoPoints;
    private Player servingPlayer = Player.ONE;


    public interface MatchScoreBoard {
        void servingPlayer(Player player);
    }

    public Match(MatchScoreBoard matchScoreBoard) {
        this.matchScoreBoard = matchScoreBoard;
    }

    public void winsRally(Player player) {
        if (player == servingPlayer) {
            if (player == Player.ONE) {
                playerOnePoints++;
            } else {
                playerTwoPoints++;
            }
        }

        servingPlayer = player;
        matchScoreBoard.servingPlayer(player);
    }

    public int playerOnePoints() {
        return playerOnePoints;
    }

    public int playerTwoPoints() {
        return playerTwoPoints;
    }

    public Player playerServing() {
        return servingPlayer;
    }
}
