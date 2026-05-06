package vista.paneles;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import vista.componentes.Estilo;

/**
 * Panel que muestra el informe de partidos politicos generado con JasperReports.
 * Presenta un boton para generar el informe y luego lo embebe dentro del mismo panel.
 */
public class InformePartidos extends javax.swing.JPanel {

    private static final String CARD_BOTON   = "boton";
    private static final String CARD_INFORME = "informe";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel     pnlCards   = new JPanel(cardLayout);
    private       JPanel     pnlViewer;

    private JButton btnGenerar;

    /**
     * Inicializa el panel configurando el CardLayout y los estilos.
     */
    public InformePartidos() {
        setLayout(new BorderLayout());
        configurarCards();
        Estilo.aplicarCard(this);
    }

    /**
     * Arma el CardLayout con el boton inicial y el espacio para el informe.
     */
    private void configurarCards() {
        JPanel pnlBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlBoton.setOpaque(false);

        btnGenerar = new JButton("Generar informe");
        Estilo.aplicarBoton(btnGenerar);
        pnlBoton.add(btnGenerar);

        pnlCards.setOpaque(false);
        pnlCards.add(pnlBoton, CARD_BOTON);

        add(pnlCards, BorderLayout.CENTER);
        cardLayout.show(pnlCards, CARD_BOTON);
    }

    /**
     * Muestra el informe JasperReports dentro del panel, reemplazando el boton.
     * @param print resultado del llenado del informe listo para visualizar
     */
    public void mostrarInforme(JasperPrint print) {
        if (pnlViewer != null) {
            pnlCards.remove(pnlViewer);
        }

        pnlViewer = new JPanel(new BorderLayout());
        pnlViewer.setOpaque(false);
        pnlViewer.add(new JRViewer(print), BorderLayout.CENTER);

        pnlCards.add(pnlViewer, CARD_INFORME);
        cardLayout.show(pnlCards, CARD_INFORME);

        pnlCards.revalidate();
        pnlCards.repaint();
    }

    /**
     * Vuelve a mostrar el boton, ocultando el informe.
     */
    public void mostrarBoton() {
        cardLayout.show(pnlCards, CARD_BOTON);
    }

    // Getter
    public JButton getBtnGenerar() { return btnGenerar; }

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
