package views.components;

import java.awt.*;
import javax.swing.*;

/**
 * Classe que representa um título de uma página.
 * 
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 */
public class Title extends JLabel {
    /**
     * Cria um título com o texto passado.
     * 
     * @param text <code>String</code> Texto do título.
     */
    public Title(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalAlignment(JLabel.CENTER);
    }
}
