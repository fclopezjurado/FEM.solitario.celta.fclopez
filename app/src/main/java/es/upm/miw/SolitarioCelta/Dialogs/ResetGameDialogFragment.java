package es.upm.miw.SolitarioCelta.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import es.upm.miw.SolitarioCelta.activities.MainActivity;
import es.upm.miw.SolitarioCelta.R;

/**
 * Created by franlopez on 25/10/2016.
 */

public class ResetGameDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.dialogTitle))
                .setMessage(getString(R.string.dialogMessage))
                .setPositiveButton(
                        getString(R.string.YesDialogOption),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                main.getJuego().reiniciar();
                                main.mostrarTablero();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.NoDialogOption),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }
                );

        return builder.create();
    }
}
