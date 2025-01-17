import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ColorSampler extends JFrame{
    private static String fileName = "colors.txt";
    private static int numColors = 11;
    private Color[] colors;

    public static void main (String argv []){
        new ColorSampler("Color Sampler");
    }

    public ColorSampler (String title){
        super(title);
        setSize(350, 150);
        addWindowListener(new WindowDestroyer());

        colors = readColorFile(fileName);

        getContentPane().setLayout(new BorderLayout());
    
        setVisible(true);
    }

    private class WindowDestroyer extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }

    private Color[] readColorFile(String f){
        Color[] output = null;
        try{
            FileInputStream stream = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(stream);
            StreamTokenizer tokens = new StreamTokenizer(reader);
            
            output = new Color[numColors];
            for(int i = 0; i < numColors; i ++){
                Color temp = new Color();
                temp.setName((String) tokens.sval);
                tokens.nextToken();
                temp.setRValue((int) tokens.nval);
                tokens.nextToken();
                temp.setGValue((int) tokens.nval);
                tokens.nextToken();
                temp.setBValue((int) tokens.nval);
                tokens.nextToken();
                output[i] = temp;
            }

            stream.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("IOException occurred");
        }
        return output;
    }

    private void writeColorFile(String f){
        try{
            FileOutputStream ostream = new FileOutputStream(f);
            PrintWriter writer = new PrintWriter(ostream);

            for(int i = 0; i < numColors; i ++){
                writer.println(colors[i].getName() + " " + colors[i].getRValue() + " " + colors[i].getGValue() + " " + colors[i].getBValue());
            }

            writer.flush();
            ostream.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch(IOException e) {
            System.out.println("IOException occurred");
        }
    }
}