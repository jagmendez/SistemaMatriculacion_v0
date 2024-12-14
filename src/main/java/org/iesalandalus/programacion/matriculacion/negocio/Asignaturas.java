package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Asignaturas {
    // Atributos
    private int capacidad;
    private int tamaño;
    private Asignatura[] coleccionAsignaturas;

    // Constructor
    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    // Método para obtener una copia profunda de la colección
    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[tamaño];
        for (int i = 0; i < tamaño; i++) {
            copia[i] = new Asignatura(coleccionAsignaturas[i]);
        }
        return copia;
    }

    // Método para insertar una asignatura
    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (tamaño >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
        coleccionAsignaturas[tamaño] = new Asignatura(asignatura);
        tamaño++;
    }

    // Método para buscar una asignatura
    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }
        for (int i = 0; i < tamaño; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return new Asignatura(coleccionAsignaturas[i]);
            }
        }
        return null;
    }

    // Método para borrar una asignatura
    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        desplazarIzquierda(indice);
        tamaño--;
    }

    private int buscarIndice(Asignatura asignatura) {
        for (int i = 0; i < tamaño; i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamaño - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamaño - 1] = null;
    }

    // Método para obtener el tamaño actual de la colección
    public int getTamano() {
        return tamaño;
    }

    // Método para obtener la capacidad máxima de la colección
    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Asignaturas{" +
                "capacidad=" + capacidad +
                ", tamaño=" + tamaño +
                ", coleccionAsignaturas=" + Arrays.toString(copiaProfundaAsignaturas()) +
                '}';
    }
}
