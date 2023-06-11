package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controllers.BranchController;
import views.components.Title;
import views.layouts.BasicFrame;

public class ManageBranch extends BasicFrame {
    private BranchController branchController = new BranchController();
    private String branchUUID;

    public ManageBranch(String branchUUID) {
        super();

        this.branchUUID = branchUUID;

        makeBody();
        logoutBranchWhenWindowClose(branchUUID);
    }

    public void makeBody() {
        bodyPanel.setLayout(new BorderLayout());

        makeBranchInfoHeader();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void makeBranchInfoHeader() {
        JPanel branchInfoHeaderPanel = new JPanel();
        branchInfoHeaderPanel.setLayout(new GridLayout(2, 1));

        Title branchName = new Title("Filial: " + branchUUID);
        branchInfoHeaderPanel.add(branchName);

        Title branchAddress = new Title("Endereço: " + branchController.getBranchByUUID(branchUUID).getAddress().toString());
        branchInfoHeaderPanel.add(branchAddress);

        bodyPanel.add(branchInfoHeaderPanel, BorderLayout.NORTH);
    }

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
