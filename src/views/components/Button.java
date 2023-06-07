package views.components;

import java.awt.Color;
import javax.swing.JButton;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        this.setBackground(Color.WHITE);
        this.setFocusable(false);
        this.setBorderPainted(false);
    }
}
