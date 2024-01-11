package game;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.nio.file.Paths;

public class Save implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SAVE_FILE_PATH = "save/";
    private static final File SAVE_FILE;
    static {
        File saveFile = null;
        try {
            saveFile = Paths.get(Save.class.getClassLoader().getResource(SAVE_FILE_PATH + "save.ser").toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        SAVE_FILE = saveFile;
    }


    public Save() {
    }

    public static void save(Model model) {
        System.out.println("Tentative de sauvegarde");
        System.out.println("path: " + SAVE_FILE);

        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in save.ser");
        } catch (java.io.IOException i) {
            i.printStackTrace();
        }
    }

    public static Model load() {
        Model model = null;
        try {
            FileInputStream fileIn = new FileInputStream(SAVE_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            model = (Model) in.readObject();
            in.close();
            fileIn.close();
        } catch (java.io.IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Model class not found");
            c.printStackTrace();
            return null;
        }
        return model;
    }

    public static boolean isSaveExist() {
        return SAVE_FILE.exists();
    }

    public static void deleteSave() {
        java.io.File f = new java.io.File(SAVE_FILE_PATH + "save.ser");
        f.delete();
    }


}
