package views.components;

import java.text.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

/**
 * Classe representa um campo de texto formatado para receber apenas números
 * inteiros.
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class IntegerField extends JFormattedTextField {
    /**
     * Cria um objeto IntegerField.
     */
    public IntegerField() {
        super(createFormatter());
        handleLastDigitRemove();
        this.setText("0");
    }

    /**
     * Cria um campo de texto formatado para receber apenas números inteiros.
     * 
     * @return <code>NumberFormatter</code> o formatador de números.
     */
    protected static NumberFormatter createFormatter() {
        NumberFormat format = new DecimalFormat("###.###");
        format.setMaximumFractionDigits(3);

        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        return formatter;
    }

    /**
     * Adiciona um listener para quando o último dígito for removido.
     */
    protected void handleLastDigitRemove() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE && getLength() == 1) {
                    setText("0");
                }
            }
        });
    }

    /**
     * Retorna o tamanho do texto.
     * 
     * @return <code>Integer</code> o tamanho do texto.
     */
    public Integer getLength() {
        return getText().length();
    }
}
