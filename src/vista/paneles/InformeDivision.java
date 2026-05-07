package vista.paneles;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import vista.componentes.Estilo;

/**
 * Panel que muestra el informe de división de votos generado con JasperReports.
 * El informe se visualiza directamente al cargar el panel.
 */
public class InformeDivision extends javax.swing.JPanel {

    private JPanel pnlViewer;

    /**
     * Inicializa el panel configurando el layout y aplicando estilos base.
     */
    public InformeDivision() {
        initComponents(); // Inicialización de dimensiones de NetBeans
        setLayout(new BorderLayout()); // Layout para expansión total del informe
        Estilo.aplicarCard(this);
    }

    /**
     * Muestra el informe JasperReports dentro del panel, expandiéndose al centro.
     * @param print resultado del llenado del informe listo para visualizar
     */
    public void mostrarInforme(JasperPrint print) {
        // Limpieza de visualizaciones previas
        if (pnlViewer != null) {
            remove(pnlViewer);
        }

        pnlViewer = new JPanel(new BorderLayout());
        pnlViewer.setOpaque(false);
        
        // Se agrega el visor del informe generado
        pnlViewer.add(new JRViewer(print), BorderLayout.CENTER);

        // Se añade al contenedor principal en la posición central
        add(pnlViewer, BorderLayout.CENTER);

        // Refrescar componentes visuales
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
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
