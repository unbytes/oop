package views;

import javax.swing.*;
import java.awt.*;

public class Home extends BasicFrame {
    public Home() {
        super();

        makeBody();
    }

    public void makeBody() {
        this.bodyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton branchesButton = new JButton("Access Branches");
        branchesButton.setBackground(Color.WHITE);
        branchesButton.setFocusable(false);
        branchesButton.setBorderPainted(false);

        JButton clientsButton = new JButton("Access Clients");
        clientsButton.setBackground(Color.WHITE);
        clientsButton.setFocusable(false);
        clientsButton.setBorderPainted(false);

        bodyPanel.add(branchesButton, gbc);
        bodyPanel.add(clientsButton, gbc);

        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
