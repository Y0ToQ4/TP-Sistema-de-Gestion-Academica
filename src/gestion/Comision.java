package gestion;

/**
 * Clase que representa una Comisión Académica. Administra los arreglos de
 * alumnos, docentes y la matriz del cronograma de asignación.
 */
public class Comision {
	private String[][] cronogramaAsignacion;
	private Docente[] listaDocentes;
	private int cantidadDocentes;

	private Alumno[] listaAlumnos;
	private int cantidadInscritos;

	private boolean abierta;

	/**
	 * Clase que representa una Comisión Académica. Administra los arreglos de
	 * alumnos, docentes y la matriz del cronograma de asignación.
	 */
	public Comision(int cupoMaxAlumnos, int cupoMaxDocentes) {
		this.listaDocentes = new Docente[cupoMaxDocentes];
		this.cantidadDocentes = 0;
		this.cronogramaAsignacion = new String[3][5];

		this.listaAlumnos = new Alumno[cupoMaxAlumnos];
		this.cantidadInscritos = 0;
		this.abierta = true;
	}

	/**
	 * Pre: Recibe un número de legajo entero. Post: Realiza una búsqueda lineal
	 * dentro de las posiciones ocupadas del arreglo de alumnos. Devuelve true si
	 * encuentra un alumno con ese mismo legajo; si no lo encuentra o el número es
	 * menor o igual a cero, emite un aviso por consola y devuelve false.
	 */
	public boolean existeLegajo(int legajo) {
		if (legajo <= 0) {
			System.out.println("Error: El legajo ingresado debe ser válido.");
			return false;
		}
		boolean encontrado = false;
		for (int i = 0; i < this.cantidadInscritos && !encontrado; i++) {
			if (this.listaAlumnos[i] != null && this.listaAlumnos[i].getLegajo() == legajo) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	/**
	 * Pre: Recibe una instancia de Alumno con atributos válidos (nombre no vacío,
	 * legajo mayor a cero y promedio entre 0 y 10). Post: Si el legajo no existe y
	 * hay cupo disponible, registra al alumno en la comisión, incrementa la
	 * cantidad de inscritos y devuelve true. Caso contrario, muestra un error y
	 * devuelve false.
	 */
	public boolean darAlumnoDeAlta(Alumno nuevoAlumno) {
		if (nuevoAlumno == null) {
			System.out.println("Error: El alumno debe tener un nombre válido.");
			return false;
		}
		if (nuevoAlumno.getNombre() == null || nuevoAlumno.getNombre().trim().equals("")) {
			System.out.println("Error: El nombre del alumno no puede estar vacío.");
			return false;
		}
		if (nuevoAlumno.getLegajo() <= 0) {
			System.out.println("Error: El legajo ingresado debe ser un número válido.");
			return false;
		}
		if (nuevoAlumno.getPromedio() < 0 || nuevoAlumno.getPromedio() > 10) {
			System.out.println("Error: El promedio académico debe estar entre 0 y 10.");
			return false;
		}
		if (this.cantidadInscritos >= this.listaAlumnos.length) {
			System.out.println("Error: No hay cupo disponible en esta comisión.");
			return false;
		}
		if (existeLegajo(nuevoAlumno.getLegajo())) {
			System.out.println("Error: Ya existe un alumno inscrito con el legajo " + nuevoAlumno.getLegajo());
			return false;
		}
		for (int i = 0; i < listaAlumnos.length; i++) {
			if (listaAlumnos[i] == null) {
				listaAlumnos[i] = nuevoAlumno;
				cantidadInscritos++;
				return true;
			}
		}
		return false;
	}

	/**
	 * Pre: Recibe un número de legajo entero mayor a cero. Post: Si encuentra al
	 * alumno con ese legajo, lo elimina del arreglo, reordena las posiciones
	 * restantes hacia adelante para no dejar huecos vacíos (null) en el medio,
	 * decrementa los inscritos y limpia la última posición del arreglo.
	 */
	public void darAlumnoDeBaja(int legajoBaja) {
		if (legajoBaja <= 0) {
			System.out.println("Error: El legajo para dar de baja debe ser válido.");
			return;
		}

		int indiceEncontrado = -1;
		boolean encontrado = false;

		for (int i = 0; i < cantidadInscritos && !encontrado; i++) {
			if (listaAlumnos[i] != null && listaAlumnos[i].getLegajo() == legajoBaja) {
				indiceEncontrado = i;
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("Error: No se encontró ningún alumno con el legajo " + legajoBaja);
			return;
		}

		for (int j = indiceEncontrado; j < cantidadInscritos - 1; j++) {
			listaAlumnos[j] = listaAlumnos[j + 1];
		}
		listaAlumnos[cantidadInscritos - 1] = null;
		cantidadInscritos--;
		System.out.println("Alumno con legajo " + legajoBaja + " dado de baja correctamente.");
	}

	/**
	 * Pre: Recibe un legajo mayor a cero, una cadena de texto para el nombre (no
	 * nula ni vacía) y un número para el promedio entre 0 y 10. Post: Si el legajo
	 * existe en la comisión, actualiza sus atributos de nombre y promedio con los
	 * nuevos valores ingresados.
	 */
	public void cambiarAlumnoPorLegajo(int legajoAModificar, String nombreAModificar, double promedioAModificar) {
		if (legajoAModificar <= 0) {
			System.out.println("Error: El legajo a editar no es válido.");
			return;
		}
		if (nombreAModificar == null || nombreAModificar.trim().isEmpty()) {
			System.out.println("Error: El nombre modificado no puede estar vacío.");
			return;
		}
		if (promedioAModificar < 0 || promedioAModificar > 10) {
			System.out.println("Error: El promedio debe estar entre 0 y 10.");
			return;
		}
		boolean encontrado = false;

		for (int i = 0; i < cantidadInscritos && !encontrado; i++) {
			if (listaAlumnos[i] != null && listaAlumnos[i].getLegajo() == legajoAModificar) {
				listaAlumnos[i].setNombre(nombreAModificar);
				listaAlumnos[i].setPromedio(promedioAModificar);
				encontrado = true;
			}
		}
		if (encontrado) {
		} else {
			System.out.println("Error: No se encontró al alumno con legajo " + legajoAModificar);
		}
	}

	public Alumno obtenerAlumnoPorLegajo(int legajoABuscar) {
		Alumno alumnoEncontrado = null;
		if (legajoABuscar <= 0) {
			return null;
		}
		boolean encontrado = false;

		for (int i = 0; i < cantidadInscritos && !encontrado; i++) {
			Alumno actual = listaAlumnos[i];
			if (actual != null && actual.getLegajo() == legajoABuscar) {
				alumnoEncontrado = actual;
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("El legajo " + legajoABuscar + " no corresponde a ningún alumno registrado.");
		}
		return alumnoEncontrado;
	}

	/**
	 * Pre: Nada (el método evalúa internamente si hay alumnos suficientes). Post:
	 * Ordena los objetos Alumno del arreglo de mayor a menor según su promedio
	 * utilizando el algoritmo de Bubble Sort.
	 */
	public void ordenarAlumnosPorPromedio() {
		if (cantidadInscritos < 2) {
			return;
		}
		boolean huboCambio;
		int pasadas = 0;
		do {
			huboCambio = false;
			for (int j = 0; j < cantidadInscritos - 1 - pasadas; j++) {
				if (listaAlumnos[j] != null && listaAlumnos[j + 1] != null) {
					if (listaAlumnos[j].getPromedio() < listaAlumnos[j + 1].getPromedio()) {
						Alumno temp = listaAlumnos[j];
						listaAlumnos[j] = listaAlumnos[j + 1];
						listaAlumnos[j + 1] = temp;
						huboCambio = true;
					}
				}
			}
			pasadas++;
		} while (huboCambio);
	}

	/**
	 * Pre: Nada. El método valida internamente si existen alumnos en la comisión.
	 * Post: Invoca al ordenamiento interno de los alumnos por promedio, genera y
	 * devuelve una copia (un nuevo arreglo en el Heap) con las referencias
	 * ordenadas de forma descendente.
	 */
	public Alumno[] obtenerAlumnosOrdenados() {
		this.ordenarAlumnosPorPromedio();

		if (cantidadInscritos == 0) {
			System.out.println("No hay alumnos inscritos en esta comisión.");
			return new Alumno[0];
		}
		Alumno[] copiaOrdenada = new Alumno[this.cantidadInscritos];
		for (int i = 0; i < this.cantidadInscritos; i++) {
			copiaOrdenada[i] = this.listaAlumnos[i];
		}
		return copiaOrdenada;
	}

	/**
	 * Pre: Nada. Post: Devuelve la referencia original de la matriz bidimensional
	 * de asignaciones.
	 */
	public String[][] obtenerCronograma() {
		return this.cronogramaAsignacion;
	}

	/**
	 * Pre: Recibe una instancia de Docente que no sea nula (null). Post: Si hay
	 * espacio disponible en el arreglo de docentes, almacena al nuevo docente en la
	 * primera posición libre, incrementa el contador de docentes, muestra un
	 * mensaje de éxito y devuelve true. Si no hay cupo, devuelve false.
	 */
	public boolean darDocenteDeAlta(Docente nuevoD) {
		if (nuevoD == null) {
			return false;
		}
		if (cantidadDocentes >= listaDocentes.length) {
			System.out.println("Error: No hay más cupo para docentes en esta comisión.");
			return false;
		}
		listaDocentes[cantidadDocentes] = nuevoD;
		cantidadDocentes++;
		return true;
	}

	/**
	 * Pre: Recibe índices enteros válidos para las dimensiones de la matriz (turno
	 * entre 0 y 2, día entre 0 y 4) y una cadena de texto para la materia no vacía.
	 * Post: Si el casillero de la matriz está vacío (null), guarda el nombre de la
	 * materia normalizado. Si ya estaba ocupado o los rangos son inválidos, muestra
	 * un mensaje de error o aviso en consola sin modificar el estado previo.
	 */
	public void asignarTurno(int turnoAAsignar, int diaAAsignar, String materia) {
		if (turnoAAsignar < 0 || turnoAAsignar > 2 || diaAAsignar < 0 || diaAAsignar > 4) {
			System.out.println("Error: Turno o día fuera de rango válido.");
			return;
		}
		if (materia == null || materia.trim().isEmpty()) {
			System.out.println("Error: El nombre de la materia no puede estar vacío.");
			return;
		}
		if (this.cronogramaAsignacion[turnoAAsignar][diaAAsignar] != null) {
			System.out.println("Aviso: El turno ya está ocupado por la materia: "
					+ this.cronogramaAsignacion[turnoAAsignar][diaAAsignar]);
		} else {
			this.cronogramaAsignacion[turnoAAsignar][diaAAsignar] = materia.trim();
			System.out.println("Materia " + materia + " asignada con éxito.");
		}
	}

	/**
	 * Pre: Ninguna. Post: Devuelve el estado de apertura de la comisión (true si
	 * está abierta, false si está cerrada).
	 */
	public boolean isAbierta() {
		return abierta;
	}

	/**
	 * Pre: Recibe un valor booleano (true o false). Post: Modifica el estado de la
	 * variable de apertura de la comisión con el valor recibido.
	 */
	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

	/**
	 * Pre: Nada. Post: Devuelve la referencia de memoria del arreglo que contiene a
	 * los docentes de la comisión.
	 */
	public Docente[] getListaDocentes() {
		return listaDocentes;
	}

	/**
	 * Pre: Nada. Post: Devuelve la referencia de memoria del arreglo que contiene a
	 * los alumnos de la comisión.
	 */
	public Alumno[] getListaAlumnos() {
		return listaAlumnos;
	}
}