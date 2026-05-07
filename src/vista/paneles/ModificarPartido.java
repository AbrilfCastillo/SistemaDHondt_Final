package vista.paneles;

import modelo.Partido;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import servicios.PartidoService;
import vista.componentes.Estilo;

public class ModificarPartido extends javax.swing.JPanel {

    private List<Partido> lista, partidos, partidosFiltrados;
    private int indice = 0;
    
    public ModificarPartido() {
        initComponents();
        cargarDatosIniciales();
        Estilo.aplicarSubtitulo(lblModifique);
        Estilo.aplicarTexto(lblNombrePartido);
        Estilo.aplicarTexto(lblDatos);
        Estilo.aplicarTexto(lblFiltro);
        Estilo.aplicarTexto(lblCantVotos);
        Estilo.aplicarSeparadorTitulo(sepTituloForm);
        Estilo.aplicarTextField(txtNombrePartido);
        Estilo.aplicarTextField(ftxtCantVotos);
        Estilo.aplicarTextField(txtFiltro);
        Estilo.aplicarBoton(btnAnterior);
        Estilo.aplicarBoton(btnPosterior);
        Estilo.aplicarBoton(btnFin);
        Estilo.aplicarBoton(btnPrincipio);
        Estilo.aplicarBoton(btnCargar);
        Estilo.aplicarCard(this);
    }
    
