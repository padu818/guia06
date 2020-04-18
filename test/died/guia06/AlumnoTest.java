package died.guia06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlumnoTest {
	Alumno a1;
	Curso c1,c2,c3,c4,c5;
	
	@BeforeEach
	public void init(){
		a1 = new Alumno("Pedro",23);
		c1 = new Curso();
		c2 = new Curso();
		c3 = new Curso();
		c4 = new Curso();
		c5 = new Curso();
		c1.setId(1);c1.setCreditos(6);
		c2.setId(2);c2.setCreditos(6);
		c3.setId(3);c3.setCreditos(3);
		c4.setId(4);c4.setCreditos(6);
		c5.setId(5);c5.setCreditos(3);
		a1.getCursando().add(c1);
		a1.getCursando().add(c2);
		a1.getCursando().add(c3);
		a1.getAprobados().add(c5);

	}
	
	@Test
	void testCreditosObtenidos() {
		//aprobo materia c5 por lo tanto son 3 puntos.. luego agrego otra materia para verificar resultado
		Integer esperado = 3;
		assertEquals(esperado, a1.creditosObtenidos());
		esperado = 9;
		a1.aprobar(c1); //nose si esta bien debido a que pruebo un metodo de prueba en diferente test
		assertEquals(esperado, a1.creditosObtenidos());
	}


	@Test
	void testAprobar() {
		//debe tener como resultado final 2 materias aprobadas y dos cursando
		Integer esperadoCursado = 2;
		Integer esperadoAprobado = 2;
		a1.aprobar(c1);
		assertEquals(esperadoCursado, a1.getCursando().size());
		assertEquals(esperadoAprobado, a1.getAprobados().size());
	}

	@Test
	void testInscripcionAceptada() {
		//debe tener 4 materias en estado "cursado" 3 debido a la init y uno mas por el metodo
		Integer esperado = 4;
		a1.inscripcionAceptada(c4);
		assertEquals(esperado, a1.getCursando().size());
	}

}
