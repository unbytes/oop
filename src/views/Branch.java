package views;

import java.awt.*;
import java.util.*;
import views.components.Form;
import views.layouts.BasicFrame;

public class Branch extends BasicFrame {
    public Branch() {
        super();
        makeBody();
    }

    public void makeBody() {
        bodyPanel.setLayout(new GridLayout(1, 2));

        createBranchForm();
        // createBranchList();

        this.add(bodyPanel, BorderLayout.CENTER);
    }

    public void createBranchForm() {
        LinkedHashMap<String, Form.FieldTypes> components = new LinkedHashMap<String, Form.FieldTypes>() {
            {
                put("Address", Form.FieldTypes.TEXT);
                put("Password", Form.FieldTypes.PASSWORD);
                put("Confirm password", Form.FieldTypes.PASSWORD);
            }
        };
        Form branchForm = new Form("Create Branch", components);
        bodyPanel.add(branchForm);
    }
}
