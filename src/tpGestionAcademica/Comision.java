package tpGestionAcademica;

public class Comision {
	private Docente[] listaDocentes;
	private int cantidadDocentes;
	private Alumno[] listaAlumnos;
	private int cantidadInscritos;
	private boolean abierta;

	// Constructor recibe la cantidad máxima de docentes y alumnos permitidos
	public Comision(int cupoMaxDocentes, int cupoMaxAlumnos) {
		this.listaDocentes = new Docente[cupoMaxDocentes];
		this.cantidadDocentes = 0;

		this.listaAlumnos = new Alumno[cupoMaxAlumnos];
		this.cantidadInscritos = 0;

		this.abierta = true; 
	}

	public boolean registrarDocente(Docente nuevoDocente) {
		if (cantidadDocentes >= listaDocentes.length) {
			System.out.println("Error: No hay más cupo para docentes en esta comisión.");
			return false;
		}
		listaDocentes[cantidadDocentes] = nuevoDocente;
		cantidadDocentes++;
		return true;
	}

	public boolean registrarAlumno(Alumno nuevoAlumno) {
		if (!abierta) {
			System.out.println("Error: La inscripción está cerrada.");
			return false;
		}
		if (cantidadInscritos >= listaAlumnos.length) {
			System.out.println("Error: No hay cupo disponible en esta comisión.");
			return false;
		}

		listaAlumnos[cantidadInscritos] = nuevoAlumno;
		cantidadInscritos++;
		return true;
	}

	public Alumno buscarAlumnoPorNombre(String nombreBuscar) {
		if (nombreBuscar == null || nombreBuscar.isEmpty()) {
			return null;
		}
		for (int i = 0; i < cantidadInscritos; i++) {
			if (listaAlumnos[i].getNombre().equalsIgnoreCase(nombreBuscar.trim())) {
				return listaAlumnos[i]; // Devolvemos el objeto Alumno completo
			}
		}
		System.out.println("El alumno '" + nombreBuscar + "' no se encuentra en esta comisión.");
		return null;
	}

	public void ordenarAlumnosPorPromedio() {
		if (cantidadInscritos < 2) {
			return;
		}
		for (int i = 0; i < cantidadInscritos - 1; i++) {
			for (int j = 0; j < cantidadInscritos - i - 1; j++) {
				if (listaAlumnos[j].getPromedio() < listaAlumnos[j + 1].getPromedio()) {
					Alumno temp = listaAlumnos[j];
					listaAlumnos[j] = listaAlumnos[j + 1];
					listaAlumnos[j + 1] = temp;
				}
			}
		}
	}

	public void mostrarCuadroDeMerito() {
		this.ordenarAlumnosPorPromedio();

		System.out.println("--- CUADRO DE MÉRITO (Ordenado por Promedio) ---");
		if (cantidadInscritos == 0) {
			System.out.println("No hay alumnos inscritos en esta comisión.");
			return;
		}

		for (int i = 0; i < cantidadInscritos; i++) {
			System.out.println(
					(i + 1) + "°º - " + listaAlumnos[i].getNombre() + " | Promedio: " + listaAlumnos[i].getPromedio());
		}
		System.out.println("------------------------------------------------");
	}

	// Getters y Setters
	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

	public Docente[] getListaDocentes() {
		return listaDocentes;
	}

	public Alumno[] getListaAlumnos() {
		return listaAlumnos;
	}
}