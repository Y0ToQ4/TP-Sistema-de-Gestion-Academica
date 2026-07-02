package main;

/**
 * Clase para los métodos estándar de System.out.
 */
public class IO {
	/**
	 * Pre: Recibe una cadena de caracteres (String) con el mensaje a mostrar. Post:
	 * Imprime el mensaje recibido en la consola, reemplazando la palabra null por guiones.
	 */
	public static void print(String mensaje) {
		if (mensaje.equals("[null]\t")) {
			System.out.print("[ ----- ] \t");
		} else {
			System.out.print(mensaje);
		}
	}

	/**
	 * Pre: Recibe una cadena de caracteres (String) con el mensaje a mostrar. Post:
	 * Imprime el mensaje recibido en la consola.
	 */
	public static void println(String mensaje) {
		System.out.println(mensaje);
	}
	
	/**
	 * Pre: Ninguna. Post: Genera un salto de línea vacío.
	 */
	public static void println() {
		System.out.println();
	}
}