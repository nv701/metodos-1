package metodos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un usuario con nombre y contraseña.
 * Permite gestionar la lista de tareas del usuario.
 */
public class Usuario implements Serializable {
    private String nombre;
    private String contraseña;
    private ListaDeTareas listaDeTareas;

    private static final long serialVersionUID = 1L;

    /**
     * Constructor para inicializar un nuevo usuario con su nombre y contraseña.
     *
     * @param nombre      Nombre del usuario.
     * @param contraseña  Contraseña del usuario.
     */
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.listaDeTareas = new ListaDeTareas();
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para el usuario.
     *
     * @param nombre Nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return Contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece una nueva contraseña para el usuario.
     *
     * @param contraseña Nueva contraseña del usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene la lista de tareas del usuario.
     *
     * @return Lista de tareas del usuario.
     */
    public ListaDeTareas getListaDeTareas() {
        return listaDeTareas;
    }

    /**
     * Guarda los usuarios en un archivo binario.
     */
    public void guardarUsuarios() {
        List<Usuario> usuarios = cargarUsuarios();
        usuarios.add(this);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usuarios.bin"))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los usuarios desde un archivo binario.
     *
     * @return Lista de usuarios almacenados en el archivo.
     */
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("usuarios.bin"))) {
            usuarios = (List<Usuario>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de usuarios.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
