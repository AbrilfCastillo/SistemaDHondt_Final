package controladores;

import dao.Conexion;
import vista.MenuPrincipal;
import vista.paneles.InformeAnalisisPartidos;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Controlador encargado de generar y mostrar
 * el informe de partidos registrados.
 */
public class ControladorInformeAnalisisPartidos {

    // Referencia a la vista donde se mostrara el informe
    private final InformeAnalisisPartidos vista;

    // Ruta del archivo JRXML del reporte
    private static final String RUTA_JRXML =
        "/informes/informePartidos.jrxml";

    /**
     * Constructor del controlador.
     * Configura el evento del boton del menu principal.
     *
     * @param vista Panel donde se mostrara el informe
     * @param menu Ventana principal del sistema
     */
    public ControladorInformeAnalisisPartidos(
        InformeAnalisisPartidos vista,
        MenuPrincipal menu
    ) {

        this.vista = vista;

        // Verifica que el boton exista antes de asignar el evento
        if (menu.getBtnInfPartidos() != null) {

            // Evento del boton para generar el informe
            menu.getBtnInfPartidos().addActionListener(
                e -> cargarInformeManual()
            );
        }
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

                // Verifica si el informe contiene paginas
                if (print.getPages().isEmpty()) {

                    SwingUtilities.invokeLater(() -> {

                        JOptionPane.showMessageDialog(
                            vista,
                            "No hay partidos registrados "
                            + "para mostrar en el informe.",
                            "Informe vacio",
                            JOptionPane.INFORMATION_MESSAGE
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
                        "Error al cargar el informe "
                        + "de partidos: " + ex.getMessage(),
                        "Error de Reporte",
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