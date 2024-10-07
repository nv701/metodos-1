package metodos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que representa la interfaz gráfica de la aplicación.
 * Permite al usuario iniciar sesión, registrar usuarios y gestionar sus tareas.
 */
public class InterfazGrafica {
    private JFrame ventanaPrincipal;
    private JList<Tarea> listaTareas;
    private DefaultListModel<Tarea> modeloTareas;
    private ListaDeTareas tareasUsuario;
    private JTextField campoUsuario;
    private JTextField campoContraseña;

    private int anchoCampoLogin = 10;
    private int anchoCampoTarea = 10;

    /**
     * Constructor de la clase `InterfazGrafica`.
     * Inicializa la interfaz de inicio de sesión y registro de usuarios.
     */
    public InterfazGrafica() {
        ventanaPrincipal = new JFrame("LISTA DE TAREAS");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(400, 100);
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.getContentPane().setBackground(new Color(240, 240, 240));

        modeloTareas = new DefaultListModel<>();
        listaTareas = new JList<>(modeloTareas);

        JPanel panelLogin = new JPanel();
        panelLogin.setBackground(new Color(220, 220, 220));
        campoUsuario = new JTextField(anchoCampoLogin);
        campoContraseña = new JTextField(anchoCampoLogin);
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar");

        panelLogin.add(new JLabel("Usuario:"));
        panelLogin.add(campoUsuario);
        panelLogin.add(new JLabel("Contraseña:"));
        panelLogin.add(campoContraseña);
        panelLogin.add(btnIniciarSesion);
        panelLogin.add(btnRegistrar);
        ventanaPrincipal.add(panelLogin);

        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        btnRegistrar.addActionListener(e -> registrarUsuario());

        ventanaPrincipal.setVisible(true);
    }

    /**
     * Método para iniciar sesión con los datos proporcionados.
     * Valida las credenciales y muestra la lista de tareas del usuario si las credenciales son correctas.
     */
    private void iniciarSesion() {
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();
        List<Usuario> usuarios = Usuario.cargarUsuarios();

        for (Usuario user : usuarios) {
            if (user.getNombre().equals(usuario) && user.getContraseña().equals(contraseña)) {
                tareasUsuario = user.getListaDeTareas();
                mostrarListaDeTareas();
                return;
            }
        }
        JOptionPane.showMessageDialog(ventanaPrincipal, "Usuario o contraseña incorrectos.");
    }

