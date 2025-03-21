package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;// Importamos la clase Usuario
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio; // Importamos la clase UsuarioRepositorio
import edu.uni.comfenalco.tecnobanco.vistas.UsuarioVista;

public class Aplicacion {

    private static Usuario usuarioAutenticado;

    public static void main(String[] args) {

        prepararCierreDelPrograma();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("+-------------------------------+");
            System.out.println("|       ** Bienvenido a         |");
            System.out.println("|        TecnoBanco **          |");
            System.out.println("+-------------------------------+");

            // Solicitar y validar el nombre de usuario
            String usuarioNombre = solicitarNombreUsuario(scanner);

            // Solicitar y validar la contraseña del usuario
            String usuarioClave = solicitarContrasenia();

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
        } catch (NoSuchElementException e) {
            // Este bloque captura la excepción NoSuchElementException, que ocurre cuando el
            // usuario
            // presiona Ctrl+C mientras el programa está esperando una entrada (por ejemplo,
            // con scanner.nextLine()).
            //
            // ¿Por qué ocurre esto?
            // - Al presionar Ctrl+C, el sistema operativo interrumpe la ejecución del
            // programa.
            // - Si esto sucede mientras el Scanner está esperando una entrada, el Scanner
            // no puede
            // encontrar la línea solicitada y lanza una NoSuchElementException.
            //
            // ¿Cómo lo manejamos?
            // - Mostramos un mensaje amigable al usuario indicando que la operación fue
            // cancelada.
            // - Esto evita que el programa termine abruptamente con un mensaje de error
            // confuso.
            System.out.println("\nOperación cancelada por el usuario.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Solicita y valida la contraseña del usuario.
     * 
     * @return La contraseña ingresada por el usuario.
     */
    private static String solicitarContrasenia() {
        // Console para leer la contraseña de manera segura.
        Console console = System.console();
        char[] passwordArray = console.readPassword("Ingrese su contraseña: ");
        return new String(passwordArray);
    }

    /**
     * Solicita y valida el nombre de usuario.
     * 
     * @param scanner Scanner para leer la entrada del usuario.
     * @return El nombre de usuario válido.
     */
    private static String solicitarNombreUsuario(Scanner scanner) {
        System.out.println("Ingrese el nombre de usuario:");
        String usuarioNombre = scanner.nextLine();

        while (usuarioNombre.trim().isEmpty()) {
            System.out.println("El nombre de usuario es requerido. Inténtelo de nuevo:");
            usuarioNombre = scanner.nextLine();
        }

        return usuarioNombre;
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
