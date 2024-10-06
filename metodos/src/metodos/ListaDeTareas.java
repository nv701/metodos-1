/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeTareas {
    private String nombreDeUsuario;
    private List<Tarea> tareasPersonales;
    private String nombreArchivoTareas;

    public ListaDeTareas(String nombreUsuario) {
        this.nombreDeUsuario = nombreUsuario;
        this.tareasPersonales = new ArrayList<>();
        this.nombreArchivoTareas = nombreUsuario + "_tareas.txt";
        cargarTareas();
    }

    public void agregarTarea(Tarea tarea) {
        tareasPersonales.add(tarea);
        guardarTareas();
    }

    public void eliminarTarea(Tarea tarea) {
        tareasPersonales.remove(tarea);
        guardarTareas();
    }

    public List<Tarea> getTareas() {
        return tareasPersonales;
    }

    public void guardarTareas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoTareas))) {
            for (Tarea tarea : tareasPersonales) {
                bw.write(tarea.getNombre() + "," + tarea.getFecha() + "," + tarea.getPrioridad() + ","
                        + tarea.isCompletada());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tareas: " + e.getMessage());
        }
    }

    private void cargarTareas() {
        File archivo = new File(nombreArchivoTareas);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    LocalDate fecha = LocalDate.parse(partes[1]);
                    String prioridad = partes[2];
                    boolean completada = Boolean.parseBoolean(partes[3]);
                    Tarea tarea = new Tarea(nombre, fecha, prioridad);
                    tarea.setCompletada(completada);
                    tareasPersonales.add(tarea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tareas: " + e.getMessage());
        }
    }
}