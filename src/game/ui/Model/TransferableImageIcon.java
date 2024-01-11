package game.ui.Model;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class TransferableImageIcon implements Transferable {
    private ImageIcon icon;

    public TransferableImageIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { new DataFlavor(ImageIcon.class, "Image Icon") };
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.getRepresentationClass() == ImageIcon.class;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor.getRepresentationClass() == ImageIcon.class) {
            return icon;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
