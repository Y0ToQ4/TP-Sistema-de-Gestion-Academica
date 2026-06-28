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
	
	private String sinAcentos(String frase) {
		if (frase == null || frase.isEmpty()) {
			return "";
		}
		String conAcentos = "áéíóúüñÁÉÍÓÚÜÑ";
		String sinAcentos = "aeiouunAEIOUUN";
		for (int i = 0; i < conAcentos.length(); i++) {
			frase = frase.replace(String.valueOf(conAcentos.charAt(i)), String.valueOf(sinAcentos.charAt(i)));
		}
		return frase.toLowerCase().trim();
	}
	
	public boolean existeLegajo(int legajo) {
		for (int i = 0; i < this.cantidadInscritos; i++) {
			if (this.listaAlumnos[i] != null && this.listaAlumnos[i].getLegajo() == legajo) {
				return true;
			}
		}
		return false;
	}
	
	public boolean registrarDocente(Docente nuevoDocente) {
		if (nuevoDocente == null) {
			return false;
		}
		if (cantidadDocentes >= listaDocentes.length) {
			System.out.println("Error: No hay más cupo para docentes en esta comisión.");
			return false;
		}
		listaDocentes[cantidadDocentes] = nuevoDocente;
		cantidadDocentes++;
		return true;
	}

	public void asignarDiaDocente(String nombreDocente, String nombreDia) {
		int indiceDocente = -1;
		int indiceDia = -1;
		String docenteBuscado = sinAcentos(nombreDocente);

		for (int i = 0; i < cantidadDocentes; i++) {
			String docenteEnLista = sinAcentos(listaDocentes[i].getNombre());

			if (docenteEnLista.equals(docenteBuscado)) {
				indiceDocente = i;
			}
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
			if (this.cronogramaAsignacion[indiceDocente][indiceDia]) {
				System.out.println("Aviso: El docente " + listaDocentes[indiceDocente].getNombre()
						+ " ya tiene asignado el día " + nombreDia);
			} else {
				this.cronogramaAsignacion[indiceDocente][indiceDia] = true;
				System.out.println("Clase asignada con éxito a " + listaDocentes[indiceDocente].getNombre() + " el día "
						+ nombreDia);
			}
		} else {
			System.out.println("Error de asignación: Verifique el nombre del docente o el día.");
		}
	}

	public void mostrarCronograma() {
		System.out.println(" CRONOGRAMA DE ASIGNACIÓN ");
		System.out.println("Docente |  Lun  |  Mar  |  Mié  |  Jue  |  Vie  |");

		for (int i = 0; i < cantidadDocentes; i++) {
			System.out.print(listaDocentes[i].getNombre() + "   |");

			for (int j = 0; j < 5; j++) {
				if (cronogramaAsignacion[i][j] == true) {
					System.out.print(" CLASE |");
				} else {
					System.out.print(" ----- |");
				}
			}
			System.out.println();
		}
	}

	public void buscarAlumnoPorLegajo(int legajo) {
		for (int i = 0; i < listaAlumnos.length; i++) {
			Alumno actual = listaAlumnos[i];

			if (actual != null && actual.getLegajo() == legajo) {
				System.out.println("Alumno encontrado");
				System.out.println("Nombre: " + actual.getNombre());
				System.out.println("Legajo: " + actual.getLegajo());
				return;
			}
		}
		System.out.println("No se pudo encontrar un alumno con el legajo " + legajo);
	}

	public boolean registrarAlumno(Alumno nuevoAlumno) {
		if (nuevoAlumno == null) {
			return false;
		}
		for (int i = 0; i < listaAlumnos.length; i++) {
			if (listaAlumnos[i] != null && listaAlumnos[i].getLegajo() == nuevoAlumno.getLegajo()) {
				System.out.println("Error: Ya existe el alumno " + listaAlumnos[i].getNombre() + " con el legajo "
						+ nuevoAlumno.getLegajo());
				return false;
			}
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

	public void bajaAlumno(int legajoBaja) {
		int indiceEncontrado = -1;
		boolean encontrado = false;

		for (int i = 0; i < cantidadInscritos; i++) {
			if (listaAlumnos[i].getLegajo() == legajoBaja) {
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
	
	public void mostrarDatosAlumno(int legajoBuscar) {
		boolean encontrado = false;
		Alumno alumnoEncontrado = null;
		
		for (int i = 0; i < cantidadInscritos; i++) {
			if (listaAlumnos[i] != null && listaAlumnos[i].getLegajo() == legajoBuscar) {
				alumnoEncontrado = listaAlumnos[i];
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("El legajo " + legajoBuscar + " no corresponde a ningún alumno registrado.");
			return;
		}
		System.out.println("--- FICHA DE ALUMNO ---");
		System.out.println(alumnoEncontrado.toString());
		System.out.println("------------------------");
	}

	public void modificarAlumno(int legajoEditar, String nuevoNombre, double nuevoPromedio) {
		boolean encontrado = false;

		for (int i = 0; i < cantidadInscritos; i++) {
			if (listaAlumnos[i].getLegajo() == legajoEditar) {
				listaAlumnos[i].setNombre(nuevoNombre);
				listaAlumnos[i].setPromedio(nuevoPromedio);
				encontrado = true;
			}
		}
		
		if (encontrado) {
			System.out.println("Datos del alumno legajo " + legajoEditar + " modificados con éxito.");
		} else {
			System.out.println("Error: No se encontró al alumno con legajo " + legajoEditar);
		}
	}
	
	public void ordenarAlumnosPorPromedio() {
		if (cantidadInscritos < 2) {
			return;
		}
		for (int i = 0; i < cantidadInscritos - 1; i++) {
			for (int j = 0; j < cantidadInscritos - i - 1; j++) {
				if (listaAlumnos[j] != null && listaAlumnos[j + 1] != null) {
					if (listaAlumnos[j].getPromedio() < listaAlumnos[j + 1].getPromedio()) {
						Alumno temp = listaAlumnos[j];
						listaAlumnos[j] = listaAlumnos[j + 1];
						listaAlumnos[j + 1] = temp;
					}
				}
			}
		}
	}
	
	public void mostrarCuadroDeMerito() {
		this.ordenarAlumnosPorPromedio();
		System.out.println(" CUADRO DE MÉRITO (ORDENADO)");
		if (cantidadInscritos == 0) {
			System.out.println("No hay alumnos inscritos en esta comisión.");
			return;
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