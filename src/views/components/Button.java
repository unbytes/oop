package views.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Button extends JButton {

    public Button(String text) {
        super(text);
        setUp();
        enableRoundedBorder(8);
    }

    public Button(Icon icon) {
        super(icon);
        setUp();
    }

    public void setUp() {
        this.setBackground(Color.WHITE);
        this.setForeground(new Color(12, 12, 12));
        this.setFocusable(false);
        this.setBorder(null);
        this.setPreferredSize(new Dimension(200, 30));
    }

    public void enableRoundedBorder(Integer radius) {
        this.setBorder(new RoundedBorder(radius));
    }

    public void disableRoundedBorder() {
        this.setBorder(null);
    }

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
