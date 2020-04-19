package died.guia06;

import java.util.Comparator;

public class ComparaAlumnoPorLegajo implements Comparator<Alumno> {

	public int compare(Alumno a, Alumno b) {
			return a.getNroLibreta().compareTo(b.getNroLibreta());
	}

}
