package es.upm.miw.SolitarioCelta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import es.upm.miw.SolitarioCelta.activities.MainActivity;
import es.upm.miw.SolitarioCelta.R;

/**
 * Created by franlopez on 27/10/2016.
 */

public class LoadGameDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        final CharSequence[] gamesNames = main.getJuego().recoverGamesFromStore(main);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.loadGameDialogTitle))
                .setItems(gamesNames,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                main.getJuego().loadGameInBoard(main, gamesNames[which].toString());
                            }
                        }
                );

        return builder.create();
    }
}
