package controladores;

import modelo.Partido;
import servicios.PartidoService;
import vista.paneles.BajaPartido;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorBajaPartido {

    private final BajaPartido vista;
    private List<Partido> partidos;

    public ControladorBajaPartido(BajaPartido vista) {
        this.vista = vista;
        cargarCombo();
        iniciarEventos();
        vista.setOnVisible(this::cargarCombo); // recarga el combo cuando el panel se muestra

    }

    private void cargarCombo() {
        partidos = PartidoService.obtenerPartidos();
        vista.getCmbPartidos().removeAllItems();
        for (Partido p : partidos) {
            vista.getCmbPartidos().addItem(p.toString());
        }
    }

    private void iniciarEventos() {
        vista.getBtnDarBaja().addActionListener(e -> darBaja());
    }

    private void darBaja() {
        if (partidos.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay partidos cargados.");
            return;
        }

        int idx = vista.getCmbPartidos().getSelectedIndex();
        if (idx < 0) return;

        Partido seleccionado = partidos.get(idx);

        int confirm = JOptionPane.showConfirmDialog(vista,
            "¿Eliminar el partido " + seleccionado.getNombre() + "?",
            "Confirmar baja",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            PartidoService.eliminarPartido(seleccionado.getId());
            JOptionPane.showMessageDialog(vista, "Partido eliminado correctamente.");
            cargarCombo();
        }
    }
}