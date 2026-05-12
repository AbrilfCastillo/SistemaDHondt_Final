package vista;

import controladores.ControladorBajaPartido;
import controladores.ControladorCargarPartido;
import controladores.ControladorInformeCargos;
import controladores.ControladorModificarPartido;
import controladores.ControladorInformeDivision;
import controladores.ControladorInformePartidos;
import dao.SistemaDAO;
import vista.paneles.InformeDivision;
import vista.paneles.InformePartidos;
import vista.paneles.Inicio;
import vista.paneles.BajaPartido;
import vista.paneles.ModificarPartido;
import vista.paneles.CargarPartido;
import vista.paneles.InformeCargos;
import vista.componentes.Degradado;
import vista.componentes.BotonMenu;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import vista.componentes.Estilo;

/**
 * Ventana principal del sistema.
 * Contiene el menu lateral y la navegacion entre paneles.
 */
public class MenuPrincipal extends javax.swing.JFrame {

    // Guarda el nombre del panel actualmente visible
    private String panelActual = "pnlInicio";

    /**
     * Constructor principal de la ventana.
     * Inicializa componentes, controladores y configuraciones.
     */
    public MenuPrincipal() {

        initComponents();
        
        // Configuracion general de la ventana
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 650));

        // Configuracion inicial
        cerrarPrograma();
        configurarBotonesMenu();
        configurarNavegacion();

        // Configuracion del menu lateral
        pnlMenu.setPreferredSize(new Dimension(250, 0));
        pnlMenu.setMinimumSize(new Dimension(250, 0));
        pnlMenu.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));

        
        // Centra el boton reset dentro del BoxLayout
        btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Define tamaño maximo del boton
        btnReset.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 40)
        ); 
        btnReset.addActionListener(e -> resetearDatos()); // Evento del boton

        /*
         * Creacion de paneles y controladores
         */

        Inicio o1 = new Inicio();

        CargarPartido o2 = new CargarPartido();
        new ControladorCargarPartido(o2);

        ModificarPartido o3 = new ModificarPartido();
        new ControladorModificarPartido(o3);

        BajaPartido o4 = new BajaPartido();
        new ControladorBajaPartido(o4);

        InformeCargos o5 = new InformeCargos();
        new ControladorInformeCargos(o5);

        InformeDivision o6 = new InformeDivision();
        new ControladorInformeDivision(o6, this);

        InformePartidos o7 = new InformePartidos();
        new ControladorInformePartidos(o7, this);

        /*
         * Agrega los paneles al CardLayout
         */

        pnlFormularios.add(o1, "pnlInicio");
        pnlFormularios.add(o2, "pnlCargarPartido");
        pnlFormularios.add(o3, "pnlModificarPartido");
        pnlFormularios.add(o4, "pnlBajaPartido");
        pnlFormularios.add(o5, "pnlInformeCargos");
        pnlFormularios.add(o6, "pnlInformeDivision");
        pnlFormularios.add(o7, "pnlInformePartidos");

        // Muestra el panel inicial
        mostrarPanel("pnlInicio", "Inicio", null);
        SwingUtilities.invokeLater(() -> btnCargarPartido.requestFocusInWindow());
    }

    /**
     * Solicita confirmacion y elimina todos los datos
     * almacenados en el sistema.
     */
    private void resetearDatos() {

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Esta seguro de que desea eliminar "
            + "todos los datos del sistema?\n"
            + "Esta accion no se puede deshacer.",
            "Confirmar reseteo",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        // Verifica confirmacion del usuario
        if (confirm == JOptionPane.YES_OPTION) {

            // Elimina todos los datos
            SistemaDAO.eliminarDatos();

            JOptionPane.showMessageDialog(
                this,
                "Todos los datos fueron eliminados correctamente."
            );

            // Regresa al panel inicial
            mostrarPanel("pnlInicio", "Inicio", null);
        }
    }

    /**
     * Configura textos, iconos y estilos
     * de los botones del menu lateral.
     */
    private void configurarBotonesMenu() {

        btnCargarPartido.setText("Cargar");
        btnCargarPartido.setIconos(
            new ImageIcon(
                getClass().getResource(
                    "/recursos/cargarPartidoNotHover.png"
                )
            ),
            new ImageIcon(
                getClass().getResource(
                    "/recursos/cargarPartidoHover.png"
                )
            ),
            null
        );

        btnModificarPartido.setText("Modificar");
        btnModificarPartido.setIconos(
            new ImageIcon(
                getClass().getResource(
                    "/recursos/modificarPartidoNotHover.png"
                )
            ),
            new ImageIcon(
                getClass().getResource(
                    "/recursos/modificarPartidoHover.png"
                )
            ),
            null
        );

        btnBajaPartido.setText("Dar de baja");
        btnBajaPartido.setIconos(
            new ImageIcon(
                getClass().getResource(
                    "/recursos/bajaPartidoNotHover.png"
                )
            ),
            new ImageIcon(
                getClass().getResource(
                    "/recursos/bajaPartidoHover.png"
                )
            ),
            null
        );

        btnInfCargos.setText("Reparticion de cargos");
        btnInfCargos.setIconos(
            new ImageIcon(
                getClass().getResource(
                    "/recursos/cargosNotHover.png"
                )
            ),
            new ImageIcon(
                getClass().getResource(
                    "/recursos/cargosHover.png"
                )
            ),
            null
        );

        btnInfDivision.setText("Division de votos");
        btnInfDivision.setIconos(
            new ImageIcon(
                getClass().getResource(
                    "/recursos/divisionNotHover.png"
                )
            ),
            new ImageIcon(
                getClass().getResource(
                    "/recursos/divisionHover.png"
                )
            ),
            null
        );

        btnInfPartidos.setText("Partidos politicos");
        btnInfPartidos.setIconos(
            new ImageIcon(
                getClass().getResource(
                    "/recursos/partidosNotHover.png"
                )
            ),
            new ImageIcon(
                getClass().getResource(
                    "/recursos/partidosHover.png"
                )
            ),
            null
        );

        // Configuracion de teclado
        Estilo.configurarTeclado(btnCargarPartido);
        Estilo.configurarTeclado(btnModificarPartido);
        Estilo.configurarTeclado(btnBajaPartido);
        Estilo.configurarTeclado(btnInfCargos);
        Estilo.configurarTeclado(btnInfDivision);
        Estilo.configurarTeclado(btnInfPartidos);
    }

    /**
     * Configura la navegacion entre paneles.
     */
    private void configurarNavegacion() {

        btnCargarPartido.addActionListener(e ->
            mostrarPanel(
                "pnlCargarPartido",
                "Cargar Partido",
                btnCargarPartido
            )
        );

        btnModificarPartido.addActionListener(e ->
            mostrarPanel(
                "pnlModificarPartido",
                "Modificar Datos de un Partido",
                btnModificarPartido
            )
        );

        btnBajaPartido.addActionListener(e ->
            mostrarPanel(
                "pnlBajaPartido",
                "Dar de baja un Partido",
                btnBajaPartido
            )
        );

        btnInfCargos.addActionListener(e ->
            mostrarPanel(
                "pnlInformeCargos",
                "Informe de Reparticion de Cargos",
                btnInfCargos
            )
        );

        btnInfDivision.addActionListener(e ->
            mostrarPanel(
                "pnlInformeDivision",
                "Informe de Division de Votos",
                btnInfDivision
            )
        );

        btnInfPartidos.addActionListener(e ->
            mostrarPanel(
                "pnlInformePartidos",
                "Informe de Partidos",
                btnInfPartidos
            )
        );
    }

    /**
     * Muestra un panel especifico del CardLayout.
     *
     * @param nombre Nombre del panel
     * @param titulo Titulo mostrado en pantalla
     * @param botonActivo Boton seleccionado actualmente
     */
    private void mostrarPanel(
        String nombre,
        String titulo,
        BotonMenu botonActivo
    ) {

        // Obtiene el CardLayout
        CardLayout card =
            (CardLayout) pnlFormularios.getLayout();

        // Muestra el panel seleccionado
        card.show(pnlFormularios, nombre);

        // Actualiza el titulo
        lblTituloForm.setText(titulo);

        // Desactiva todos los botones
        desactivarBotones();

        // Activa el boton seleccionado
        if (botonActivo != null) {
            botonActivo.setActive(true);
        }

        // Guarda el panel actual
        panelActual = nombre;
    }

    /**
     * Desactiva el estado activo de todos los botones.
     */
    private void desactivarBotones() {

        btnCargarPartido.setActive(false);
        btnModificarPartido.setActive(false);
        btnBajaPartido.setActive(false);
        btnInfCargos.setActive(false);
        btnInfDivision.setActive(false);
        btnInfPartidos.setActive(false);
    }

    /**
     * Configura la tecla ESC.
     * Si esta en inicio, cierra el programa.
     * Si no, vuelve al panel principal.
     */
    private void cerrarPrograma() {

        getRootPane().registerKeyboardAction(
            e -> {

                if ("pnlInicio".equals(panelActual)) {

                    // Cierra la ventana
                    dispose();

                } else {

                    // Regresa al panel inicio
                    mostrarPanel(
                        "pnlInicio",
                        "Inicio",
                        null
                    );
                }

            },
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
            JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    /*
     * Getters
     */

    public vista.componentes.BotonMenu getBtnInfDivision() {
        return btnInfDivision;
    }

    public vista.componentes.BotonMenu getBtnInfPartidos() {
        return btnInfPartidos;
    }

        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new Degradado();
        pnlTop = new javax.swing.JPanel();
        lblTituloMenu = new javax.swing.JLabel();
        EspTituloMenu = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5));
        sepMenu = new javax.swing.JSeparator();
        pnlBotones = new javax.swing.JPanel();
        lblPartidos = new javax.swing.JLabel();
        btnCargarPartido = new vista.componentes.BotonMenu();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15));
        btnModificarPartido = new vista.componentes.BotonMenu();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15));
        btnBajaPartido = new vista.componentes.BotonMenu();
        lblInformes = new javax.swing.JLabel();
        btnInfCargos = new vista.componentes.BotonMenu();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15));
        btnInfDivision = new vista.componentes.BotonMenu();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 15));
        btnInfPartidos = new vista.componentes.BotonMenu();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15));
        btnReset = new javax.swing.JButton();
        pnlContenido = new javax.swing.JPanel();
        pnlTopContenido = new javax.swing.JPanel();
        lblTituloForm = new javax.swing.JLabel();
        EspTituloCont = new javax.swing.Box.Filler(new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18), new java.awt.Dimension(0, 18));
        sepTitulo = new javax.swing.JSeparator();
        pnlFormularios = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 700));

        pnlMenu.setMaximumSize(new java.awt.Dimension(220, 0));
        pnlMenu.setMinimumSize(new java.awt.Dimension(220, 0));
        pnlMenu.setOpaque(false);
        pnlMenu.setPreferredSize(new java.awt.Dimension(220, 0));
        pnlMenu.setLayout(new java.awt.BorderLayout());

        pnlTop.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 20, 20, 20));
        pnlTop.setMinimumSize(new java.awt.Dimension(179, 0));
        pnlTop.setOpaque(false);
        pnlTop.setPreferredSize(new java.awt.Dimension(0, 115));
        pnlTop.setLayout(new javax.swing.BoxLayout(pnlTop, javax.swing.BoxLayout.Y_AXIS));

        lblTituloMenu.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloMenu.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTituloMenu.setForeground(new java.awt.Color(228, 232, 242));
        lblTituloMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTituloMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logo.png"))); // NOI18N
        lblTituloMenu.setText("<html>Sistema<br>D'Hondt</html>");
        lblTituloMenu.setIconTextGap(10);
        lblTituloMenu.setInheritsPopupMenu(false);
        pnlTop.add(lblTituloMenu);
        pnlTop.add(EspTituloMenu);

        sepMenu.setBackground(new java.awt.Color(154, 171, 198));
        sepMenu.setForeground(new java.awt.Color(136, 158, 191));
        sepMenu.setMinimumSize(new java.awt.Dimension(50, 0));
        sepMenu.setName(""); // NOI18N
        sepMenu.setPreferredSize(new java.awt.Dimension(180, 2));
        pnlTop.add(sepMenu);

        pnlMenu.add(pnlTop, java.awt.BorderLayout.NORTH);

        pnlBotones.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 30, 20));
        pnlBotones.setOpaque(false);
        pnlBotones.setLayout(new javax.swing.BoxLayout(pnlBotones, javax.swing.BoxLayout.Y_AXIS));

        lblPartidos.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPartidos.setForeground(new java.awt.Color(255, 255, 255));
        lblPartidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPartidos.setText("Partidos");
        lblPartidos.setToolTipText("");
        lblPartidos.setAlignmentX(0.5F);
        lblPartidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        lblPartidos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblPartidos.setInheritsPopupMenu(false);
        pnlBotones.add(lblPartidos);

        btnCargarPartido.setText("Cargar");
        pnlBotones.add(btnCargarPartido);
        pnlBotones.add(filler1);

        btnModificarPartido.setText("Modificar");
        pnlBotones.add(btnModificarPartido);
        pnlBotones.add(filler2);

        btnBajaPartido.setText("Dar de baja");
        pnlBotones.add(btnBajaPartido);

        lblInformes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblInformes.setForeground(new java.awt.Color(255, 255, 255));
        lblInformes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInformes.setText("Informes");
        lblInformes.setToolTipText("");
        lblInformes.setAlignmentX(0.5F);
        lblInformes.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 0, 15, 0));
        lblInformes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblInformes.setInheritsPopupMenu(false);
        pnlBotones.add(lblInformes);

        btnInfCargos.setText("Repartición de cargos");
        pnlBotones.add(btnInfCargos);
        pnlBotones.add(filler3);

        btnInfDivision.setText("División de votos");
        pnlBotones.add(btnInfDivision);
        pnlBotones.add(filler4);

        btnInfPartidos.setText("Partidos políticos");
        pnlBotones.add(btnInfPartidos);
        pnlBotones.add(filler5);

        btnReset.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/resetNotHover.png"))); // NOI18N
        btnReset.setText("Resetear datos");
        btnReset.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 1, 1));
        btnReset.setBorderPainted(false);
        btnReset.setContentAreaFilled(false);
        btnReset.setFocusPainted(false);
        btnReset.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReset.setIconTextGap(10);
        btnReset.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnReset.setMaximumSize(new java.awt.Dimension(1000, 40));
        btnReset.setMinimumSize(new java.awt.Dimension(200, 40));
        btnReset.setPreferredSize(new java.awt.Dimension(200, 40));
        pnlBotones.add(btnReset);

        pnlMenu.add(pnlBotones, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlMenu, java.awt.BorderLayout.WEST);

        pnlContenido.setBackground(new java.awt.Color(241, 240, 245));
        pnlContenido.setDoubleBuffered(false);
        pnlContenido.setLayout(new java.awt.BorderLayout());

        pnlTopContenido.setBackground(new java.awt.Color(241, 240, 245));
        pnlTopContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 50, 0, 50));
        pnlTopContenido.setPreferredSize(new java.awt.Dimension(0, 115));
        pnlTopContenido.setLayout(new javax.swing.BoxLayout(pnlTopContenido, javax.swing.BoxLayout.Y_AXIS));

        lblTituloForm.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTituloForm.setForeground(new java.awt.Color(61, 75, 102));
        lblTituloForm.setText("Cargar datos de partidos");
        lblTituloForm.setInheritsPopupMenu(false);
        pnlTopContenido.add(lblTituloForm);
        pnlTopContenido.add(EspTituloCont);

        sepTitulo.setForeground(new java.awt.Color(233, 233, 240));
        sepTitulo.setPreferredSize(new java.awt.Dimension(180, 2));
        pnlTopContenido.add(sepTitulo);

        pnlContenido.add(pnlTopContenido, java.awt.BorderLayout.NORTH);

        pnlFormularios.setBackground(new java.awt.Color(241, 240, 245));
        pnlFormularios.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 50, 50));
        pnlFormularios.setToolTipText("");
        pnlFormularios.setLayout(new java.awt.CardLayout());
        pnlContenido.add(pnlFormularios, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlContenido, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler EspTituloCont;
    private javax.swing.Box.Filler EspTituloMenu;
    private vista.componentes.BotonMenu btnBajaPartido;
    private vista.componentes.BotonMenu btnCargarPartido;
    private vista.componentes.BotonMenu btnInfCargos;
    private vista.componentes.BotonMenu btnInfDivision;
    private vista.componentes.BotonMenu btnInfPartidos;
    private vista.componentes.BotonMenu btnModificarPartido;
    private javax.swing.JButton btnReset;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JLabel lblInformes;
    private javax.swing.JLabel lblPartidos;
    private javax.swing.JLabel lblTituloForm;
    private javax.swing.JLabel lblTituloMenu;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlFormularios;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlTopContenido;
    private javax.swing.JSeparator sepMenu;
    private javax.swing.JSeparator sepTitulo;
    // End of variables declaration//GEN-END:variables
}
