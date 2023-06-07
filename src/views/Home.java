package views;

import java.awt.*;
import views.components.Button;

public class Home extends BasicFrame {
    public Home() {
        super();

        makeBody();
    }

    public void makeBody() {
        this.bodyPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Button branchesButton = new Button("Access Branches");
        Button clientsButton = new Button("Access Clients");

        bodyPanel.add(branchesButton, gbc);
        bodyPanel.add(clientsButton, gbc);

        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
