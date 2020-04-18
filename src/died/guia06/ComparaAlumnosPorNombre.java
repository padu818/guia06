/**
 * 
 */
package died.guia06;

import java.util.Comparator;


public class ComparaAlumnosPorNombre implements Comparator<Alumno> {
		public int compare(Alumno a, Alumno b) {
			return a.getNombre().compareTo(b.getNombre());
		}
}
