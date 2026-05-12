package vista.componentes;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Estilo {

    //Colores
    public static final Color AZUL_PRIMARIO = new Color(109, 142, 173);
    public static final Color AZUL_HOVER = new Color(90, 122, 150);
    public static final Color AZUL_FOCUS = new Color(120, 160, 200);

    public static final Color GRIS_BORDE = new Color(210, 210, 210);
    public static final Color GRIS_SUAVE = new Color(245, 246, 248);
    public static final Color TITULOS = new Color(36, 56, 87);
    public static final Color TEXTO = new Color(36, 56, 87);
    public static final Color INFO = new Color(123, 149, 189);

    public static final Color FONDO_PANEL = new Color(248, 249, 251);

    //Fuentes
    public static final Font TITULO = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font SUBTITULO = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font TEXTO_FUENTE = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font TEXTO_INFO = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BOTON = new Font("Segoe UI Semibold", Font.PLAIN, 14);

    //Titulo
    public static void aplicarTitulo(JLabel lbl) {
        lbl.setFont(TITULO);
        lbl.setForeground(TITULOS);
    }
    
    //Separador
    public static void aplicarSeparadorTitulo(JSeparator sep) {

        sep.setPreferredSize(new Dimension(1, 3));

        sep.setUI(new javax.swing.plaf.basic.BasicSeparatorUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();

                int w = c.getWidth();

                GradientPaint grad = new GradientPaint(
                        0, 0, new Color(200, 210, 230),
                        w, 0, new Color(240, 242, 248)
                );

                g2.setPaint(grad);
                g2.fillRect(0, 1, w, 2);

                g2.dispose();
            }
        });

        sep.setBorder(BorderFactory.createEmptyBorder(8, 0, 12, 0));
    }
    
    //Subtitulo
    public static void aplicarSubtitulo(JLabel lbl) {
        lbl.setFont(SUBTITULO);
        lbl.setForeground(TITULOS);
    }

    //Texto Normal
    public static void aplicarTexto(JLabel lbl) {
        lbl.setFont(TEXTO_FUENTE);
        lbl.setForeground(TEXTO);
    }
    
    //Texto info
    public static void aplicarInfo(JLabel lbl) {
        lbl.setFont(TEXTO_INFO);
        lbl.setForeground(INFO);
    }

    //Boton
    public static void aplicarBoton(JButton btn) {
        btn.setBackground(AZUL_PRIMARIO);
        btn.setForeground(Color.WHITE);
        btn.setFont(BOTON);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 40));
        btn.setMaximumSize(new Dimension(140, 40));
        btn.setMinimumSize(new Dimension(140, 40));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(AZUL_HOVER);
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(AZUL_PRIMARIO);
            }
        });
    }
    
    //Boton nav
    public static void aplicarBotonNav(JButton btn) {
        aplicarBoton(btn);

        Dimension tamNav = new Dimension(33, 33); 
        btn.setPreferredSize(tamNav);
        btn.setMaximumSize(tamNav);
        btn.setMinimumSize(tamNav);
    }
    
    //Configuracion de teclado
    public static void configurarTeclado(java.awt.Component comp) {
        comp.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                switch (e.getKeyCode()) {
                    case java.awt.event.KeyEvent.VK_ENTER:
                        if (comp instanceof javax.swing.JButton) {
                            ((javax.swing.JButton) comp).doClick(); // Ejecuta botón
                        } else {
                            comp.transferFocus(); // Salta al siguiente
                        }
                        break;
                    case java.awt.event.KeyEvent.VK_DOWN:
                        comp.transferFocus(); // Flecha abajo -> Siguiente
                        break;
                    case java.awt.event.KeyEvent.VK_UP:
                        comp.transferFocusBackward(); // Flecha arriba -> Anterior
                        break;
                }
            }
        });
    }
    
    //Campo de texto
    public static void aplicarTextField(JTextField txt) {
        txt.setFont(TEXTO_FUENTE);
        txt.setBackground(Color.WHITE);

        txt.setBorder(new CompoundBorder(
                new LineBorder(GRIS_BORDE, 1),
                new EmptyBorder(8, 10, 8, 10)
        ));

        txt.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txt.setBorder(new CompoundBorder(
                        new LineBorder(AZUL_FOCUS, 2),
                        new EmptyBorder(8, 10, 8, 10)
                ));
            }

            public void focusLost(FocusEvent e) {
                txt.setBorder(new CompoundBorder(
                        new LineBorder(GRIS_BORDE, 1),
                        new EmptyBorder(8, 10, 8, 10)
                ));
            }
        });
    }

    //Combobox
    public static void aplicarComboBox(JComboBox<?> combo) {
        combo.setFont(TEXTO_FUENTE);
        combo.setBackground(Color.WHITE);
        combo.setBorder(new LineBorder(GRIS_BORDE, 1));
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //Label con imagen
    public static void aplicarImagenResponsive(JLabel lbl, String ruta) {

        ImageIcon icono = new ImageIcon(Estilo.class.getResource(ruta));
        Image imgOriginal = icono.getImage();

        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setText("");

        lbl.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int w = lbl.getWidth();
                int h = lbl.getHeight();

                if (w > 0 && h > 0) {
                    Image imgEscalada = imgOriginal.getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lbl.setIcon(new ImageIcon(imgEscalada));
                }
            }
        });
    }
    
    //Panel tipo card
    public static void aplicarCard(JPanel panel) {
        panel.setBackground(Color.WHITE);

        panel.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 3, 3, new Color(0, 0, 0, 20)),
                new CompoundBorder(
                        new LineBorder(GRIS_BORDE),
                        new EmptyBorder(25, 25, 25, 25)
                )
        ));
    }

    //Scroll
    public static void aplicarScroll(JScrollPane scroll) {
        scroll.setBorder(new LineBorder(GRIS_BORDE));
        scroll.getViewport().setBackground(Color.WHITE);
    }
}