package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import controllers.BranchController;
import models.Cosmetic;
import models.Medicament;
import views.components.Button;
import views.components.Form;
import views.components.IntegerField;
import views.components.Title;
import views.layouts.BasicFrame;

public class Product extends BasicFrame {
    private JTextField searchField = new JTextField(20);
    private JPanel handleProductsPanel = new JPanel();
    private JScrollPane productListPanel = null;
    private JList<String> productList = null;
    private Form currentUpdateForm;
    private BranchController branchController = new BranchController();
    private String branchUUID;

    public Product(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    public void refreshProductList() {
        handleProductsPanel.repaint();
        handleProductsPanel.revalidate();
    }

    public void styleProductListPanel() {
        refreshProductList();
        productListPanel.setPreferredSize(new Dimension(500, 500));
        productListPanel.setBorder(BorderFactory.createEmptyBorder());
        productListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        productListPanel.setBackground(Color.WHITE);
    }

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
                styleProductListPanel();
            }
        };

        return searchAction;
    }

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

        styleProductListPanel();
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
                    """, branchUUID, branchController.getBranchCity(branchUUID),
                branchController.getBranchRegion(branchUUID));
        Title titleLabel = new Title(titleText);
        bodyPanel.add(titleLabel, BorderLayout.NORTH);

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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        bodyPanel.add(buttonPanel, BorderLayout.SOUTH);

        Button createProductButton = new Button("Cadastrar produto");
        createProductButton.addActionListener(e -> {
            this.dispose();
            new CreateProduct(branchUUID);
        });
        buttonPanel.add(createProductButton, BorderLayout.SOUTH);

        Button updateProductButton = new Button("Atualizar");
        updateProductButton.addActionListener(initUpdateFormEvent -> {
            handleUpdateProductForm();
        });
        buttonPanel.add(updateProductButton, BorderLayout.SOUTH);

        Button deleteProductButton = new Button("Deletar");
        deleteProductButton.addActionListener(e -> {
            handleDeleteProductPopUpForm();
        });
        buttonPanel.add(deleteProductButton, BorderLayout.SOUTH);

        initProductList("");
        handleProductsPanel.setBackground(Color.WHITE);

        productBodyPanel.add(searchPanel, BorderLayout.NORTH);
        productBodyPanel.add(handleProductsPanel, BorderLayout.CENTER);

        bodyPanel.add(productBodyPanel, BorderLayout.CENTER);
        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void handleDeleteProductPopUpForm() {
        String selectedValue = productList.getSelectedValue();
        if (selectedValue != null) {
            IntegerField quantityField = new IntegerField();
            int option = JOptionPane.showOptionDialog(null, quantityField, "Quantos serão removidos?",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

            if (option == JOptionPane.OK_OPTION) {
                String productName = selectedValue.split("<br>")[1].split(": ")[1].trim();
                Integer quantity = Integer.parseInt(quantityField.getText());
                branchController.removeProduct(branchUUID, productName, quantity);
                initProductList(searchField.getText());
            }
        }
    }

    public void handleUpdateProductForm() {
        String selectedValue = productList.getSelectedValue();
        if (selectedValue != null) {
            String productName = selectedValue.split("<br>")[1].split(": ")[1].trim();
            String productType = selectedValue.split("<br>")[0].split(": ")[1].trim();

            Form updateForm;
            if (productType.equals("Medicamento")) {
                updateForm = createMedicamentUpdateForm(productName);
            } else {
                updateForm = createCosmeticUpdateForm(productName);
            }
            Button submitButton = updateForm.getSubmitButton();
            submitButton.addActionListener(updateProductEvent -> {
                LinkedHashMap<String, String> productData = updateForm.retrieveFieldValues();
                branchController.updateProduct(branchUUID, productName, productType, productData);
                initProductList(searchField.getText());
            });

            if (currentUpdateForm != null) {
                handleProductsPanel.remove(currentUpdateForm);
            }

            handleProductsPanel.add(updateForm);
            refreshProductList();

            currentUpdateForm = updateForm;
        }
    }

    public LinkedHashMap<String, Form.FieldTypes> createBasicProductFormComponents() {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Nome", Form.FieldTypes.TEXT);
                put("Preço", Form.FieldTypes.INTEGER);
                put("Quantidade", Form.FieldTypes.INTEGER);
            }
        };
        return components;
    }

    public Form createMedicamentUpdateForm(String productName) {
        LinkedHashMap<String, Form.FieldTypes> components = createBasicProductFormComponents();
        components.put("Cor da Caixa", Form.FieldTypes.TEXT);
        components.put("Dosagem (mg)", Form.FieldTypes.INTEGER);
        components.put("Idade Mínima", Form.FieldTypes.INTEGER);

        Medicament product = (Medicament) branchController.getProductByName(branchUUID, productName);
        ArrayList<String> fieldValues = new ArrayList<String>() {
            {
                add(productName);
                add(product.getPrice().toString());
                add(branchController.getProductQuantity(branchUUID, product).toString());
                add(product.getBoxColor());
                add(product.getDosageMl().toString());
                add(product.getMinimumAge().toString());
            }
        };

        Form updateProductForm = new Form("Atualizar", "", components);
        updateProductForm.setFieldDefaultValuesInOder(fieldValues);
        return updateProductForm;
    }

    public Form createCosmeticUpdateForm(String productName) {
        LinkedHashMap<String, Form.FieldTypes> components = createBasicProductFormComponents();
        components.put("Marca", Form.FieldTypes.TEXT);
        components.put("Tipo", Form.FieldTypes.TEXT);
        components.put("UV", Form.FieldTypes.CHECKBOX);

        Cosmetic product = (Cosmetic) branchController.getProductByName(branchUUID, productName);
        ArrayList<String> fieldValues = new ArrayList<String>() {
            {
                add(productName);
                add(product.getPrice().toString());
                add(branchController.getProductQuantity(branchUUID, product).toString());
                add(product.getBrand());
                add(product.getType());
                add(product.getContainsSunProtectionFactor().toString());
            }
        };

        Form updateProductForm = new Form("Atualizar", "", components);
        updateProductForm.setFieldDefaultValuesInOder(fieldValues);
        return updateProductForm;
    }
}
