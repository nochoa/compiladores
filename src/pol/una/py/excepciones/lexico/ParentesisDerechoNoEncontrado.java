/**
 * 
 */
package pol.una.py.excepciones.lexico;

/**
 * Es utilizada para indicar que se ha encontrado el parentesis derecho
 * esperado.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public class ParentesisDerechoNoEncontrado extends AnalizadorLexicoException {

	private static final long serialVersionUID = 1L;

	public ParentesisDerechoNoEncontrado(String msg) {
		super(msg);
	}

	public ParentesisDerechoNoEncontrado() {
		super("Se esperaba ')' ");
	}
}
