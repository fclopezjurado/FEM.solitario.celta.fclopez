package es.upm.miw.SolitarioCelta.logic;

import java.util.Comparator;

/**
 * Created by franlopez on 01/11/2016.
 */

public class ScoresComparator implements Comparator<Score> {

    @Override
    public int compare(Score score1, Score score2) {
        return (score1.getNumberOfPieces() > score2.getNumberOfPieces()) ? 1 : (
                score1.getNumberOfPieces() < score2.getNumberOfPieces()) ? -1 : 0;
    }
}
