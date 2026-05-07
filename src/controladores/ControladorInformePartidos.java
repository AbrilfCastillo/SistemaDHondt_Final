package controladores;

import dao.Conexion;
import vista.MenuPrincipal;
import vista.paneles.InformePartidos;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Controlador que gestiona la visualización del informe de partidos
 * vinculándolo al botón de navegación del menú principal.
 */
public class ControladorInformePartidos {

    private final InformePartidos vista;
    private static final String RUTA_JRXML = "/informes/informePartidos.jrxml";

    /**
     * @param vista El panel donde se mostrará el informe
     * @param menu El JFrame principal para obtener el botón de navegación
     */
    public ControladorInformePartidos(InformePartidos vista, MenuPrincipal menu) {
        this.vista = vista;
        
        // Vinculamos la carga al botón de la barra lateral
        if (menu.getBtnInfPartidos() != null) {
            menu.getBtnInfPartidos().addActionListener(e -> cargarInformeManual());
        }
    }

    /**
     * Ejecuta la generación del informe a petición del usuario.
     */
    private void cargarInformeManual() {
        new Thread(() -> {
            try {
                JasperPrint print = generarInforme();

                // Verificamos si hay páginas para evitar el mensaje de error de Jasper
                if (print.getPages().isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(vista, 
                            "No hay partidos registrados para mostrar en el informe.", 
                            "Informe vacío", JOptionPane.INFORMATION_MESSAGE);
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
                        "Error al cargar el informe de partidos: " + ex.getMessage(),
                        "Error de Reporte", JOptionPane.ERROR_MESSAGE);
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