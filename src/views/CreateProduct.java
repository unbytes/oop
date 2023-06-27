package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controllers.BranchController;
import views.components.Form;
import views.layouts.BasicFrame;

public class CreateProduct extends BasicFrame {
    private BranchController BranchController = new BranchController();
    private String branchUUID;

    public CreateProduct(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new GridLayout(1, 2));

        makeSignUpForm();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void makeSignUpForm() {
        String[] productTypes = { "Medicamento", "Cosmético" };
        HashMap<String, String[]> comboBoxOptions = new HashMap<String, String[]>() {
            {
                put("Tipo", productTypes);
            }
        };

        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Tipo", Form.FieldTypes.COMBOBOX);
                put("Nome", Form.FieldTypes.TEXT);
                put("Preço", Form.FieldTypes.INTEGER);
                put("Quantidade", Form.FieldTypes.INTEGER);
            }
        };
        Form productForm = new Form("Criar", "Criar Produto", components, comboBoxOptions);
        bodyPanel.add(productForm);
        JButton submitButton = productForm.getSubmitButton();
        submitButton.addActionListener(e -> {
            LinkedHashMap<String, String> fields = productForm.retrieveFieldValues();
            handleSignUp(fields);
        });
    }

    public void handleSignUp(LinkedHashMap<String, String> fields){
        String name = fields.get("Nome");
        Float price = Float.parseFloat(fields.get("Preço"));
        Integer quantity = Integer.parseInt(fields.get("Quantidade"));
        String productType = fields.get("Tipo");

        if (name.equals("") || price.equals("") || quantity.equals("") || productType.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            BranchController.addProduct(branchUUID, name, price, quantity, productType);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            this.dispose();
            new Product(branchUUID);
        }
    }

    
}
