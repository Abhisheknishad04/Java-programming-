
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Notepad extends JFrame {

    JTextArea textArea;
    JFileChooser fileChooser;
    File currentFile = null;

    Notepad() {

        // Window settings
        setTitle("My Notepad");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Text Area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem selectAll = new JMenuItem("Select All");

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(selectAll);

        // Format Menu
        JMenu formatMenu = new JMenu("Format");

        JCheckBoxMenuItem wordWrap =
            new JCheckBoxMenuItem("Word Wrap");

        formatMenu.add(wordWrap);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem about = new JMenuItem("About Notepad");
        helpMenu.add(about);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        // File Operations
        newFile.addActionListener(e -> {
            textArea.setText("");
            currentFile = null;
            setTitle("My Notepad");
        });

        openFile.addActionListener(e -> open());

        saveFile.addActionListener(e -> save());

        saveAs.addActionListener(e -> saveAs());

        exit.addActionListener(e -> dispose());

        // Edit Operations
        cut.addActionListener(e -> textArea.cut());

        copy.addActionListener(e -> textArea.copy());

        paste.addActionListener(e -> textArea.paste());

        selectAll.addActionListener(e -> textArea.selectAll());

        // Word Wrap
        wordWrap.addActionListener(e -> {
            textArea.setLineWrap(wordWrap.isSelected());
            textArea.setWrapStyleWord(wordWrap.isSelected());
        });

        // About
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "My Notepad Application\nCreated using Java Swing.",
                "About Notepad",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Open File Method
    void open() {
        if (fileChooser.showOpenDialog(this) ==
            JFileChooser.APPROVE_OPTION) {

            currentFile = fileChooser.getSelectedFile();

            try (BufferedReader reader =
                 new BufferedReader(new FileReader(currentFile))) {

                textArea.setText("");
                String line;

                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                setTitle(currentFile.getName() + " - My Notepad");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                    "Error opening file!");
            }
        }
    }

    // Save File Method
    void save() {
        if (currentFile == null) {
            saveAs();
            return;
        }

        writeFile(currentFile);
    }

    // Save As Method
    void saveAs() {
        if (fileChooser.showSaveDialog(this) ==
            JFileChooser.APPROVE_OPTION) {

            currentFile = fileChooser.getSelectedFile();
            writeFile(currentFile);
        }
    }

    // Write File Method
    void writeFile(File file) {
        try (BufferedWriter writer =
             new BufferedWriter(new FileWriter(file))) {

            writer.write(textArea.getText());

            JOptionPane.showMessageDialog(this,
                "File saved successfully!");

            setTitle(file.getName() + " - My Notepad");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error saving file!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notepad());
    }
}
