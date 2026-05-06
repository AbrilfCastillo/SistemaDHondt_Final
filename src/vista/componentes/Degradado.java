package vista.componentes;
import javax.swing.*;
import java.awt.*;

public class Degradado extends JPanel {

    // Colores del degradado
    private Color color1 = new Color(59, 86, 128);    // arriba
    private Color color2 = new Color(126, 153, 191);  // abajo

    public Degradado() {
        setOpaque(false); // importante para pintar manualmente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // suavizado de render
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 🎨 Degradado principal (vertical)
        GradientPaint vertical = new GradientPaint(
                0, 0, color1,
                0, getHeight(), color2
        );
        g2.setPaint(vertical);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // ✨ Luz lateral suave (izquierda → derecha)
        GradientPaint light = new GradientPaint(
                0, 0, new Color(255, 255, 255, 40),
                getWidth(), 0, new Color(255, 255, 255, 0)
        );
        g2.setPaint(light);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // 🎯 Linea separadora derecha
        g2.setColor(new Color(0, 0, 0, 40));
        g2.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight());

        g2.dispose();
    }
}