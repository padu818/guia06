package died.guia06;

import java.io.IOException;

public class RegistroAuditoriaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RegistroAuditoriaException() throws IOException {
		super("Registro de Aditoria \n");
	}
}
