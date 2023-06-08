package views.components;

import java.text.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class IntegerField extends JFormattedTextField {
    public IntegerField() {
        super(createFormatter());
        handleLastDigitRemove();
    }

    protected static NumberFormatter createFormatter() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        return formatter;
    }

    protected void handleLastDigitRemove() {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE && getLength() == 1) {
                    setText("0");
                }
            }
        });
    }

    public Integer getLength() {
        return getText().length();
    }
}
