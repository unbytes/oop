package views.components;

import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 * Classe para criar um formulário
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class Form extends JPanel {
    private Button submitButton;
    private GridBagConstraints gbc = new GridBagConstraints();
    private LinkedHashMap<String, JComponent> fields = new LinkedHashMap<String, JComponent>();
    private HashMap<String, String[]> comboBoxOptions = new HashMap<String, String[]>();
    
    /**
     * Enumeração dos tipos de campos que podem ser adicionados ao formulário.
     */
    public enum FieldTypes {
        TEXT, PASSWORD, INTEGER, COMBOBOX, CHECKBOX
    }

    /**
     * Construtor da classe Form.
     * 
     * @param buttonText <code>String</code> o texto do botão de submissão.
     * @param title <code>String</code> o título do formulário.
     * @param components <code>LinkedHashMap&lt;String, FieldTypes&gt;</code> os componentes do formulário.
     */
    public Form(String buttonText, String title, LinkedHashMap<String, FieldTypes> components) {
        initForm(buttonText, title, components);
    }

    /**
     * Construtor da classe Form.
     * 
     * @param buttonText <code>String</code> o texto do botão de submissão.
     * @param title <code>String</code> o título do formulário.
     * @param components <code>LinkedHashMap&lt;String, FieldTypes&gt;</code> os componentes do formulário.
     * @param comboBoxOptions <code>HashMap&lt;String, String[]&gt;</code> as opções do combobox.
     */
    public Form(String buttonText, String title, LinkedHashMap<String, FieldTypes> components,
            HashMap<String, String[]> comboBoxOptions) {
        this.comboBoxOptions = comboBoxOptions;
        initForm(buttonText, title, components);
    }

    /**
     * Inicializador padrão para o formulário.
     * 
     * @param buttonText <code>String</code> o texto do botão de submissão.
     * @param title <code>String</code> o título do formulário.
     * @param components <code>LinkedHashMap&lt;String, FieldTypes&gt;</code> os componentes do formulário.
     */
    public void initForm(String buttonText, String title, LinkedHashMap<String, FieldTypes> components) {
        submitButton = new Button(buttonText);
        setUp(title);
        addComponents(components);
        addSubmitButton();
    }

    /**
     * Inicializador dos campos do formulário.
     * 
     * @param fieldValues <code>ArrayList&lt;String&gt;</code> os valores dos campos.
     */
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

    /**
     * Inicializador dos componentes do formulário.
     * 
     * @param title <code>String</code> o título do formulário.
     */
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

    /**
     * Inicializa um componente do formulário.
     * 
     * @param name <code>String</code> o nome do componente.    
     * @param component <code>JComponent</code> o componente.
     */
    public void setUpComponent(String name, JComponent component) {
        component.setPreferredSize(new Dimension(200, 30));
        component.setBackground(Color.WHITE);
        component.setName(name);
        fields.put(name, component);
        this.add(component, gbc);
    }

    /**
     * Adiciona os componentes do formulário.
     * 
     * @param components <code>LinkedHashMap&lt;String, FieldTypes&gt;</code> os componentes do formulário.
     */
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
    
    /**
     * Remove os componentes do formulário.
     */
    public void removeFields() {
        for (JComponent component : getFields().values()) {
            this.remove(component);
        }
    }

    /**
     * Atualiza os componentes do formulário.
     * 
     * @param components <code>LinkedHashMap&lt;String, FieldTypes&gt;</code> os componentes do formulário.
     */
    public void updateFields(LinkedHashMap<String, FieldTypes> components) {
        removeFields();
        addComponents(components);
    }

    /**
     * Adiciona o botão de submissão do formulário.
     */
    public void addSubmitButton() {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        this.add(submitButton, gbc);
    }

    /**
     * Retorna os campos dos componentes do formulário.
     * 
     * @return <code>LinkedHashMap&lt;String, String&gt;</code> os campos dos componentes do formulário.
     */
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

    /**
     * Retorna os componentes do formulário.
     * 
     * @return <code>LinkedHashMap&lt;String, JComponent&gt;</code> os componentes do formulário.
     */
    public LinkedHashMap<String, JComponent> getFields() {
        return this.fields;
    }

    /**
     * Retorna o botão de submissão do formulário.
     * 
     * @return <code>Button</code> o botão de submissão do formulário.
     */
    public Button getSubmitButton() {
        return this.submitButton;
    }

    /**
     * Remove o botão de submissão do formulário.
     */
    public void removeSubmitButton() {
        this.remove(submitButton);
    }
}
