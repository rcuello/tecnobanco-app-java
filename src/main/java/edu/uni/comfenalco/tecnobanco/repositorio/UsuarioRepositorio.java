package edu.uni.comfenalco.tecnobanco.repositorio;

import java.util.ArrayList;
import java.util.List;

import edu.uni.comfenalco.tecnobanco.modelo.CuentaAhorro;
import edu.uni.comfenalco.tecnobanco.modelo.RolUsuario;
import edu.uni.comfenalco.tecnobanco.modelo.Usuario;

public class UsuarioRepositorio {
    private static Usuario[] LISTA_USUARIOS;

    // Inicializamos la lista de usuarios
    static {
        LISTA_USUARIOS = obtenerListaUsuarios();
    }

    // Método para buscar el usuario a traves de su nombre y clave
    public static Usuario obtenerUsuarioAutenticado(String nombreUsuario, String claveUsuario) {
        // recorremos la lista de usuarios (usamos foreach)
        for (Usuario usuario : LISTA_USUARIOS) {
            // Verificar si el usuario y la clave coinciden
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getClave().equals(claveUsuario)) {
                // Si el usuario y la clave coinciden, retornamos el usuario
                return usuario;
            }
        }

        // Si no se encuentra el usuario, retornamos null
        return null;
    }

    private static Usuario[] obtenerListaUsuarios() {
        Usuario[] usuarios = new Usuario[5];
        // El banco regala $20,000 a cada cliente al abrir una cuenta
        double saldoInicial = 20000;
        usuarios[0] = crearUsuario("usuario", "1234", "USR001", RolUsuario.CLIENTE, "001", saldoInicial);
        usuarios[1] = crearUsuario("cliente", "1234", "USR002", RolUsuario.CLIENTE, "002", saldoInicial);
        usuarios[2] = crearUsuario("cliente2", "1234", "USR003", RolUsuario.CLIENTE, "003", saldoInicial);

        usuarios[3] = crearUsuario("asesor", "1234", "ASE001", RolUsuario.ASESOR_FINANCIERO, null, 0);
        usuarios[4] = crearUsuario("asesor2", "1234", "ASE002", RolUsuario.ASESOR_FINANCIERO, null, 0);
        return usuarios;
    }

    private static Usuario crearUsuario(String nombre, String clave, String identificacion, RolUsuario rol,
            String numeroCuentaAhorro, double saldoInicial) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setClave(clave);
        usuario.setIdentificacion(identificacion);
        usuario.setRol(rol);

        // Solo los clientes del Banco tienen Cuentas de Ahorro
        if (rol == RolUsuario.CLIENTE) {
            CuentaAhorro cuentaAhorro = new CuentaAhorro(numeroCuentaAhorro, saldoInicial);
            usuario.agregarCuentaAhorro(cuentaAhorro);
        }

        return usuario;
    }

    public static List<Usuario> obtenerUsuariosClientes() {
        List<Usuario> usuariosClientes = new ArrayList<Usuario>();
        for (Usuario usuario : LISTA_USUARIOS) {
            if(usuario.getRol() == RolUsuario.CLIENTE){
                usuariosClientes.add(usuario);
            }
        }

        return usuariosClientes;
    }

    public static Usuario[] getLISTA_USUARIOS() {
        return LISTA_USUARIOS;
    }

}
