package game;

import game.ui.Model;

import java.io.Serializable;

public class Save extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String SAVE_FILE_PATH = "save/";

    public Save() {
        super();
    }

    public void save() {
        try {
            java.io.FileOutputStream fileOut = new java.io.FileOutputStream(SAVE_FILE_PATH + "save.ser");
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);
            out.writeObject(this);
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
            java.io.FileInputStream fileIn = new java.io.FileInputStream(SAVE_FILE_PATH + "save.ser");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);
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

}
