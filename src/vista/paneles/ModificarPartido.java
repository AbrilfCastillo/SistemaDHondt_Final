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
        sepTituloForm = new javax.swing.JSeparator();
        btnCargar = new javax.swing.JButton();

        lblNombrePartido.setText("Nombre del partido");
        lblNombrePartido.setInheritsPopupMenu(false);
        lblNombrePartido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNombrePartidoMouseClicked(evt);
            }
        });

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

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnPosterior.setText(">");
        btnPosterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPosteriorActionPerformed(evt);
            }
        });

        btnFin.setText(">|");
        btnFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinActionPerformed(evt);
            }
        });

        btnPrincipio.setText("|<");
        btnPrincipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipioActionPerformed(evt);
            }
        });

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

        lblFiltro.setText("Filtrar");
        lblFiltro.setInheritsPopupMenu(false);
        lblFiltro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFiltroMouseClicked(evt);
            }
        });

        lblModifique.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblModifique.setText("Modifique los datos del partido seleccionado");
        lblModifique.setInheritsPopupMenu(false);

        btnCargar.setText("Cargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModifique)
                            .addComponent(sepTituloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFiltro)
                                .addGap(43, 43, 43)
                                .addComponent(txtFiltro))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblNombrePartido)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCantVotos)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnPrincipio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPosterior, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                        .addComponent(btnFin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ftxtCantVotos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(59, 59, 59)
                        .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(btnCargar)))
                .addContainerGap(358, Short.MAX_VALUE))
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
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombrePartido)
                    .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantVotos)
                    .addComponent(ftxtCantVotos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnPrincipio)
                    .addComponent(lblGuia)
                    .addComponent(btnPosterior)
                    .addComponent(btnFin))
                .addGap(74, 74, 74)
                .addComponent(btnCargar)
                .addContainerGap(183, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBorrarFiltro;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnFin;
    private javax.swing.JButton btnPosterior;
    private javax.swing.JButton btnPrincipio;
    private javax.swing.JFormattedTextField ftxtCantVotos;
    private javax.swing.JLabel lblCantVotos;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblGuia;
    private javax.swing.JLabel lblModifique;
    private javax.swing.JLabel lblNombrePartido;
    private javax.swing.JSeparator sepTituloForm;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtNombrePartido;
    // End of variables declaration//GEN-END:variables
}
