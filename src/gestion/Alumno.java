package gestion;

/**
 * Clase que representa a un Alumno de la institución. Almacena y gestiona sus
 * datos académicos individuales como nombre, legajo y promedio.
 */
public class Alumno {
	private String nombre;
	private int legajo;
	private double promedio;

	/**
	 * Pre: Recibe un nombre (String), un número de legajo entero y un valor de
	 * promedio (double). Post: Inicializa las propiedades del objeto Alumno,
	 * aplicando automáticamente el proceso de normalización de texto sobre el
	 * nombre ingresado.
	 */
	public Alumno(String nombre, int legajo, double promedio) {
		this.nombre = normalizarNombre(nombre);
		this.legajo = legajo;
		this.promedio = promedio;
	}

	/**
	 * Pre: Recibe una cadena de caracteres (String) con el nombre original. Post:
	 * Si el texto no es nulo ni vacío, remueve los espacios extras de las puntas e
	 * intermedios, convierte cada palabra al formato de Tipo Capital (Primera letra
	 * mayúscula, el resto minúsculas) y devuelve la nueva cadena corregida.
	 */
	private String normalizarNombre(String nombreOriginal) {
		if (nombreOriginal == null || nombreOriginal.isEmpty()) {
			return nombreOriginal;
		}
		String[] palabras = nombreOriginal.trim().split("\\s+");
		StringBuilder nombreNormalizado = new StringBuilder();
		for (String p : palabras) {
			if (!p.isEmpty()) {
				nombreNormalizado.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1).toLowerCase())
						.append(" ");
			}
		}
		return nombreNormalizado.toString().trim();
	}

	/**
	 * Pre: Ninguna. Post: Retorna la cadena de caracteres correspondiente al nombre
	 * normalizado del alumno.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Pre: Recibe una cadena de caracteres con el nuevo nombre. Post: Actualiza de
	 * forma segura el atributo nombre aplicando la normalización de texto.
	 */
	public void setNombre(String nombre) {
		this.nombre = normalizarNombre(nombre);
	}

	/**
	 * Pre: Ninguna. Post: Retorna el número de legajo del alumno.
	 */
	public int getLegajo() {
		return legajo;
	}

	/**
	 * Pre: Recibe un entero para el nuevo número de legajo. Post: Modifica el
	 * número de legajo del alumno con el valor recibido.
	 */
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	/**
	 * Pre: Ninguna. Post: Retorna el promedio del alumno.
	 */
	public double getPromedio() {
		return promedio;
	}

	/**
	 * Pre: Recibe un valor double para el nuevo promedio. Post: Modifica el
	 * promedio del alumno con el valor recibido.
	 */
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	/**
	 * Pre: Ninguna. Post: Construye y devuelve una representación en formato de
	 * texto plano con los datos formateados de la ficha del alumno (Nombre, Legajo
	 * y Promedio).
	 */
	@Override
	public String toString() {
		return "Alumno " + nombre + "| Legajo " + legajo + "| Promedio: " + promedio;
	}
}