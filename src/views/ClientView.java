package views;

import java.awt.*;
import javax.swing.*;
import controllers.StoreController;
import models.Client;
import views.components.Title;
import views.components.Button;
import views.layouts.BasicFrame;

public class ClientView extends BasicFrame {
    private StoreController storeController = new StoreController();
    private JList<String> clientList;
    private String branchUUID;

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

    public void makeClientList() {
        Title titleLabel = new Title("Clientes");
        bodyPanel.add(titleLabel, BorderLayout.NORTH);

        clientList = new JList<String>(storeController.getClientesAsHTMLTemplate());
        bodyPanel.add(clientList, BorderLayout.CENTER);

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

    }

    public void handleClientPopUpLogin(String clientCPF) {
        String clientName = storeController.getClientName(clientCPF);
        JOptionPane.showMessageDialog(null, "Bem vindo, " + clientName);
    }

    public void handleClientPopUpDelete(String clientCPF) {
        storeController.removeClient(clientCPF);
        clientList.setListData(storeController.getClientesAsHTMLTemplate());
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso");
    }
}
