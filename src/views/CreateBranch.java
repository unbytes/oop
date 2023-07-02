package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controllers.BranchController;
import views.components.Form;
import views.layouts.BasicFrame;

/**
 * Classe responsável por criar a tela de cadastro de filial.
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class CreateBranch extends BasicFrame {
    private BranchController branchController = new BranchController();

    /**
     * Construtor da classe CreateBranch.
     */
    public CreateBranch() {
        super();
        makeBody();
    }

    /**
     * Criar o corpo da tela de cadastro de filial.
     */
    public void makeBody() {
        bodyPanel.setLayout(new GridLayout(1, 2));

        makeSignUpForm();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Cria o formulário de cadastro de filial.
     */
    public void makeSignUpForm() {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Cidade", Form.FieldTypes.TEXT);
                put("Região", Form.FieldTypes.TEXT);
                put("Senha", Form.FieldTypes.PASSWORD);
                put("Confirmar senha", Form.FieldTypes.PASSWORD);
            }
        };
        Form branchForm = new Form("Criar", "Criar Filial", components);
        bodyPanel.add(branchForm);

        JButton submitButton = branchForm.getSubmitButton();
        submitButton.addActionListener(e -> {
            LinkedHashMap<String, String> fields = branchForm.retrieveFieldValues();
            handleSignUp(fields);
        });
    }

    /**
     * Cria campo de texto para cadastro de senha.
     * 
     * @param fields <code>LinkedHashMap&lt;String, String&gt;</code> contendo os campos do formulário.
     */
    public void handleSignUp(LinkedHashMap<String, String> fields) {
        String password = fields.get("Senha").strip();
        String confirmPassword = fields.get("Confirmar senha").strip();

        if (password.equals(confirmPassword) && password.length() >= 5) {
            branchController.registerBranch(password, fields.get("Cidade"), fields.get("Região"));
            JOptionPane.showMessageDialog(null, "Filial cadastrada com sucesso.");
            this.dispose();
            new LoginBranch();
        } else if (password.length() < 5) {
            JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 5 caracteres.");
        } else {
            JOptionPane.showMessageDialog(null, "As senhas não coincidem.");
        }
    }
}
