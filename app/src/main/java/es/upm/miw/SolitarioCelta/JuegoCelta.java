package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class JuegoCelta {
    public static final int TAMANIO = 7;
    public static final int HUECO = 0;
    public static final int FICHA = 1;
    private static final int NUM_MOVIMIENTOS = 4;
    private int[][] tablero;

    private static final int[][] TABLERO_INICIAL = {
            {HUECO, HUECO, FICHA, FICHA, FICHA, HUECO, HUECO},
            {HUECO, HUECO, FICHA, FICHA, FICHA, HUECO, HUECO},
            {FICHA, FICHA, FICHA, FICHA, FICHA, FICHA, FICHA},
            {FICHA, FICHA, FICHA, FICHA, FICHA, FICHA, FICHA},
            {FICHA, FICHA, FICHA, FICHA, FICHA, FICHA, FICHA},
            {HUECO, HUECO, FICHA, FICHA, FICHA, HUECO, HUECO},
            {HUECO, HUECO, FICHA, FICHA, FICHA, HUECO, HUECO}
    };
    private static final int[][] desplazamientos = {
            {0, 2},   // Dcha
            {0, -2},   // Izda
            {2, 0},   // Abajo
            {-2, 0}    // Arriba
    };
    private int numFichas;
    private int iSeleccionada, jSeleccionada;   // coordenadas origen ficha
    private int iSaltada, jSaltada;             // coordenadas ficha sobre la que se hace el movimiento

    private enum Estado {
        ESTADO_SELECCION_FICHA, ESTADO_SELECCION_DESTINO, ESTADO_TERMINADO
    }

    private Estado estadoJuego;

    /**
     * Constructor
     * Inicializa el tablero y el estado del juego
     */
    public JuegoCelta() {
        numFichas = 32;
        tablero = new int[TAMANIO][TAMANIO];
        for (int i = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                tablero[i][j] = TABLERO_INICIAL[i][j];
        tablero[TAMANIO / 2][TAMANIO / 2] = HUECO;   // hueco en posición central

        estadoJuego = Estado.ESTADO_SELECCION_FICHA;
    }

    /**
     * Devuelve el contenido de una posición del tablero
     *
     * @param i fila del tablero
     * @param j columna del tablero
     * @return contenido
     */
    public int obtenerFicha(int i, int j) {
        return tablero[i][j];
    }

    /**
     * @return Número de fichas en el tablero
     */
    public int numeroFichas() {
        return numFichas;
    }

    /**
     * Determina si el movimiento (i1, j1) a (i2, j2) es aceptable
     *
     * @param i1 fila origen
     * @param j1 columna origen
     * @param i2 fila destino
     * @param j2 columna destino
     * @return valor lógico
     */
    private boolean movimientoAceptable(int i1, int j1, int i2, int j2) {

        if (tablero[i1][j1] == HUECO || tablero[i2][j2] == FICHA)
            return false;

        if ((j1 == j2 && Math.abs(i2 - i1) == 2)
                || (i1 == i2 && Math.abs(j2 - j1) == 2)) {
            iSaltada = (i1 + i2) / 2;
            jSaltada = (j1 + j2) / 2;
            if (tablero[iSaltada][jSaltada] == FICHA)
                return true;
        }

        return false;
    }

    /**
     * Recibe las coordenadas de la posición pulsada y dependiendo del estado, realiza la acción
     *
     * @param iPulsada coordenada fila
     * @param jPulsada coordenada columna
     */
    public void jugar(int iPulsada, int jPulsada) {
        if (estadoJuego == Estado.ESTADO_SELECCION_FICHA) {
            iSeleccionada = iPulsada;
            jSeleccionada = jPulsada;
            estadoJuego = Estado.ESTADO_SELECCION_DESTINO;
        } else if (estadoJuego == Estado.ESTADO_SELECCION_DESTINO) {
            if (movimientoAceptable(iSeleccionada, jSeleccionada, iPulsada, jPulsada)) {
                estadoJuego = Estado.ESTADO_SELECCION_FICHA;

                // Actualizar tablero
                numFichas--;
                tablero[iSeleccionada][jSeleccionada] = HUECO;
                tablero[iSaltada][jSaltada] = HUECO;
                tablero[iPulsada][jPulsada] = FICHA;

                if (juegoTerminado())
                    estadoJuego = Estado.ESTADO_TERMINADO;
            } else { // El movimiento no es aceptable, la última ficha pasa a ser la seleccionada
                iSeleccionada = iPulsada;
                jSeleccionada = jPulsada;
            }
        }
    }

    /**
     * Determina si el juego ha terminado (no se puede realizar ningún movimiento)
     *
     * @return valor lógico
     */
    public boolean juegoTerminado() {

        for (int i = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                if (tablero[i][j] == FICHA) {
                    for (int k = 0; k < NUM_MOVIMIENTOS; k++) {
                        int p = i + desplazamientos[k][0];
                        int q = j + desplazamientos[k][1];
                        if (p >= 0 && p < TAMANIO && q >= 0 && q < TAMANIO
                                && tablero[p][q] == HUECO
                                && TABLERO_INICIAL[p][q] == FICHA)
                            if (movimientoAceptable(i, j, p, q))
                                return false;
                    }
                }

        return true;
    }

    /**
     * Serializa el tablero, devolviendo una cadena de 7x7 caracteres (dígitos 0 o 1)
     *
     * @return tablero serializado
     */
    public String serializaTablero() {
        String str = "";
        for (int i = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                str += Integer.toString(tablero[i][j]);
        return str;
    }

    /**
     * recupera el estado del tablero a partir de su representación serializada
     *
     * @param str representación del tablero
     */
    public void deserializaTablero(String str) {
        for (int i = 0, cont = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                tablero[i][j] = str.charAt(cont++) - '0';
    }

    /**
     * Recupera el juego a su estado inicial
     */
    public void reiniciar() {
        numFichas = 32;
        for (int i = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                tablero[i][j] = TABLERO_INICIAL[i][j];
        tablero[TAMANIO / 2][TAMANIO / 2] = HUECO;   // hueco en posición central

        estadoJuego = Estado.ESTADO_SELECCION_FICHA;
    }

    /**
     * Function to know what is the number of pieces in the board.
     *
     * @return int the number of pieces in board.
     */
    private int numberOfPieces() {
        int numberOfPieces = 0;

        for (int i = 0; i < TAMANIO; i++)
            for (int j = 0; j < TAMANIO; j++)
                if (this.tablero[i][j] == JuegoCelta.FICHA)
                    numberOfPieces++;

        return numberOfPieces;
    }

    private String serializeScore(String playerName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss", Locale.UK);
        Date date = new Date();
        return playerName + ";" + this.numberOfPieces() + ";" + dateFormat.format(date);
    }

    /**
     * Function to save current game in an application file called "game.txt".
     *
     * @param main the active application context (MainActivity).
     */
    public void saveGame(MainActivity main) {
        FileHandler fileHandler = new FileHandler(FileHandler.GAME);
        fileHandler.writeFile(main, main.juego.serializaTablero(), Context.MODE_PRIVATE);
        Toast.makeText(main, main.getString(R.string.toastForSaveGame),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Function to load a game stored in an application file called "game.txt". If this file
     * exists, a dialog is shown to confirm the load. The load be done after confirmation.
     *
     * @param main the active application context (MainActivity).
     */
    public void loadGame(MainActivity main) {
        FileHandler fileHandler = new FileHandler(FileHandler.GAME);

        if (fileHandler.fileExist(main))
            new LoadGameDialogFragment().show(main.getFragmentManager(), "LOAD GAME DIALOG");
        else
            Toast.makeText(main, main.getString(R.string.toastForLoadGameWhenThereAreNotGames),
                    Toast.LENGTH_SHORT).show();
    }

    /**
     * Function to load a game stored in an application file called "game.txt". This file stores
     * a serialized board.
     *
     * @param main the active application context (MainActivity).
     */
    public void loadGameInBoard(MainActivity main) {
        FileHandler fileHandler = new FileHandler(FileHandler.GAME);
        String fileData = fileHandler.readFile(main);
        main.juego.deserializaTablero(fileData);
    }

    /**
     * Function to save the score of the last game in an application file called "scores.txt".
     *
     * @param main       the active application context (MainActivity).
     * @param playerName the name of player who played the last game.
     */
    public void saveScore(MainActivity main, String playerName) {
        FileHandler fileHandler = new FileHandler(FileHandler.SCORES);
        fileHandler.writeFile(main, main.juego.serializeScore(playerName), Context.MODE_APPEND);
        Toast.makeText(main, main.getString(R.string.toastToSaveScore),
                Toast.LENGTH_SHORT).show();
    }
}
