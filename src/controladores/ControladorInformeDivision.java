package controladores;

import dao.Conexion;
import vista.paneles.InformeDivision;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Controlador que gestiona la visualizacion del informe de division de votos.
 * Compila el archivo .jrxml, lo llena con los datos de la base de datos
 * y lo embebe dentro del panel usando JRViewer.
 */
public class ControladorInformeDivision {

    private final InformeDivision vista;

    /** Ruta al archivo fuente del informe dentro del classpath. */
    private static final String RUTA_JRXML = "/informes/informeDivisionVotos.jrxml";

    /**
     * Inicializa el controlador vinculando la vista y registrando los eventos.
     * @param vista panel del informe de division de votos
     */
    public ControladorInformeDivision(InformeDivision vista) {
        this.vista = vista;
        iniciarEventos();
    }

    /**
     * Registra los listeners de los componentes interactivos del panel.
     */
    private void iniciarEventos() {
        vista.getBtnGenerar().addActionListener(e -> generar());
    }

    /**
     * Compila y llena el informe con los datos actuales de la base de datos,
     * luego lo embebe dentro del panel reemplazando el boton.
     * Muestra un JOptionPane de error si el informe no puede generarse.
     */
    private void generar() {
        try {
            JasperPrint print = generarInforme();
            vista.mostrarInforme(print);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista,
                "Error al generar el informe: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Compila el archivo .jrxml y lo llena con los datos de la base de datos.
     * Usa la conexion JDBC de UCanAccess directamente, igual que el resto del sistema.
     * @return JasperPrint listo para ser visualizado
     * @throws Exception si ocurre un error de compilacion, conexion o llenado
     */
    private JasperPrint generarInforme() throws Exception {
        Connection conn = Conexion.conectar();
        JasperReport reporte = JasperCompileManager.compileReport(
            getClass().getResourceAsStream(RUTA_JRXML)
        );
        return JasperFillManager.fillReport(reporte, null, conn);
    }
}