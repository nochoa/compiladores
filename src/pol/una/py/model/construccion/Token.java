/**
 * 
 */
package pol.una.py.model.construccion;

import pol.una.py.model.base.Operacion;

/**
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 30/09/2013
 */
public class Token {
	// Debe corresponderse con el name del alfabeto
	private String value;
	private Operacion operacionSiguiente;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Operacion getOperacionSiguiente() {
		return operacionSiguiente;
	}

	public void setOperacionSiguiente(Operacion operacionSiguiente) {
		this.operacionSiguiente = operacionSiguiente;
	}
}
