package es.upm.miw.SolitarioCelta.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import es.upm.miw.SolitarioCelta.dialogs.SaveGameDialogFragment;
import es.upm.miw.SolitarioCelta.logic.JuegoCelta;
import es.upm.miw.SolitarioCelta.R;
import es.upm.miw.SolitarioCelta.dialogs.ResetGameDialogFragment;
import es.upm.miw.SolitarioCelta.dialogs.SaveScoreDialogFragment;

public class MainActivity extends Activity {

    private JuegoCelta juego;
    private final String GRID_KEY = "GRID_KEY";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        juego = new JuegoCelta();
        mostrarTablero();
    }

    /**
     * Se ejecuta al pulsar una ficha
     * Las coordenadas (i, j) se obtienen a partir del nombre, ya que el botón
     * tiene un identificador en formato pXY, donde X es la fila e Y la columna
     *
     * @param v Vista de la ficha pulsada
     */
    public void fichaPulsada(View v) {
        String resourceName = getResources().getResourceEntryName(v.getId());
        int i = resourceName.charAt(1) - '0';   // fila
        int j = resourceName.charAt(2) - '0';   // columna

        juego.jugar(i, j);

        mostrarTablero();
        if (juego.juegoTerminado()) {
            new SaveScoreDialogFragment().show(getFragmentManager(), "SAVE SCORE DIALOG");
        }
    }

    /**
     * Visualiza el tablero
     */
    public void mostrarTablero() {
        RadioButton button;
        String strRId;
        String prefijoIdentificador = getPackageName() + ":id/p"; // formato: package:type/entry
        int idBoton;

        for (int i = 0; i < JuegoCelta.TAMANIO; i++)
            for (int j = 0; j < JuegoCelta.TAMANIO; j++) {
                strRId = prefijoIdentificador + Integer.toString(i) + Integer.toString(j);
                idBoton = getResources().getIdentifier(strRId, null, null);
                if (idBoton != 0) { // existe el recurso identificador del botón
                    button = (RadioButton) findViewById(idBoton);
                    button.setChecked(juego.obtenerFicha(i, j) == JuegoCelta.FICHA);
                }
            }
    }

    /**
     * Guarda el estado del tablero (serializado)
     *
     * @param outState Bundle para almacenar el estado del juego
     */
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(GRID_KEY, juego.serializaTablero());
        super.onSaveInstanceState(outState);
    }

    /**
     * Recupera el estado del juego
     *
     * @param savedInstanceState Bundle con el estado del juego almacenado
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String grid = savedInstanceState.getString(GRID_KEY);
        juego.deserializaTablero(grid);
        mostrarTablero();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcAjustes:
                startActivity(new Intent(this, SCeltaPrefs.class));
                return true;
            case R.id.opcAcercaDe:
                startActivity(new Intent(this, AcercaDe.class));
                return true;
            case R.id.opcReiniciarPartida:
                new ResetGameDialogFragment().show(getFragmentManager(), "RESET GAME DIALOG");
                return true;
            case R.id.opcGuardarPartida:
                new SaveGameDialogFragment().show(getFragmentManager(), "SAVE GAME DIALOG");
                return true;
            case R.id.opcRecuperarPartida:
                this.juego.loadGame(this);
                return true;
            case R.id.opcMejoresResultados:
                this.juego.bestScores(this);
                return true;

            // TODO!!! resto opciones

            default:
                Toast.makeText(
                        this,
                        getString(R.string.txtSinImplementar),
                        Toast.LENGTH_SHORT
                ).show();
        }
        return true;
    }

    /**
     * Getter for "juego" class attribute
     *
     * @return an instance of "JuegoCelta" class stored in "juego" class attribute.
     */
    public JuegoCelta getJuego() {
        return this.juego;
    }
}
