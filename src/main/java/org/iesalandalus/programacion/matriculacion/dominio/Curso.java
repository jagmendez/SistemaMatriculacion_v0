package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    // Valores del enumerado
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    // Atributo para almacenar la cadena a mostrar
    private final String cadenaAMostrar;

    // Constructor
    private Curso(String cadenaAMostrar) {
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
