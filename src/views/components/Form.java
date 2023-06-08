package views.components;

import java.awt.*;
import javax.swing.*;
import java.util.LinkedHashMap;

public class Form extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private Button submitButton = new Button("Submit");

    public enum FieldTypes {
        TEXT, PASSWORD, INTEGER
    }

    public Form(String title, LinkedHashMap<String, FieldTypes> components) {
        setUp(title);
        addComponents(components);
        addSubmitButton();
    }

    public void setUp(String title) {
        this.setLayout(new GridBagLayout());
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);
    }

    public void addComponents(LinkedHashMap<String, FieldTypes> components) {
        gbc.gridwidth = 1;
        for (String key : components.keySet()) {
            gbc.gridx = 0;
            gbc.gridy++;
            JLabel label = new JLabel(key + ":", SwingConstants.LEFT);
            label.setPreferredSize(new Dimension(200, 30));
            this.add(label, gbc);

            gbc.gridx = 1;
            FieldTypes type = components.get(key);
            if (type == FieldTypes.TEXT) {
                JTextField textField = new JTextField();
                textField.setPreferredSize(new Dimension(200, 30));
                this.add(textField, gbc);
            } else if (type == FieldTypes.PASSWORD) {
                JPasswordField passwordField = new JPasswordField();
                passwordField.setPreferredSize(new Dimension(200, 30));
                this.add(passwordField, gbc);
            } else if (type == FieldTypes.INTEGER) {
                IntegerField integerField = new IntegerField();
                integerField.setPreferredSize(new Dimension(200, 30));
                this.add(integerField, gbc);
            } else {
                throw new Error("Invalid component type");
            }
        }
    }

    public void addSubmitButton() {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        this.add(submitButton, gbc);
    }
}
