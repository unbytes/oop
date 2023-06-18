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

        Button loginBranchesButton = new Button("Filiais");
        loginBranchesButton.addActionListener(e -> {
            this.dispose();
            new LoginBranch();
        });
        gbc.gridy = 1;
        bodyPanel.add(loginBranchesButton, gbc);

        Button clientsButton = new Button("Clientes");
        clientsButton.addActionListener(e -> {
            this.dispose();
            new Client();
        });
        gbc.gridy = 2;
        bodyPanel.add(clientsButton, gbc);

        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
