package died.guia06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.runners.Parameterized.Parameter;

import died.guia06.util.Registro;


/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo; 
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;
	
	private Registro log;
	
	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();

	}
	/**
	@param codigo id de la clase
	@param cicloL Ciclo electivo de la clase
	@param cup Cupo de alumnos permitidos
	@param credit Cantidad creditos del curso
	@param creditosReq Creditos necesarios para poder cursarla
	*/
	public Curso(Integer codigo, Integer cicloL,Integer cup, Integer credit, Integer creditosReq) {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
		id=codigo;
		cicloLectivo= cicloL;
		cupo = cup;
		creditos = credit;
		creditosRequeridos = creditosReq;
	}
	

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 */
	

	public Boolean inscribir(Alumno a) {
		try {
			Boolean validarCreditosRequeridos = false;
			Boolean validarCupos =false;
			Boolean validarMateriaAprobada = false;
			Boolean validarMateriaIncripta = false;

			if(creditosRequeridos > a.creditosObtenidos()) {
				validarCreditosRequeridos = true;
			}
			if(this.getInscriptos().size() >= cupo) {
				validarCupos = true;
			}

			for(Curso c: a.getAprobados()) {
				if(c.getId() == this.getId())
					validarMateriaAprobada = true;
			}
			for(Curso c: a.getCursando()) {
				if(c.getId() == this.getId())
					validarMateriaIncripta = true;
			}
			if(validarMateriaAprobada == false || validarMateriaIncripta == false || validarCupos == false || validarCreditosRequeridos == false) {
				log.registrar(this, "inscribir ",a.toString());
				a.inscripcionAceptada(this);
				return true; //debido a que se pudo inscribir en materia
			}
			//no escribo ningun mensaje para luego realizar un mensaje detallado de todos los posibles errores.
			//debido a no saber si a la hora de inscribirse a una materia no permiten que se inscriban en una materia ya aprobada o que esta incripto, decidi poner los metodos de validacion.
		}catch (Exception e) {
			System.out.println("Error al inscribir" + e.getMessage());
		}
		return false;
	}
	
	
	/**
	 * 
	 * Imprimir un curso: se debe poder imprimir el listado de inscriptos ordenados alfab�ticamente, 
	por numero de libreta universitaria, o por cantidad de cr�ditos obtenidos.
	 */
	public void imprimirInscriptos(Integer opcion) {
		try {
			switch(opcion) {
			case 1:{	Collections.sort(this.inscriptos, (s1,s2)-> s1.getNroLibreta().compareTo(s2.getNroLibreta()));
						System.out.println(this.inscriptos);
						break;
			}
			case 2:{
						Collections.sort(this.inscriptos, (s1,s2)-> s1.creditosObtenidos().compareTo(s2.creditosObtenidos()));
						System.out.println(this.inscriptos);
						break;
			}
			default:{ Collections.sort(this.inscriptos, new ComparaAlumnosPorNombre());
					System.out.println(this.inscriptos);}
			}
//			
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}catch(Exception e) {
			System.out.println ("Error al inscribir" + e.getMessage());
		}
	}
	
	
	//
	
	//get and set
	public Integer getId() {
		return id;
	}
	
	public Integer getCicloLectivo() {
		return cicloLectivo;
	}



	public void setCicloLectivo(Integer cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Integer getCupo() {
		return cupo;
	}



	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}



	public List<Alumno> getInscriptos() {
		return inscriptos;
	}



	public void setInscriptos(List<Alumno> inscriptos) {
		this.inscriptos = inscriptos;
	}



	public Integer getCreditos() {
		return creditos;
	}



	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}



	public Integer getCreditosRequeridos() {
		return creditosRequeridos;
	}



	public void setCreditosRequeridos(Integer creditosRequeridos) {
		this.creditosRequeridos = creditosRequeridos;
	}



	public Registro getLog() {
		return log;
	}



	public void setLog(Registro log) {
		this.log = log;
	}




}
