package edu.uni.comfenalco.tecnobanco.vistas;

import java.util.List;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.CuentaAhorro;
import edu.uni.comfenalco.tecnobanco.modelo.Usuario;
import edu.uni.comfenalco.tecnobanco.repositorio.UsuarioRepositorio;
import edu.uni.comfenalco.tecnobanco.seguridad.SesionUsuario;
import edu.uni.comfenalco.tecnobanco.util.FormateadorMoneda;

public class UsuarioAsesorFinancieroVista {
    private static Scanner scanner = new Scanner(System.in);
    // Constantes para mensajes y formatos
    private static final String SEPARADOR = "+-----------------+----------------------+------------------+-------------+";
    private static final String FORMATO_TABLA = "| %-15s | %-20s | %-16s | %-11s |\n";
    private static final String MENSAJE_SIN_CUENTAS = "| ** El cliente no tiene cuentas de ahorro registradas. ** |";
    private static final String ENCABEZADO_TABLA = "| ID             | Cliente               | Número de Cuenta | Saldo       |";

    public static void mostrarMenu() {
        Usuario usuario = SesionUsuario.getUsuarioAutenticado();

        boolean salirDelPrograma = false;

        while (!salirDelPrograma) {
            System.out.println("+--------------------------------+");
            System.out.println("|    ** Menú Asesor Financiero **|");
            System.out.println("+-------------------------------+");
            System.out.println("| 1. Ver clientes                |");
            System.out.println("| 2. Generar reportes            |");
            System.out.println("| 3. Ver información del usuario |");
            System.out.println("| 4. Cerrar sesión               |");
            System.out.println("+--------------------------------+");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            // Hacer un salto de Linea en la pantalla
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarClientes();
                    break;
                case 2:
                    System.out.println("Generando reportes...");
                    // Lógica para generar reportes
                    break;
                case 3:
                    mostrarInformacionUsuario(usuario);
                    break;
                case 4:
                    SesionUsuario.cerrarSesion();
                    salirDelPrograma = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción del 1 al 4.");
            }
        }
    }

    private static void mostrarInformacionUsuario(Usuario usuario) {
        System.out.println("+-------------------------------+");
        System.out.println("| ** Información del Asesor  ** |");
        System.out.println("+-------------------------------+");
        System.out.println("* Nombre de usuario: " + usuario.getNombre());
        System.out.println("* ID de usuario: " + usuario.getIdentificacion());
        System.out.println("+-------------------------------+");
    }

    private static void mostrarClientes() {
        List<Usuario> usuarios = UsuarioRepositorio.obtenerUsuariosClientes();

        if (usuarios.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println(SEPARADOR);
        System.out.println(ENCABEZADO_TABLA);
        System.out.println(SEPARADOR);

        for (Usuario usuario : usuarios) {
            List<CuentaAhorro> cuentas = usuario.getCuentas();

            if (cuentas.isEmpty()) {
                // Mostrar una fila indicando que el cliente no tiene cuentas
                System.out.printf(FORMATO_TABLA, usuario.getIdentificacion(), usuario.getNombre(), "N/A", "N/A");
                System.out.println(MENSAJE_SIN_CUENTAS);
                System.out.println(SEPARADOR);
            } else {
                // Mostrar cada cuenta del cliente en una fila
                for (CuentaAhorro cuenta : cuentas) {
                    String saldoFormateado = FormateadorMoneda.formatear(cuenta.getSaldo());
                    System.out.printf(FORMATO_TABLA, usuario.getIdentificacion(), usuario.getNombre(), cuenta.getNumeroCuenta(), saldoFormateado);
                }
                System.out.println(SEPARADOR);
            }
        }
    }
}
