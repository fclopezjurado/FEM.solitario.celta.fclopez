package es.upm.miw.SolitarioCelta.logic;

import java.io.Serializable;

/**
 * Created by franlopez on 30/10/2016.
 */

public class Game implements Serializable {
    private String boardState;
    private String gameName;

    public Game(String boardState, String gameName) {
        this.setBoardState(boardState);
        this.setGameName(gameName);
    }

    public String getBoardState() {
        return boardState;
    }

    private void setBoardState(String boardState) {
        this.boardState = boardState;
    }

    public String getGameName() {
        return gameName;
    }

    private void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
