package edu.uni.comfenalco.tecnobanco.util; // Asegúrate de que el paquete sea correcto

import java.text.NumberFormat;
import java.util.Locale;

public class FormateadorMoneda {

    public static String formatear(double cantidad) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return formatoMoneda.format(cantidad);
    }
}