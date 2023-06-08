package views.layouts;

import javax.swing.*;
import java.awt.*;
import views.Home;
import views.components.Button;

public abstract class BasicFrame extends JFrame {
    protected JPanel headerPanel = new JPanel();
    protected JPanel bodyPanel = new JPanel();
    protected JPanel footerPanel = new JPanel();
    protected Color redColor = new Color(218, 0, 55);

    public BasicFrame() {
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        makeHeader();
        makeFooter();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void makeHeader() {
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(0, 50));

        JLabel label = new JLabel("Drogas Lícitas");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(new Color(12, 12, 12));
        label.setHorizontalAlignment(JLabel.CENTER);

        headerPanel.setBackground(redColor);
        headerPanel.add(label, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
    }

    public abstract void makeBody();

    public void makeFooter() {
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(0, 50));
        footerPanel.setBackground(redColor);

        //createBackButton();

        this.add(footerPanel, BorderLayout.SOUTH);
    }
}
