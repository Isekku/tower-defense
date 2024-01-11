package game;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Save implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SAVE_FILE_PATH = "save\\";

    public Save() {
    }

    public static void save(Model model) {
        System.out.println("Tentative de sauvegarde");
        System.out.println("path: " + SAVE_FILE_PATH + "save.ser");
        try {
            FileOutputStream fileOut = new FileOutputStream(SAVE_FILE_PATH + "save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(model);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in save.ser");
        } catch (java.io.IOException i) {
            i.printStackTrace();
        }
    }

    public static Save load() {
        Save save = null;
        try {
            FileInputStream fileIn = new FileInputStream(SAVE_FILE_PATH + "save.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            save = (Save) in.readObject();
            in.close();
            fileIn.close();
        } catch (java.io.IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Save class not found");
            c.printStackTrace();
            return null;
        }
        return save;
    }

    public static boolean isSaveExist() {
        java.io.File f = new java.io.File(SAVE_FILE_PATH + "save.ser");
        return f.exists() && !f.isDirectory();
    }

    public static void deleteSave() {
        java.io.File f = new java.io.File(SAVE_FILE_PATH + "save.ser");
        f.delete();
    }


}
