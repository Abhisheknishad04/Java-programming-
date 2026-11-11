
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class LoginApplet extends Applet implements ActionListener {

    Label nameLabel, passwordLabel, resultLabel;
    TextField nameField, passwordField;
    Button submitButton, clearButton;

    public void init() {

        setLayout(new GridLayout(4, 2, 10, 10));

        nameLabel = new Label("Enter Name:");
        passwordLabel = new Label("Enter Password:");

        nameField = new TextField(20);
        passwordField = new TextField(20);
        passwordField.setEchoChar('*');

        submitButton = new Button("Submit");
        clearButton = new Button("Clear");

        resultLabel = new Label("");

        add(nameLabel);
        add(nameField);

        add(passwordLabel);
        add(passwordField);

        add(submitButton);
        add(clearButton);

        add(resultLabel);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {
            resultLabel.setText("Welcome " + nameField.getText());
        }

        if (e.getSource() == clearButton) {
            nameField.setText("");
            passwordField.setText("");
            resultLabel.setText("");
        }
    }
}
