
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentCRUD extends JFrame implements ActionListener {

    JLabel nameLabel, passwordLabel;
    JTextField nameField;
    JPasswordField passwordField;
    JButton insertButton, readButton, updateButton, deleteButton;

    Connection con;

    StudentCRUD() {

        setTitle("Student CRUD Portal");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2, 10, 10));

        nameLabel = new JLabel("Student Name:");
        passwordLabel = new JLabel("Password:");

        nameField = new JTextField();
        passwordField = new JPasswordField();

        insertButton = new JButton("Insert");
        readButton = new JButton("Read");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        add(nameLabel);
        add(nameField);
        add(passwordLabel);
        add(passwordField);
        add(insertButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        insertButton.addActionListener(this);
        readButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/StudentDB",
                "root",
                "YOUR_MYSQL_PASSWORD"
            );

            if (e.getSource() == insertButton) {

                String sql = "INSERT INTO students(name, password) VALUES(?,?)";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, password);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Inserted!");
            }

            else if (e.getSource() == readButton) {

                String sql = "SELECT * FROM students";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                StringBuilder data = new StringBuilder();

                while (rs.next()) {
                    data.append("Name: ")
                        .append(rs.getString("name"))
                        .append("\nPassword: ")
                        .append(rs.getString("password"))
                        .append("\n\n");
                }

                JOptionPane.showMessageDialog(this,
                    data.length() == 0 ? "No records found!" : data.toString());
            }

            else if (e.getSource() == updateButton) {

                String sql = "UPDATE students SET password=? WHERE name=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, password);
                ps.setString(2, name);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Updated!");
            }

            else if (e.getSource() == deleteButton) {

                String sql = "DELETE FROM students WHERE name=?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Record Deleted!");
            }

            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Database Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentCRUD();
    }
}
