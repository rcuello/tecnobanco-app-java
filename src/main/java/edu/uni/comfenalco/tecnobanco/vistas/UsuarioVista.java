package edu.uni.comfenalco.tecnobanco.vistas;

import java.util.List;
import java.util.Scanner;

import edu.uni.comfenalco.tecnobanco.modelo.CuentaAhorro;
import edu.uni.comfenalco.tecnobanco.modelo.Usuario;
import edu.uni.comfenalco.tecnobanco.repositorio.CuentaAhorroRepositorio;
import edu.uni.comfenalco.tecnobanco.seguridad.SesionUsuario;
import edu.uni.comfenalco.tecnobanco.servicios.DepositoServicio;
import edu.uni.comfenalco.tecnobanco.servicios.TransferenciaServicio;
import edu.uni.comfenalco.tecnobanco.util.ConsolaUtil;
import edu.uni.comfenalco.tecnobanco.util.FormateadorMoneda;

/**
 * Clase que maneja la interfaz de usuario (menú) de la aplicación.
 * Esta clase tiene la responsabilidad de mostrar todo lo relacionado al Usuario
 * del sistema.
 * 
 * ¿Por qué usar una clase de vista?
 * 1. **Separación de responsabilidades**: La lógica de la interfaz de usuario
 * se encapsula en esta clase,
 * lo que hace que el código sea más organizado y fácil de mantener.
 * 2. **Facilita cambios en la interfaz**: Si en el futuro necesitas cambiar la
 * forma en que se muestra el menú,
 * solo necesitas modificar esta clase.
 * 3. **Claridad**: El código relacionado con la presentación está separado de
 * la lógica de negocio y la lógica de servicios.
 */
