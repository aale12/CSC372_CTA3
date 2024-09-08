
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;

public class MenuGUI extends JFrame {

    private final JTextArea textArea;
    private final JMenuBar menuBar;
    private final JMenu menu;
    private final JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
    private final JPanel panel;

    public MenuGUI() {
        //JFrame init
        setTitle("Simple GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // text component
        textArea = new JTextArea();
        textArea.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // menu bar
        menuBar = new JMenuBar();
        menu = new JMenu("Options");

        // menu options
        menuItem1 = new JMenuItem("Print Date & Time");
        menuItem2 = new JMenuItem("Save Log to File");
        menuItem3 = new JMenuItem("Change Background Color");
        menuItem4 = new JMenuItem("Exit");

        // add options to menu
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);

        // add menu to menu bar
        menuBar.add(menu);

        // add menu bar to frame
        setJMenuBar(menuBar);

        // add panel to frame
        add(panel, BorderLayout.CENTER);

        // action listners for menu options
        menuItem1.addActionListener((ActionEvent e) -> {
            printDateTime();
        });

        menuItem2.addActionListener((ActionEvent e) -> {
            saveLogToFile();
        });

        menuItem3.addActionListener((ActionEvent e) -> {
            changeBackgroundColor();
        });

        menuItem4.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    // append date and time to textarea
    private void printDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        textArea.append("Current Date and Time: " + formatter.format(date) + "\n");
    }

    // Save the textArea content to log.txt
    private void saveLogToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "Log saved to log.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving log to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // random green color
    private void changeBackgroundColor() {
        Random random = new Random();
        float hue = random.nextFloat() * 0.3f; // Limiting to green hues (0.3 max)
        float saturation = 1.0f;
        float brightness = 1.0f;
        Color randomGreen = Color.getHSBColor(hue, saturation, brightness);
        panel.setBackground(randomGreen);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuGUI gui = new MenuGUI();
            gui.setVisible(true);
        });
    }
}
