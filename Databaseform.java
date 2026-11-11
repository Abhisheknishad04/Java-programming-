package databaseform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseForm extends JFrame implements ActionListener {

    JTextField nameField;
    JPasswordField passwordField;
    JButton submitButton, clearButton;

    DatabaseForm() {

        setTitle("JDBC Database Form");
        setSize(400, 250);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Enter Password:"));
        passwordField = new JPasswordField(20);
        add(passwordField);

        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        add(submitButton);
        add(clearButton);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {

            String name = nameField.getText();
            String password =
                new String(passwordField.getPassword());

            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please enter all fields.");
                return;
            }

            try {
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/StudentDB",
                    "root",
                    "YOUR_MYSQL_PASSWORD"
                );

                String query =
                    "INSERT INTO users (name, password) VALUES (?, ?)";

                PreparedStatement ps =
                    con.prepareStatement(query);

                ps.setString(1, name);
                ps.setString(2, password);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                    "Data saved successfully!");

                ps.close();
                con.close();

                nameField.setText("");
                passwordField.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                    "Database Error: " + ex.getMessage());
            }
        }

        if (e.getSource() == clearButton) {
            nameField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new DatabaseForm();
    }
}

