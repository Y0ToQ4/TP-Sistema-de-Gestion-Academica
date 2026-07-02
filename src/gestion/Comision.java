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
		this.cronogramaAsignacion = new String[3][6];

		this.listaAlumnos = new Alumno[cupoMaxAlumnos];
		this.cantidadInscritos = 0;
		this.abierta = true;
	}

	/**
	 * Pre: Recibe un número de legajo entero. Post: Realiza una búsqueda lineal
	 * dentro de las posiciones ocupadas del arreglo de alumnos. Devuelve true si
	 * encuentra un alumno con ese mismo legajo; si no lo encuentra, devuelve false.
	 */
	public boolean legajoRepetido(int legajo) {
		if (legajo <= 0) {
			System.out.println("Error: El legajo ingresado debe ser válido.");
			return false;
		}
		boolean repetido = false;
		for (int i = 0; i < this.cantidadInscritos && !repetido; i++) {
			if (this.listaAlumnos[i] != null && this.listaAlumnos[i].getLegajo() == legajo) {
				repetido = true;
			}
		}
		return repetido;
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
		if (legajoRepetido(nuevoAlumno.getLegajo())) {
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
	 * Pre: Recibe un número de legajo entero (legajoDeBaja). Post: Si el legajo es
	 * menor o igual a cero o no pertenece a ningún alumno registrado, muestra un
	 * mensaje de error por consola y devuelve false. Si el alumno existe, lo
	 * elimina del arreglo desplazando los elementos posteriores para mantener el
	 * orden, asigna null al último casillero que estaba ocupado, decrementa la
	 * cantidad de inscritos y devuelve true.
	 */
	public boolean darAlumnoDeBaja(int legajoDeBaja) {
		if (legajoDeBaja <= 0) {
			System.out.println("Error: El legajo para dar de baja debe ser válido(positivo).");
			return false;
		}
		boolean encontrado = false;
		int indiceEncontrado = 0;
		for (int i = 0; i < cantidadInscritos && !encontrado; i++) {
			if (listaAlumnos[i] != null && listaAlumnos[i].getLegajo() == legajoDeBaja) {
				indiceEncontrado = i;
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("Error: No se encontró ningún alumno con el legajo " + legajoDeBaja);
			return false;
		}
		for (int j = indiceEncontrado; j < cantidadInscritos - 1; j++) {
			listaAlumnos[j] = listaAlumnos[j + 1];
		}
		listaAlumnos[cantidadInscritos - 1] = null;
		cantidadInscritos--;
		return true;
	}

	/**
	 * Pre: Recibe un entero para el legajo (legajoAModificar), un String para el
	 * nombre (nombreAModificar) y un valor real para el promedio
	 * (promedioAModificar). Post: Si los parámetros de entrada no cumplen con las
	 * restricciones de validación (legajo positivo, nombre no vacío y promedio
	 * entre 0 y 10) o si el legajo no corresponde a ningún alumno inscrito, emite
	 * un mensaje de error por consola y finaliza la ejecución sin alterar el estado
	 * del sistema. Si el alumno existe y los datos son válidos, actualiza su nombre
	 * y promedio.
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

	/**
	 * Pre: Recibe un número de legajo entero (legajoABuscar). Post: Si el legajo es
	 * menor o igual a cero, finaliza la ejecución devolviendo null. Si el legajo es
	 * positivo pero no se encuentra registrado en la comisión, emite un mensaje de
	 * aviso por consola y devuelve null. Si el alumno existe, devuelve la
	 * referencia original de su objeto de tipo Alumno.
	 */
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
	 * Pre: Ninguna. Post: Si la cantidad de alumnos inscritos es menor a dos,
	 * finaliza la ejecución sin realizar modificaciones. Caso contrario, reorganiza
	 * los elementos dentro del arreglo interno de alumnos de forma descendente (de
	 * mayor a menor) según su promedio, preservando los mismos objetos e integridad
	 * de los datos mediante el algoritmo Bubble Sort.
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
	 * Pre: Ninguna. Post: Llama al método de ordenamiento descendente del arreglo
	 * interno. Si no hay alumnos inscritos en la comisión, muestra un mensaje de
	 * aviso por consola y devuelve un arreglo vacío de tipo Alumno (tamaño cero).
	 * Si existen alumnos inscritos, genera, estructura y devuelve un nuevo arreglo
	 * en el Heap que contiene las referencias de los alumnos ordenadas de mayor a
	 * menor según su promedio académico.
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
	 * Pre: Ninguna. Post: Provee acceso externo a la estructura del cronograma,
	 * devolviendo la referencia original de la matriz bidimensional de asignaciones
	 * para que pueda ser leída o procesada por componentes de la interfaz de
	 * usuario.
	 */
	public String[][] obtenerCronograma() {
		return this.cronogramaAsignacion; // da una referencia de la matriz cronogramaAsignacion
	}

	/**
	 * Pre: Recibe una referencia de tipo Docente (nuevoD). Post: Si el docente
	 * recibido es nulo o si se ha alcanzado el límite máximo de cupo en el arreglo
	 * de docentes (mostrando en este último caso un mensaje de error por consola),
	 * finaliza la ejecución devolviendo false sin alterar el sistema. Caso
	 * contrario, almacena al docente en la posición indexada por el contador
	 * actual, incrementa la cantidad de docentes inscritos y devuelve true.
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
	 * Pre: Recibe dos números enteros para las coordenadas (turnoAAsignar y
	 * diaAAsignar) y una referencia de tipo String para la asignatura (materia).
	 * Post: Si los índices están fuera de los rangos válidos (turno entre 0 y 2,
	 * día entre 0 y 5), si la materia es nula o vacía, o si el casillero
	 * seleccionado de la matriz ya se encuentra ocupado, muestra el correspondiente
	 * mensaje de error o aviso por consola y finaliza la ejecución sin alterar el
	 * cronograma. Si todas las validaciones son exitosas, almacena el nombre de la
	 * materia sin espacios en blanco redundantes.
	 */
	public void asignarTurno(int turnoAAsignar, int diaAAsignar, String materia) {
		if (turnoAAsignar < 0 || turnoAAsignar > 2 || diaAAsignar < 0 || diaAAsignar > 5) {
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