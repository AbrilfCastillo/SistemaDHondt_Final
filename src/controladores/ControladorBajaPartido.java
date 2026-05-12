package controladores;

import modelo.Partido;
import servicios.PartidoService;
import vista.paneles.BajaPartido;
import java.util.List;
import javax.swing.JOptionPane;
import vista.componentes.Estilo;

/**
 * Controlador encargado de la logica del panel BajaPartido.
 * Maneja la carga de partidos y la eliminacion de un partido seleccionado.
 */
public class ControladorBajaPartido {

    // Referencia a la vista
    private final BajaPartido vista;

    // Lista de partidos cargados desde el servicio
    private List<Partido> partidos;

    /**
     * Constructor del controlador.
     * Inicializa la vista, carga el combo y configura eventos.
     *
     * @param vista Panel BajaPartido asociado al controlador
     */
    public ControladorBajaPartido(BajaPartido vista) {
        this.vista = vista;

        cargarCombo();
        iniciarEventos();

        // Recarga el combo cada vez que el panel se muestra
        vista.setOnVisible(this::cargarCombo);
    }

    /**
     * Carga todos los partidos en el JComboBox.
     */
    private void cargarCombo() {

        // Obtiene la lista actualizada de partidos
        partidos = PartidoService.obtenerPartidos();

        // Limpia el combo antes de volver a cargarlo
        vista.getCmbPartidos().removeAllItems();

        // Agrega cada partido al combo
        for (Partido p : partidos) {
            vista.getCmbPartidos().addItem(p.toString());
        }
    }

    /**
     * Configura los eventos y estilos de los componentes.
     */
    private void iniciarEventos() {

        // Evento del boton para eliminar un partido
        vista.getBtnDarBaja().addActionListener(e -> darBaja());

        // Configuracion de teclado para navegacion
        Estilo.configurarTeclado(vista.getCmbPartidos());
        Estilo.configurarTeclado(vista.getBtnDarBaja());
    }

    /**
     * Elimina el partido seleccionado del sistema.
     */
    private void darBaja() {

        // Verifica si existen partidos cargados
        if (partidos.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay partidos cargados.");
            return;
        }

        // Obtiene el indice seleccionado en el combo
        int idx = vista.getCmbPartidos().getSelectedIndex();

        // Verifica que exista una seleccion valida
        if (idx < 0) return;

        // Obtiene el partido seleccionado
        Partido seleccionado = partidos.get(idx);

        // Solicita confirmacion antes de eliminar
        int confirm = JOptionPane.showConfirmDialog(
            vista,
            "¿Eliminar el partido " + seleccionado.getNombre() + "?",
            "Confirmar baja",
            JOptionPane.YES_NO_OPTION
        );

        // Si el usuario confirma, elimina el partido
        if (confirm == JOptionPane.YES_OPTION) {

            PartidoService.eliminarPartido(seleccionado.getId());

            JOptionPane.showMessageDialog(
                vista,
                "Partido eliminado correctamente."
            );

            // Recarga el combo actualizado
            cargarCombo();
        }
    }
}