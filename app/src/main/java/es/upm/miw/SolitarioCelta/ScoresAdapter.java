package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by franlopez on 30/10/2016.
 */

public class ScoresAdapter extends ArrayAdapter {
    private Context context;
    private int scoreStructureID;
    private ArrayList<Score> scoresList;

    public ScoresAdapter(Context context, int scoreStructureID, List<Score> scoresList) {
        super(context, scoreStructureID, scoresList);
        this.setContext(context);
        this.setScoreStructureID(scoreStructureID);
        this.setScoresList((ArrayList<Score>) scoresList);
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private int getScoreStructureID() {
        return scoreStructureID;
    }

    private void setScoreStructureID(int scoreStructureID) {
        this.scoreStructureID = scoreStructureID;
    }

    private ArrayList<Score> getScoresList() {
        return scoresList;
    }

    private void setScoresList(ArrayList<Score> scoresList) {
        this.scoresList = scoresList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;

        if (null != convertView)
            view = (LinearLayout) convertView;
        else {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = (LinearLayout) layoutInflater.inflate(this.getScoreStructureID(), parent, false);
        }

        TextView playerNameTextView = (TextView) view.findViewById(R.id.playerName);
        playerNameTextView.setText(this.getScoresList().get(position).getPlayerName());

        TextView numberOfPiecesTextView = (TextView) view.findViewById(R.id.numberOfPieces);
        numberOfPiecesTextView.setText(String.format(Locale.getDefault(), "%d",
                this.getScoresList().get(position).getNumberOfPieces()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",
                Locale.getDefault());
        Date scoreDate = new Date(this.getScoresList().get(position).getCurrentDate());
        TextView scoreDateTextView = (TextView) view.findViewById(R.id.scoreDate);
        scoreDateTextView.setText(simpleDateFormat.format(scoreDate));

        return view;
    }
}
