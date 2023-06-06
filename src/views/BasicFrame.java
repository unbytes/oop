package views;

import javax.swing.*;
import java.awt.*;

public abstract class BasicFrame extends JFrame {
    protected JPanel headerPanel = new JPanel();
    protected JPanel bodyPanel = new JPanel();
    protected JPanel footerPanel = new JPanel();

    public BasicFrame() {
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setLayout(new BorderLayout());

        this.makeHeader();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void makeHeader() {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0, 50));

        JLabel label = new JLabel("Drogas Lícitas");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(new Color(0, 0, 0));

        headerPanel.setBackground(new Color(218, 0, 55));
        headerPanel.add(label, BorderLayout.WEST);

        this.add(headerPanel, BorderLayout.NORTH);
    }

    public void close() {
        this.dispose();
    }
}
