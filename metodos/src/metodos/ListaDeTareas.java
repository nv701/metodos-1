package metodos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la lista de tareas de un usuario.
 * Permite agregar, eliminar y guardar las tareas.
 */
public class ListaDeTareas implements Serializable {
    private List<Tarea> tareas;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor para inicializar una lista de tareas vacía.
     */
    public ListaDeTareas() {
        tareas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de tareas.
     *
     * @return Lista de tareas.
     */
    public List<Tarea> getTareas() {
        return tareas;
    }

    /**
     * Agrega una tarea a la lista de tareas.
     *
     * @param tarea Tarea a agregar.
     */
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    /**
     * Elimina una tarea de la lista de tareas.
     *
     * @param tarea Tarea a eliminar.
     */
    public void eliminarTarea(Tarea tarea) {
        tareas.remove(tarea);
    }

    /**
     * Guarda las tareas en un archivo binario.
     */
    public void guardarTareas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tareas.bin"))) {
            out.writeObject(tareas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga las tareas desde un archivo binario.
     *
     * @return Lista de tareas almacenadas en el archivo.
     */
    public static List<Tarea> cargarTareas() {
        List<Tarea> tareas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("tareas.bin"))) {
            tareas = (List<Tarea>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de tareas.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tareas;
    }
}