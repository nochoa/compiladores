/**
 * 
 */
package pol.una.py.model.lexico;

/**
 * Representa la definición de una expresión regular
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 30/09/2013
 */
public class ProduccionBNF {
	// Identificador de la produccion, sirve para identificar de manera univoca
	// a cada produccion
	private String identificador;
	// Representa el termino izquierdo de la expresión regular
	private String name;
	// Representa el termino derecho de la expresión regular
	private ExpresionRegular expresionRegular;

	/**
	 * @param name
	 * @param expresionRegular
	 */
	public ProduccionBNF(String identificador, String name,
			ExpresionRegular expresionRegular) {
		super();
		this.identificador = identificador;
		this.name = name;
		this.expresionRegular = expresionRegular;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExpresionRegular getExpresionRegular() {
		return expresionRegular;
	}

	public void setExpresionRegular(ExpresionRegular expresionRegular) {
		this.expresionRegular = expresionRegular;
	}

	/**
	 * Impresion de la produccion.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(" --> ");
		sb.append(getExpresionRegular().toString());
		return sb.toString();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

}
