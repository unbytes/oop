package views;

import java.awt.*;
import java.util.*;
import views.components.Form;
import views.layouts.BasicFrame;

public class Client extends BasicFrame {
    public Client() {
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
    }
}
