package tpGestionAcademica;

public class Comision {
	private boolean[][] cronogramaAsignacion;
	private Docente[] listaDocentes;
	private int cantidadDocentes;
	private Alumno[] listaAlumnos;
	private int cantidadInscritos;
	private boolean abierta;

	public Comision(int cupoMaxDocentes, int cupoMaxAlumnos) {
		this.listaDocentes = new Docente[cupoMaxDocentes];
		this.cantidadDocentes = 0;
		this.cronogramaAsignacion = new boolean[cupoMaxDocentes][5];

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

	private String sinAcentos(String frase) {
		if (frase == null) {
			return "";
		}
		String conAcentos = "áéíóúüñÁÉÍÓÚÜÑ";
		String sinAcentos = "aeiouunAEIOUUN";
		for (int i = 0; i < conAcentos.length(); i++) {
			frase = frase.replace(String.valueOf(conAcentos.charAt(i)), String.valueOf(sinAcentos.charAt(i)));
		}
		return frase.toLowerCase().trim();
	}

	public Alumno buscarAlumnoPorNombre(String nombreBuscar) {
		if (nombreBuscar == null || nombreBuscar.isEmpty()) {
			return null;
		}
		String buscadoLimpio = sinAcentos(nombreBuscar);
		for (int i = 0; i < cantidadInscritos; i++) {
			String alumnoEnListaLimpio = sinAcentos(listaAlumnos[i].getNombre());
			if (alumnoEnListaLimpio.equals(buscadoLimpio)) {
				return listaAlumnos[i];
			}
		}
		System.out.println("El alumno '" + nombreBuscar + "' no se encuentra en esta comisión.");
		return null;
	}

	public void asignarDiaDocente(String nombreDocente, String nombreDia) {
		int indiceDocente = -1;
		int indiceDia = -1;
		int i = 0;
		boolean encontrado = false;
		String docenteBuscado = sinAcentos(nombreDocente);
		
		while (i < cantidadDocentes && !encontrado) {
			String docenteEnLista = sinAcentos(listaDocentes[i].getNombre());

			if (docenteEnLista.equals(docenteBuscado)) {
				indiceDocente = i;
				encontrado = true;
			}
			i++;
		}
		
		String diaLimpio = sinAcentos(nombreDia);
		if (diaLimpio.equals("lunes")) {
			indiceDia = 0;
		} else if (diaLimpio.equals("martes")) {
			indiceDia = 1;
		} else if (diaLimpio.equals("miercoles")) {
			indiceDia = 2;
		} else if (diaLimpio.equals("jueves")) {
			indiceDia = 3;
		} else if (diaLimpio.equals("viernes")) {
			indiceDia = 4;
		}

		if (indiceDocente != -1 && indiceDia != -1) {
			this.cronogramaAsignacion[indiceDocente][indiceDia] = true;
			System.out.println("Asignación exitosa: " + nombreDocente + " dictará clases el " + nombreDia + ".");
		} else {
			System.out.println("Error de asignación: Verifique el nombre del docente o el día.");
		}
	}

	public void mostrarCronograma() {
		System.out.println("=== CRONOGRAMA DE ASIGNACIÓN ===");
		System.out.println("Docente |  Lun  |  Mar  |  Mié  |  Jue  |  Vie  |");

		for (int i = 0; i < cantidadDocentes; i++) {
			System.out.print(listaDocentes[i].getNombre() + "\t|");

			for (int j = 0; j < 5; j++) {
				if (cronogramaAsignacion[i][j] == true) {
					System.out.print(" CLASE\t|");
				} else {
					System.out.print(" -----\t|");
				}
			}
			System.out.println();
		}
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

		System.out.println("CUADRO DE MÉRITO (ORDENADO)");
		if (cantidadInscritos == 0) {
			System.out.println("No hay alumnos inscritos en esta comisión.");
			return;
		}

		int n = listaAlumnos.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1 - i; j++) {
				if (listaAlumnos[j].getPromedio() < listaAlumnos[j + 1].getPromedio()) {
					Alumno temp = listaAlumnos[j];
					listaAlumnos[j] = listaAlumnos[j + 1];
					listaAlumnos[j + 1] = temp;
				}
			}
		}

		for (int i = 0; i < cantidadInscritos; i++) {
			System.out.println(
					(i + 1) + "° - " + listaAlumnos[i].getNombre() + " | Promedio: " + listaAlumnos[i].getPromedio());
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