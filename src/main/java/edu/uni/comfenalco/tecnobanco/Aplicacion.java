package edu.uni.comfenalco.tecnobanco;

import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio;
import edu.uni.comfenalco.tecnobanco.vistas.UsuarioVista;

public class Aplicacion {

    private static Usuario usuarioAutenticado;

    public static void main(String[] args) {

        prepararCierreDelPrograma();

        // Utilizamos try-with-resources para asegurar que el Scanner se cierre
        // automáticamente
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("+-------------------------------+");
            System.out.println("|       ** Bienvenido a         |");
            System.out.println("|        TecnoBanco **          |");
            System.out.println("+-------------------------------+");

            iniciarSesion(scanner);

        } catch (NoSuchElementException e) {
            // Esta excepción ocurre cuando se interrumpe la entrada (Ctrl+C)
            System.out.println("\nOperación cancelada por el usuario.");
        } catch (Exception e) {
            // Capturamos cualquier otra excepción no esperada
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());

            // Útil durante el desarrollo y depuración
            e.printStackTrace();
        }
    }

    /**
     * Gestiona el flujo de inicio de sesión.
     * Solicita credenciales y muestra el menú de usuario si la autenticación es
     * exitosa.
     * 
     * @param scanner Scanner para leer la entrada del usuario
     */
    private static void iniciarSesion(Scanner scanner) {
        // Solicitar y validar el nombre de usuario
        String usuarioNombre = solicitarNombreUsuario(scanner);

        // Solicitar y validar la contraseña del usuario
        String usuarioClave = solicitarContrasenia(scanner);

        usuarioAutenticado = UsuarioRepositorio.obtenerUsuarioAutenticado(usuarioNombre, usuarioClave);

        if (usuarioAutenticado != null) {
            System.out.println(" ===============================");
            System.out.println("|  OPERACION COMPLETADA         |");
            System.out.println("|  Ha iniciado sesión con éxito |");
            System.out.println(" ===============================");

            // Llamamos a la vista del menú para manejar la interacción con el usuario.
            UsuarioVista.mostrarMenu(usuarioAutenticado);

        } else {
            System.out.println("/////////////////////////////////");
            System.out.println("//   ERROR: ACCESO DENEGADO    //");
            System.out.println("//   Credenciales inválidas    //");
            System.out.println("/////////////////////////////////");
            esperarTecla(scanner);
        }
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     * Útil para mostrar mensajes antes de continuar.
     * 
     * @param scanner Scanner para leer la entrada del usuario
     */
    private static void esperarTecla(Scanner scanner) {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Solicita y valida la contraseña del usuario.
     * Intenta utilizar Console para ocultar la entrada, pero si no está disponible
     * utiliza Scanner como alternativa.
     * 
     * @param scanner Scanner alternativo si Console no está disponible
     * @return La contraseña ingresada por el usuario
     */
    private static String solicitarContrasenia(Scanner scanner) {
        // Intentamos obtener la consola del sistema
        Console console = System.console();

        if (console != null) {
            // Si la consola está disponible, usamos readPassword para ocultar la entrada
            char[] passwordArray = console.readPassword("Ingrese su contraseña: ");
            return new String(passwordArray);
        } else {
            // Si la consola no está disponible (por ejemplo, en IDEs), usamos Scanner
            System.out.println("Ingrese su contraseña: ");
            System.out.println("(Nota: la contraseña será visible debido a limitaciones del entorno)");
            return scanner.nextLine();
        }
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

        while (usuarioNombre == null || usuarioNombre.trim().isEmpty()) {
            System.out.println("El nombre de usuario es requerido. Inténtelo de nuevo:");
            usuarioNombre = scanner.nextLine();
        }

        return usuarioNombre;
    }

    /**
     * Registra una tarea que se ejecutará cuando la aplicación se cierre.
     * 
     * Esto es útil para:
     * 1. Liberar recursos (conexiones a bases de datos, archivos, etc.)
     * 2. Guardar datos pendientes
     * 3. Mostrar un mensaje de despedida
     * 
     * La tarea se ejecutará en diferentes situaciones:
     * - Cuando el programa termina normalmente
     * - Cuando se interrumpe con Ctrl+C
     * - Cuando el sistema se apaga
     * - En caso de errores graves
     */
    private static void prepararCierreDelPrograma() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nCerrando TecnoBanco...");
            // Aquí podríamos agregar código para:
            // - Cerrar conexiones a bases de datos
            // - Guardar información en archivos
            // - Liberar otros recursos
            System.out.println("¡Gracias por usar TecnoBanco. Hasta pronto!");
        }));
    }
}
