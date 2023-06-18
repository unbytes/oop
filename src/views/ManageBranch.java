package views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import controllers.BranchController;
import models.Branch;
import models.Product;
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
                HashMap<Product, Integer> branchProducts = branch.getProducts();

                String productSearchName = searchField.getText();
                ArrayList<Product> searchedProducts = branch.searchProductsByWord(productSearchName);

                productListPanel.removeAll();
                if (searchedProducts == null) {
                    productListPanel.add(new JLabel("Nenhum produto encontrado"));
                } else {
                    String products[] = new String[searchedProducts.size()];

                    Integer index = 0;
                    for (Product product : searchedProducts) {
                        String productAsHTMLTemplate = String.format("""
                                <html>
                                    <body>
                                        Produto: %s
                                        <br>
                                        Preço: %.2f
                                        <br>
                                        Quantidade: %d
                                    </body>
                                </html>
                                """, product.getName(), product.getPrice(), branchProducts.get(product));
                        products[index] = productAsHTMLTemplate;
                        index++;
                    }
                    JList<String> productList = new JList<String>(products);
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
