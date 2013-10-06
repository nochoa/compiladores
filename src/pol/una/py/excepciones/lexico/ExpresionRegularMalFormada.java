/**
 * 
 */
package pol.una.py.excepciones.lexico;

/**
 * Es utilizada para indicar que la expresión regular no pudo analizada por el
 * analizador lexico debido a que la misma no se encuentra formada
 * correctamente.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public class ExpresionRegularMalFormada extends AnalizadorLexicoException {

	private static final long serialVersionUID = 1L;

	public ExpresionRegularMalFormada(String msg) {
		super(msg);
	}

	public ExpresionRegularMalFormada() {
		super("La expresión regular esta mal formada");
	}
}
