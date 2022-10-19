import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
class CodeEditor extends JFrame {

    JFrame codeFrame;
    String openedName;

    JTextArea codingArea;
    File chosenFile;
    boolean fileOpened = false;
    CodeEditor()
    {
        codeFrame = new JFrame("Code Editor");

        codeFrame.setSize(1000, 700);
        codeFrame.setLayout(new BorderLayout());

        codingArea = new JTextArea();
        JMenuBar nav = new JMenuBar();
        JMenuBar nav2 = new JMenuBar();

        JScrollPane scroll = new JScrollPane(codingArea);

        JMenu fileMenu = new JMenu("File");
        JMenu colorMenu = new JMenu("Themes");

        JMenuItem newFile = new JMenuItem("New File");
        JMenuItem openFile = new JMenuItem("Open File");
        JMenuItem saveFile = new JMenuItem("Save File");

        JMenuItem openPalette = new JMenuItem("Palette");
        JMenuItem monokaiTheme = new JMenuItem("Monokai");
        JMenuItem solarizedDark = new JMenuItem("Solarized Dark");

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);

        colorMenu.add(openPalette);
        colorMenu.add(monokaiTheme);
        colorMenu.add(solarizedDark);

        nav.add(fileMenu);
        nav2.add(colorMenu);

        codeFrame.setJMenuBar(nav);
        codeFrame.setJMenuBar(nav2);
        codeFrame.add(scroll);

        codeFrame.setVisible(true);

        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser openFCmd = new JFileChooser("f:");
                int dialog = openFCmd.showOpenDialog(null);

                if(dialog == JFileChooser.APPROVE_OPTION){

                    fileOpened = true;
                    chosenFile = new File(openFCmd.getSelectedFile().getAbsolutePath());
                    openedName = openFCmd.getSelectedFile().getName();

                    try{

                        String reader;

                        FileReader fr = new FileReader(chosenFile);
                        BufferedReader br = new BufferedReader(fr);

                        String currText = "";

                        reader = br.readLine();

                        while(reader != null){

                            currText =  currText + " \n" + reader;
                            reader = br.readLine();
                        }

                        codingArea.setText(currText);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "File could not be opened");
                    }
                }
            }
        });

        saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser saveCmd;

                if(fileOpened == true){
                    saveCmd = new JFileChooser(chosenFile.getAbsolutePath());
                    saveCmd.setSelectedFile(new File(openedName));
                }
                else
                saveCmd = new JFileChooser("f:");

                int dialog = saveCmd.showSaveDialog(null);

                if (dialog == JFileChooser.APPROVE_OPTION) {

                    File saveFile = new File(saveCmd.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter w = new FileWriter(saveFile);
                        BufferedWriter writeBuff = new BufferedWriter(w);

                        writeBuff.write(codingArea.getText());
                        writeBuff.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "File could not be saved");
                    }
                }
            }
        });

    }

    public static void main(String args[])
    {
        CodeEditor e = new CodeEditor();
    }
}