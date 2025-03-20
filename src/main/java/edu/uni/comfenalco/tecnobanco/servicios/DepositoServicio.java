package edu.uni.comfenalco.tecnobanco.servicios;

import edu.uni.comfenalco.tecnobanco.modelo.Usuario;
import edu.uni.comfenalco.tecnobanco.util.FormateadorMoneda;

/**
 * Clase de servicio para manejar la lógica relacionada con los depósitos.
 * 
 * ¿Por qué usar una clase de servicio?
 * 1. **Separación de responsabilidades**: La lógica de depósitos se encapsula en esta clase,
 *    lo que hace que el código sea más organizado y fácil de mantener.
 * 2. **Reutilización**: Si en el futuro necesitas realizar depósitos desde diferentes partes
 *    de la aplicación, solo necesitas llamar a esta clase en lugar de repetir el código.
 * 3. **Facilita las pruebas**: Al tener la lógica separada, es más fácil probar (unit testing)
 *    las operaciones de depósito sin necesidad de interactuar con otras partes del sistema.
 */
public class DepositoServicio {
    /**
     * Realiza un depósito en la cuenta del usuario.
     * 
     * @param usuario El usuario al que se le realizará el depósito.
     * @param monto   El monto a depositar. Debe ser mayor que 0.
     */
    public static void realizarDeposito(Usuario usuario, double monto) {
        if (monto > 0) {
            double nuevoSaldo = usuario.getSaldo() + monto;
            usuario.setSaldo(nuevoSaldo);
            String saldoFormateado = FormateadorMoneda.formatear(usuario.getSaldo());
            System.out.println("Depósito realizado con éxito. Su nuevo saldo es: " + saldoFormateado);
        } else {
            System.out.println("El monto debe ser mayor que 0.");
        }
    }
}
