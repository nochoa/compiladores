/**
 * 
 */
package pol.una.py.excepciones.lexico;

/**
 * Se utiliza para indicar que no se pudo obtener el siguiente token de la
 * expresión regular.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public class MatchingFailure extends AnalizadorLexicoException {

	private static final long serialVersionUID = 1L;

	public MatchingFailure(String msg) {
		super(msg);
	}

	public MatchingFailure() {
		super("Error al obtener el siguiente token");
	}
}
