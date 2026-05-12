package vista.paneles;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import vista.componentes.Estilo;

/**
 * Panel que permite configurar y generar el informe de reparticion de cargos.
 * Muestra un formulario para ingresar la cantidad de cargos y el porcentaje
 * minimo, y al confirmar oculta el formulario y muestra el informe embebido.
 */
public class InformeCargos extends javax.swing.JPanel {

    private JPanel pnlViewer;
    private javax.swing.JButton btnVolver;

    // Callback que avisa al controlador cuando se restaura el formulario
    private Runnable onFormularioRestaurado;

    /**
     * Inicializa el panel aplicando estilos a los componentes.
     * Agrega un listener para redimensionar el viewer si la ventana cambia de tamaño.
     */
    public InformeCargos() {
        initComponents();
        Estilo.aplicarSubtitulo(lblInfCargos);
        Estilo.aplicarTexto(lblCantCargos);
        Estilo.aplicarTexto(lblPorcentaje);
        Estilo.aplicarSeparadorTitulo(sepTituloForm);
        Estilo.aplicarTextField(txtCantCargos);
        Estilo.aplicarComboBox(cmbPorcentaje);
        Estilo.aplicarBoton(btnAceptar);
        Estilo.aplicarCard(this);

        this.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent e) {
                txtCantCargos.requestFocusInWindow();
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent e) {}
            public void ancestorMoved(javax.swing.event.AncestorEvent e) {}
        });

        setFocusCycleRoot(true);

        // Redimensiona el viewer si el panel cambia de tamaño
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (pnlViewer != null) {
                    pnlViewer.setBounds(0, 50, getWidth(), getHeight() - 50);
                }
            }
        });
    }

    /**
     * Registra el callback que se ejecuta cuando el formulario es restaurado.
     * El controlador lo usa para reconectarse a los nuevos componentes.
     * @param callback accion a ejecutar al restaurar el formulario
     */
    public void setOnFormularioRestaurado(Runnable callback) {
        this.onFormularioRestaurado = callback;
    }

    /**
     * Oculta todos los componentes del formulario original.
     */
    private void ocultarFormulario() {
        for (Component c : getComponents()) {
            c.setVisible(false);
        }
    }

    /**
     * Oculta el formulario y muestra el informe JasperReports embebido.
     * Agrega un boton "Volver" en la parte superior para poder regresar al formulario.
     * @param print resultado del llenado del informe listo para visualizar
     */
    public void mostrarInforme(JasperPrint print) {
        ocultarFormulario();

        if (pnlViewer != null) {
            remove(pnlViewer);
        }
        if (btnVolver != null) {
            remove(btnVolver);
        }

        // Null layout para posicionar manualmente
        setLayout(null);

        // Boton volver sobre el viewer
        btnVolver = new javax.swing.JButton("← Volver");
        Estilo.aplicarBoton(btnVolver);
        btnVolver.setBounds(10, 8, 120, 35);
        btnVolver.addActionListener(e -> mostrarFormulario());

        pnlViewer = new JPanel(new BorderLayout());
        pnlViewer.add(new JRViewer(print), BorderLayout.CENTER);
        pnlViewer.setBounds(0, 50, getWidth(), getHeight() - 50);

        add(btnVolver);
        add(pnlViewer);
        revalidate();
        repaint();
    }

    /**
     * Remueve el viewer y el boton volver, recrea el formulario original
     * con todos sus estilos y notifica al controlador para que reconecte los listeners.
     */
    public void mostrarFormulario() {
        // Limpia todo el panel
        removeAll();

        // Resetea el layout al GroupLayout original recreando los componentes
        initComponents();

        // Vuelve a aplicar estilos porque initComponents() recrea los componentes
        Estilo.aplicarSubtitulo(lblInfCargos);
        Estilo.aplicarTexto(lblCantCargos);
        Estilo.aplicarTexto(lblPorcentaje);
        Estilo.aplicarSeparadorTitulo(sepTituloForm);
        Estilo.aplicarTextField(txtCantCargos);
        Estilo.aplicarComboBox(cmbPorcentaje);
        Estilo.aplicarBoton(btnAceptar);
        Estilo.aplicarCard(this);

        // Rehabilita el boton aceptar por si quedo deshabilitado
        btnAceptar.setEnabled(true);

        // Limpia las referencias anteriores
        pnlViewer = null;
        btnVolver = null;

        revalidate();
        repaint();

        // Notifica al controlador para que reconecte sus listeners al nuevo btnAceptar
        if (onFormularioRestaurado != null) {
            onFormularioRestaurado.run();
        }

        // Devuelve el foco al campo de cargos
        txtCantCargos.requestFocusInWindow();
    }

    // Getters
    public javax.swing.JButton getBtnAceptar() { return btnAceptar; }
    public javax.swing.JTextField getTxtCantCargos() { return txtCantCargos; }
    public javax.swing.JComboBox<String> getCmbPorcentaje() { return cmbPorcentaje; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbPorcentaje = new javax.swing.JComboBox<>();
        txtCantCargos = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        lblInfCargos = new javax.swing.JLabel();
        lblCantCargos = new javax.swing.JLabel();
        lblPorcentaje = new javax.swing.JLabel();
        sepTituloForm = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(300, 300));

        txtCantCargos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnAceptar.setText("Aceptar");

        lblInfCargos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblInfCargos.setText("Distribución de cargos a cubrir");
        lblInfCargos.setInheritsPopupMenu(false);

        lblCantCargos.setText("Cantidad de cargos");
        lblCantCargos.setInheritsPopupMenu(false);

        lblPorcentaje.setText("Porcentaje");
        lblPorcentaje.setInheritsPopupMenu(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepTituloForm)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantCargos)
                            .addComponent(txtCantCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblPorcentaje))
                            .addComponent(cmbPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 295, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfCargos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepTituloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCantCargos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPorcentaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addComponent(btnAceptar)
                .addContainerGap(156, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox<String> cmbPorcentaje;
    private javax.swing.JLabel lblCantCargos;
    private javax.swing.JLabel lblInfCargos;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JSeparator sepTituloForm;
    private javax.swing.JTextField txtCantCargos;
    // End of variables declaration//GEN-END:variables
}
