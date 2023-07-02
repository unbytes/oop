package views;

import java.awt.*;
import views.components.Button;
import views.components.Title;
import views.layouts.BasicFrame;

/**
 * Classe que cria a tela inicial do programa
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class Home extends BasicFrame {
    /**
     * Construtor da classe Home
     */
    public Home() {

        super();

        makeBody();
    }

    /**
     * Cria o corpo da tela inicial
     */
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
