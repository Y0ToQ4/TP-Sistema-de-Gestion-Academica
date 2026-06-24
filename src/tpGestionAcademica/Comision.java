package tpGestionAcademica;

public class Comision {
	private Docente docente;
	private Alumno[] listaAlumnos; // Arreglo nativo en el Heap
	private int cantidadInscritos; // Contador de alumnos actuales
	private boolean inscripcionAbierta;

	// Constructor: Recibe el cupo máximo para dimensionar el arreglo
	public Comision(Docente docente, int cupoMaximo) {
		this.docente = docente;
		this.listaAlumnos = new Alumno[cupoMaximo]; // Se reserva el espacio en memoria Heap
		this.cantidadInscritos = 0;
		this.inscripcionAbierta = true; // Por defecto la abrimos para probar
	}

	// ALTA (Create): Registra un alumno validando cupo e inscripción abierta
	public boolean registrarAlumno(Alumno nuevoAlumno) {
		if (!inscripcionAbierta) {
			System.out.println("Error: La inscripción está cerrada.");
			return false;
		}
		if (cantidadInscritos >= listaAlumnos.length) {
			System.out.println("Error: No hay cupo disponible en esta comisión.");
			return false;
		}

		// Guardamos la referencia del objeto Alumno en el casillero correspondiente del
		// arreglo
		listaAlumnos[cantidadInscritos] = nuevoAlumno;
		cantidadInscritos++;
		return true;
	}

	// LECTURA (Read): Lista los alumnos inscritos saltando las posiciones null
	public void listarAlumnos() {
		System.out.println("--- Lista de Alumnos Inscritos ---");
		for (int i = 0; i < listaAlumnos.length; i++) {
			// Recordatorio de memoria: Evitamos el NullPointerException saltando los null
			if (listaAlumnos[i] != null) {
				System.out.println("Legajo: " + listaAlumnos[i].getLegajo() + " | Nombre: "
						+ listaAlumnos[i].getNombre() + " | Promedio: " + listaAlumnos[i].getPromedio());
			}
		}
	}

	// Getters y Setters básicos
	public boolean isInscripcionAbierta() {
		return inscripcionAbierta;
	}

	public void setInscripcionAbierta(boolean inscripcionAbierta) {
		this.inscripcionAbierta = inscripcionAbierta;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
}