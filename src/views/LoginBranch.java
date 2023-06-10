package views;

import java.awt.*;
import java.util.*;
import views.components.Form;
import views.layouts.BasicFrame;

public class LoginBranch extends BasicFrame{
    public LoginBranch() {
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
                put("UUID", Form.FieldTypes.TEXT);
                put("Password", Form.FieldTypes.PASSWORD);

            }
        };
        Form branchForm = new Form("Login Branch", components);
        bodyPanel.add(branchForm);
    }
    
}
