package tpGestionAcademica;

public class Alumno {
	private String nombre;
	private int legajo;
	private double promedio; //float
	
	// Constructor
	public Alumno(String nombre, int legajo, double promedio) {
		this.nombre = normalizarNombre(nombre);
		this.legajo = legajo;
		this.promedio = promedio;
	}
	
	private String normalizarNombre(String nombreOriginal) {
		if (nombreOriginal == null || nombreOriginal.isEmpty()) {
			return nombreOriginal;
		}
		String[] palabras = nombreOriginal.trim().split("\\s+");  // trim Borra todos los espacios libres que estén al principio y al final del texto. split Corta el texto y lo convierte en un arreglo de palabras (String[])
		StringBuilder nombreNormalizado = new StringBuilder();
		for (String p : palabras) {
			if (!p.isEmpty()) {
				nombreNormalizado.append(Character.toUpperCase(p.charAt(0)))
						.append(p.substring(1).toLowerCase()).append(" ");
			}
		}
		return nombreNormalizado.toString().trim();
	}
	
	// Getters y Setters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = normalizarNombre(nombre);
	}
	
	public int getLegajo() {
		return legajo;
	}
	
	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}
	
	public double getPromedio() {
		return promedio;
	}
	
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

	@Override
	public String toString() {
		return "Alumno " + nombre + "| Legajo " + legajo + "| Promedio: " + promedio;
	}
}