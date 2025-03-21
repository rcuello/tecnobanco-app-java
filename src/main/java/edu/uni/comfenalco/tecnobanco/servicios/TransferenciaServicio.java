package edu.uni.comfenalco.tecnobanco.servicios;

import edu.uni.comfenalco.tecnobanco.modelo.CuentaAhorro;
import edu.uni.comfenalco.tecnobanco.util.FormateadorMoneda;

/**
 * Clase de servicio para manejar la lógica relacionada con las transferencias.
 * 
 */
public class TransferenciaServicio {

    public static void realizarTransferencia(CuentaAhorro cuentaOrigen, CuentaAhorro cuentaDestino, double monto) {

        if (cuentaOrigen == null || cuentaDestino == null) {
            System.out.println("Error: Las cuentas de origen y destino no pueden ser nulas.");
            return;
        }

        if (monto <= 0) {
            System.out.println("Error: El monto a transferir debe ser mayor que 0.");
            return;
        }

        if (cuentaOrigen.getSaldo() < monto) {
            System.out.println("Error: Saldo insuficiente en la cuenta de origen.");
            return;
        }

        // Realizar la transferencia
        cuentaOrigen.retirar(monto);
        cuentaDestino.depositar(monto);

        System.out.println("Transferencia realizada con éxito.");
        System.out.println("Nuevo saldo de la cuenta de origen (" + cuentaOrigen.getNumeroCuenta() + "): "
                + FormateadorMoneda.formatear(cuentaOrigen.getSaldo()));
    }
}
