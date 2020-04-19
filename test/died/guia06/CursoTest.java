package died.guia06;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
}
