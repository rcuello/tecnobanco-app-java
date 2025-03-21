package edu.uni.comfenalco.tecnobanco.repositorio;

import edu.uni.comfenalco.tecnobanco.modelo.CuentaAhorro;
import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

public class CuentaAhorroRepositorio {
    public static CuentaAhorro buscarCuentaAhorro(String numeroCuenta){
        Usuario[] usuarios = UsuarioRepositorio.getLISTA_USUARIOS();

        for(Usuario usuario : usuarios){
            for(CuentaAhorro cuenta : usuario.getCuentas()){
                if(cuenta.getNumeroCuenta().equals(numeroCuenta)){
                    return cuenta;
                }
            }
        }
        return null;
    }
}
