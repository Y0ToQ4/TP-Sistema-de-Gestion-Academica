package main;

public class Comision {
	private String[][] cronogramaAsignacion;
	private Docente[] listaDocentes;
	private int cantidadDocentes;

	private Alumno[] listaAlumnos;
	private int cantidadInscritos;

	private boolean abierta;

	public Comision(int cupoMaxDocentes, int cupoMaxAlumnos) {
		this.listaDocentes = new Docente[cupoMaxDocentes];
		this.cantidadDocentes = 0;
		this.cronogramaAsignacion = new String[3][5];

		this.listaAlumnos = new Alumno[cupoMaxAlumnos];
		this.cantidadInscritos = 0;
		this.abierta = true;
	}

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

	public boolean darAlumnoDeAlta(Alumno nuevoAlumno) {
		if (nuevoAlumno == null) {
			System.out.println("Error: El alumno debe tener un nombre válido.");
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
				System.out.println("Alumno " + nuevoAlumno.getNombre() + " inscrito exitosamente.");
				return true;
			}
		}
		System.out.println("Error: No hay cupo disponible en esta comisión.");
		return false;
	}

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

	public void cambiarAlumnoPorLegajo(int legajoAModificar, String nombreAModificar, double promedioAModificar) {
		if (legajoAModificar <= 0) {
			System.out.println("Error: El legajo a editar no es válido.");
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
			System.out.println("Datos del alumno legajo " + legajoAModificar + " modificados con éxito.");
		} else {
			System.out.println("Error: No se encontró al alumno con legajo " + legajoAModificar);
		}
	}

	public Alumno obtenerAlumnoPorLegajo(int legajoABuscar) {
		Alumno alumnoEncontrado = null;
		if (legajoABuscar <= 0) {
			System.out.println("Error: El legajo ingresado no es válido.");
			return null;
		}
		boolean encontrado = false;

		for (int i = 0; i < cantidadInscritos && !encontrado; i++) {
			Alumno actual = listaAlumnos[i];
			if (actual != null && actual.getLegajo() == legajoABuscar) {
				System.out.println("--- FICHA DE ALUMNO ---");
				System.out.println(actual.toString());
				System.out.println("------------------------");
				alumnoEncontrado = actual;
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("El legajo " + legajoABuscar + " no corresponde a ningún alumno registrado.");
		}
		return alumnoEncontrado;
	}

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

	public Alumno[] obtenerAlumnosOrdenados() {
		this.ordenarAlumnosPorPromedio();

		System.out.println(" CUADRO DE MÉRITO (ORDENADO)");

		if (cantidadInscritos == 0) {
			System.out.println("No hay alumnos inscritos en esta comisión.");
			return this.listaAlumnos;
		}
		for (int i = 0; i < cantidadInscritos; i++) {
			Alumno temp = listaAlumnos[i];

			if (temp != null) {
				System.out.println((i + 1) + "° - " + temp.getNombre() + " | Promedio: " + temp.getPromedio());
			}
		}
		System.out.println("------------------------------------------------");

		return this.listaAlumnos;
	}

	public String[][] obtenerCronograma() {
		System.out.println(" CRONOGRAMA DE ASIGNACIÓN ");
		System.out.println("Docente |  Lun  |  Mar  |  Mié  |  Jue  |  Vie  |");
		for (int i = 0; i < 3; i++) {
			if (i == 0) {
				System.out.print("Mañana  |");
			} else if (i == 1) {
				System.out.print("Tarde   |");
			} else {
				System.out.print("Noche   |");
			}
			for (int j = 0; j < 5; j++) {
				String materiaAsignada = cronogramaAsignacion[i][j];

				if (materiaAsignada != null) {
					if (materiaAsignada.length() > 5) {
						System.out.print(" " + materiaAsignada.substring(0, 5) + " |");
					} else {
						System.out.printf(" %-5s |", materiaAsignada);
					}
				} else {
					System.out.print(" ----- |");
				}
			}
			System.out.println();
		}
		return this.cronogramaAsignacion;
	}

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

		System.out.println("Docente registrado con éxito.");
		return true;
	}

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