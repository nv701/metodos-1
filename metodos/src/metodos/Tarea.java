/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.time.LocalDate;

public class Tarea {
    private String nombreTarea;
    private LocalDate fechaTarea;
    private String prioridadTarea;
    private boolean estadoCompletada;

    public Tarea(String nombre, LocalDate fecha, String prioridad) {
        this.nombreTarea = nombre;
        this.fechaTarea = fecha;
        this.prioridadTarea = prioridad;
        this.estadoCompletada = false;
    }

    public String getNombre() {
        return nombreTarea;
    }

    public void setNombre(String nombre) {
        this.nombreTarea = nombre;
    }

    public LocalDate getFecha() {
        return fechaTarea;
    }

    public void setFecha(LocalDate fecha) {
        this.fechaTarea = fecha;
    }

    public String getPrioridad() {
        return prioridadTarea;
    }

    public void setPrioridad(String prioridad) {
        this.prioridadTarea = prioridad;
    }

    public boolean isCompletada() {
        return estadoCompletada;
    }

    public void setCompletada(boolean completada) {
        this.estadoCompletada = completada;
    }

    @Override
    public String toString() {
        return "          # " + nombreTarea + "  " + "   [ FECHA: " + fechaTarea + " ]   " + "   [ PRIORIDAD "
                + prioridadTarea + "]   " + "   [ TAREA" + (estadoCompletada ? " COMPLETADA" : " NO COMPLETADA")
                + "]   ";
    }
}