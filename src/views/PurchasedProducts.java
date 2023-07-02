package views;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import controllers.BranchController;
import models.Client;
import models.Product;
import views.components.Title;
import views.components.Button;
import views.layouts.BasicFrame;

/**
 * Classe que representa a tela de produtos comprados.
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class PurchasedProducts extends BasicFrame {
    private BranchController branchController = new BranchController();
    private JTextField searchField = new JTextField(20);
    private JPanel handleProductsPanel = new JPanel();
    private JPanel purchasedBodyPanel = new JPanel();
    private JScrollPane productListPanel = null;
    private JScrollPane purchasedProductsListPanel = null;
    private JList<String> productList = null;
    private JList<String> purchasedProductsList = null;
    private String branchUUID;
    private Client client;

    /**
     * Construtor da classe PurchasedProducts.
     * 
     * @param branchUUID UUID da filial.
     * @param client     Cliente.
     */
    public PurchasedProducts(String branchUUID, Client client) {
        super();

        this.client = client;
        this.branchUUID = branchUUID;

        makeBody();
    }

    /**
     * Cria o corpo da tela de produtos comprados.
     */
    public void makeBody() {
        bodyPanel.setLayout(new BorderLayout());

        makePurchasedProductsList();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Cria uma lista de produtos comprados
     */
    public void makePurchasedProductsList() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 2));

        Title purchasedProductsPanel = new Title("Produtos comprados");
        headerPanel.add(purchasedProductsPanel);

        Title productsToBuyPanel = new Title("Produtos para comprar");
        headerPanel.add(productsToBuyPanel);

        bodyPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel productBodyPanel = new JPanel();
        productBodyPanel.setLayout(new BorderLayout());
        productBodyPanel.setBackground(Color.WHITE);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Nome do produto: ");
        searchPanel.add(searchLabel);

        searchField.addActionListener(createSearchAction(searchField));
        searchPanel.add(searchField);

        initProductList("");
        initPurchasedProducts();
        handleProductsPanel.setBackground(Color.WHITE);
        purchasedBodyPanel.setBackground(Color.WHITE);

        productBodyPanel.add(searchPanel, BorderLayout.NORTH);
        productBodyPanel.add(handleProductsPanel, BorderLayout.CENTER);

        JPanel bodyCenterPanel = new JPanel();
        bodyCenterPanel.setBackground(Color.WHITE);
        bodyCenterPanel.setLayout(new GridLayout(1, 2));
        bodyCenterPanel.setBackground(Color.WHITE);

        purchasedProductsPanel.setLayout(new BorderLayout());
        purchasedProductsPanel.setBackground(Color.WHITE);

        Button buyButton = new Button("Comprar");
        buyButton.addActionListener(e -> {
            String selectedValue = productList.getSelectedValue();
            if (selectedValue != null) {
                String productName = selectedValue.split("<br>")[1].split(": ")[1].trim();
                branchController.buyProduct(branchUUID, client, productName);
                initProductList("");
                initPurchasedProducts();
            }
        });
        bodyPanel.add(buyButton, BorderLayout.SOUTH);

        bodyCenterPanel.add(purchasedBodyPanel);
        bodyCenterPanel.add(productBodyPanel);

        bodyPanel.add(bodyCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Atualiza a lista de produtos.
     * 
     * @param panel Painel.
     */
    public void refreshProductList(JPanel panel) {
        if (panel != null) {
            panel.revalidate();
            panel.repaint();
        }
    }

    /**
     * Estiliza a lista de produtos.
     * 
     * @param panel      Painel.
     * @param scrollPane Painel de rolagem.
     */
    public void styleProductListPanel(JPanel panel, JScrollPane scrollPane) {
        refreshProductList(panel);
        if (scrollPane != null) {
            scrollPane.setPreferredSize(new Dimension(500, 500));
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBackground(Color.WHITE);
        }
    }

    /**
     * Cria uma ação de busca.
     * 
     * @param searchField Campo de busca.
     * @return Ação de busca.
     */
    public Action createSearchAction(JTextField searchField) {
        Action searchAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productSearchName = searchField.getText();
                String searchedProducts[] = branchController.searchProductsByWordAsHTMLTemplate(branchUUID,
                        productSearchName);

                handleProductsPanel.removeAll();
                if (searchedProducts == null) {
                    handleProductsPanel.add(new JLabel("Nenhum produto encontrado"));
                } else {
                    productList = new JList<String>(searchedProducts);
                    productListPanel = new JScrollPane(productList);
                    handleProductsPanel.add(productListPanel);
                }
                styleProductListPanel(handleProductsPanel, productListPanel);
            }
        };

        return searchAction;
    }

    /**
     * Inicializa a lista de produtos.
     * 
     * @param productName Nome do produto.
     */
    public void initProductList(String productName) {
        String products[] = branchController.searchProductsByWordAsHTMLTemplate(branchUUID, productName);

        handleProductsPanel.removeAll();
        if (products == null) {
            handleProductsPanel.add(new JLabel("Nenhum produto cadastrado"));
        } else {
            productList = new JList<String>(products);
            productListPanel = new JScrollPane(productList);
            handleProductsPanel.add(productListPanel);
        }

        styleProductListPanel(handleProductsPanel, productListPanel);
    }

    /**
     * Inicializa a lista de produtos comprados.
     */
    public void initPurchasedProducts() {
        purchasedBodyPanel.removeAll();

        HashMap<Product, Integer> purchasedProducts = branchController.getPurchasedProducts(client);
        String[] purchasedProductsAsHTMLTemplate = branchController
                .generatePurchasedProductsHTMLTemplate(purchasedProducts);

        if (purchasedProductsAsHTMLTemplate == null) {
            purchasedBodyPanel.add(new JLabel("Nenhum produto comprado"));
        } else {
            purchasedProductsList = new JList<String>(purchasedProductsAsHTMLTemplate);
            purchasedProductsListPanel = new JScrollPane(purchasedProductsList);
            purchasedBodyPanel.add(purchasedProductsListPanel);
        }

        styleProductListPanel(purchasedBodyPanel, purchasedProductsListPanel);
    }
}
