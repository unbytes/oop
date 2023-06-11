package views.components;

import java.awt.*;
import javax.swing.*;

public class Title extends JLabel {
    public Title(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
