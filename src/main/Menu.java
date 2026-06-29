package main;

import gestion.Alumno;
import gestion.Comision;
import gestion.Docente;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Menu {
	private Comision comision;
	private Scanner entrada;

	public Menu() {
		this.comision = new Comision(60, 3);
		this.entrada = new Scanner(System.in);
		this.precargarDatos();
	}

	public void iniciar() {
		int opcion = -1;
		while (opcion != 0) {
			this.mostrarMenu();
			IO.print("> Seleccioná una opción del menú: ");
			try {
				opcion = Integer.parseInt(entrada.nextLine());
				this.procesarOpcion(opcion);
			} catch (NumberFormatException excepcion) {
				IO.println("\n[ERROR] Ingresá un número entero válido");
			} catch (Error error) {
				IO.println("\n[ERROR] " + error.getMessage());
			} catch (Exception excepcion) {
				IO.println("\n[ERROR] " + excepcion.getMessage());
			}
			if (opcion != 0) {
				esperarTecla();
			}
		}
		IO.println("\n> Saliendo del sistema...");
	}
	
	private void mostrarMenu() {
		IO.println("\n╔═══════════════════════════════════════╗");
		IO.println("║      SGA - Sistema Gestión Académica  ║");
		IO.println("╠═══════════════════════════════════════╣");
		IO.println("║ 1. Dar alumno de alta                 ║");
		IO.println("║ 2. Dar alumno de baja                 ║");
		IO.println("║ 3. Modificar alumno                   ║");
		IO.println("║ 4. Ver datos de alumno                ║");
		IO.println("║ 5. Listar alumnos ordenados           ║");
		IO.println("║ 6. Asignar cronograma                 ║");
		IO.println("║ 0. Salir                              ║");
		IO.println("╚═══════════════════════════════════════╝");
	}

	private void procesarOpcion(int opcion) {
		switch (opcion) {
		case 1:
			IO.println("\n[SISTEMA] ALTA DE ALUMNO");
			IO.print("> Nombre completo: ");
			String nombreDeAlta = entrada.nextLine();
			IO.print("> Número de legajo: ");
			int legajoDeAlta = Integer.parseInt(entrada.nextLine());
			IO.print("> Promedio académico inicial: ");
			double promedioDeAlta = Double.parseDouble(entrada.nextLine());
			Alumno nuevoAlumno = new Alumno(nombreDeAlta, legajoDeAlta, promedioDeAlta);
			comision.darAlumnoDeAlta(nuevoAlumno);
			IO.println("\n[ÉXITO] Alumno dado de alta correctamente en la comisión.");
			break;
		case 2:
			IO.println("\n[SISTEMA] BAJA DE ALUMNO");
			IO.print("> Ingresá el legajo del alumno a dar de baja");
			int legajoDeBaja = Integer.parseInt(entrada.nextLine());
			comision.darAlumnoDeBaja(legajoDeBaja);
			IO.println("\n[ÉXITO] Alumn dado de baja correctamente.");
			break;
		case 3:
			IO.println("\n[SISTEMA] MODIFICACIÓN DE ALUMNO");
			IO.print("> Ingresá el legajo del alumno a modificar: ");
			int legajoAModificar = Integer.parseInt(entrada.nextLine());
			IO.print("> Nuevo nombre completo: ");
			String nombreAModificar = entrada.nextLine();
			IO.print("> Nuevo promedio: ");
			double promedioAModificar = Double.parseDouble(entrada.nextLine());
			comision.cambiarAlumnoPorLegajo(legajoAModificar, nombreAModificar, promedioAModificar);
			IO.println("\n[ÉXITO] Alumno modificado correctamente.");
			break;
		case 4:
			IO.println("\n[SISTEMA] BUSCAR ALUMNO");
			IO.print("> Ingresá el legajo a buscar de forma secuencial: ");
			int legajoABuscar = Integer.parseInt(entrada.nextLine());
			Alumno alumnoEncontrado = comision.obtenerAlumnoPorLegajo(legajoABuscar);
			if (alumnoEncontrado != null) {
				IO.println("\nAlumno: \n" + alumnoEncontrado);
			} else {
				IO.println("\n[ALERTA] No se encontró un alumno con el legajo " + legajoABuscar);
			}
			break;
		case 5:
			IO.println("\n[SISTEMA] ALUMNOS ORDENADOS POR MAYOR PROMEDIO");
			Alumno[] alumnosOrdenados = comision.obtenerAlumnosOrdenados();
			if (alumnosOrdenados.length == 0) {
				IO.println("> No hay alumnos en el sistema.");
			} else {
				for (int i = 0; i < alumnosOrdenados.length; i++) {
					IO.println((i + 1) + ". " + alumnosOrdenados[i]);
				}
			}
			break;
		case 6:
			IO.println("\n[SISTEMA] ASIGNAR CRONOGRAMA");
			IO.println("> Seleccioná el turno: 0 = Mañana | 1 = Tarde | 2 = Noche");
			int turnoAAsignar = Integer.parseInt(entrada.nextLine());
			IO.println(
					"> Seleccioná el día: 0 = Lunes | 1 = Martes | 2 = Miércoles | 3 = Jueves | 4 = Viernes | 5 = Sábado");
			int diaAAsignar = Integer.parseInt(entrada.nextLine());
			IO.print("> Nombre de la Cátedra: ");
			String materia = entrada.nextLine();
			comision.asignarTurno(turnoAAsignar, diaAAsignar, materia);
			IO.println("\n[ÉXITO] Cronograma actualizado correctamente.");
			this.imprimirCronograma();
			break;
		case 0:
			break;
		default:
			IO.println("\n[ALERTA] Opción incorrecta, intentá de nuevo.");
		}
	}

	private void imprimirCronograma() {
		String[][] cronograma = comision.obtenerCronograma();
		String[] turnos = { "Mañana", "Tarde", "Noche" };
		IO.println("\tLunes\t\tMartes\t\tMiércoles\tJueves\t\tViernes\t\tSábado");
		for (int i = 0; i < cronograma.length; i++) {
			IO.print(turnos[i] + "\t");
			for (int j = 0; j < cronograma[i].length; j++) {
				IO.print("[" + cronograma[i][j] + "]\t");
			}
			IO.println();
		}
	}
	
	private void esperarTecla() {
		IO.print("\n> Pulsá Enter para continuar...");
		entrada.nextLine();
	}

	private void precargarDatos() {
		try {
			String contenido = new String(Files.readAllBytes(Paths.get("datos.json")));
			Pattern patronAlumno = Pattern.compile(
					"\\{\\s*\"nombre\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"legajo\"\\s*:\\s*(\\d+)\\s*,\\s*\"promedio\"\\s*:\\s*([\\d.]+)\\s*\\}");
			Matcher matcherAlumno = patronAlumno.matcher(contenido);
			while (matcherAlumno.find()) {
				String nombre = matcherAlumno.group(1);
				int legajo = Integer.parseInt(matcherAlumno.group(2));
				double promedio = Double.parseDouble(matcherAlumno.group(3));
				Alumno nuevo = new Alumno(nombre, legajo, promedio);
				this.comision.darAlumnoDeAlta(nuevo);
			}
			Pattern patronDocente = Pattern.compile(
					"\\{\\s*\"nombre\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"catedra\"\\s*:\\s*\"([^\"]+)\"\\s*,\\s*\"antiguedad\"\\s*:\\s*(\\d+)\\s*\\}");
			Matcher matcherDocente = patronDocente.matcher(contenido);
			while (matcherDocente.find()) {
				String nombre = matcherDocente.group(1);
				String catedra = matcherDocente.group(2);
				int antiguedad = Integer.parseInt(matcherDocente.group(3));
				Docente nuevoD = new Docente(nombre, catedra, antiguedad);
				this.comision.darDocenteDeAlta(nuevoD);
			}
			IO.println("\n[ÉXITO] Datos iniciales precargados desde datos.json de forma automática.");
		} catch (Exception excepcion) {
			IO.println("\n[INFO] No se pudo realizar la precarga automática (datos.json no encontrado o inválido).");
		}
	}
}