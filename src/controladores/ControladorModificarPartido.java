package controladores;

import modelo.Partido;
import servicios.PartidoService;
import vista.paneles.ModificarPartido;
import java.util.List;
import javax.swing.JOptionPane;
import vista.componentes.Estilo;

/**
 * Controlador que gestiona la logica de modificacion de partidos politicos.
 * Conecta el panel ModificarPartido con el servicio PartidoService para
 * actualizar los datos de un partido existente en la base de datos.
 */
public class ControladorModificarPartido {

    private final ModificarPartido vista;

    /**
     * Inicializa el controlador vinculando la vista y configurando los eventos.
     * @param vista panel de modificacion de partidos
     */
    public ControladorModificarPartido(ModificarPartido vista) {
        this.vista = vista;
        iniciarEventos();
    }

    /**
     * Registra los listeners de los componentes interactivos del panel.
     */
    private void iniciarEventos() {
        vista.getBtnCargar().addActionListener(e -> actualizar());
        Estilo.configurarTeclado(vista.getTxtFiltro());
        Estilo.configurarTeclado(vista.getBtnBorrarFiltro());
        Estilo.configurarTeclado(vista.getTxtNombrePartido());
        Estilo.configurarTeclado(vista.getFtxtCantVotos());
        Estilo.configurarTeclado(vista.getBtnPrincipio());
        Estilo.configurarTeclado(vista.getBtnAnterior());
        Estilo.configurarTeclado(vista.getBtnPosterior());
        Estilo.configurarTeclado(vista.getBtnFin());
        Estilo.configurarTeclado(vista.getBtnCargar());
    }

    /**
     * Obtiene los datos del formulario y delega la actualizacion al servicio.
     * Muestra un JOptionPane de exito o error segun el resultado de la operacion.
     */
    private void actualizar() {
        try {
            vista.actualizarPartidoActual();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}