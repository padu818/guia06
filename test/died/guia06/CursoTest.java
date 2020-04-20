package died.guia06;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;



class CursoTest {
	Alumno a1,a2,a3,a4,a5,a6;
	Curso c1,c2,c3,c4,c5;
	
	
	@BeforeEach
	public void init() {
		a1 = new Alumno("Pedro",1);
		a2 = new Alumno("Jose",2);
		a3 = new Alumno("Juan",3);
		a4 = new Alumno("Maria",4);
		a5 = new Alumno("Belen",5);
		a6 = new Alumno("Pablo", 6);
		c1 = new Curso(1,1,5,6,0);
		c2 = new Curso(2,1,2,3,0);
		c3 = new Curso(3,1,5,3,0);
		c4 = new Curso(4,1,5,3,0);
		c5 = new Curso(5,2,10,3,3);
		a1.aprobar(c3);
		c4.setNombre("Matematica");c3.setNombre("Biologia");
		c1.inscribir(a1);c1.inscribir(a2);c1.inscribir(a3);c1.inscribir(a4);c1.inscribir(a5);
		c2.inscribir(a2);c3.inscribir(a2); //alumno a2 ya esta en 3 materias de un mismo ciclo.
		c4.inscribir(a4);
	}
	
	@Test
	void testInscribir() {
		assertTrue(c3.inscribir(a5));
	}
	
	@Test
	void testInscribirCursoConCreditosNecesarios() {
		assertTrue(c5.inscribir(a1)); //inscribir a un curso de 2do /cantidad crditos requeridos permitidos.
	}

	
	@Test
	
	void testNoInscribirCursoAprobado() {
		assertFalse(c3.inscribir(a1));
	}
	
	@Test
	
	void testNoInscribirCursoIncripto() {
		assertFalse(c4.inscribir(a4));
	}
	
	
	@Test
	
	void testNoInscribirCicloElectivoMaximo() {
		assertFalse(c4.inscribir(a2));
	}
	
	@Test
	
	void testNoInscribirCreditosNecesarios() {
		assertFalse(c5.inscribir(a5));
	}
	
	@Test
	void testNoInscribirCuposMaximos() {
		assertFalse(c1.inscribir(a6));
	}
	
	
	@Test
	void testImprimirInscriptosPorLegajo() {

		c1.imprimirInscriptos(1);
	}
	@Test
	void testImprimirInscriptosPorCreditos() {
		c1.imprimirInscriptos(2);
	}
	@Test
	void testImprimirInscriptosPorNombre() {
		c1.imprimirInscriptos(0);
	}
	
	
	
	@Test
	   void testInscribirAlumno() { 
		c3.inscribirAlumno(a5);
		assertEquals(2, c3.getInscriptos().size());
	    }
	
	@Test
	void testInscribirAlumnoCursoConCreditosNecesarios() {
		c5.inscribirAlumno(a1); //inscribir a un curso de 2do /cantidad crditos requeridos permitidos.
		assertEquals(1, c5.getInscriptos().size());
	}
	
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
//	
	

	 @Test
	 public void testExcepcionesNoInscribirCuposMaximo() { 
		 InscribirException thrown = assertThrows(InscribirException.class,  () -> c1.inscribirAlumno(a6));
	      assertEquals("Los cupos de la clase ("+c1.getCupo()+") ya estan completos.\n", thrown.getMessage());
	    }
	 @Test
	 public void testExcepcionesNoInscribirCreditosNecesarios() { 
		 InscribirException thrown = assertThrows(InscribirException.class,  () -> c5.inscribirAlumno(a5));
	      assertEquals("Los creditos del alumno ("+a5.creditosObtenidos()+") no le alcanza para inscribirse al Curso cuyo creditos requeridos son de "+c5.getCreditosRequeridos()+".\n", thrown.getMessage());
	    }
	 @Test
	 public void testExcepcionesNoInscribirCicloElectivoMaximo() { 
		 InscribirException thrown = assertThrows(InscribirException.class,  () -> c4.inscribirAlumno(a2));
	      assertEquals("La cantidad de cursos incriptos en un mismo ciclo ("+a5.cantidadCursoEnCicloElectivo(c5.getCicloLectivo())+") es mayor o igual al permitido ("+3+").\n", thrown.getMessage());
	    }
	 @Test
	 public void testExcepcionesNoInscribirCursoInscripto() { 
		 InscribirException thrown = assertThrows(InscribirException.class,  () -> c4.inscribirAlumno(a4));
	      assertEquals("El curso "+c4.getNombre()+" esta incripto.\n", thrown.getMessage()); //dara el nombre de la clase null porque no se le asigno ningun nombre
	    }
	 @Test
	 public void testExcepcionesNoInscribirCursoAprobado() { 
		 InscribirException thrown = assertThrows(InscribirException.class,  () -> c3.inscribirAlumno(a1));
	      assertEquals("El curso "+c3.getNombre()+" esta aprobado.\n", thrown.getMessage());
	    }
	
	
}