public class UsuarioVista {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú principal y maneja las opciones seleccionadas por el usuario.
     * 
     */
    public static void mostrarMenu() {
        Usuario usuario = SesionUsuario.getUsuarioAutenticado();

        boolean salirDelPrograma = false;

        while (!salirDelPrograma) {
            System.out.println("+-------------------------------+");
            System.out.println("|        ** Menú Cliente **     |");
            System.out.println("+-------------------------------+");
            System.out.println("| 1. Depositar                  |");
            System.out.println("| 2. Transferir                 |");
            System.out.println("| 3. Ver saldo                  |");
            System.out.println("| 4. Información del usuario    |");
            System.out.println("| 5. Cerrar sesión              |");
            System.out.println("+-------------------------------+");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            // Hacer un salto de Linea en la pantalla
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    realizarDeposito(usuario);
                    break;
                case 2:
                    realizarTransferencia(usuario);
                    break;
                case 3:
                    mostrarSaldo(usuario);
                    break;
                case 4:
                    mostrarInformacionUsuario(usuario);
                    break;
                case 5:
                    SesionUsuario.cerrarSesion();
                    salirDelPrograma = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción del 1 al 5.");
            }
        }
    }

    /**
     * Muestra el saldo actual del usuario.
     * 
     * @param usuario El usuario autenticado.
     */
    private static void mostrarSaldo(Usuario usuario) {
        System.out.println("Su saldo actual es: " + FormateadorMoneda.formatear(usuario.getSaldo()));
        List<CuentaAhorro> cuentas = usuario.getCuentas();

        if (cuentas.isEmpty()) {
            System.out.println("No tiene cuentas de ahorro registradas.");
            return;
        }

        System.out.println("+-------------------------------+");
        System.out.println("|       ** Cuentas de Ahorro **  |");
        System.out.println("+-------------------------------+");
        System.out.println("| Número de Cuenta | Saldo       |");
        System.out.println("+-------------------------------+");

        for (CuentaAhorro cuenta : cuentas) {
            String numeroCuenta = cuenta.getNumeroCuenta();
            String saldoFormateado = FormateadorMoneda.formatear(cuenta.getSaldo());
            // Esta instruccion no se adapta
            // System.out.println("| " + numeroCuenta + " | " + saldoFormateado + " |");

            /*
             * System.out.printf es una forma de imprimir texto formateado.
             * 
             * El formato "| %-16s | %-11s |\n" se compone de:
             * - "|": Un carácter literal que se imprime tal cual.
             * - "%-16s": Especificador de formato para el número de cuenta.
             * - "%s": Indica que se imprimirá una cadena (String).
             * - "-16": Ajusta la cadena a 16 caracteres de ancho, alineada a la izquierda.
             * Si la cadena es más corta, se rellena con espacios en blanco.
             * - "|": Otro carácter literal.
             * - "%-11s": Especificador de formato para el saldo formateado.
             * - "%s": Indica que se imprimirá una cadena (String).
             * - "-11": Ajusta la cadena a 11 caracteres de ancho, alineada a la izquierda.
             * - "|": Otro carácter literal.
             * - "\n": Salto de línea al final.
             * 
             * Los argumentos "numeroCuenta" y "saldoFormateado" son los valores que se
             * insertarán
             * en los especificadores de formato (%s).
             */
            System.out.printf("| %-16s | %-11s |\n", numeroCuenta, saldoFormateado);
        }
        System.out.println("+-------------------------------+");

        String saldoTotalFormateado = FormateadorMoneda.formatear(usuario.getSaldo());
        System.out.println("Saldo total: " + saldoTotalFormateado);
    }

    /**
     * Muestra la información del usuario.
     * 
     * @param usuario El usuario autenticado.
     */
    private static void mostrarInformacionUsuario(Usuario usuario) {
        String saldoFormateado = FormateadorMoneda.formatear(usuario.getSaldo());

        System.out.println("+-------------------------------+");
        System.out.println("| ** Información del Usuario ** |");
        System.out.println("+-------------------------------+");
        System.out.println("* Nombre de usuario: " + usuario.getNombre());
        System.out.println("* ID de usuario: " + usuario.getIdentificacion());
        System.out.println("* Saldo actual: " + saldoFormateado);
        System.out.println("+-------------------------------+");
    }

    private static void realizarDeposito(Usuario usuario) {
        List<CuentaAhorro> cuentas = usuario.getCuentas();

        if (cuentas.isEmpty()) {
            System.out.println("Necesita al menos una cuenta de ahorro para realizar un deposito.");
            return;
        }
        // Solicitar la cuenta de origen
        System.out.println("Seleccione su número de cuenta: ");
        String numeroCuentaOrigen = scanner.nextLine();

        CuentaAhorro cuentaOrigen = usuario.buscarCuentaAhorro(numeroCuentaOrigen);

        if (cuentaOrigen == null) {
            System.out.println("Número de cuenta de ahorro invalido.");
            return;
        }
                
        double montoDeposito = ConsolaUtil.solicitarMontoADepositar(scanner);

        DepositoServicio.realizarDeposito(cuentaOrigen, montoDeposito);
    }

    private static void realizarTransferencia(Usuario usuario) {
        List<CuentaAhorro> cuentas = usuario.getCuentas();

        if (cuentas.size() == 0) {
            System.out.println("Necesita al menos una cuenta de ahorro para realizar una transferencia.");
            return;
        }

        // Solicitar la cuenta de origen
        System.out.println("Seleccione el número de la cuenta de origen: ");
        String numeroCuentaOrigen = scanner.nextLine();

        // Solicitar la cuenta de destino
        System.out.println("Seleccione el número de la cuenta de destino: ");
        String numeroCuentaDestino = scanner.nextLine();

        if (numeroCuentaOrigen == numeroCuentaDestino) {
            System.out.println("No puede transferir a la misma cuenta.");
            return;
        }

        // Solicitar el monto a transferir
        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();

        // Realizar la transferencia
        CuentaAhorro cuentaOrigen = usuario.buscarCuentaAhorro(numeroCuentaOrigen);

        if (cuentaOrigen == null) {
            System.out.println("Número de cuenta de ahorro origen invalido.");
            return;
        }

        CuentaAhorro cuentaDestino = CuentaAhorroRepositorio.buscarCuentaAhorro(numeroCuentaDestino);

        if (cuentaDestino == null) {
            System.out.println("Número de cuenta de ahorro destino no existe.");
            return;
        }

        TransferenciaServicio.realizarTransferencia(cuentaOrigen, cuentaDestino, monto);
    }

}
