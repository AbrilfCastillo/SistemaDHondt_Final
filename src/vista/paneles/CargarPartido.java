/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.paneles;

import vista.componentes.Estilo;

/**
 *
 * @author cielo
 */
public class CargarPartido extends javax.swing.JPanel {

    /**
     * Creates new form CargarDatos
     */
public CargarPartido() {
    initComponents();

    //Estilos
    Estilo.aplicarSubtitulo(lblIngrese);
    Estilo.aplicarTexto(lblNombrePartido);
    Estilo.aplicarTexto(lblCantVotos);
    Estilo.aplicarSeparadorTitulo(sepTituloForm);
    Estilo.aplicarTextField(txtNombrePartido);
    Estilo.aplicarTextField(txtCantVotos);
    Estilo.aplicarBoton(btnCargar);
    Estilo.aplicarCard(this);
}
    public javax.swing.JTextField getTxtNombrePartido() { return txtNombrePartido; }
    public javax.swing.JTextField getTxtCantVotos() { return txtCantVotos; }
    public javax.swing.JButton getBtnCargar() { return btnCargar; }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombrePartido = new javax.swing.JLabel();
        txtNombrePartido = new javax.swing.JTextField();
        lblCantVotos = new javax.swing.JLabel();
        txtCantVotos = new javax.swing.JTextField();
        btnCargar = new javax.swing.JButton();
        lblIngrese = new javax.swing.JLabel();
        sepTituloForm = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(204, 204, 204), javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")));
        setPreferredSize(new java.awt.Dimension(600, 400));

        lblNombrePartido.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblNombrePartido.setForeground(new java.awt.Color(61, 75, 102));
        lblNombrePartido.setLabelFor(txtNombrePartido);
        lblNombrePartido.setText("Nombre de partido");
        lblNombrePartido.setInheritsPopupMenu(false);

        txtNombrePartido.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombrePartido.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(188, 194, 207), new java.awt.Color(188, 194, 207)));
        txtNombrePartido.setPreferredSize(new java.awt.Dimension(4, 30));

        lblCantVotos.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCantVotos.setForeground(new java.awt.Color(61, 75, 102));
        lblCantVotos.setText("Cantidad de votos");
        lblCantVotos.setInheritsPopupMenu(false);

        txtCantVotos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantVotos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(188, 194, 207), new java.awt.Color(188, 194, 207)));

        btnCargar.setText("Cargar");

        lblIngrese.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblIngrese.setForeground(new java.awt.Color(61, 75, 102));
        lblIngrese.setText("Ingrese los datos del partido");
        lblIngrese.setInheritsPopupMenu(false);

        sepTituloForm.setForeground(new java.awt.Color(233, 233, 240));
        sepTituloForm.setPreferredSize(new java.awt.Dimension(50, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepTituloForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantVotos, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombrePartido)
                            .addComponent(lblIngrese)
                            .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantVotos)
                            .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 610, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblIngrese)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepTituloForm, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblNombrePartido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombrePartido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCantVotos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCantVotos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JLabel lblCantVotos;
    private javax.swing.JLabel lblIngrese;
    private javax.swing.JLabel lblNombrePartido;
    private javax.swing.JSeparator sepTituloForm;
    private javax.swing.JTextField txtCantVotos;
    private javax.swing.JTextField txtNombrePartido;
    // End of variables declaration//GEN-END:variables
}
