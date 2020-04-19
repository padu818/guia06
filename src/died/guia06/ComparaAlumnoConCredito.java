package died.guia06;

import java.util.Comparator;

public class ComparaAlumnoConCredito implements Comparator<Alumno> {

	@Override
	public int compare(Alumno a, Alumno b) {
		return a.creditosObtenidos().compareTo(b.creditosObtenidos());
	}

}
