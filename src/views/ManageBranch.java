package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controllers.BranchController;
import models.Branch;
import views.components.Title;
import views.layouts.BasicFrame;

public class ManageBranch extends BasicFrame {
    private BranchController branchController = new BranchController();
    private Branch branch;

    public ManageBranch(String branchUUID) {
        super();

        branch = branchController.getBranchByUUID(branchUUID);

        makeBody();
        logoutBranchWhenWindowClose(branchUUID);
    }

    public void makeBody() {
        bodyPanel.setLayout(new BorderLayout());
        makeBranchInfoHeader();

        JPanel branchBodyPanel = new JPanel();
        branchBodyPanel.setLayout(new BorderLayout());
        branchBodyPanel.setBackground(Color.WHITE);
        bodyPanel.add(branchBodyPanel, BorderLayout.CENTER);

        JPanel productListPanel = new JPanel();
        productListPanel.setBackground(Color.WHITE);
        branchBodyPanel.add(productListPanel, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Nome do produto: ");
        searchPanel.add(searchLabel);

        JTextField searchField = new JTextField(20);
        Action searchAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productSearchName = searchField.getText();
                String searchedProducts[] = branchController.searchProductsByWordAsHTMLTemplate(branch.getId(), productSearchName);

                productListPanel.removeAll();
                if (searchedProducts == null) {
                    productListPanel.add(new JLabel("Nenhum produto encontrado"));
                } else {
                    JList<String> productList = new JList<String>(searchedProducts);
                    productListPanel.add(productList);
                }
                productListPanel.repaint();
                productListPanel.revalidate();
            }
        };
        searchField.addActionListener(searchAction);
        searchPanel.add(searchField);

        branchBodyPanel.add(searchPanel, BorderLayout.NORTH);

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void makeBranchInfoHeader() {
        JPanel branchInfoHeaderPanel = new JPanel();
        branchInfoHeaderPanel.setBackground(Color.WHITE);
        branchInfoHeaderPanel.setLayout(new GridLayout(2, 1));

        Title branchName = new Title("Filial: " + branch.getId());
        branchInfoHeaderPanel.add(branchName);

        Title branchAddress = new Title("Endereço: " + branch.getAddress().toString());
        branchInfoHeaderPanel.add(branchAddress);

        bodyPanel.add(branchInfoHeaderPanel, BorderLayout.NORTH);
    }

    public void logoutBranchWhenWindowClose(String branchUUID) {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                branchController.logoutBranch(branchUUID);
            }
        };
        this.addWindowListener(windowListener);
    }
}
