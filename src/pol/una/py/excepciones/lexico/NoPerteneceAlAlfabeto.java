/**
 * 
 */
package pol.una.py.excepciones.lexico;

/**
 * Se utiliza para indicar que un token especifico no pertenece al alfabeto
 * asociado.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public class NoPerteneceAlAlfabeto extends AnalizadorLexicoException {

	private static final long serialVersionUID = 1L;

	public NoPerteneceAlAlfabeto(String msg) {
		super(msg);
	}

	public NoPerteneceAlAlfabeto() {
		super("El simbolo no pertenece al alfabeto");
	}
}
