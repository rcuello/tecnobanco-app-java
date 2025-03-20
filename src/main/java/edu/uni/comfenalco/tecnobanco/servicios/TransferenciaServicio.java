package edu.uni.comfenalco.tecnobanco.servicios;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;
import edu.uni.comfenalco.tecnobanco.util.FormateadorMoneda;

/**
 * Clase de servicio para manejar la lógica relacionada con las transferencias.
 * 
 * ¿Por qué usar una clase de servicio?
 * 1. **Separación de responsabilidades**: La lógica de transferencias se encapsula en esta clase,
 *    lo que permite que el código sea más modular y fácil de entender.
 * 2. **Reutilización**: Si en el futuro necesitas realizar transferencias desde diferentes partes
 *    de la aplicación, solo necesitas llamar a esta clase en lugar de repetir el código.
 * 3. **Facilita las pruebas**: Al tener la lógica separada, es más fácil probar (unit testing)
 *    las operaciones de transferencia sin necesidad de interactuar con otras partes del sistema.
 */
public class TransferenciaServicio {
    /**
     * Realiza una transferencia desde la cuenta del usuario.
     * 
     * @param usuario El usuario que realiza la transferencia.
     * @param monto   El monto a transferir. Debe ser mayor que 0 y no exceder el saldo disponible.
     */
    public static void realizarTransferencia(Usuario usuario, double monto) {
        if (monto > 0) {
            if (monto <= usuario.getSaldo()) {
                double nuevoSaldo = usuario.getSaldo() - monto;
                usuario.setSaldo(nuevoSaldo);
                String saldoFormateado = FormateadorMoneda.formatear(usuario.getSaldo());
                System.out.println("Transferencia realizada con éxito. Su nuevo saldo es: " + saldoFormateado);
            } else {
                System.out.println("Saldo insuficiente para realizar la transferencia.");
            }
        } else {
            System.out.println("El monto debe ser mayor que 0.");
        }
    }
}
