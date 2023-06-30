package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controllers.BranchController;
import views.components.Form;
import views.components.Button;
import views.layouts.BasicFrame;

public class CreateProduct extends BasicFrame {
    private BranchController BranchController = new BranchController();
    private String branchUUID;
    private Form generalProductForm = null;

    public CreateProduct(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new GridLayout(2, 2));

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
        productForm.removeSubmitButton();
        LinkedHashMap<String, JComponent> fields = productForm.getFields();

        @SuppressWarnings("unchecked")
        JComboBox<String> productType = (JComboBox<String>) fields.get("Tipo");
        productType.addActionListener(productTypeEvent -> {
            String selectedProductType = (String) productType.getSelectedItem();
            if (selectedProductType.equals("Medicamento")) {
                if (bodyPanel.getComponentCount() > 1) {
                    Component[] componentList = bodyPanel.getComponents();
                    bodyPanel.remove(componentList[1]);
                }
                generalProductForm = createMedicamentForm();
                Button submitButton = generalProductForm.getSubmitButton();
                submitButton.addActionListener(submitButtonEvent -> {
                    handleSignUp(productForm, generalProductForm);
                });
            } else {
                if (bodyPanel.getComponentCount() > 1) {
                    Component[] componentList = bodyPanel.getComponents();
                    bodyPanel.remove(componentList[1]);
                }
                generalProductForm = createCosmeticForm();
                Button submitButton = generalProductForm.getSubmitButton();
                submitButton.addActionListener(submitButtonEvent -> {
                    handleSignUp(productForm, generalProductForm);
                });
            }
            bodyPanel.revalidate();
            bodyPanel.repaint();
        });

        bodyPanel.add(productForm);
    }

    public Form createMedicamentForm() {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Cor da Caixa", Form.FieldTypes.TEXT);
                put("Dosagem (mg)", Form.FieldTypes.INTEGER);
                put("Idade Mínima", Form.FieldTypes.INTEGER);
            }
        };
        Form productForm = new Form("Criar", "Criar Medicamento", components);
        productForm.setName("medicament");
        bodyPanel.add(productForm);

        return productForm;
    }

    public Form createCosmeticForm() {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Marca", Form.FieldTypes.TEXT);
                put("Tipo", Form.FieldTypes.INTEGER);
                put("UV", Form.FieldTypes.CHECKBOX);
            }
        };
        Form productForm = new Form("Criar", "Criar Cosmético", components);
        productForm.setName("cosmetic");
        bodyPanel.add(productForm);

        return productForm;
    }

    public void handleSignUp(Form productForm, Form generalProductForm) {
        LinkedHashMap<String, String> fields = productForm.retrieveFieldValues();
        String name = fields.get("Nome");
        String price = fields.get("Preço");
        String quantity = fields.get("Quantidade");
        String productType = fields.get("Tipo");

        if (name.equals("") || price.equals("") || quantity.equals("") || productType.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            Integer priceInt = Integer.parseInt(price);
            Integer quantityInt = Integer.parseInt(quantity);
            if (productType == "Medicamento") {
                LinkedHashMap<String, String> generalFields = generalProductForm.retrieveFieldValues();
                String boxColor = generalFields.get("Cor da Caixa");
                String dosage = generalFields.get("Dosagem (mg)");
                String minimumAge = generalFields.get("Idade Mínima");
                if ( boxColor.equals("") || dosage.equals("") || minimumAge.equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }else{
                    Integer dosageInt = Integer.parseInt(dosage);
                    Integer minimumAgeInt = Integer.parseInt(minimumAge);
                    BranchController.addMedicament(branchUUID, name, priceInt, quantityInt, boxColor, dosageInt, minimumAgeInt);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    this.dispose();
                    new ProductView(branchUUID);
                }
            } else {
                LinkedHashMap<String, String> generalFields = generalProductForm.retrieveFieldValues();
                String brand = generalFields.get("Marca");
                String type = generalFields.get("Tipo");
                Boolean uv = Boolean.parseBoolean(generalFields.get("UV"));
                if ( brand.equals("") || type.equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }else{
                    BranchController.addCosmetic(branchUUID, name, priceInt, quantityInt, brand, type, uv);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    this.dispose();
                    new ProductView(branchUUID);
                }
            }
        }
    }
}
