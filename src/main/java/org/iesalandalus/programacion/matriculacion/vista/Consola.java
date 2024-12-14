package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public final class Consola {

    // Constructor privado para evitar instanciación
    private Consola() {
        throw new IllegalStateException("No se puede instanciar la clase de utilidades Consola.");
    }

    // Método para mostrar el menú
    public static void mostrarMenu() {
        System.out.println("***********************************");
        System.out.println("******** Menú de opciones: ********");
        System.out.println("***********************************");
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    // Método para elegir una opción
    public static Opcion elegirOpcion() {
        mostrarMenu();
        int opcionElegida;
        do {
            System.out.print("Introduce el número de la opción: ");
            opcionElegida = Entrada.entero();
        } while (opcionElegida < 0 || opcionElegida >= Opcion.values().length);
        return Opcion.values()[opcionElegida];
    }

    // Método para leer un alumno completo
    public static Alumno leerAlumno() {
        System.out.print("Introduce el nombre del alumno: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();
        System.out.print("Introduce el correo del alumno: ");
        String correo = Entrada.cadena();
        System.out.print("Introduce el teléfono del alumno: ");
        String telefono = Entrada.cadena();
        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento (formato: dd/MM/yyyy): ");
        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    // Método para obtener un alumno por DNI
    public static Alumno getAlumnoPorDni() {
        System.out.print("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();
        return new Alumno("Ficticio", dni, "ficticio@dominio.com", "600000000", LocalDate.of(2000, 1, 1));
    }

    // Método para leer una fecha
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.print(mensaje);
                String fechaTexto = Entrada.cadena();
                fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. Intenta nuevamente.");
            }
        }
        return fecha;
    }

    // Método para leer un grado
    public static Grado leerGrado() {
        System.out.println("Grados disponibles:");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.ordinal() + " .- " + grado);
        }
        System.out.print("Selecciona el número del grado: ");
        int opcion = Entrada.entero();
        return Grado.values()[opcion];
    }

    // Método para leer un ciclo formativo
    public static CicloFormativo leerCicloFormativo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        System.out.print("Introduce el nombre del ciclo formativo: ");
        String nombre = Entrada.cadena();
        Grado grado = leerGrado();
        System.out.print("Introduce las horas del ciclo formativo: ");
        int horas = Entrada.entero();
        System.out.print("Introduce la familia profesional: ");
        String familiaProfesional = Entrada.cadena();
        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    // Método para mostrar ciclos formativos
    public static void mostrarCiclosFormativos(CicloFormativo[] ciclos) {
        if (ciclos.length == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            for (CicloFormativo ciclo : ciclos) {
                if (ciclo != null) {
                    System.out.println(ciclo);
                }
            }
        }
    }

    // Método para obtener un ciclo formativo por código
    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        return new CicloFormativo(codigo, "Ficticia", Grado.GDCFGM, "Ficticio", 1000);
    }

    // Método para leer un curso
    public static Curso leerCurso() {
        System.out.println("Cursos disponibles:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso);
        }
        Curso curso = null;
        do {
            try {
                System.out.print("Selecciona un curso: ");
                String cursoTexto = Entrada.cadena().toUpperCase();
                curso = Curso.valueOf(cursoTexto);
            } catch (IllegalArgumentException e) {
                System.out.println("Curso no válido. Inténtalo de nuevo.");
            }
        } while (curso == null);
        return curso;
    }

    // Método para leer una especialidad del profesorado
    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Especialidades disponibles:");
        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad);
        }
        EspecialidadProfesorado especialidad = null;
        do {
            try {
                System.out.print("Selecciona una especialidad: ");
                String especialidadTexto = Entrada.cadena().toUpperCase();
                especialidad = EspecialidadProfesorado.valueOf(especialidadTexto);
            } catch (IllegalArgumentException e) {
                System.out.println("Especialidad no válida. Inténtalo de nuevo.");
            }
        } while (especialidad == null);
        return especialidad;
    }

    // Método para leer una asignatura
    public static Asignatura leerAsignatura(CicloFormativo ciclo) {
        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();
        System.out.print("Introduce el nombre de la asignatura: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce las horas de la asignatura: ");
        int horas = Entrada.entero();
        Curso curso = leerCurso();
        System.out.print("Introduce las horas de desdoble: ");
        int horasDesdoble = Entrada.entero();
        EspecialidadProfesorado especialidad = leerEspecialidadProfesorado();
        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidad, ciclo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();
        return new Asignatura(codigo, "Nombre Ficticio", 100, Curso.PRIMERO, 0, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1, "Familia Ficticia", Grado.GDCFGS, "Nombre Ficticio", 1000));
    }

    // Método para mostrar asignaturas
    public static void mostrarAsignaturas(Asignatura[] asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }
        }
    }

    public static boolean asignaturaYaMatriculada(Asignatura[] asignaturas, Asignatura asignatura) {
        for (Asignatura a : asignaturas) {
            if (a != null && a.equals(asignatura)) {
                return true;
            }
        }
        return false;
    }

    // Método para leer una matrícula
    public static Matricula leerMatricula(Alumno alumno, Asignatura[] asignaturas) {
        System.out.print("Introduce el identificador de la matrícula: ");
        int id = Entrada.entero();
        System.out.print("Introduce el curso académico (e.g., 24-25): ");
        String cursoAcademico = Entrada.cadena();
        LocalDate fechaMatriculacion = leerFecha("Introduce la fecha de matriculación (formato: dd/MM/yyyy): ");

        Asignatura[] asignaturasSeleccionadas = new Asignatura[5];
        int contador = 0;

        while (true) {
            mostrarAsignaturas(asignaturas);
            System.out.print("Selecciona el código de una asignatura o escribe 'fin' para terminar: ");
            String codigo = Entrada.cadena();
            if (codigo.equalsIgnoreCase("fin")) break;

            Asignatura asignaturaSeleccionada = null;
            for (Asignatura asignatura : asignaturas) {
                if (asignatura != null && asignatura.getCodigo().equals(codigo)) {
                    asignaturaSeleccionada = asignatura;
                    break;
                }
            }

            if (asignaturaSeleccionada != null) {
                if (!asignaturaYaMatriculada(asignaturasSeleccionadas, asignaturaSeleccionada)) {
                    asignaturasSeleccionadas[contador++] = asignaturaSeleccionada;
                } else {
                    System.out.println("La asignatura ya está matriculada.");
                }
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        }

        try {
            return new Matricula(id, cursoAcademico, fechaMatriculacion, alumno, asignaturasSeleccionadas);
        } catch (OperationNotSupportedException e) {
            System.out.println("Error al crear la matrícula: " + e.getMessage());
            return null;
        }
    }
        public static Matricula getMatriculaPorIdentificador() {
        System.out.print("Introduce el identificador de la matrícula: ");
        int id = Entrada.entero();
            try {
                return new Matricula(id, "24-25", LocalDate.now(), getAlumnoPorDni(), new Asignatura[0]);
            } catch (OperationNotSupportedException e) {
                System.out.println("Error al obtener la matrícula: " + e.getMessage());
                return null;
            }
    }
}
