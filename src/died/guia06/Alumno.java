package died.guia06;

import java.util.ArrayList;
import java.util.List;


public class Alumno {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;
	
	
	public Alumno() {
		super();
		aprobados = new ArrayList<Curso>();
		cursando = new ArrayList<Curso>();
	}
	
	public Alumno(String nombr, Integer nroLib) {
		super();
		aprobados = new ArrayList<Curso>();
		cursando = new ArrayList<Curso>();
		nombre = nombr;
		nroLibreta = nroLib;
	}

	public Integer creditosObtenidos() {
		Integer creditos = 0;	
		
		for (Curso c : aprobados) {
			creditos += c.getCreditos();
		}
		return creditos;
	}

	public void aprobar(Curso c) {
		this.getAprobados().add(c);
		this.getCursando().remove(c);
	}
	
	
	
	public void inscripcionAceptada(Curso c) {
		this.getCursando().add(c);
	}
	
	
	//metodo creado para simplificar el metodo incribir de la clase CURSO
	public Integer cantidadCursoEnCicloElectivo(Integer ciclo) {
		Integer valor = 0;
		for(Curso c: cursando) {
			if(c.getCicloLectivo() == ciclo) {
				valor++;
			}
		}
		return valor;
	}
	
	//no es necesario comparar clases debido a que es un metodo de la clase ALUMNO y porque el parametro solo permite del mismo tipo.
	public Boolean equals(Alumno a) {
		return this.getNroLibreta() == a.getNroLibreta();
	}
	
	
	
	
	
	//getters and setters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroLibreta() {
		return nroLibreta;
	}

	public void setNroLibreta(Integer nroLibreta) {
		this.nroLibreta = nroLibreta;
	}

	public List<Curso> getCursando() {
		return cursando;
	}

	public void setCursando(List<Curso> cursando) {
		this.cursando = cursando;
	}

	public List<Curso> getAprobados() {
		return aprobados;
	}

	public void setAprobados(List<Curso> aprobados) {
		this.aprobados = aprobados;
	}


	

}
