package views;

import java.awt.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import controllers.StoreController;
import views.components.Form;
import views.layouts.BasicFrame;

public class CreateClient extends BasicFrame {
    private StoreController storeController = new StoreController();

    public CreateClient() {
        super();
        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new GridLayout(1, 2));

        makeSignUpForm();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

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
            new Client();
        }
    }
}
