package views;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import controllers.StoreController;
import models.Client;
import views.components.Title;
import views.components.Button;
import views.components.Form;
import views.layouts.BasicFrame;

public class ClientView extends BasicFrame {
    private StoreController storeController = new StoreController();
    private JPanel handleClientPanel = new JPanel();
    private JScrollPane clientListPanel = null;
    private JList<String> clientList;
    private String branchUUID;
    private Form currentUpdateForm;

    public ClientView(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new BorderLayout());

        makeClientList();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void refreshClientList() {
        handleClientPanel.repaint();
        handleClientPanel.revalidate();
        handleClientPanel.setBackground(Color.WHITE);
    }

    public void styleClientListPanel() {
        refreshClientList();
        clientListPanel.setPreferredSize(new Dimension(500, 500));
        clientListPanel.setBorder(BorderFactory.createEmptyBorder());
        clientListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        clientListPanel.setBackground(Color.WHITE);
    }

    public void initClientList() {
        String clients[] = storeController.getClientesAsHTMLTemplate();

        handleClientPanel.removeAll();
        if (clients.length == 0) {
            handleClientPanel.add(new JLabel("Nenhum cliente cadastrado"));
        } else {
            clientList = new JList<String>(clients);
            clientListPanel = new JScrollPane(clientList);
            handleClientPanel.add(clientListPanel);
        }

        styleClientListPanel();
    }

    public void makeClientList() {
        Title titleLabel = new Title("Clientes");
        bodyPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        bodyPanel.add(buttonPanel, BorderLayout.SOUTH);

        Button registerButton = new Button("Cadastrar");
        registerButton.addActionListener(e -> {
            this.dispose();
            new CreateClient(branchUUID);
        });
        buttonPanel.add(registerButton);

        Button loginButton = new Button("Entrar");
        loginButton.addActionListener(e -> {
            String selectedValue = clientList.getSelectedValue();
            String clientCPF = selectedValue.split("<br>")[0].split(": ")[1].trim();
            handleClientPopUpLogin(clientCPF);
            Client client =  storeController.getClientByCPF(clientCPF) ;
            this.dispose(); 
            new PurchasedProducts(branchUUID,  client);
        });
        buttonPanel.add(loginButton);

        Button deleteButton = new Button("Deletar");
        deleteButton.addActionListener(e -> {
            String selectedValue = clientList.getSelectedValue();
            String clientCPF = selectedValue.split("<br>")[0].split(": ")[1].trim();
            handleClientPopUpDelete(clientCPF);
        });
        buttonPanel.add(deleteButton);

        Button updateProductButton = new Button("Atualizar");
        updateProductButton.addActionListener(initUpdateFormEvent -> {
            handleUpdateClientForm();
        });
        buttonPanel.add(updateProductButton, BorderLayout.SOUTH);

        initClientList();
        bodyPanel.add(handleClientPanel, BorderLayout.CENTER);
    }

    public void handleClientPopUpLogin(String clientCPF) {
        String clientName = storeController.getClientName(clientCPF);
        JOptionPane.showMessageDialog(null, "Bem vindo, " + clientName);
    }

    public void handleClientPopUpDelete(String clientCPF) {
        storeController.removeClient(clientCPF);
        initClientList();
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
    }

    public void handleUpdateClientForm() {
        String selectedValue = clientList.getSelectedValue();
        if (selectedValue != null) {
            String clientCPF = selectedValue.split("<br>")[0].split(": ")[1].trim();

            Form updateForm = createClientUpdateForm(clientCPF);
            Button submitButton = updateForm.getSubmitButton();
            submitButton.addActionListener(updateProductEvent -> {
                LinkedHashMap<String, String> clientData = updateForm.retrieveFieldValues();
                storeController.updateClient(clientCPF, clientData);
                initClientList();
            });

            if (currentUpdateForm != null) {
                handleClientPanel.remove(currentUpdateForm);
            }

            handleClientPanel.add(updateForm);
            refreshClientList();

            currentUpdateForm = updateForm;
        }
    }

    public Form createClientUpdateForm(String clientCPF) {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("CPF", Form.FieldTypes.TEXT);
                put("Nome", Form.FieldTypes.TEXT);
                put("Idade", Form.FieldTypes.INTEGER);
            }
        };

        ArrayList<String> fieldValues = new ArrayList<String>() {
            {
                add(clientCPF);
                add(storeController.getClientName(clientCPF));
                add(storeController.getClientByCPF(clientCPF).getAge().toString());
            }
        };

        Form updateClientForm = new Form("Atualizar", "", components);
        updateClientForm.setFieldDefaultValuesInOder(fieldValues);
        return updateClientForm;
    }
}
