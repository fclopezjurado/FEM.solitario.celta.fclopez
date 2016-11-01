package es.upm.miw.SolitarioCelta;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by franlopez on 26/10/2016.
 */

public class FileHandler<T> {

    public static final String GAME = "game.ser";
    public static final String SCORES = "scores.ser";

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

    public void writeFile(Context context, ArrayList<T> object, int openMode) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(this.getFileName(),
                    openMode);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> readFile(Context context) {
        ArrayList<T> fileData = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(this.getFileName());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            fileData = (ArrayList<T>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fileData;
    }

    public boolean fileExist(Context context) {
        File file = new File(context.getFilesDir(), this.getFileName());
        return file.exists();
    }
}
