//Create a form containing fields name and password respectively, using Applet as a container. ?



import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {

    Form() {
        setTitle("Login Form");
        setSize(350, 180);
        setLayout(new FlowLayout());

        add(new JLabel("Name:"));
        add(new JTextField(20));

        add(new JLabel("Password:"));
        JPasswordField password = new JPasswordField(20);
        add(password);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Form();
    }
}