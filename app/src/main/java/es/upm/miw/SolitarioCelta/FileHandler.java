package es.upm.miw.SolitarioCelta;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by franlopez on 26/10/2016.
 */

public class FileHandler {

    public static final String GAME = "game.txt";

    private String fileName;

    public FileHandler(String fileName) {
        this.setFileName(fileName);
    }

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(Context context, String fileData, int openMode) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(this.getFileName(),
                    openMode);
            fileOutputStream.write(fileData.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile(Context context) throws IOException {
        String fileData = "";
        String fileLine;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                context.openFileInput(this.getFileName())));
        fileLine = bufferedReader.readLine();
        while (fileLine != null) {
            fileData += fileLine;
            fileLine = bufferedReader.readLine();
        }

        return fileData;
    }
}
