package vista.paneles;

import vista.componentes.Estilo;

/**
 * Panel encargado de mostrar la interfaz
 * para eliminar partidos del sistema.
 */
public class BajaPartido extends javax.swing.JPanel {

    /**
     * Constructor del panel.
     * Inicializa componentes y aplica estilos.
     */
    public BajaPartido() {

        initComponents();

        // Aplicacion de estilos visuales
        Estilo.aplicarSubtitulo(lblElimine);
        Estilo.aplicarTexto(lblSeleccione);
        Estilo.aplicarSeparadorTitulo(sepTituloForm);
        Estilo.aplicarComboBox(cmbPartidos);
        Estilo.aplicarBoton(btnDarBaja);
        Estilo.aplicarCard(this);

        /*
         * Listener que coloca el foco automaticamente
         * en el combo al mostrar el panel.
         */
        this.addAncestorListener(
            new javax.swing.event.AncestorListener() {

                @Override
                public void ancestorAdded(
                    javax.swing.event.AncestorEvent e
                ) {

                    cmbPartidos.requestFocusInWindow();
                }

                @Override
                public void ancestorRemoved(
                    javax.swing.event.AncestorEvent e
                ) {
                }

                @Override
                public void ancestorMoved(
                    javax.swing.event.AncestorEvent e
                ) {
                }
            }
        );

        // Permite controlar la navegacion por foco
        setFocusCycleRoot(true);
    }

    /**
     * Detecta cuando el panel se vuelve visible.
     *
     * @param aFlag Estado de visibilidad
     */
    @Override
    public void setVisible(boolean aFlag) {

        super.setVisible(aFlag);

        // Ejecuta evento personalizado al mostrarse
        if (aFlag) {
            notifyVisible();
        }
    }

    // Evento ejecutado cuando el panel se muestra
    private Runnable onVisible;

    /**
     * Define la accion que se ejecutara
     * al mostrarse el panel.
     *
     * @param onVisible Evento personalizado
     */
    public void setOnVisible(Runnable onVisible) {
        this.onVisible = onVisible;
    }

    /**
     * Ejecuta el evento configurado
     * cuando el panel se vuelve visible.
     */
    private void notifyVisible() {

        if (onVisible != null) {
            onVisible.run();
        }
    }

    /*
     * Getters
     */

    /**
     * Devuelve el JComboBox de partidos.
     *
     * @return ComboBox de partidos
     */
    public javax.swing.JComboBox<String> getCmbPartidos() {
        return cmbPartidos;
    }

    /**
     * Devuelve el boton de baja.
     *
     * @return Boton para eliminar partido
     */
    public javax.swing.JButton getBtnDarBaja() {
        return btnDarBaja;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbPartidos = new javax.swing.JComboBox<>();
        lblSeleccione = new javax.swing.JLabel();
        btnDarBaja = new javax.swing.JButton();
        lblElimine = new javax.swing.JLabel();
        sepTituloForm = new javax.swing.JSeparator();

        setPreferredSize(new java.awt.Dimension(500, 500));

        lblSeleccione.setText("Seleccione partido a dar de baja");
        lblSeleccione.setInheritsPopupMenu(false);

        btnDarBaja.setText("Dar de baja");

        lblElimine.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblElimine.setText("Elimine un partido del sistema");
        lblElimine.setInheritsPopupMenu(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepTituloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSeleccione)
                    .addComponent(lblElimine)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbPartidos, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnDarBaja)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblElimine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepTituloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSeleccione)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPartidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDarBaja))
                .addContainerGap(353, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDarBaja;
    private javax.swing.JComboBox<String> cmbPartidos;
    private javax.swing.JLabel lblElimine;
    private javax.swing.JLabel lblSeleccione;
    private javax.swing.JSeparator sepTituloForm;
    // End of variables declaration//GEN-END:variables
}
