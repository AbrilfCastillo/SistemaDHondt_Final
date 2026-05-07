package vista.paneles;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import vista.componentes.Estilo;

/**
 * Panel que muestra el informe de partidos políticos generado con JasperReports.
 * El informe se embebe directamente en el panel principal.
 */
public class InformePartidos extends javax.swing.JPanel {

    private JPanel pnlViewer;

    /**
     * Inicializa el panel con un BorderLayout y aplica los estilos base.
     */
    public InformePartidos() {
        initComponents(); // Mantiene la inicialización de dimensiones de NetBeans
        setLayout(new BorderLayout());
        Estilo.aplicarCard(this);
    }

    /**
     * Muestra el informe JasperReports dentro del panel.
     * Al llamar a este método, el visor ocupará todo el espacio disponible.
     * @param print resultado del llenado del informe listo para visualizar
     */
    public void mostrarInforme(JasperPrint print) {
        // Limpiar contenido previo si existe
        if (pnlViewer != null) {
            remove(pnlViewer);
        }

        // Crear el contenedor del visor
        pnlViewer = new JPanel(new BorderLayout());
        pnlViewer.setOpaque(false);
        
        // Agregar el JRViewer de JasperReports
        pnlViewer.add(new JRViewer(print), BorderLayout.CENTER);

        // Añadir al panel principal (CENTER en BorderLayout para que expanda)
        add(pnlViewer, BorderLayout.CENTER);

        // Refrescar la interfaz
        revalidate();
        repaint();
    }
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
