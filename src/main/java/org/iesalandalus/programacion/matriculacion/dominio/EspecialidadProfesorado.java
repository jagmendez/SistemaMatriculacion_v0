package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
    // Valores del enumerado
    INFORMATICA("Informática"),
    SISTEMAS("Sistemas y Aplicaciones Informáticas"),
    FOL("Formación y Orientación Laboral");

    // Atributo para almacenar la cadena a mostrar
    private final String cadenaAMostrar;

    // Constructor
    private EspecialidadProfesorado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Método imprimir
    public String imprimir() {
        return this.ordinal() + ".-" + this.cadenaAMostrar;
    }

    // Método toString
    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
