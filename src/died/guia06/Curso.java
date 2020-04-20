package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



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
	


	public Boolean inscribir(Alumno a) {
		try {
			Boolean validarCreditosRequeridos = false;
			Boolean validarCupos = false; 
			Boolean validarMateriaAprobada = false;
			Boolean validarMateriaIncripta = false;
			Boolean validarCursosMismoCiclo = false;
			
			if(a.cantidadCursoEnCicloElectivo(this.getCicloLectivo()) >= 3) {
				validarCursosMismoCiclo = true;
			}
			
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
			if(validarMateriaAprobada == false && validarCursosMismoCiclo == false && validarMateriaIncripta == false && validarCupos == false && validarCreditosRequeridos == false) {
				inscriptos.add(a);
				a.inscripcionAceptada(this);
				log.registrar(this, "inscribir ",a.toString());
				return true; //debido a que se pudo inscribir en materia
			}
			
		}catch (Exception e) {
			System.out.println("Error al inscribir" + e.getMessage());
		}
		return false;
	}
	

	
	public void inscribirAlumno(Alumno a) {
		try {
			Boolean validarCursoAprobada = false;
			Boolean validarCursoIncripto = false;
			tieneCreditoRequerido(a.creditosObtenidos());
			tieneCupos(this.getInscriptos().size());
			tieneCursoEnCiclo(a.cantidadCursoEnCicloElectivo(cicloLectivo));
			for(Curso c: a.getAprobados()) {
				if(c.getId() == this.getId())
					validarCursoAprobada = true;
			}
			tieneCursoAprobada(validarCursoAprobada);
			for(Curso c: a.getCursando()) {
				if(c.getId() == this.getId())
					validarCursoIncripto = true;
			}
			tieneCursoIncripto(validarCursoIncripto);
			try {
				log.registrar(this, "inscribir ",a.toString()); // no se como relanzarla, pense en modificar la clase registro haciendo que el throw sea RegistroAduitoriaException pero decidi no tocar esa clase.
				inscriptos.add(a);
				a.inscripcionAceptada(this);
			}
			catch(IOException rae) {
				System.out.println("No se hizo el registro" + rae.getMessage());
			}
			
		}catch (InscribirException e) {
			System.out.println("No se hizo la incripcion \n" + e.getMessage());
		}
	}
	

	public void imprimirInscriptos(Integer opcion) {
		try {
			switch(opcion) {
			case 1:{	
						//en forma ascendente por numero de legajo
					Collections.sort(this.inscriptos, new ComparaAlumnoPorLegajo());
					System.out.println(this.inscriptos);
						break;
			}
			case 2:{
						//en forma ascendente por cantidad de creditos
					Collections.sort(this.inscriptos, new ComparaAlumnoConCredito());
					System.out.println(this.inscriptos);
						break;
			}
			default:{ 
					//en forma ascendente por nombre
					Collections.sort(this.inscriptos, new ComparaAlumnosPorNombre());
					System.out.println(this.inscriptos);}
			}		
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}catch(Exception e) {
			System.out.println ("Error al inscribir" + e.getMessage());
		}
	}
	
	//metodos de exception, cree una clase InscribirExcepcion para ordenarlo, se podia directamente usar extends Exception.
	public void tieneCreditoRequerido(Integer credito) throws InscribirException{
		if(credito < creditosRequeridos) throw new InscribirException("Los creditos del alumno ("+credito+") no le alcanza para inscribirse al Curso cuyo creditos requeridos son de "+creditosRequeridos+".\n");
	}
	
	public void tieneCupos(Integer c) throws InscribirException{
		if(c >= cupo) throw new InscribirException("Los cupos de la clase ("+cupo+") ya estan completos.\n");
	}
	
	public void tieneCursoEnCiclo(Integer curs) throws InscribirException {
		if(curs >= 3) throw new InscribirException("La cantidad de cursos incriptos en un mismo ciclo ("+curs+") es mayor o igual al permitido "+ 3+".\n");
	}
	
	public void tieneCursoAprobada(Boolean valor) throws InscribirException {
		if(valor) throw new InscribirException("El curso "+nombre+" esta aprobada.\n");
	}
	
	public void tieneCursoIncripto(Boolean valor) throws InscribirException{
		if(valor) throw new InscribirException("El curso "+nombre+" esta incripto.\n");
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
