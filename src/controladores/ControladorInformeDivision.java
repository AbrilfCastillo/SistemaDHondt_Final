package controladores;

import dao.Conexion;
import vista.MenuPrincipal;
import vista.paneles.InformeDivision;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Controlador que genera el informe de división de votos al hacer clic
 * en el botón de navegación del menú principal.
 */
public class ControladorInformeDivision {

    private final InformeDivision vista;
    private static final String RUTA_JRXML = "/informes/informeDivisionVotos.jrxml";

    /**
     * @param vista El panel donde se mostrará el informe
     * @param menu El JFrame principal que contiene el botón de navegación
     */
    public ControladorInformeDivision(InformeDivision vista, MenuPrincipal menu) {
        this.vista = vista;
        
        // Escuchamos el botón de la barra lateral del menú principal
        menu.getBtnInfDivision().addActionListener(e -> cargarInformeManual());
    }

    private void cargarInformeManual() {
        // Mostramos un mensaje o un estado de carga si fuera necesario
        new Thread(() -> {
            try {
                JasperPrint print = generarInforme();

                if (print.getPages().isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(vista, 
                            "No hay datos para mostrar. Asegúrese de haber calculado los votos primero.", 
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    });
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    vista.mostrarInforme(print);
                });

            } catch (Exception ex) {
                ex.printStackTrace();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(vista,
                        "Error al generar el informe: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    private JasperPrint generarInforme() throws Exception {
        Connection conn = Conexion.conectar();
        JasperReport reporte = JasperCompileManager.compileReport(
            getClass().getResourceAsStream(RUTA_JRXML)
        );
        return JasperFillManager.fillReport(reporte, null, conn);
    }
}