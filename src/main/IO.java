package main;

/**
 * Clase de utilidad para simplificar operaciones de salida por consola
 * (Input/Output). Provee envoltorios (wrappers) sobre los métodos estándar de
 * System.out.
 */
public class IO {
	/**
	 * Pre: Recibe una cadena de caracteres (String) con el mensaje a mostrar. Post:
	 * Imprime el mensaje recibido en la consola seguido de un salto de línea
	 * automático.
	 */
	public static void println(String mensaje) {
		System.out.println(mensaje);
	}

	/**
	 * Pre: Ninguna. Post: Genera un salto de línea vacío en la consola del sistema.
	 */
	public static void println() {
		System.out.println();
	}

	/**
	 * Pre: Recibe una cadena de caracteres (String) con el mensaje a mostrar. Post:
	 * Imprime el mensaje recibido en la consola. (Nota de diseño: Actualmente se
	 * comporta igual a println debido al uso interno de System.out.println).
	 */
	public static void print(String mensaje) {
		System.out.println(mensaje);// Aquí imprime con salto de línea por el método interno usado
	}
}