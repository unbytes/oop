package views.components;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Form extends JPanel {
    private Button submitButton;
    private GridBagConstraints gbc = new GridBagConstraints();
    private LinkedHashMap<String, JComponent> fields = new LinkedHashMap<String, JComponent>();
    private HashMap<String, String[]> comboBoxOptions = new HashMap<String, String[]>();

    public enum FieldTypes {
        TEXT, PASSWORD, INTEGER, COMBOBOX, CHECKBOX
    }

    public Form(String buttonText, String title, LinkedHashMap<String, FieldTypes> components) {
        initForm(buttonText, title, components);
    }

    public Form(String buttonText, String title, LinkedHashMap<String, FieldTypes> components,
            HashMap<String, String[]> comboBoxOptions) {
        this.comboBoxOptions = comboBoxOptions;
        initForm(buttonText, title, components);
    }

    public void initForm(String buttonText, String title, LinkedHashMap<String, FieldTypes> components) {
        submitButton = new Button(buttonText);
        setUp(title);
        addComponents(components);
        addSubmitButton();
    }

    public void setFieldDefaultValuesInOder(ArrayList<String> fieldValues) {
        ArrayList<JComponent> fieldComponents = new ArrayList<JComponent>(fields.values());
        for (int index = 0; index < fieldValues.size(); index++) {
            JComponent component = fieldComponents.get(index);
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText(fieldValues.get(index));
            } else if (component instanceof JPasswordField) {
                JPasswordField passwordField = (JPasswordField) component;
                passwordField.setText(fieldValues.get(index));
            } else if (component instanceof IntegerField) {
                IntegerField integerField = (IntegerField) component;
                integerField.setText(fieldValues.get(index));
            } else if (component instanceof JComboBox) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) component;
                comboBox.setSelectedItem(fieldValues.get(index));
            } else if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                checkBox.setSelected(Boolean.parseBoolean(fieldValues.get(index)));
            } else {
                throw new Error("Invalid field type");
            }
        }
    }

    public void setUp(String title) {
        this.setBackground(Color.WHITE);
        this.setLayout(new GridBagLayout());
        gbc.insets = new Insets(8, 8, 8, 8);

        Title titleLabel = new Title(title);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        this.add(titleLabel, gbc);
    }

    public void setUpComponent(String name, JComponent component) {
        component.setPreferredSize(new Dimension(200, 30));
        component.setBackground(Color.WHITE);
        component.setName(name);
        fields.put(name, component);
        this.add(component, gbc);
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
                setUpComponent(key, new JTextField());
            } else if (type == FieldTypes.PASSWORD) {
                setUpComponent(key, new JPasswordField());
            } else if (type == FieldTypes.INTEGER) {
                setUpComponent(key, new IntegerField());
            } else if (type == FieldTypes.COMBOBOX) {
                setUpComponent(key, new JComboBox<String>(comboBoxOptions.get(key)));
            } else if (type == FieldTypes.CHECKBOX) {
                setUpComponent(key, new JCheckBox());
            } else {
                throw new Error("Invalid field type");
            }
        }
    }

    public void removeFields() {
        for (JComponent component : getFields().values()) {
            this.remove(component);
        }
    }

    public void updateFields(LinkedHashMap<String, FieldTypes> components) {
        removeFields();
        addComponents(components);
    }

    public void addSubmitButton() {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        this.add(submitButton, gbc);
    }

    public LinkedHashMap<String, String> retrieveFieldValues() {
        LinkedHashMap<String, String> fieldValues = new LinkedHashMap<String, String>();
        for (JComponent component : getFields().values()) {
            if (component instanceof JPasswordField) {
                JPasswordField passwordField = (JPasswordField) component;
                fieldValues.put(passwordField.getName(), String.valueOf(passwordField.getPassword()));
            } else if (component instanceof JComboBox) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) component;
                fieldValues.put(comboBox.getName(), (String) comboBox.getSelectedItem());
            } else if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                fieldValues.put(checkBox.getName(), String.valueOf(checkBox.isSelected()));
            } else {
                JTextField textField = (JTextField) component;
                fieldValues.put(textField.getName(), textField.getText());
            }
        }
        return fieldValues;
    }

    public LinkedHashMap<String, JComponent> getFields() {
        return this.fields;
    }

    public Button getSubmitButton() {
        return this.submitButton;
    }

    public void removeSubmitButton() {
        this.remove(submitButton);
    }
}
