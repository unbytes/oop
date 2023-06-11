package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controllers.BranchController;
import views.components.Button;
import views.components.Title;
import views.layouts.BasicFrame;

public class LoginBranch extends BasicFrame {
    private BranchController branchController = new BranchController();

    public LoginBranch() {
        super();
        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new BorderLayout());

        makeBranchList();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void makeBranchList() {
        JPanel branchListPanel = new JPanel();
        branchListPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        Title titleLabel = new Title("Branches");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        branchListPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        HashMap<String, String> branchesAsHTMLTemplate = branchController.getBranchesAsHTMLTemplate();
        for (String branchUUID : branchesAsHTMLTemplate.keySet()) {
            gbc.gridx = 0;
            gbc.gridy++;
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(350, 30));
            label.setText(branchesAsHTMLTemplate.get(branchUUID));
            branchListPanel.add(label, gbc);

            gbc.gridx = 1;
            Button button = new Button("Login");
            button.setName(branchUUID);
            button.setPreferredSize(new Dimension(80, 30));
            button.addActionListener(e -> handleBranchPopUpLogin(branchUUID));
            branchListPanel.add(button, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(branchListPanel);
        bodyPanel.add(scrollPane);
    }

    public void handleBranchPopUpLogin(String branchUUID) {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showOptionDialog(null, passwordField, "Enter Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            String password = new String(passwordField.getPassword());
            boolean response = branchController.authenticateBranch(branchUUID, password);
            if (response) {
                this.dispose();
                new ManageBranch(branchUUID);
            } else {
                JOptionPane.showMessageDialog(null, "Wrong password");
            }
        }
    }
}
