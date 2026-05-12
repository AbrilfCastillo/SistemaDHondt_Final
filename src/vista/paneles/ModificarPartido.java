package vista.paneles;

import java.awt.Dimension;
import modelo.Partido;
import javax.swing.JOptionPane;
import vista.componentes.Estilo;

public class ModificarPartido extends javax.swing.JPanel {

    // Callback para cuando el panel se hace visible
    private Runnable onVisible;

    public ModificarPartido() {
        initComponents();
        aplicarEstilos();
        configurarFoco();

        btnCargar.setPreferredSize(new Dimension(100, 30));
        btnCargar.setMaximumSize(new Dimension(100, 30));
        btnCargar.setMinimumSize(new Dimension(100, 30));

        btnBorrarFiltro.setPreferredSize(new Dimension(80, 30));
        btnBorrarFiltro.setMaximumSize(new Dimension(80, 30));
        btnBorrarFiltro.setMinimumSize(new Dimension(80, 30));
    }

    private void aplicarEstilos() {
        Estilo.aplicarSubtitulo(lblModifique);
        Estilo.aplicarTexto(lblNombrePartido);
        Estilo.aplicarTexto(lblDatos);
        Estilo.aplicarTexto(lblFiltro);
        Estilo.aplicarTexto(lblCantVotos);
        Estilo.aplicarSeparadorTitulo(sepTituloForm);
        Estilo.aplicarTextField(txtNombrePartido);
        Estilo.aplicarTextField(ftxtCantVotos);
        Estilo.aplicarTextField(txtFiltro);
        Estilo.aplicarBotonNav(btnAnterior);
        Estilo.aplicarBotonNav(btnPosterior);
        Estilo.aplicarBotonNav(btnFin);
        Estilo.aplicarBotonNav(btnPrincipio);
        Estilo.aplicarBoton(btnCargar);
        Estilo.aplicarBoton(btnBorrarFiltro);
        Estilo.aplicarCard(this);
    }

