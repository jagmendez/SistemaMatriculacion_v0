package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    // Valores del enumerado
    GDCFGB("Grado D Ciclo Formativo de Grado Básico"),
    GDCFGM("Grado D Ciclo Formativo de Grado Medio"),
    GDCFGS("Grado D Ciclo Formativo de Grado Superior"),
    ;

    // Atributo para almacenar la cadena a mostrar
    private final String cadenaAMostrar;

    // Constructor
    private Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Método imprimir
    public String imprimir() {
        return this.ordinal() + ".-" + this.cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Grado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
