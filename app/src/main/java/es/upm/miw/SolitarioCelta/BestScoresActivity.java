package es.upm.miw.SolitarioCelta;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by franlopez on 30/10/2016.
 */

public class BestScoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.scores);

        ListView scoresListView = (ListView) findViewById(R.id.scoresList);
        ArrayList<Score> scores = (ArrayList<Score>) getIntent().getSerializableExtra("SCORES");
        ScoresAdapter adapter = new ScoresAdapter(this, R.layout.score, scores);

        scoresListView.setAdapter(adapter);
    }
}
