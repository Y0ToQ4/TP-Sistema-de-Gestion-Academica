package gestion;

public class Docente {
	private String nombre;
	private String catedra;
	private int antiguedad;

	public Docente(String nombre, String catedra, int antiguedad) {
		this.nombre = nombre;
		this.catedra = catedra;
		this.antiguedad = antiguedad;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCatedra() {
		return catedra;
	}

	public void setCatedra(String catedra) {
		this.catedra = catedra;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
}