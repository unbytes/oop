package views.components;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Form extends JPanel {
    private Button submitButton;
    private GridBagConstraints gbc = new GridBagConstraints();
    private ArrayList<JComponent> fields = new ArrayList<JComponent>();

    public enum FieldTypes {
        TEXT, PASSWORD, INTEGER
    }

    public Form(String buttonText, String title, LinkedHashMap<String, FieldTypes> components) {
        submitButton = new Button(buttonText);
        setUp(title);
        addComponents(components);
        addSubmitButton();
    }

    public Form(String title, LinkedHashMap<String, FieldTypes> components) {
        submitButton = new Button("Submit");
        setUp(title);
        addComponents(components);
        addSubmitButton();
    }

    public void setUp(String title) {
        this.setBackground(Color.WHITE);
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
                textField.setName(key);
                this.fields.add(textField);
                this.add(textField, gbc);
            } else if (type == FieldTypes.PASSWORD) {
                JPasswordField passwordField = new JPasswordField();
                passwordField.setPreferredSize(new Dimension(200, 30));
                passwordField.setName(key);
                this.fields.add(passwordField);
                this.add(passwordField, gbc);
            } else if (type == FieldTypes.INTEGER) {
                IntegerField integerField = new IntegerField();
                integerField.setPreferredSize(new Dimension(200, 30));
                integerField.setName(key);
                this.fields.add(integerField);
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

    public LinkedHashMap<String, String> retrieveFieldValues() {
        LinkedHashMap<String, String> fieldValues = new LinkedHashMap<String, String>();
        for (JComponent component : getFields()) {
            if (component instanceof JPasswordField) {
                JPasswordField passwordField = (JPasswordField) component;
                fieldValues.put(passwordField.getName(), String.valueOf(passwordField.getPassword()));
            } else {
                JTextField field = (JTextField) component;
                fieldValues.put(field.getName(), field.getText());
            }
        }
        return fieldValues;
    }

    public ArrayList<JComponent> getFields() {
        return this.fields;
    }

    public Button getSubmitButton() {
        return this.submitButton;
    }
}
