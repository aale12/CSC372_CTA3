
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
    private final JPanel panel;

    public MenuGUI() {
        // create framerame
        setTitle("Menu Options");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create main content panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // create textarea to display texts
        textArea = new JTextArea();
        textArea.setEditable(false);
        //need opacity to show background color
        textArea.setOpaque(false);

        // create addtext area to jscrollpane
        JScrollPane scrollPane = new JScrollPane(textArea);
        //need opacity on viewport and the pane to show color
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // add to panel
        panel.add(scrollPane, BorderLayout.CENTER);

        // add panel to frame
        add(panel);

        // create menu bar
        JMenuBar menuBar = new JMenuBar();

        // create menu
        JMenu menu = new JMenu("Options");

        // create menu items
        JMenuItem menuItem1 = new JMenuItem("Print Date & Time");
        JMenuItem menuItem2 = new JMenuItem("Save Log to File");
        JMenuItem menuItem3 = new JMenuItem("Change Background Color");
        JMenuItem menuItem4 = new JMenuItem("Exit");

        // add menu items to menu
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);

        // add menu to menu bar
        menuBar.add(menu);

        // add menu bar to frame
        setJMenuBar(menuBar);

        // date and time actionlistener
        menuItem1.addActionListener((var e) -> {
            printDateTime();
        });

        // save to log file actionlistener
        menuItem2.addActionListener((ActionEvent e) -> {
            saveLogToFile();
        });

        // background color action listener
        menuItem3.addActionListener((ActionEvent e) -> {
            changeBackgroundColor();
        });

        // exit action listener
        menuItem4.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    // create print date time method
    private void printDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        textArea.append("Current Date and Time: " + formatter.format(date) + "\n");
    }

    // create log file txt method
    private void saveLogToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
            JOptionPane.showMessageDialog(this, "Log saved to log.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving log to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // create method to change background color
    private void changeBackgroundColor() {
        Random random = new Random();
        float hue = random.nextFloat() * 0.3f;  // Restrict hue to green range
        float saturation = 1.0f;
        float brightness = 1.0f;
        Color randomGreen = Color.getHSBColor(hue, saturation, brightness);

        // set background color
        panel.setBackground(randomGreen);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuGUI gui = new MenuGUI();
            gui.setVisible(true);
        });
    }
}
