package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;// Importamos la clase Usuario
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio; // Importamos la clase UsuarioRepositorio
import edu.uni.comfenalco.tecnobanco.vistas.UsuarioVista;

public class Aplicacion {

    private static Usuario usuarioAutenticado;

    public static void main(String[] args) {

        prepararCierreDelPrograma();

        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        try {
            System.out.println("+-------------------------------+");
            System.out.println("|       ** Bienvenido a         |");
            System.out.println("|        TecnoBanco **          |");
            System.out.println("+-------------------------------+");

            System.out.println("Ingrese el nombre de usuario:");

            String usuarioNombre = scanner.nextLine();

            while (usuarioNombre.trim().isEmpty()) {
                System.out.println("El nombre de usuario es requerido. Inténtelo de nuevo:");
                usuarioNombre = scanner.nextLine();
            }

            char[] passwordArray = console.readPassword("Ingrese su contraseña: ");
            String usuarioClave = new String(passwordArray);

            usuarioAutenticado = UsuarioRepositorio.obtenerUsuarioAutenticado(usuarioNombre, usuarioClave);

            if (usuarioAutenticado != null) {
                System.out.println(" ===============================");
                System.out.println("|  OPERACION COMPLETADA         |");
                System.out.println("|  Ha iniciado sesión con éxito |");
                System.out.println(" ===============================");

                // Llamamos a la vista del menú para manejar la interacción con el usuario.
                UsuarioVista.mostrarMenu(usuarioAutenticado);

            } else {
                System.out.println("Credenciales invalidas!");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /*
     * Al igual que un "conserje" que cierra las puertas y apaga las luces al final
     * del día,
     * este método prepara el programa para realizar tareas de limpieza y despedida
     * antes de terminar.
     * Sirve para limpiar recursos importantes (como conexiones a bases de datos o
     * archivos abiertos)
     * y para mostrar un mensaje de despedida.
     *
     * La tarea de limpieza se ejecuta una sola vez, incluso si el programa recibe
     * múltiples señales de cierre.
     *
     * La acción se ejecuta en diversas situaciones de cierre, incluyendo:
     * - Cierre normal o forzado (Ctrl+C).
     * - Apagado del sistema.
     * - Errores graves.
     */
    private static void prepararCierreDelPrograma() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nCerrando TecnoBanco...");
            // Aquí puedes agregar lógica para cerrar conexiones, guardar datos, etc.
            System.out.println("¡Hasta pronto!");
        }));
    }
}
