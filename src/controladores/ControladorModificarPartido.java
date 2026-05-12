package controladores;

import modelo.Partido;
import servicios.PartidoService;
import vista.paneles.ModificarPartido;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.componentes.Estilo;

/**
 * Controlador que gestiona la logica de modificacion de partidos politicos.
 * Maneja el estado de la lista, navegacion, filtrado y persistencia,
 * delegando a la vista solo la presentacion de datos.
 */
public class ControladorModificarPartido {

    private final ModificarPartido vista;

    private List<Partido> partidos;          // lista completa desde la base de datos
    private List<Partido> lista;             // lista activa (completa o filtrada)
    private List<Partido> partidosFiltrados; // resultado del filtro actual
    private int indice = 0;

    /**
     * Inicializa el controlador vinculando la vista y configurando los eventos.
     * @param vista panel de modificacion de partidos
     */
    public ControladorModificarPartido(ModificarPartido vista) {
        this.vista = vista;
        cargarDatosIniciales();
        iniciarEventos();

        // Recarga los datos cada vez que el panel se hace visible
        vista.setOnVisible(this::cargarDatosIniciales);
    }

    /**
     * Consulta la base de datos, inicializa las listas y muestra el primer partido.
     * Si no hay partidos, limpia los campos.
     */
    private void cargarDatosIniciales() {
        partidos = PartidoService.obtenerPartidos();
        lista = partidos;
        indice = 0;

        if (!partidos.isEmpty()) {
            mostrarActual();
        } else {
            vista.limpiarCampos();
        }
    }

    /**
     * Registra los listeners de todos los componentes interactivos del panel.
     */
    private void iniciarEventos() {

        // Navegacion
        vista.getBtnPrincipio().addActionListener(e -> irAlPrincipio());
        vista.getBtnAnterior().addActionListener(e -> irAlAnterior());
        vista.getBtnPosterior().addActionListener(e -> irAlPosterior());
        vista.getBtnFin().addActionListener(e -> irAlFin());

        // Guardar
        vista.getBtnCargar().addActionListener(e -> actualizar());

        // Filtro
        vista.getTxtFiltro().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrar();
            }
        });

        vista.getBtnBorrarFiltro().addActionListener(e -> borrarFiltro());

        // Enter en campos dispara actualizacion
        vista.getTxtNombrePartido().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    actualizar();
                }
            }
        });

        vista.getFtxtCantVotos().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    actualizar();
                }
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                // Solo permite digitos numericos
                if (!Character.isDigit(evt.getKeyChar())) {
                    evt.consume();
                }
            }
        });

        // Configuracion de teclado (flechas y enter para navegacion entre componentes)
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
     * Muestra en la vista el partido en la posicion actual del indice.
     */
    private void mostrarActual() {
        if (lista.isEmpty()) {
            vista.limpiarCampos();
            return;
        }
        Partido p = lista.get(indice);
        vista.mostrarPartido(p, indice + 1, lista.size());
    }

    /**
     * Obtiene los datos del formulario y delega la actualizacion al servicio.
     * Si hay filtro activo, lo reaaplica tras la actualizacion.
     */
    private void actualizar() {
        if (lista.isEmpty()) return;

        try {
            Partido p = lista.get(indice);
            String nuevoNombre = vista.getNombreIngresado();
            long nuevosVotos = vista.getVotos();

            PartidoService.actualizarPartido(p.getId(), nuevoNombre, nuevosVotos);

            partidos = PartidoService.obtenerPartidos();

            // Si habia filtro activo, lo reaaplica sobre la lista actualizada
            String textoFiltro = vista.getTextoFiltro();
            if (!textoFiltro.isEmpty()) {
                partidosFiltrados = PartidoService.filtrar(partidos, textoFiltro);
                lista = partidosFiltrados;
                indice = 0;
            } else {
                lista = partidos;
            }

            mostrarActual();
            JOptionPane.showMessageDialog(vista, "Actualizado correctamente.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Filtra la lista en tiempo real segun el texto ingresado.
     * Si el campo esta vacio, muestra todos los partidos.
     */
    private void filtrar() {
        String texto = vista.getTextoFiltro();
        if (texto.isEmpty()) {
            lista = partidos;
        } else {
            partidosFiltrados = PartidoService.filtrar(partidos, texto);
            lista = partidosFiltrados;
        }
        indice = 0;
        mostrarActual();
    }

    /**
     * Limpia el filtro y vuelve a mostrar la lista completa.
     */
    private void borrarFiltro() {
        vista.getTxtFiltro().setText("");
        lista = partidos;
        indice = 0;
        mostrarActual();
    }

    // =========================================================
    // Navegacion
    // =========================================================

    private void irAlPrincipio() {
        indice = 0;
        mostrarActual();
    }

    private void irAlAnterior() {
        if (indice > 0) {
            indice--;
        }
        mostrarActual();
    }

    private void irAlPosterior() {
        if (indice < lista.size() - 1) {
            indice++;
        }
        mostrarActual();
    }

    private void irAlFin() {
        if (!lista.isEmpty()) {
            indice = lista.size() - 1;
        }
        mostrarActual();
    }
}