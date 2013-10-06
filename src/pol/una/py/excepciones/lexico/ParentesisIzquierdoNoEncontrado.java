/**
 * 
 */
package pol.una.py.excepciones.lexico;

/**
 * Es utilizada para indicar que no se ha encontrado el parentesis izquierdo.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public class ParentesisIzquierdoNoEncontrado extends AnalizadorLexicoException {

	private static final long serialVersionUID = 1L;

	public ParentesisIzquierdoNoEncontrado(String msg) {
		super(msg);
	}

	public ParentesisIzquierdoNoEncontrado() {
		super("Se experaba '(' ");
	}
}
