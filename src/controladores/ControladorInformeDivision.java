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
 * Controlador encargado de generar y mostrar
 * el informe de division de votos.
 */
public class ControladorInformeDivision {

    // Referencia a la vista donde se mostrara el informe
    private final InformeDivision vista;

    // Ruta del archivo JRXML del reporte
    private static final String RUTA_JRXML =
        "/informes/informeDivisionVotos.jrxml";

    /**
     * Constructor del controlador.
     * Configura el evento del boton del menu principal.
     *
     * @param vista Panel donde se mostrara el informe
     * @param menu Ventana principal del sistema
     */
    public ControladorInformeDivision(
        InformeDivision vista,
        MenuPrincipal menu
    ) {

        this.vista = vista;

        // Evento del boton para generar el informe
        menu.getBtnInfDivision().addActionListener(
            e -> cargarInformeManual()
        );
    }

    /**
     * Genera y carga el informe en un hilo separado
     * para evitar bloquear la interfaz grafica.
     */
    private void cargarInformeManual() {

        new Thread(() -> {

            try {

                // Genera el informe
                JasperPrint print = generarInforme();

                // Verifica si el informe contiene datos
                if (print.getPages().isEmpty()) {

                    SwingUtilities.invokeLater(() -> {

                        JOptionPane.showMessageDialog(
                            vista,
                            "No hay datos para mostrar. "
                            + "Asegurese de haber calculado "
                            + "los votos primero.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE
                        );
                    });

                    return;
                }

                // Muestra el informe en la vista
                SwingUtilities.invokeLater(() -> {
                    vista.mostrarInforme(print);
                });

            } catch (Exception ex) {

                // Muestra mensaje de error
                ex.printStackTrace();

                SwingUtilities.invokeLater(() -> {

                    JOptionPane.showMessageDialog(
                        vista,
                        "Error al generar el informe: "
                        + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                });
            }

        }).start();
    }

    /**
     * Genera el reporte utilizando JasperReports.
     *
     * @return JasperPrint con el informe generado
     * @throws Exception si ocurre un error al generar el reporte
     */
    private JasperPrint generarInforme() throws Exception {

        // Conexion a la base de datos
        Connection conn = Conexion.conectar();

        // Compila el archivo JRXML
        JasperReport reporte =
            JasperCompileManager.compileReport(
                getClass().getResourceAsStream(RUTA_JRXML)
            );

        // Llena el reporte con los datos obtenidos
        return JasperFillManager.fillReport(
            reporte,
            null,
            conn
        );
    }
}