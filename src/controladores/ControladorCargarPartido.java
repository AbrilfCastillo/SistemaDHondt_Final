package controladores;

import servicios.PartidoService;
import vista.paneles.CargarPartido;
import javax.swing.JOptionPane;
import vista.componentes.Estilo;

/**
 * Controlador encargado de la logica del panel CargarPartido.
 * Permite registrar nuevos partidos en el sistema.
 */
public class ControladorCargarPartido {

    // Referencia a la vista
    private final CargarPartido vista;

    /**
     * Constructor del controlador.
     * Inicializa la vista y configura los eventos.
     *
     * @param vista Panel CargarPartido asociado al controlador
     */
    public ControladorCargarPartido(CargarPartido vista) {
        this.vista = vista;
        iniciarEventos();
    }

    /**
     * Configura los eventos y estilos de los componentes.
     */
    private void iniciarEventos() {

        // Evento del boton para cargar un partido
        vista.getBtnCargar().addActionListener(e -> cargar());

        // Configuracion de teclado para navegacion
        Estilo.configurarTeclado(vista.getTxtNombrePartido());
        Estilo.configurarTeclado(vista.getTxtCantVotos());
        Estilo.configurarTeclado(vista.getBtnCargar());
    }

    /**
     * Obtiene los datos ingresados y registra un nuevo partido.
     */
    private void cargar() {

        // Obtiene el nombre del partido
        String nombre = vista.getTxtNombrePartido().getText().trim();

        // Obtiene la cantidad de votos
        String votos = vista.getTxtCantVotos().getText().trim();

        try {

            // Intenta crear el partido
            PartidoService.crearPartido(nombre, votos);

            JOptionPane.showMessageDialog(
                vista,
                "Partido cargado correctamente."
            );

            // Limpia los campos luego de cargar
            limpiarCampos();

        } catch (Exception ex) {

            // Muestra mensaje de error si ocurre una excepcion
            JOptionPane.showMessageDialog(
                vista,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Limpia los campos del formulario y devuelve el foco
     * al campo de nombre del partido.
     */
    private void limpiarCampos() {

        vista.getTxtNombrePartido().setText("");
        vista.getTxtCantVotos().setText("");

        // Coloca el cursor nuevamente en el primer campo
        vista.getTxtNombrePartido().requestFocus();
    }
}