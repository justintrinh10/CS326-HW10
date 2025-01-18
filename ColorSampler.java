import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;

public class ColorSampler extends JFrame{
    private static String name = "Color Sampler";
    private static String fileName = "colors.txt";
    private static int numColors = 11;
    private PresetColor[] colors;
    private Color curColor;

    private JPanel colorPanel;
    private JTextField redNum;
    private JTextField greenNum;
    private JTextField blueNum;
    private JButton minusRedButton;
    private JButton minusGreenButton;
    private JButton minusBlueButton;
    private JButton plusRedButton;
    private JButton plusGreenButton;
    private JButton plusBlueButton;
    private JButton saveButton;
    private JButton resetButton;
    private JList<String> colorList;

    public static void main (String argv []){
        new ColorSampler(name);
    }

    public ColorSampler (String title){
        super(title);
        setSize(350, 350);
        addWindowListener(new WindowDestroyer());
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 5, 5, 5);

        colors = new PresetColor[numColors];
        colors = readColorFile(fileName);
        curColor = new Color(colors[0].getRValue(), colors[0].getGValue(), colors[0].getBValue());

        colorPanel = new JPanel();
        colorPanel.setBackground(curColor);
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        getContentPane().add(colorPanel, constraints);

        JLabel redLabel = new JLabel("Red:");
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 4;
        constraints.gridheight = 1;
        getContentPane().add(redLabel, constraints);

        JLabel greenLabel = new JLabel("Green:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        getContentPane().add(greenLabel, constraints);   

        JLabel blueLabel = new JLabel("Blue:");
        constraints.gridx = 0;
        constraints.gridy = 6;
        getContentPane().add(blueLabel, constraints);

        redNum = new JTextField(Integer.toString(curColor.getRed()));    
        constraints.gridx = 1;
        constraints.gridy = 4;
        getContentPane().add(redNum, constraints);

        greenNum = new JTextField(Integer.toString(curColor.getGreen()));    
        constraints.gridx = 1;
        constraints.gridy = 5;
        getContentPane().add(greenNum, constraints); 

        blueNum = new JTextField(Integer.toString(curColor.getBlue()));    
        constraints.gridx = 1;
        constraints.gridy = 6;
        getContentPane().add(blueNum, constraints);

        minusRedButton = new JButton("-");
        constraints.gridx = 2;
        constraints.gridy = 4;
        minusRedButton.addActionListener(new ActionHandler());
        getContentPane().add(minusRedButton, constraints);

        minusGreenButton = new JButton("-");
        constraints.gridx = 2;
        constraints.gridy = 5;
        minusGreenButton.addActionListener(new ActionHandler());
        getContentPane().add(minusGreenButton, constraints);

        minusBlueButton = new JButton("-");
        constraints.gridx = 2;
        constraints.gridy = 6;
        minusBlueButton.addActionListener(new ActionHandler());
        getContentPane().add(minusBlueButton, constraints);

        plusRedButton = new JButton("+");
        constraints.gridx = 3;
        constraints.gridy = 4;
        plusRedButton.addActionListener(new ActionHandler());
        getContentPane().add(plusRedButton, constraints);

        plusGreenButton = new JButton("+");
        constraints.gridx = 3;
        constraints.gridy = 5;
        plusGreenButton.addActionListener(new ActionHandler());
        getContentPane().add(plusGreenButton, constraints);

        plusBlueButton = new JButton("+");
        constraints.gridx = 3;
        constraints.gridy = 6;
        plusBlueButton.addActionListener(new ActionHandler());
        getContentPane().add(plusBlueButton, constraints);

        JPanel space1 = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 7;
        getContentPane().add(space1, constraints);

        saveButton = new JButton("Save");
        constraints.gridx = 1;
        constraints.gridy = 7;
        saveButton.addActionListener(new ActionHandler());
        getContentPane().add(saveButton, constraints);

        resetButton = new JButton("Reset");
        constraints.gridx = 2;
        constraints.gridy = 7;
        resetButton.addActionListener(new ActionHandler());
        getContentPane().add(resetButton, constraints);

        JPanel space2 = new JPanel();
        constraints.gridx = 3;
        constraints.gridy = 7;
        getContentPane().add(space2, constraints);

        String[] colorNames = new String[numColors];
        colorNames = getColorNames();
        colorList = new JList<String>(colorNames);
        constraints.gridx = 4;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        constraints.gridheight = 8;
        colorList.addListSelectionListener(new ListHandler());
        getContentPane().add(colorList, constraints);
        getContentPane().add(new JScrollPane(colorList), constraints);

        setVisible(true);
    }

    private class ActionHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == minusRedButton){
                curColor = new Color(curColor.getRed() - 5, curColor.getGreen(), curColor.getBlue());
                setTitle(name + "*");
            }
            else if(e.getSource() == minusGreenButton){
                curColor = new Color(curColor.getRed(), curColor.getGreen() - 5, curColor.getBlue());
                setTitle(name + "*");
            }
            else if(e.getSource() == minusBlueButton){
                curColor = new Color(curColor.getRed(), curColor.getGreen(), curColor.getBlue() - 5);
                setTitle(name + "*");
            }
            else if(e.getSource() == plusRedButton){
                curColor = new Color(curColor.getRed() + 5, curColor.getGreen(), curColor.getBlue());
                setTitle(name + "*");
            }
            else if(e.getSource() == plusGreenButton){
                curColor = new Color(curColor.getRed(), curColor.getGreen() + 5, curColor.getBlue());
                setTitle(name + "*");
            }
            else if(e.getSource() == plusBlueButton){
                curColor = new Color(curColor.getRed(), curColor.getGreen(), curColor.getBlue() + 5);
                setTitle(name + "*");
            }
            else if(e.getSource() == saveButton){
                int i = colorList.getSelectedIndex();
                colors[i].setRValue(curColor.getRed());
                colors[i].setGValue(curColor.getGreen());
                colors[i].setBValue(curColor.getBlue());
                setTitle(name);
                colorList.setListData(getColorNames());
            }
            else if(e.getSource() == resetButton){
                int i = colorList.getSelectedIndex();
                curColor = new Color(colors[i].getRValue(), colors[i].getGValue(), colors[i].getBValue());
                setTitle(name);

            }
            updateColorPanel();
        }
    }

    private class ListHandler implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e){
            if(e.getSource() == colorList){
                if(!e.getValueIsAdjusting()){
                    int i = colorList.getSelectedIndex();
                    curColor = new Color(colors[i].getRValue(), colors[i].getGValue(), colors[i].getBValue());
                    setTitle(name);
                    updateColorPanel();
                }
            }
        }
    }

    private class WindowDestroyer extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            writeColorFile(fileName);
            System.exit(0);
        }
    }

    private void updateColorPanel(){
        colorPanel.setBackground(curColor);
        redNum.setText(Integer.toString(curColor.getRed()));
        greenNum.setText(Integer.toString(curColor.getGreen()));
        blueNum.setText(Integer.toString(curColor.getBlue()));
    }

    private String[] getColorNames(){
        String[] output = new String[numColors];
        for(int i = 0; i < numColors; i ++){
            output[i] = colors[i].getName();
        }
        return output;
    }

    private PresetColor[] readColorFile(String f){
        PresetColor[] output = new PresetColor[numColors];
        try{
            FileInputStream stream = new FileInputStream(f);
            InputStreamReader reader = new InputStreamReader(stream);
            StreamTokenizer tokens = new StreamTokenizer(reader);
            tokens.nextToken();
            
            output = new PresetColor[numColors];
            for(int i = 0; i < numColors; i ++){
                PresetColor temp = new PresetColor();
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