    /**
    * Sobrescribe setVisible para recargar los datos cada vez que el panel
    * se hace visible con tal de que siempre muestre informaciin actualizada.
    */
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            cargarDatosIniciales();
        }
    }
    
    /**
    * Inicializa el panel obteniendo la lista de partidos desde la base de datos.
    * Si hay partidos, muestra el primero; si no, limpia los campos y avisa al usuario.
    */
    private void cargarDatosIniciales() {
        partidos = PartidoService.obtenerPartidos(); // consulta a la base de datos
        lista = partidos; // lista activa apunta a todos los partidos por defecto
        indice = 0; // siempre empieza desde el primer elemento

        if (!partidos.isEmpty()) {
            mostrarActual();
        } else {
            limpiarCampos();
        }
    }
    
    
    /**
    * Muestra en los campos de texto el partido en la posiciin actual del indice.
    * Si la lista esti vacia, limpia los campos.
    */
    private void mostrarActual() {
        if (lista.isEmpty()) {
            limpiarCampos();
            return;
        }

        Partido p = lista.get(indice);
        
        this.txtNombrePartido.setText(p.getNombre());
        ftxtCantVotos.setValue((long) p.getVotos()); // setValue respeta el formato numirico

        lblGuia.setText((indice + 1) + " / " + lista.size()); // ej: "2 / 5"
    }
    
    /**
    * Vacia los campos de texto y restablece el indicador de posiciin a "0 / 0".
    */
    private void limpiarCampos(){
        txtNombrePartido.setText("");
        ftxtCantVotos.setText("");
        lblGuia.setText("0 / 0");
    }
    
    
    /**
    * Guarda los cambios del partido actualmente mostrado en la base de datos.
    * Si se esti usando un filtro, lo reaaplica tras la actualizaciin para mantener
    * la vista filtrada. Si no hay filtro activo, recarga la lista completa.
    */
    public void actualizarPartidoActual() {
        if (lista.isEmpty()) return;

        try {
            Partido p = lista.get(indice);
            String nuevoNombre = txtNombrePartido.getText();
            
            ftxtCantVotos.commitEdit(); // fuerza el commit del valor antes de leerlo
            
            // Si el valor es null (campo vacio), usa 0 como valor por defecto
            long nuevosVotos = ftxtCantVotos.getValue() != null ? (Long) ftxtCantVotos.getValue() : 0;
            
            PartidoService.actualizarPartido(p.getId(), nuevoNombre, nuevosVotos);
            
            partidos = PartidoService.obtenerPartidos(); // recarga la lista actualizada
            
            if(lista == partidosFiltrados && !txtFiltro.getText().isEmpty()){
                // Si hay filtro activo, se actualiza la lista con el filtro
                partidosFiltrados = PartidoService.filtrar(partidos, txtFiltro.getText());
                lista = partidosFiltrados;
                indice = 0;
            } else {
                lista =  partidos;
            }
            
            mostrarActual();
            
            JOptionPane.showMessageDialog(null, "Actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Dispara la actualizaciin del partido cuando el usuario presiona Enter
    * en cualquiera de los campos de texto.
    */
    private void actualizarRegistro(java.awt.event.KeyEvent evt){
        try {
            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                actualizarPartidoActual();
            }
        } catch (HeadlessException e){
            System.out.println(e);
        }
    }

    public javax.swing.JButton getBtnCargar() { return btnCargar; }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

        btnBorrarFiltro.setText("Borrar filtro");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModifique)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblDatos)
                                    .addGap(247, 247, 247))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblCantVotos)
                                            .addGap(18, 18, 18)
                                            .addComponent(ftxtCantVotos))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblNombrePartido)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                            .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(82, 82, 82)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPrincipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCargar)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPosterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 736, Short.MAX_VALUE))
                    .addComponent(sepTituloForm))
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
                    .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrincipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPosterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuia))
                .addGap(30, 30, 30)
                .addComponent(btnCargar)
                .addContainerGap(223, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
    * Retrocede al partido anterior en la lista si no esti en el primero.
    */
    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (indice > 0) {
            indice--;
        }
        mostrarActual();
    }//GEN-LAST:event_btnAnteriorActionPerformed
    
    /**
    * Vuelve al primer partido de la lista.
    */
    private void btnPrincipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipioActionPerformed
        indice = 0;
        mostrarActual();
    }//GEN-LAST:event_btnPrincipioActionPerformed
    
    /**
    * Avanza al partido siguiente en la lista si no esti en el iltimo.
    */
    private void btnPosteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPosteriorActionPerformed
        if (indice < lista.size()-1) {
            indice++;
        }
        mostrarActual();
    }//GEN-LAST:event_btnPosteriorActionPerformed
    
    /**
    * Salta directamente al iltimo partido de la lista.
    */
    private void btnFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinActionPerformed
        indice = lista.size() - 1;
        mostrarActual();
    }//GEN-LAST:event_btnFinActionPerformed
    
    /**
    * Filtra la lista de partidos en tiempo real mientras el usuario escribe.
    * Si el campo de filtro esti vacio, muestra todos los partidos.
    */
    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased

        String texto = txtFiltro.getText();
        partidosFiltrados = PartidoService.filtrar(partidos, texto);
        lista = texto.isEmpty() ? partidos : partidosFiltrados;
        
        indice = 0;
        
        mostrarActual();
    }//GEN-LAST:event_txtFiltroKeyReleased
    
    /**
    * Dispara la actualizaciin al presionar Enter en el campo de votos.
    */
    private void ftxtCantVotosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftxtCantVotosKeyPressed
        actualizarRegistro(evt);
    }//GEN-LAST:event_ftxtCantVotosKeyPressed

    /**
    * Dispara la actualizaciin al presionar Enter en el campo de nombre.
    */
    private void txtNombrePartidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePartidoKeyPressed
        actualizarRegistro(evt);
    }//GEN-LAST:event_txtNombrePartidoKeyPressed
    
    /**
    * Permite solo digitos numiricos en el campo de votos.
    */
    private void ftxtCantVotosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ftxtCantVotosKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_ftxtCantVotosKeyTyped
    
    /**
    * Limpia el campo de filtro para mostrar todos los partidos nuevamente.
    */
    private void btnBorrarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarFiltroActionPerformed
        txtFiltro.setText("");
    }//GEN-LAST:event_btnBorrarFiltroActionPerformed
    
    /**
    * Redirige el foco al campo de nombre al hacer click en su etiqueta.
    */
    private void lblNombrePartidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNombrePartidoMouseClicked
        txtNombrePartido.requestFocus();
    }//GEN-LAST:event_lblNombrePartidoMouseClicked
    
    /**
    * Redirige el foco al campo de votos al hacer click en su etiqueta.
    */
    private void lblCantVotosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCantVotosMouseClicked
        ftxtCantVotos.requestFocus();
    }//GEN-LAST:event_lblCantVotosMouseClicked

    /**
    * Redirige el foco al campo de filtro al hacer click en su etiqueta.
    */
    private void lblFiltroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFiltroMouseClicked
        txtFiltro.requestFocus();
    }//GEN-LAST:event_lblFiltroMouseClicked

    private void lblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDatosMouseClicked
        // TODO add your handling code here:
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
