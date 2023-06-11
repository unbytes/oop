package views.layouts;

import javax.swing.*;
import java.awt.*;
import views.Home;
import views.components.Button;
import views.components.Title;

public abstract class BasicFrame extends JFrame {
    protected JPanel headerPanel = new JPanel();
    protected JPanel bodyPanel = new JPanel();
    protected JPanel footerPanel = new JPanel();
    protected Color redColor = new Color(218, 0, 55);

    public BasicFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(854, 480));

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

        Title label = new Title("Drogas Lícitas");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(new Color(12, 12, 12));

        headerPanel.setBackground(redColor);
        headerPanel.add(label, BorderLayout.CENTER);

        this.add(headerPanel, BorderLayout.NORTH);
    }

    public abstract void makeBody();

    public void makeFooter() {
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(0, 50));
        footerPanel.setBackground(redColor);

        createBackButton();

        this.add(footerPanel, BorderLayout.SOUTH);
    }

    public void createBackButton() {
        try {
            ImageIcon icon = new ImageIcon("src/assets/return_white_button.png");
            icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

            Button backButton = new Button(icon);
            backButton.setPreferredSize(new Dimension(50, 50));
            backButton.setBackground(redColor);
            backButton.addActionListener(e -> {
                if (!this.getClass().getSimpleName().equals("Home")) {
                    this.dispose();
                    new Home();
                }
            });

            footerPanel.add(backButton, BorderLayout.EAST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