    /**
     * Método para registrar un nuevo usuario con los datos ingresados.
     * Crea un nuevo usuario, lo guarda en la lista y muestra la lista de tareas vacía.
     */
    private void registrarUsuario() {
        String usuario = campoUsuario.getText();
        String contraseña = campoContraseña.getText();

        if (!usuario.isEmpty() && !contraseña.isEmpty()) {
            Usuario nuevoUsuario = new Usuario(usuario, contraseña);
            nuevoUsuario.guardarUsuarios();
            tareasUsuario = nuevoUsuario.getListaDeTareas();
            mostrarListaDeTareas();
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "Por favor ingrese usuario y contraseña.");
        }
    }

    /**
     * Muestra la lista de tareas del usuario en la interfaz gráfica.
     * Permite agregar, completar, eliminar, modificar y guardar tareas.
     */
    private void mostrarListaDeTareas() {
        ventanaPrincipal.getContentPane().removeAll();
        ventanaPrincipal.setSize(650, 450);
        JButton btnAñadir = new JButton("AÑADIR TAREA");
        JButton btnFinalizar = new JButton("FINALIZAR");
        JButton btnCompletar = new JButton("COMPLETAR TAREA");
        JButton btnEliminar = new JButton("ELIMINAR TAREA");
        JButton btnModificar = new JButton("MODIFICAR TAREA");
        JButton btnImprimir = new JButton("IMPRIMIR LISTA");

        btnAñadir.addActionListener(e -> añadirTarea());
        btnCompletar.addActionListener(e -> completarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
        btnModificar.addActionListener(e -> modificarTarea());
        btnImprimir.addActionListener(e -> imprimirLista());
        btnFinalizar.addActionListener(e -> finalizarPrograma());

        modeloTareas.clear();
        for (Tarea tarea : tareasUsuario.getTareas()) {
            modeloTareas.addElement(tarea);
        }

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(btnAñadir);
        panelSuperior.add(btnFinalizar);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(new JScrollPane(listaTareas), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.add(btnCompletar);
        panelInferior.add(btnEliminar);
        panelInferior.add(btnModificar);
        panelInferior.add(btnImprimir);

        ventanaPrincipal.add(panelSuperior, BorderLayout.NORTH);
        ventanaPrincipal.add(panelCentral, BorderLayout.CENTER);
        ventanaPrincipal.add(panelInferior, BorderLayout.SOUTH);

        ventanaPrincipal.revalidate();
        ventanaPrincipal.repaint();
    }

    /**
     * Añade una nueva tarea a la lista de tareas.
     * Solicita al usuario los detalles de la tarea a través de una ventana emergente.
     */
    private void añadirTarea() {
        JTextField campoNombre = new JTextField(10);
        JTextField campoFecha = new JTextField(10);
        String[] prioridades = { "ALTA", "MEDIA", "BAJA" };
        JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);

        JPanel panel = new JPanel();
        panel.add(new JLabel("NOMBRE DE TAREA:"));
        panel.add(campoNombre);
        panel.add(new JLabel("FECHA LIMITE (AÑO-MES-DIA):"));
        panel.add(campoFecha);
        panel.add(new JLabel("PRIORIDAD:"));
        panel.add(comboPrioridad);

        int option = JOptionPane.showConfirmDialog(ventanaPrincipal, panel, "AÑADIR TAREA",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nombre = campoNombre.getText();
            String fechaString = campoFecha.getText();
            String prioridad = (String) comboPrioridad.getSelectedItem();
            try {
                Tarea tarea = new Tarea(nombre, LocalDate.parse(fechaString), prioridad);
                tareasUsuario.agregarTarea(tarea);
                modeloTareas.addElement(tarea);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL AÑADIR TAREA: " + e.getMessage());
            }
        }
    }

    /**
     * Marca la tarea seleccionada como completada.
     */
    private void completarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            tareaSeleccionada.setCompletada(true);
            modeloTareas.removeElement(tareaSeleccionada);
            modeloTareas.addElement(tareaSeleccionada);
            tareasUsuario.guardarTareas();
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA COMPLETAR.");
        }
    }

    /**
     * Elimina la tarea seleccionada de la lista.
     */
    private void eliminarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            tareasUsuario.eliminarTarea(tareaSeleccionada);
            modeloTareas.removeElement(tareaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA ELIMINAR");
        }
    }

    /**
     * Modifica la tarea seleccionada.
     * Solicita nuevos valores para la tarea y los guarda.
     */
    private void modificarTarea() {
        Tarea tareaSeleccionada = listaTareas.getSelectedValue();
        if (tareaSeleccionada != null) {
            JTextField campoNombre = new JTextField(anchoCampoTarea);
            campoNombre.setText(tareaSeleccionada.getNombre());
            JTextField campoFecha = new JTextField(anchoCampoTarea);
            campoFecha.setText(tareaSeleccionada.getFecha().toString());
            String[] prioridades = { "ALTA", "MEDIA", "BAJA" };
            JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);
            comboPrioridad.setSelectedItem(tareaSeleccionada.getPrioridad());

            JPanel panel = new JPanel();
            panel.add(new JLabel("NUEVO NOMBRE:"));
            panel.add(campoNombre);
            panel.add(new JLabel("NUEVA FECHA (AÑO-MES-DIA):"));
            panel.add(campoFecha);
            panel.add(new JLabel("NUEVA PRIORIDAD:"));
            panel.add(comboPrioridad);

            int option = JOptionPane.showConfirmDialog(ventanaPrincipal, panel, "MODIFICAR TAREA",
                    JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String nuevoNombre = campoNombre.getText();
                String nuevaFechaString = campoFecha.getText();
                String nuevaPrioridad = (String) comboPrioridad.getSelectedItem();
                try {
                    tareaSeleccionada.setNombre(nuevoNombre);
                    tareaSeleccionada.setFecha(LocalDate.parse(nuevaFechaString));
                    tareaSeleccionada.setPrioridad(nuevaPrioridad);
                    modeloTareas.removeElement(tareaSeleccionada);
                    modeloTareas.addElement(tareaSeleccionada);
                    tareasUsuario.guardarTareas();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL MODIFICAR TAREA: " + e.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(ventanaPrincipal, "POR FAVOR SELECCIONE UNA TAREA PARA MODIFICAR.");
        }
    }

    /**
     * Imprime la lista de tareas en un archivo de texto.
     */
    private void imprimirLista() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ListaDeTareas.txt"));
            for (int i = 0; i < modeloTareas.size(); i++) {
                writer.write(modeloTareas.get(i).toString());
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(ventanaPrincipal, "LISTA DE TAREAS IMPRESA EXITOSAMENTE.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(ventanaPrincipal, "ERROR AL IMPRIMIR LA LISTA: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de tareas y cierra la aplicación.
     */
    private void finalizarPrograma() {
        tareasUsuario.guardarTareas();
        JOptionPane.showMessageDialog(ventanaPrincipal, "LISTA DE TAREAS GUARDADA.");
        System.exit(0);
    }
}
