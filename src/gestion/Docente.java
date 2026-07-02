package gestion;

/**
 * Clase que representa a un Docente de la institución. Almacena y gestiona sus
 * datos profesionales individuales como nombre, cátedra asignada y antigüedad.
 */
public class Docente {
	private String nombre;
	private String catedra;
	private int antiguedad;
	/**
	 * Pre: Recibe cadenas de caracteres válidas para el nombre y la cátedra, y un
	 * entero mayor o igual a cero para la antigüedad. Post: Inicializa las
	 * propiedades del objeto Docente en el Heap con los valores provistos.
	 */
	public Docente(String nombre, String catedra, int antiguedad) {
		this.nombre = nombre;
		this.catedra = catedra;
		this.antiguedad = antiguedad;
	}

	/**
	 * Pre: Ninguna. Post: Retorna la cadena de caracteres correspondiente al nombre
	 * del docente.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Pre: Recibe una cadena de caracteres con el nuevo nombre. Post: Modifica el
	 * atributo nombre del docente con el valor recibido.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Pre: Ninguna. Post: Retorna el nombre de la cátedra asignada al docente.
	 */
	public String getCatedra() {
		return catedra;
	}

	/**
	 * Pre: Recibe una cadena de caracteres con el nuevo nombre de la cátedra. Post:
	 * Modifica la cátedra asignada al docente con el valor recibido.
	 */
	public void setCatedra(String catedra) {
		this.catedra = catedra;
	}

	/**
	 * Pre: Ninguna. Post: Retorna los años de antigüedad del docente.
	 */
	public int getAntiguedad() {
		return antiguedad;
	}
	
	/**
	 * Pre: Recibe un entero mayor o igual a cero con los años de antigüedad. Post:
	 * Modifica la antigüedad del docente con el valor recibido.
	 */
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
}