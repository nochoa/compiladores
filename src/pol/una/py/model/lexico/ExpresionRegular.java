package pol.una.py.model.lexico;

/**
 * Clase que representa una expresion regular.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 30/09/2013
 */
public class ExpresionRegular {

	private String value;

	/**
	 * Crea una expresión regular.
	 * 
	 * @param expresion
	 *            Expresión regular
	 */
	public ExpresionRegular(String expresion) {

		this.value = expresion.trim();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String expresion) {
		this.value = expresion;
	}

	/**
	 * Impresion de la expresion regular.
	 */
	@Override
	public String toString() {

		return value;
	}
}
