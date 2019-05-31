package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

public class MyDropTargetListener extends DropTargetAdapter {

    private DropTarget dropTarget;
    private JPanel p;

    public MyDropTargetListener(JPanel panel) {
        this.p = panel;
        dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);

    }

    @Override
    public void drop(DropTargetDropEvent event) {
        try {
            DropTarget test = (DropTarget) event.getSource();
            Component ca = (Component) test.getComponent();
            Point dropPoint = ca.getMousePosition();
            Transferable tr = event.getTransferable();

            if (event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                //final ImageIcon icon = (ImageIcon) label.getIcon();
                // get image from imageicon
                //Image image = icon.getImage();

                // cast it to bufferedimage
                //BufferedImage ico = (BufferedImage) image;

                ImageIcon ico = (ImageIcon) tr.getTransferData(DataFlavor.imageFlavor);
                //Icon ico = (Icon) tr.getTransferData(DataFlavor.imageFlavor);

                if (ico != null) {
                    JLabel label1 = new JLabel(ico);
                    //p.add(new JLabel((ImageIcon) ico));
                    p.add(label1);
                    //JLabel label1 = new JLabel(new ImageIcon(im, searchableKey));
                    //add(label1);
                    MyDragGestureListener dlistener = new MyDragGestureListener();
                    DragSource ds1 = new DragSource();
                    ds1.createDefaultDragGestureRecognizer(label1, DnDConstants.ACTION_COPY, dlistener);




                    p.revalidate();
                    p.repaint();
                    event.dropComplete(true);


                }
            } else {
                event.rejectDrop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            event.rejectDrop();
        }
    }
}