import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ColorSampler extends JFrame{

    private Color[] colors;

    public static void main (String argv []){
        new ColorSampler("Color Sampler");
    }

    public ColorSampler (String title){
        super(title);
        setSize(350, 150);
        addWindowListener(new WindowDestroyer());

        colors

        getContentPane().setLayout(new BorderLayout);

        setVisible(true);
    }

    private class WindowDestroyer extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }

    public 
}