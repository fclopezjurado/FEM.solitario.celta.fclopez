package es.upm.miw.SolitarioCelta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import es.upm.miw.SolitarioCelta.R;
import es.upm.miw.SolitarioCelta.activities.MainActivity;

/**
 * Created by franlopez on 01/11/2016.
 */

public class SaveGameDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(View.inflate(main, R.layout.save_game, null))
                .setTitle(R.string.saveGameDialogTitle)
                .setPositiveButton(
                        R.string.YesSaveGameDialogOption,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String gameName = ((EditText) SaveGameDialogFragment.this
                                        .getDialog().findViewById(R.id.gameName))
                                        .getText().toString();

                                if (gameName.length() > 0)
                                    main.getJuego().saveGame(main, gameName);
                            }
                        }
                )
                .setNegativeButton(
                        R.string.NoSaveGameDialogOption,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SaveGameDialogFragment.this.getDialog().cancel();
                            }
                        }
                );

        return builder.create();
    }
}
