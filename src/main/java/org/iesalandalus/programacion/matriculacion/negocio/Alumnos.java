package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Alumnos {

    // Atributos
    private int capacidad;
    private int tamaño;
    private Alumno[] coleccionAlumnos;

    // Constructor
    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }

    // Método para obtener una copia profunda de la colección
    public Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copia = new Alumno[tamaño];
        for (int i = 0; i < tamaño; i++) {
            copia[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copia;
    }

    // Método para insertar un alumno
    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (tamaño >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }
        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        coleccionAlumnos[tamaño] = new Alumno(alumno);
        tamaño++;
    }

    // Método para buscar un alumno
    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            return null;
        }
        for (int i = 0; i < tamaño; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return new Alumno(coleccionAlumnos[i]);
            }
        }
        return null;
    }

    // Método para borrar un alumno
    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        desplazarIzquierda(indice);
        tamaño--;
    }

    private int buscarIndice(Alumno alumno) {
        for (int i = 0; i < tamaño; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamaño - 1; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[tamaño - 1] = null;
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
        return "Alumnos{" +
                "capacidad=" + capacidad +
                ", tamaño=" + tamaño +
                ", coleccionAlumnos=" + Arrays.toString(copiaProfundaAlumnos()) +
                '}';
    }
}
