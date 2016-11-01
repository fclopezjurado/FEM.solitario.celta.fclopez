package es.upm.miw.SolitarioCelta.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.upm.miw.SolitarioCelta.R;
import es.upm.miw.SolitarioCelta.io.FileHandler;
import es.upm.miw.SolitarioCelta.logic.Score;
import es.upm.miw.SolitarioCelta.adapters.ScoresAdapter;

/**
 * Created by franlopez on 30/10/2016.
 */

public class BestScoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);

        ListView scoresListView = (ListView) findViewById(R.id.scoresList);
        ArrayList<Score> scores = (ArrayList<Score>) getIntent().getSerializableExtra("SCORES");
        ScoresAdapter adapter = new ScoresAdapter(this, R.layout.score, scores);

        scoresListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.best_scores_menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteScores:
                FileHandler<Score> fileHandler = new FileHandler<>(FileHandler.SCORES);
                ListView scoresListView = (ListView) findViewById(R.id.scoresList);

                fileHandler.deleteFileInfo(this, Context.MODE_PRIVATE);
                scoresListView.setAdapter(null);
                Toast.makeText(this, getString(R.string.toastToDeleteScores), Toast.LENGTH_SHORT
                ).show();
                return true;
            default:
                this.finish();
                break;
        }

        return true;
    }
}
