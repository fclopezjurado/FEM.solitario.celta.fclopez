package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by franlopez on 27/10/2016.
 */

public class LoadGameDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.loadGameDialogTitle))
                .setMessage(getString(R.string.loadGameDialogMessage))
                .setPositiveButton(
                        getString(R.string.YesLoadGameDialogOption),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                main.juego.loadGameInBoard(main);
                                main.mostrarTablero();
                                Toast.makeText(main, main.getString(R.string.toastForLoadGame),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.NoLoadGameDialogOption),
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
