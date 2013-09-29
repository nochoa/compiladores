package pol.una.py.model;

import java.util.List;

/**
 * Representa la definición de una expresión regular
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class ExpresionRegular {
	// Representa el termino izquierdo de la expresión regular
	private String left;
	// Representa el termino derecho de la expresión regular
	private List<String> right;

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public List<String> getRight() {
		return right;
	}

	public void setRight(List<String> right) {
		this.right = right;
	}

}
