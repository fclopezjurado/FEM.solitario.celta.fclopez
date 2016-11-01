package es.upm.miw.SolitarioCelta.logic;

import java.io.Serializable;

/**
 * Created by franlopez on 30/10/2016.
 */

public class Game implements Serializable {
    private String boardState;

    public Game(String boardState) {
        this.setBoardState(boardState);
    }

    public String getBoardState() {
        return boardState;
    }

    private void setBoardState(String boardState) {
        this.boardState = boardState;
    }
}
