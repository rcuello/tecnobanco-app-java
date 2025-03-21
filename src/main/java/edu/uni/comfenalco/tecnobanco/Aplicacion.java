package edu.uni.comfenalco.tecnobanco;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.RolUsuario;
import edu.uni.comfenalco.tecnobanco.modelo.Usuario;
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio;
import edu.uni.comfenalco.tecnobanco.seguridad.SesionUsuario;
import edu.uni.comfenalco.tecnobanco.util.ConsolaDialogo;
import edu.uni.comfenalco.tecnobanco.util.ConsolaUtil;
import edu.uni.comfenalco.tecnobanco.vistas.AutorVista;
import edu.uni.comfenalco.tecnobanco.vistas.UsuarioAsesorFinancieroVista;
import edu.uni.comfenalco.tecnobanco.vistas.UsuarioVista;

public class Aplicacion {    

    public static void main(String[] args) {

        prepararCierreDelPrograma();

        // Utilizamos try-with-resources para asegurar que el Scanner se cierre
        // automáticamente
        try (Scanner scanner = new Scanner(System.in)) {
            boolean ejecutar = true;

            while (ejecutar) {
                ConsolaDialogo.mostrarBienvenida();
                System.out.print("Seleccione una opción: ");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        // Flujo de inicio de sesión
                        iniciarSesion(scanner);
                        break;
                    case "2":
                        // Flujo para ver información de los autores
                        verAutores(scanner);
                        break;
                    case "3":
                        System.out.println("Gracias por usar TecnoBanco.");
                        ejecutar = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                        // Pequeña pausa para que el usuario pueda leer el mensaje
                        ConsolaUtil.esperarTecla(scanner);
                }
                // Limpiamos la pantalla para la siguiente iteración (solo si seguimos
                // ejecutando)
                if (ejecutar) {
                    ConsolaUtil.limpiarPantalla();
                }
            } // fin ciclo while

        } catch (NoSuchElementException e) {
            // Esta excepción ocurre cuando se interrumpe la entrada (Ctrl+C)
            System.out.println("\nOperación cancelada por el usuario.");
        } catch(IllegalArgumentException e){
            System.out.println("Ocurrió un error de validación: " + e.getMessage());
        }
        catch (Exception e) {
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
        String usuarioNombre = ConsolaUtil.solicitarNombreUsuario(scanner);

        // Solicitar y validar la contraseña del usuario
        String usuarioClave = ConsolaUtil.solicitarContrasenia(scanner);

        Usuario usuarioAutenticado = UsuarioRepositorio.obtenerUsuarioAutenticado(usuarioNombre, usuarioClave);

        if (usuarioAutenticado != null) {
            ConsolaDialogo.mostrarInicioSesionExitoso();

            SesionUsuario.guardarUsuarioAutenticado(usuarioAutenticado);

            RolUsuario rolUsuario = SesionUsuario.getUsuarioAutenticado().getRol();

            switch (rolUsuario) {
                case CLIENTE:
                    // Llamamos a la vista del menú para manejar la interacción con el usuario
                    // "Cliente".
                    UsuarioVista.mostrarMenu();
                    break;

                case ASESOR_FINANCIERO:
                    // Llamamos a la vista del menú para manejar la interacción con el usuario
                    // "Asesor Financiero".
                    UsuarioAsesorFinancieroVista.mostrarMenu();
                    break;

                default:
                    ConsolaDialogo.mostrarErrorRolDesconocido();
                    break;
            }

        } else {
            ConsolaDialogo.mostrarCredencialesInvalidas();
            ConsolaUtil.esperarTecla(scanner);
        }
    }

    /**
     * Muestra información sobre los autores del sistema.
     * 
     * @param scanner Scanner para leer la entrada del usuario
     */
    private static void verAutores(Scanner scanner) {
        // Llamamos a la vista de autores
        AutorVista.mostrarInformacion();

        // Esperamos a que el usuario presione una tecla para volver al menú principal
        System.out.println("\nPresione Enter para volver al menú principal...");
        scanner.nextLine();
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
