package died.guia06;

import java.util.List;


public class Alumno {

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;

	public int creditosObtenidos() {
		int creditos = 0;
		for(Curso cursos: this.getAprobados() ) {
			creditos += cursos.getCreditos();
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
	
	/*
	- Implementar el método equals, estableciendo que 2 alumnos son iguales si tienen el mismo número de libreta universitaria.
	- Implementar la interface comparable, estableciendo que la comparación de 2 alumnos se hace alfabéticamente por el atributo nombre.
	- Además, puede crear otros métodos que considere necesarios (getters / setters / constructores).
	 */
	
	
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
