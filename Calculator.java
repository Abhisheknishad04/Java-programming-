
import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    TextField display;
    double num1, num2, result;
    String operator = "";

    Calculator() {

        setTitle("AWT Calculator");
        setSize(350, 450);
        setLayout(new BorderLayout(5, 5));

        // Display
        display = new TextField();
        display.setFont(new Font("Arial", Font.BOLD, 25));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Button Panel
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String buttons[] = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            Button b = new Button(text);
            b.setFont(new Font("Arial", Font.BOLD, 20));
            b.addActionListener(this);
            panel.add(b);
        }

        add(panel, BorderLayout.CENTER);

        // Close Window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        }

        else if (command.equals("C")) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            operator = "";
        }

        else if (command.equals("+") ||
                 command.equals("-") ||
                 command.equals("*") ||
                 command.equals("/")) {

            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }
        }

        else if (command.equals("=")) {

            if (!display.getText().isEmpty() && !operator.isEmpty()) {

                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;

                    case "-":
                        result = num1 - num2;
                        break;

                    case "*":
                        result = num1 * num2;
                        break;

                    case "/":
                        if (num2 == 0) {
                            display.setText("Cannot divide by zero");
                            operator = "";
                            return;
                        }
                        result = num1 / num2;
                        break;
                }

                display.setText(String.valueOf(result));
                operator = "";
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
