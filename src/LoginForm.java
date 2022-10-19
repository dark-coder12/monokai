import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JDialog {

    public User currentUser = null;
    final String DB_URL = "jdbc:mysql://localhost:3306/monokai";
    final String USERNAME = "root";
    final String PASSWORD = "password";
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel loginPanel;

    public LoginForm(JFrame parent) {

        setTitle("WorkspaceLogin");
        setContentPane(loginPanel);

        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(parent);

        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

               if(loginUser(email, password)){
                   JOptionPane.showMessageDialog(LoginForm.this, "You've been logged in!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                   dispose();
               }
                else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Invalid Credentials", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        setVisible(true);
    }
    private boolean loginUser(String email_, String password_) {

        boolean userExists = false;

        try{
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement sqlStatement = con.createStatement();

            String query = "SELECT * FROM user WHERE email='"+email_+"' AND password='"+password_+"'";

            ResultSet result = sqlStatement.executeQuery(query);

            if (result.next()) {

                userExists = true;
                currentUser = new User();

                currentUser.email = result.getString("email");
                currentUser.password = result.getString("password");
            }

            sqlStatement.close();
            con.close();

        }catch(Exception e){
            System.out.println("Connection invalid.");
        }
        return userExists;
    }

    public static void main(String[] args) {

        LoginForm loginForm = new LoginForm(null);
    }
}