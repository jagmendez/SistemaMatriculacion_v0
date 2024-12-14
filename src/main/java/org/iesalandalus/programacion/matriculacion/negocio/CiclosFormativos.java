package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class CiclosFormativos {
    // Atributos
    private int capacidad;
    private int tamaño;
    private CicloFormativo[] coleccionCiclosFormativos;

    // Constructor
    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    // Método para obtener una copia profunda de la colección
    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copia = new CicloFormativo[tamaño];
        for (int i = 0; i < tamaño; i++) {
            copia[i] = new CicloFormativo(coleccionCiclosFormativos[i]);
        }
        return copia;
    }

    // Método para insertar un ciclo formativo
    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        if (tamaño >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }
        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos[tamaño] = new CicloFormativo(cicloFormativo);
        tamaño++;
    }

    // Método para buscar un ciclo formativo
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            return null;
        }
        for (int i = 0; i < tamaño; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return new CicloFormativo(coleccionCiclosFormativos[i]);
            }
        }
        return null;
    }

    // Método para borrar un ciclo formativo
    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        desplazarIzquierda(indice);
        tamaño--;
    }

    private int buscarIndice(CicloFormativo cicloFormativo) {
        for (int i = 0; i < tamaño; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < tamaño - 1; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[tamaño - 1] = null;
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
        return "CiclosFormativos{" +
                "capacidad=" + capacidad +
                ", tamaño=" + tamaño +
                ", coleccionCiclosFormativos=" + Arrays.toString(copiaProfundaCiclosFormativos()) +
                '}';
    }
}
