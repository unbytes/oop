package views;

import java.awt.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import controllers.StoreController;
import views.components.Form;
import views.layouts.BasicFrame;

/**
 * Classe responsável por criar a tela de cadastro de clientes.
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class CreateClient extends BasicFrame {
    private StoreController storeController = new StoreController();
    private String branchUUID;

    /**
     * Construtor da classe CreateClient.
     * 
     * @param branchUUID <code>String</code> que representa o UUID da filial.
     */
    public CreateClient(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    /**
     * Cria o corpo da tela de cadastro de clientes.
     */
    public void makeBody() {
        bodyPanel.setLayout(new GridLayout(1, 2));

        makeSignUpForm();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Cria o formulário de cadastro de clientes.
     */
    public void makeSignUpForm() {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Name", Form.FieldTypes.TEXT);
                put("Age", Form.FieldTypes.INTEGER);
                put("CPF", Form.FieldTypes.TEXT);
            }
        };
        Form clientForm = new Form("Submit", "Register Client", components);
        bodyPanel.add(clientForm);

        JButton submitButton = clientForm.getSubmitButton();
        submitButton.addActionListener(e -> {
            LinkedHashMap<String, String> fields = clientForm.retrieveFieldValues();
            handleSignUp(fields);
        });
    }

    /**
     * Cria o tratamento de cadastro de clientes.
     * 
     * @param fields <code>LinkedHashMap&lt;String, String&gt;</code> campos do formulário.
     */
    public void handleSignUp(LinkedHashMap<String, String> fields) {
        String name = fields.get("Name");
        String age = fields.get("Age");
        String CPF = fields.get("CPF");

        if (name.equals("") || age.equals("") || CPF.equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
        } else {
            Integer ageInt = Integer.parseInt(age);
            storeController.createClient(name, ageInt, CPF);
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            this.dispose();
            new ClientView(branchUUID);
        }
    }
}
