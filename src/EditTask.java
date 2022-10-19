import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditTask extends JDialog{

    String name;
    String priority;
    String columnVal;

    boolean buttonClicked = false;
     JPanel addTaskPanel;
     JComboBox priorityName;
     JTextField textField1;
     JButton cancelButton;
     JButton applyButton;
    JComboBox columnName;


    public EditTask(JFrame parent) {

        System.out.println("here in edit");
        setTitle("Edit Task");

        setContentPane(addTaskPanel);

        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(parent);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }

}