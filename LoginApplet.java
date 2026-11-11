
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class LoginApplet extends Applet implements ActionListener {

    Label nameLabel, passwordLabel, resultLabel;
    TextField nameField, passwordField;
    Button submitButton, clearButton;

    public void init() {

        setLayout(new FlowLayout());

        // Name Field
        nameLabel = new Label("Enter Name:");
        nameField = new TextField(20);

        // Password Field
        passwordLabel = new Label("Enter Password:");
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');

        // Buttons
        submitButton = new Button("Submit");
        clearButton = new Button("Clear");

        resultLabel = new Label("");

        // Add components
        add(nameLabel);
        add(nameField);

        add(passwordLabel);
        add(passwordField);

        add(submitButton);
        add(clearButton);

        add(resultLabel);

        // Button Actions
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {

            String name = nameField.getText();

            resultLabel.setText("Welcome " + name);
        }

        if (e.getSource() == clearButton) {

            nameField.setText("");
            passwordField.setText("");
            resultLabel.setText("");
        }
    }
}
