package views;

import java.awt.*;
import views.components.Button;
import views.layouts.BasicFrame;

public class Home extends BasicFrame {
    public Home() {
        super();

        makeBody();
    }

    public void makeBody() {
        this.bodyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Button branchesButton = new Button("Access Branches");
        branchesButton.addActionListener(e -> {
            this.dispose();
            new Branch();
        });
        gbc.gridy = 0;
        bodyPanel.add(branchesButton, gbc);

        Button clientsButton = new Button("Access Clients");
        clientsButton.addActionListener(e -> {
            this.dispose();
            new Client();
        });
        gbc.gridy = 1;
        bodyPanel.add(clientsButton, gbc);

        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