    private void configurarFoco() {
        this.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent e) {
                txtFiltro.requestFocusInWindow();
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent e) {}
            public void ancestorMoved(javax.swing.event.AncestorEvent e) {}
        });
    }

    /**
     * Muestra los datos de un partido en los campos del formulario
     * y actualiza el indicador de posicion.
     * @param p partido a mostrar
     * @param actual posicion actual (base 1)
     * @param total total de partidos en la lista
     */
    public void mostrarPartido(Partido p, int actual, int total) {
        txtNombrePartido.setText(p.getNombre());
        ftxtCantVotos.setValue((long) p.getVotos());
        lblGuia.setText(actual + " / " + total);
    }

    /**
     * Vacia los campos y resetea el indicador de posicion.
     */
    public void limpiarCampos() {
        txtNombrePartido.setText("");
        ftxtCantVotos.setText("");
        lblGuia.setText("0 / 0");
    }

    /**
     * Fuerza el commit del JFormattedTextField y retorna el valor ingresado.
     * @return votos ingresados, 0 si el campo esta vacio
     */
    public long getVotos() {
        try {
            ftxtCantVotos.commitEdit();
        } catch (java.text.ParseException e) {
            return 0;
        }
        return ftxtCantVotos.getValue() != null ? (Long) ftxtCantVotos.getValue() : 0;
    }

    /**
     * Retorna el nombre ingresado en el campo de texto.
     */
    public String getNombreIngresado() {
        return txtNombrePartido.getText();
    }

    /**
     * Retorna el texto actual del campo de filtro.
     */
    public String getTextoFiltro() {
        return txtFiltro.getText();
    }

    /**
     * Registra el callback que se ejecuta cada vez que el panel se hace visible.
     * El controlador lo usa para recargar los datos actualizados.
     */
    public void setOnVisible(Runnable onVisible) {
        this.onVisible = onVisible;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag && onVisible != null) {
            onVisible.run();
        }
    }

    // =========================================================
    // Getters para que el controlador conecte sus listeners
    // =========================================================
    public javax.swing.JTextField getTxtFiltro() { return txtFiltro; }
    public javax.swing.JButton getBtnBorrarFiltro() { return btnBorrarFiltro; }
    public javax.swing.JTextField getTxtNombrePartido() { return txtNombrePartido; }
    public javax.swing.JFormattedTextField getFtxtCantVotos() { return ftxtCantVotos; }
    public javax.swing.JButton getBtnPrincipio() { return btnPrincipio; }
    public javax.swing.JButton getBtnAnterior() { return btnAnterior; }
    public javax.swing.JButton getBtnPosterior() { return btnPosterior; }
    public javax.swing.JButton getBtnFin() { return btnFin; }
    public javax.swing.JButton getBtnCargar() { return btnCargar; }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombrePartido = new javax.swing.JLabel();
        sepTituloForm = new javax.swing.JSeparator();
        lblCantVotos = new javax.swing.JLabel();
        txtNombrePartido = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnPosterior = new javax.swing.JButton();
        btnFin = new javax.swing.JButton();
        btnPrincipio = new javax.swing.JButton();
        lblGuia = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        ftxtCantVotos = new javax.swing.JFormattedTextField();
        btnBorrarFiltro = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        lblModifique = new javax.swing.JLabel();
        btnCargar = new javax.swing.JButton();
        lblDatos = new javax.swing.JLabel();

        lblNombrePartido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombrePartido.setText("Nombre");
        lblNombrePartido.setInheritsPopupMenu(false);
        lblNombrePartido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombrePartidoMouseClicked(evt);
            }
        });

        lblCantVotos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantVotos.setText("Cantidad de votos");
        lblCantVotos.setInheritsPopupMenu(false);
        lblCantVotos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCantVotosMouseClicked(evt);
            }
        });

        txtNombrePartido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombrePartidoKeyPressed(evt);
            }
        });

        btnAnterior.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/anterior.png"))); // NOI18N
        btnAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnterior.setIconTextGap(0);
        btnAnterior.setMaximumSize(new java.awt.Dimension(33, 33));
        btnAnterior.setMinimumSize(new java.awt.Dimension(33, 33));
        btnAnterior.setOpaque(false);
        btnAnterior.setPreferredSize(new java.awt.Dimension(33, 33));
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPosterior.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPosterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/posterior.png"))); // NOI18N
        btnPosterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPosterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPosterior.setIconTextGap(0);
        btnPosterior.setMaximumSize(new java.awt.Dimension(33, 33));
        btnPosterior.setMinimumSize(new java.awt.Dimension(33, 33));
        btnPosterior.setOpaque(false);
        btnPosterior.setPreferredSize(new java.awt.Dimension(33, 33));
        btnPosterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPosteriorActionPerformed(evt);
            }
        });

        btnFin.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/fin.png"))); // NOI18N
        btnFin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFin.setIconTextGap(0);
        btnFin.setMaximumSize(new java.awt.Dimension(33, 33));
        btnFin.setMinimumSize(new java.awt.Dimension(33, 33));
        btnFin.setOpaque(false);
        btnFin.setPreferredSize(new java.awt.Dimension(33, 33));
        btnFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinActionPerformed(evt);
            }
        });

        btnPrincipio.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnPrincipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/principio.png"))); // NOI18N
        btnPrincipio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrincipio.setIconTextGap(0);
        btnPrincipio.setMaximumSize(new java.awt.Dimension(33, 33));
        btnPrincipio.setMinimumSize(new java.awt.Dimension(33, 33));
        btnPrincipio.setOpaque(false);
        btnPrincipio.setPreferredSize(new java.awt.Dimension(33, 33));
        btnPrincipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipioActionPerformed(evt);
            }
        });

        lblGuia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGuia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGuia.setText("0/0");
        lblGuia.setInheritsPopupMenu(false);

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        ftxtCantVotos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        ftxtCantVotos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ftxtCantVotos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ftxtCantVotosKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ftxtCantVotosKeyTyped(evt);
            }
        });

        btnBorrarFiltro.setText("Borrar");
        btnBorrarFiltro.setBorderPainted(false);
        btnBorrarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarFiltroActionPerformed(evt);
            }
        });

        lblFiltro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFiltro.setText("Filtrar por nombre");
        lblFiltro.setInheritsPopupMenu(false);
        lblFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFiltroMouseClicked(evt);
            }
        });

        lblModifique.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblModifique.setText("Modifique los datos del partido seleccionado");
        lblModifique.setInheritsPopupMenu(false);

        btnCargar.setText("Cargar");

        lblDatos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDatos.setText("Datos del partido");
        lblDatos.setInheritsPopupMenu(false);
        lblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDatosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sepTituloForm)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDatos)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCantVotos)
                                        .addGap(18, 18, 18)
                                        .addComponent(ftxtCantVotos, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblModifique)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFiltro)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblNombrePartido)
                                                .addGap(80, 80, 80)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBorrarFiltro)))))
                                .addGap(0, 769, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnPrincipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPosterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(btnCargar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModifique)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepTituloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFiltro)
                    .addComponent(btnBorrarFiltro))
                .addGap(34, 34, 34)
                .addComponent(lblDatos)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombrePartido)
                    .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantVotos)
                    .addComponent(ftxtCantVotos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrincipio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPosterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGuia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(btnCargar)
                .addContainerGap(222, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

    }//GEN-LAST:event_btnAnteriorActionPerformed
    

    private void btnPrincipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipioActionPerformed

    }//GEN-LAST:event_btnPrincipioActionPerformed

    private void btnPosteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPosteriorActionPerformed

    }//GEN-LAST:event_btnPosteriorActionPerformed

    private void btnFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinActionPerformed

    }//GEN-LAST:event_btnFinActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased

    }//GEN-LAST:event_txtFiltroKeyReleased

    private void ftxtCantVotosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftxtCantVotosKeyPressed

    }//GEN-LAST:event_ftxtCantVotosKeyPressed

    private void txtNombrePartidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePartidoKeyPressed

    }//GEN-LAST:event_txtNombrePartidoKeyPressed

    private void ftxtCantVotosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftxtCantVotosKeyTyped

    }//GEN-LAST:event_ftxtCantVotosKeyTyped

    private void btnBorrarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarFiltroActionPerformed

    }//GEN-LAST:event_btnBorrarFiltroActionPerformed

    private void lblNombrePartidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombrePartidoMouseClicked

    }//GEN-LAST:event_lblNombrePartidoMouseClicked

    private void lblCantVotosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCantVotosMouseClicked

    }//GEN-LAST:event_lblCantVotosMouseClicked

    private void lblFiltroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFiltroMouseClicked

    }//GEN-LAST:event_lblFiltroMouseClicked

    private void lblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDatosMouseClicked

    }//GEN-LAST:event_lblDatosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBorrarFiltro;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnFin;
    private javax.swing.JButton btnPosterior;
    private javax.swing.JButton btnPrincipio;
    private javax.swing.JFormattedTextField ftxtCantVotos;
    private javax.swing.JLabel lblCantVotos;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblGuia;
    private javax.swing.JLabel lblModifique;
    private javax.swing.JLabel lblNombrePartido;
    private javax.swing.JSeparator sepTituloForm;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtNombrePartido;
    // End of variables declaration//GEN-END:variables
}
