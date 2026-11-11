
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentPortal extends JFrame implements ActionListener {

    JLabel title, nameLabel, rollLabel, courseLabel, emailLabel;
    JTextField nameField, rollField, courseField, emailField;
    JButton registerButton, clearButton;

    StudentPortal() {

        setTitle("Student Registration Portal");
        setSize(400, 350);
        setLayout(new GridLayout(6, 2, 10, 10));

        title = new JLabel("Student Registration Portal");
        nameLabel = new JLabel("Student Name:");
        rollLabel = new JLabel("Roll Number:");
        courseLabel = new JLabel("Course:");
        emailLabel = new JLabel("Email:");

        nameField = new JTextField();
        rollField = new JTextField();
        courseField = new JTextField();
        emailField = new JTextField();

        registerButton = new JButton("Register");
        clearButton = new JButton("Clear");

        add(title);
        add(new JLabel(""));
        add(nameLabel);
        add(nameField);
        add(rollLabel);
        add(rollField);
        add(courseLabel);
        add(courseField);
        add(emailLabel);
        add(emailField);
        add(registerButton);
        add(clearButton);

        registerButton.addActionListener(this);
        clearButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {

            String name = nameField.getText();
            String roll = rollField.getText();
            String course = courseField.getText();
            String email = emailField.getText();

            try {
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/StudentDB",
                    "root",
                    "YOUR_MYSQL_PASSWORD"
                );

                String sql = "INSERT INTO students "
                    + "(name, roll_no, course, email) VALUES (?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, roll);
                ps.setString(3, course);
                ps.setString(4, email);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                    "Student Registered Successfully!");

                con.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Database Error: " + ex.getMessage());
            }
        }

        if (e.getSource() == clearButton) {
            nameField.setText("");
            rollField.setText("");
            courseField.setText("");
            emailField.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentPortal();
    }
}
