package metodos;

import java.time.LocalDate;

/**
 * Clase que representa una tarea con nombre, fecha límite y prioridad.
 * Permite gestionar los detalles y el estado de la tarea.
 */
public class Tarea {
    private String nombre;
    private LocalDate fecha;
    private String prioridad;
    private boolean completada;

    /**
     * Constructor para inicializar una nueva tarea.
     *
     * @param nombre    Nombre de la tarea.
     * @param fecha     Fecha límite de la tarea.
     * @param prioridad Prioridad de la tarea (ALTA, MEDIA o BAJA).
     */
    public Tarea(String nombre, LocalDate fecha, String prioridad) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.completada = false;
    }

    /**
     * Obtiene el nombre de la tarea.
     *
     * @return Nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la tarea.
     *
     * @param nombre Nombre nuevo de la tarea.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha límite de la tarea.
     *
     * @return Fecha límite de la tarea.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece una nueva fecha límite para la tarea.
     *
     * @param fecha Nueva fecha límite.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la prioridad de la tarea.
     *
     * @return Prioridad de la tarea.
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * Establece una nueva prioridad para la tarea.
     *
     * @param prioridad Nueva prioridad (ALTA, MEDIA o BAJA).
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Verifica si la tarea ha sido completada.
     *
     * @return `true` si la tarea está completada, `false` en caso contrario.
     */
    public boolean isCompletada() {
        return completada;
    }

    /**
     * Marca la tarea como completada o no completada.
     *
     * @param completada `true` para marcar la tarea como completada, `false` para no completada.
     */
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    /**
     * Representación en cadena de la tarea, incluyendo todos sus detalles.
     *
     * @return Cadena que representa la tarea con su nombre, fecha, prioridad y estado.
     */
    @Override
    public String toString() {
        return nombre + " - " + fecha + " - Prioridad: " + prioridad + (completada ? " (Completada)" : " (Pendiente)");
    }
}
