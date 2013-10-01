/**
 * 
 */
package pol.una.py.model.construccion;


/**
 * Representa la definición de una expresión regular
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 30/09/2013
 */
public class ProduccionBNF {
	// Representa el termino izquierdo de la expresión regular
	private String identificador;
	// Representa el termino derecho de la expresión regular
	private ExpresionRegular expresionRegular;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String left) {
		this.identificador = left;
	}

	public ExpresionRegular getExpresionRegular() {
		return expresionRegular;
	}

	public void setExpresionRegular(ExpresionRegular expresionRegular) {
		this.expresionRegular = expresionRegular;
	}

}
