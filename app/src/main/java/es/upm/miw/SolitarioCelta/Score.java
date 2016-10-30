package es.upm.miw.SolitarioCelta;

/**
 * Created by franlopez on 30/10/2016.
 */

public class Score {
    private String playerName;
    private int numberOfPieces;
    private long currentDate;

    public Score(String playerName, int numberOfPieces, long currentDate) {
        this.setPlayerName(playerName);
        this.setNumberOfPieces(numberOfPieces);
        this.setCurrentDate(currentDate);
    }

    public String getPlayerName() {
        return playerName;
    }

    private void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    private void setNumberOfPieces(int numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public long getCurrentDate() {
        return currentDate;
    }

    private void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }
}
