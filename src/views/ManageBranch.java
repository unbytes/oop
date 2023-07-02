package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controllers.BranchController;
import views.components.Title;
import views.components.Button;
import views.layouts.BasicFrame;

/**
 * Classe que cria a tela de gerenciamento de filiais
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class ManageBranch extends BasicFrame {
    private GridBagConstraints gbc = new GridBagConstraints();
    private BranchController branchController = new BranchController();
    private String branchUUID;

    /**
     * Construtor da classe ManageBranch
     *  
     * @param branchUUID <code>String</code> UUID da filial
     */
    public ManageBranch(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
    }

    /**
     * Cria o corpo da tela de gerenciamento de filiais
     */
    public void makeBody() {
        bodyPanel.setLayout(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);

        makeBranchInfoHeader();

        JPanel branchesButtonsPanel = new JPanel();
        branchesButtonsPanel.setBackground(Color.WHITE);

        Button clientsButton = new Button("Clientes");
        branchesButtonsPanel.add(clientsButton);
        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClientView(branchUUID);
            }
        });

        Button productsButton = new Button("Produtos");
        branchesButtonsPanel.add(productsButton);
        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductView(branchUUID);
            }
        });

        gbc.gridy = 1;
        bodyPanel.add(branchesButtonsPanel, gbc);

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    /**
     * Cria o cabeçalho da tela de gerenciamento de filiais
     * com o nome e endereço da filial e opção de atualizar o endereço.
     */
    public void makeBranchInfoHeader() {
        JPanel branchInfoHeaderPanel = new JPanel();
        branchInfoHeaderPanel.setBackground(Color.WHITE);
        branchInfoHeaderPanel.setLayout(new GridLayout(2, 1));

        Title branchName = new Title("Filial: " + branchUUID);
        branchInfoHeaderPanel.add(branchName);

        JPanel addressPanel = new JPanel();
        addressPanel.setBackground(Color.WHITE);
        addressPanel.setLayout(new FlowLayout());

        Title branchAddress = new Title("Endereço: ");
        addressPanel.add(branchAddress);

        String branchCity = branchController.getBranchCity(branchUUID);
        JTextField updateCityField = new JTextField(branchCity, 10);
        addressPanel.add(updateCityField);

        String branchRegion = branchController.getBranchRegion(branchUUID);
        JTextField updateRegionField = new JTextField(branchRegion, 10);
        addressPanel.add(updateRegionField);

        Button updateAddressButton = new Button("Atualizar");
        updateAddressButton.setPreferredSize(new Dimension(100, 20));
        updateAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCity = updateCityField.getText();
                String newRegion = updateRegionField.getText();
                boolean response = branchController.updateBranchAddress(branchUUID, newCity, newRegion);
                if (response) {
                    JOptionPane.showMessageDialog(null, "Endereço atualizado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar endereço");
                }
            }
        });
        addressPanel.add(updateAddressButton);

        branchInfoHeaderPanel.add(addressPanel);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        bodyPanel.add(branchInfoHeaderPanel, gbc);
    }   

    /**
     * Faz logout da filial quando a janela é fechada
     * 
     * @param branchUUID <code>String</code> UUID da filial
     */
    public void logoutBranchWhenWindowClose(String branchUUID) {
        WindowListener windowListener = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                branchController.logoutBranch(branchUUID);
            }
        };
        this.addWindowListener(windowListener);
    }
}
