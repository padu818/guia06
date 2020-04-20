package died.guia06;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {
		
		Alumno a1 = new Alumno("Pedro Rodriguez",1);
		Alumno a2 = new Alumno("Jose Martinez",2);
		Alumno a3 = new Alumno("Juan Mendez",3);
		Alumno a4 = new Alumno("Maria Marta Villa",4);
		Alumno a5 = new Alumno("Belen Guimenez",5);
		Alumno a6 = new Alumno("Pablo Perez", 6);
		Alumno a7 = new Alumno("Mateo Lopez", 7);
		Curso c1 = new Curso(1,1,5,6,0);
		Curso c2 = new Curso(2,1,2,3,0);
		Curso c3 = new Curso(3,1,5,6,0);
		Curso c4 = new Curso(4,1,5,3,0);
		Curso c5 = new Curso(5,2,10,3,3);
		Curso c6 = new Curso(6,2,10,3,9);
		c1.setNombre("Arte");
		c2.setNombre("Musica");
		c3.setNombre("Psicologia");
		c4.setNombre("Historia");
		c5.setNombre("Leyes");
		c5.setNombre("Ecologia");
		c6.setNombre("Biologia");
		List<Alumno> auxiliar = new ArrayList<Alumno>();
		auxiliar.add(a1);auxiliar.add(a2);auxiliar.add(a3);auxiliar.add(a4);auxiliar.add(a5);auxiliar.add(a6);auxiliar.add(a7);
		a5.aprobar(c1);a5.aprobar(c2);
		a6.aprobar(c4);
		a7.aprobar(c2);a7.aprobar(c4);
		
		for(Alumno a:auxiliar) {
			String materiasIncriptas = "";
			c1.inscribirAlumno(a);
			c2.inscribirAlumno(a);
			c3.inscribirAlumno(a);
			c4.inscribirAlumno(a);
			c5.inscribirAlumno(a);
			c6.inscribirAlumno(a);
			for(Curso c: a.getCursando()) {
				materiasIncriptas= materiasIncriptas + c.getNombre().toString()+"-";
			}
			System.out.println("El alumno "+a.getNombre()+" se inscribio en: "+materiasIncriptas+"\n");

		}
		//usare el metodo inscribirMateria a alumnos que ya estan inscriptos:
		c5.inscribirAlumno(a6);
		c6.inscribirAlumno(a5);
		
		/*
		c1.inscribir(a1);c1.inscribir(a2);c1.inscribir(a3);c1.inscribir(a4);c1.inscribir(a6);5
		c2.inscribir(a1);c2.inscribir(a2);2
		c3.inscribir(a1);c3.inscribir(a2);c3.inscribir(a3);c3.inscribir(a4);c3.inscribir(a5);5
		c4.inscribir(a5);c4.inscribir(a3);c4.inscribir(a4);3
		c5.inscribir(a5);c5.inscribir(a6);c5.inscribir(a7);3
		c6.inscribir(a5);1
		asi seria sin el iterador
		*/
//		System.out.println(c1.getInscriptos().size()+"\n");
//		System.out.println(c2.getInscriptos().size()+"\n");
//		System.out.println(c3.getInscriptos().size()+"\n");
//		System.out.println(c4.getInscriptos().size()+"\n");
//		System.out.println(c5.getInscriptos().size()+"\n");
//		System.out.println(c6.getInscriptos().size()+"\n");	 
		
		System.out.println("Curso 1:");
		c1.imprimirInscriptos(1);
		System.out.println("\n");
		System.out.println("Curso 2:");
		c2.imprimirInscriptos(0);//ira al default en el switch
		System.out.println("\n");
		System.out.println("Curso 3:");
		c3.imprimirInscriptos(-3);//ira al default en el switch
		System.out.println("\n");
		System.out.println("Curso 4:");
		c4.imprimirInscriptos(2);
		System.out.println("\n");
		System.out.println("Curso 5:");
		c5.imprimirInscriptos(2);
		System.out.println("\n");
		System.out.println("Curso 6:");
		c6.imprimirInscriptos(1);
		
	}
}
