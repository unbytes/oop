package views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import controllers.BranchController;
import views.components.Button;
import views.components.Form;
import views.components.Title;
import views.layouts.BasicFrame;

public class Product extends BasicFrame {
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

        productBodyPanel.setBackground(Color.WHITE);

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Nome do produto: ");
        searchPanel.add(searchLabel);

        JTextField searchField = new JTextField(20);

        String products[] = branchController.searchProductsByWordAsHTMLTemplate(branchUUID, "");
        if (products == null) {
            productListPanel.add(new JLabel("Nenhum produto cadastrado"));
        } else {
            productList = new JList<String>(products);
            productListPanel = new JScrollPane(productList);
        }

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

        Button updateProductButton = new Button("Atualizar produto");
        updateProductButton.addActionListener(e -> {
            String selectedValue = productList.getSelectedValue();
            String productName = selectedValue.split("<br>")[1].split(": ")[1].trim();
            String productType = selectedValue.split("<br>")[0].split(": ")[1].trim();

            Form updateForm;
            if (productType.equals("Medicamento")) {
                updateForm = createMedicamentUpdateForm(productName);
            } else {
                updateForm = createCosmeticUpdateForm(productName);
            }

            if (currentUpdateForm != null) {
                productBodyPanel.remove(currentUpdateForm);
            }

            productBodyPanel.add(updateForm);
            productBodyPanel.repaint();
            productBodyPanel.revalidate();

            currentUpdateForm = updateForm;
        });
        buttonPanel.add(updateProductButton, BorderLayout.SOUTH);

        Action searchAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productSearchName = searchField.getText();
                String searchedProducts[] = branchController.searchProductsByWordAsHTMLTemplate(branchUUID,
                        productSearchName);

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

        productListPanel.setPreferredSize(new Dimension(360, 300));
        productListPanel.setBorder(BorderFactory.createEmptyBorder());
        productListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        productListPanel.setBackground(Color.WHITE);

        productBodyPanel.add(productListPanel);

        bodyPanel.add(productBodyPanel, BorderLayout.CENTER);
        this.add(bodyPanel, BorderLayout.CENTER);
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

        Form updateProductForm = new Form("Atualizar", productName, components);
        return updateProductForm;
    }

    public Form createCosmeticUpdateForm(String productName) {
        LinkedHashMap<String, Form.FieldTypes> components = createBasicProductFormComponents();
        components.put("Marca", Form.FieldTypes.TEXT);
        components.put("Tipo", Form.FieldTypes.TEXT);
        components.put("UV", Form.FieldTypes.TEXT);

        Form updateProductForm = new Form("Atualizar", productName, components);
        return updateProductForm;
    }
}
