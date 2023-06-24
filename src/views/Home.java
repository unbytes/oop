package views;

import java.awt.*;
import views.components.Button;
import views.components.Title;
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

        Title title = new Title("Bem vindo");
        gbc.gridy = 0;
        bodyPanel.add(title, gbc);

        Button loginBranchesButton = new Button("Filiais");
        loginBranchesButton.addActionListener(e -> {
            this.dispose();
            new LoginBranch();
        });
        gbc.gridy = 1;
        bodyPanel.add(loginBranchesButton, gbc);

        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
