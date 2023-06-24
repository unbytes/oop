package views;

import javax.swing.*;
import controllers.BranchController;
import java.awt.*;
import java.awt.event.*;
import views.components.Title;
import views.layouts.BasicFrame;

public class Product extends BasicFrame {
    private BranchController branchController = new BranchController();
    private String branchUUID;

    public Product(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new BorderLayout());

        String titleText = String.format("""
                <html>
                    <body style='text-align:center;'>
                        <h1>Produtos</h1>
                        <p>Filial: %s</p>
                        <p>Endereço: %s | %s</p>
                    </body>
                </html>
                    """, branchUUID, branchController.getBranchCity(branchUUID), branchController.getBranchRegion(branchUUID));
        Title titleLabel = new Title(titleText);
        bodyPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel productBodyPanel = new JPanel();
        productBodyPanel.setLayout(new BorderLayout());
        productBodyPanel.setBackground(Color.WHITE);

        JPanel productListPanel = new JPanel();
        productListPanel.setBackground(Color.WHITE);

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
                String searchedProducts[] = branchController.searchProductsByWordAsHTMLTemplate(branchUUID, productSearchName);

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

        productBodyPanel.add(productListPanel, BorderLayout.CENTER);
        productBodyPanel.add(searchPanel, BorderLayout.NORTH);

        bodyPanel.add(productBodyPanel, BorderLayout.CENTER);
        this.add(bodyPanel, BorderLayout.CENTER);
    }
}
