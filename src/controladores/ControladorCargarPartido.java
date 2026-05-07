package controladores;

import servicios.PartidoService;
import vista.paneles.CargarPartido;
import javax.swing.JOptionPane;
import vista.componentes.Estilo;

public class ControladorCargarPartido {

    private final CargarPartido vista;

    public ControladorCargarPartido(CargarPartido vista) {
        this.vista = vista;
        iniciarEventos();
    }

private void iniciarEventos() {
    vista.getBtnCargar().addActionListener(e -> cargar());

    Estilo.configurarTeclado(vista.getTxtNombrePartido());
    Estilo.configurarTeclado(vista.getTxtCantVotos());
    Estilo.configurarTeclado(vista.getBtnCargar());
}

    private void cargar() {
        String nombre = vista.getTxtNombrePartido().getText().trim();
        String votos  = vista.getTxtCantVotos().getText().trim();

        try {
            PartidoService.crearPartido(nombre, votos);
            JOptionPane.showMessageDialog(vista, "Partido cargado correctamente.");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        vista.getTxtNombrePartido().setText("");
        vista.getTxtCantVotos().setText("");
        vista.getTxtNombrePartido().requestFocus();
    }
}