package tpGestionAcademica;

												/*cd TP-Sistema-de-Gestion-Academica
												git add .
												git commit -m "Explicá acá brevemente qué agregaste"
												git push origin main*/
public class TPGestionAcademica {

	public static void main(String[] args) {

		Alumno a1 = new Alumno("Pérez Juan", 101, 6.5);
		Alumno a2 = new Alumno("Gómez Ana", 102, 9.5);
		Alumno a3 = new Alumno("Díaz Bruno", 101, 8.0);
		Alumno a4 = new Alumno("López Clara", 104, 7.2);
		Alumno[] listaAlumnos = { a1, a2, a3, a4 };
		
		Docente d1 = new Docente("Gomez", "UTN",5);
        Docente d2 = new Docente("Soria", "UNTREF",67); 
        Docente[] listaDocentes = { d1, d2 };
		
		Comision comisionA = new Comision(listaDocentes.length, listaAlumnos.length);
		
        comisionA.registrarDocente(d1);
        comisionA.registrarDocente(d2);
        
		comisionA.registrarAlumno(a1);
	    comisionA.registrarAlumno(a2);
	    comisionA.registrarAlumno(a3);
	    comisionA.registrarAlumno(a4);
	    System.out.println("------------------------------------------------");
	    
	    System.out.println(" LISTA ORIGINAL (DESORDENADA):");
		for (Alumno a : listaAlumnos) {
			System.out.println(a);
		}
		System.out.println("------------------------------------------------");
	    
		comisionA.asignarDiaDocente("Gomez", "Lunes");
        comisionA.asignarDiaDocente("Gomez", "Miércoles");
        comisionA.asignarDiaDocente("Soria", "Lunes");
        comisionA.asignarDiaDocente("Soria", "Viernes");
		System.out.println("------------------------------------------------");
        comisionA.mostrarCronograma();
		System.out.println("------------------------------------------------");
		
		comisionA.mostrarCuadroDeMerito();
		
        System.out.println(" PROBANDO BÚSQUEDA DE ALUMNOS");
        comisionA.buscarAlumnoPorLegajo(101);
        comisionA.buscarAlumnoPorLegajo(167);
        
		System.out.println("------------------------------------------------");
        
        System.out.println("--- EJECUTANDO BAJA DEL LEGAJO 102 ---");
        comisionA.bajaAlumno(102); 
        System.out.println("--- PROBANDO BORRAR UN LEGAJO INEXISTENTE (999) ---");
        comisionA.bajaAlumno(999);
        
        System.out.println("--- LISTA DESPUÉS DE LA BAJA (Debería haber 3 alumnos y estar acomodados) ---");
        comisionA.mostrarCuadroDeMerito();
        
        System.out.println("--- EDITANDO LEGAJO 101 ---");
        comisionA.modificarAlumno(101, "Pérez Juan Carlos", 9.0);
        System.out.println("--- PROBANDO EDITAR UN LEGAJO INEXISTENTE (888) ---");
        comisionA.modificarAlumno(888, "Nadie", 10.0);

        System.out.println("--- LISTA DESPUÉS DE LA MODIFICACIÓN ---");
        comisionA.mostrarCuadroDeMerito();
        
        
    }
}