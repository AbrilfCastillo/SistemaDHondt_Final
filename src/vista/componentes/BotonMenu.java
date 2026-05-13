package vista.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BotonMenu extends JButton {

    //Colores
    private Color bgColor = new Color(132, 155, 191);
    private Color textColor = new Color(228, 235, 247);

    private Color hoverbgColor = new Color(228, 231, 240);
    private Color hovertextColor = new Color(34, 53, 84);

    private Color activeBgColor = new Color(255, 255, 255);
    private Color activeTextColor = new Color(34, 53, 84);

    //Estados
    private boolean hover = false;
    private boolean active = false;

    //Iconos
    private Icon iconNormal;
    private Icon iconHover;
    private Icon iconActive;

    //Inicializacion
    private void inicializar() {

        setPreferredSize(new Dimension(200, 40));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setMinimumSize(new Dimension(200, 40));
        setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(textColor);
        setFocusable(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(10);
        setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));

        //Hover y focus
        addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                actualizarEstilo();
            }

        @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                actualizarEstilo();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                actualizarEstilo();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                actualizarEstilo();
            }
        });
    }


    //Constructor vacio (NECESARIO para NetBeans)

    public BotonMenu() {
        super();
        inicializar();
    }

    //Constructor simple
    public BotonMenu(String text) {
        super(text);
        inicializar();
    }

    //Constructor completo con iconos
    public BotonMenu(String text, Icon iconNormal, Icon iconHover, Icon iconActive) {
        super(text);

        this.iconNormal = iconNormal;
        this.iconHover = iconHover;
        this.iconActive = iconActive;

        inicializar();

        if (iconNormal != null) {
            setIcon(iconNormal);
        }
    }


    //Logica de estilo

    private void actualizarEstilo() {

        if (active) {
            setForeground(activeTextColor);
            if (iconActive != null) setIcon(iconActive);

        } else if (hover || isFocusOwner()) {
            setForeground(hovertextColor);
            if (iconHover != null) setIcon(iconHover);

        } else {
            setForeground(textColor);
            if (iconNormal != null) setIcon(iconNormal);
        }

        repaint();
    }


    //Metodos publicos


    // activar/desactivar boton
    public void setActive(boolean active) {
        this.active = active;
        actualizarEstilo();
    }

    public boolean isActive() {
        return active;
    }

    // asignar iconos despues
    public void setIconos(Icon normal, Icon hover, Icon active) {
        this.iconNormal = normal;
        this.iconHover = hover;
        this.iconActive = active;

        setIcon(normal);
    }


    //Pintado

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (active) {
            g2.setColor(activeBgColor);
        } else if (hover || isFocusOwner()) {
            g2.setColor(hoverbgColor);
        } else {
            g2.setColor(bgColor);
        }

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();

        super.paintComponent(g);
    }
}