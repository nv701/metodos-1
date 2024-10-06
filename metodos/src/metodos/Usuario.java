/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private String password;
    private String archivoUsuarios;
    private ListaDeTareas listaDeTareas;

    public Usuario(String nombre, String contraseña) {
        this.nombreUsuario = nombre;
        this.password = contraseña;
        this.archivoUsuarios = "usuarios.txt";
        this.listaDeTareas = new ListaDeTareas(nombre);
    }

    public String getNombre() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return password;
    }

    public ListaDeTareas getListaDeTareas() {
        return listaDeTareas;
    }

    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios, true))) {
            bw.write(nombreUsuario + "," + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File("usuarios.txt");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo de usuarios: " + e.getMessage());
            }
            return usuarios;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    usuarios.add(new Usuario(partes[0], partes[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}