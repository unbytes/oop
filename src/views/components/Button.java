package views.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Button estilizado para uso na aplicação.
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class Button extends JButton {

    /**
     * Construtor da classe Button.
     * 
     * @param text <code>String</code> o texto do botão.
     */
    public Button(String text) {
        super(text);
        setUp();
        enableRoundedBorder(8);
    }

    /**
     * Construtor da classe Button.
     * 
     * @param icon <code>Icon</code> o ícone do botão.
     */
    public Button(Icon icon) {
        super(icon);
        setUp();
    }

    /**
     * Inicializador do Button com as configurações padrões.
     */
    public void setUp() {
        this.setBackground(Color.WHITE);
        this.setForeground(new Color(12, 12, 12));
        this.setFocusable(false);
        this.setBorder(null);
        this.setPreferredSize(new Dimension(200, 30));
    }

    /**
     * Habilita a borda arredondada do botão.
     * 
     * @param radius <code>Integer</code> o raio da borda.
     */
    public void enableRoundedBorder(Integer radius) {
        this.setBorder(new RoundedBorder(radius));
    }

    /**
     * Desabilita a borda arredondada do botão.
     * 
     * @param radius <code>Integer</code> o raio da borda.
     */
    public void disableRoundedBorder() {
        this.setBorder(null);
    }

    /**
     * Classe interna para a borda arredondada do botão.
     * 
     * @see RoundedBorder
     */
    private static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
