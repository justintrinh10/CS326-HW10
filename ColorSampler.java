import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ColorSampler extends JFrame{

    public static void main (String argv []){
        new ColorSampler("Color Sampler");
    }

    public ColorSampler (String title){
        super(title);
        setBounds(100, 100, 250, 100);
        addWindowListener(new WindowDestroyer());

        setVisible(true);
    }

    private class WindowDestroyer extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
}