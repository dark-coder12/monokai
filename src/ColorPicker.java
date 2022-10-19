import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorPicker extends JDialog {

     JButton chosenColor;
    JColorChooser colorPicker;
    private JPanel panel1;

    ColorPicker(JFrame parent) {

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout());
        setTitle("Color Picker");
        setContentPane(panel1);

        setMinimumSize(new Dimension(1000, 700));

        setLocationRelativeTo(parent);

        colorPicker = new JColorChooser();
        chosenColor = new JButton("Select Theme");

        panel1.add(colorPicker);
       panel1.add(chosenColor);
      setVisible(true);
    }

    public static void main (String [] args){

        ColorPicker c = new ColorPicker(null);
    }
}
