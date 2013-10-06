/**
 * 
 */
package pol.una.py.model.lexico;

/**
 * Clase que representa una secuencia de caracteres denominada token.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 02/10/2013
 */
public class Token {
	private TipoToken tipo;
	private String value;

	/**
	 * Recibe un simbolo válido y lo convierte en un token.
	 * 
	 * @param value
	 */
	public Token(String value) {
		this.value = value;
		setTipo(value);
	}

	public TipoToken getTipo() {
		return tipo;
	}

	public boolean isEquals(Token token) {
		return tipo.equals(token.getTipo()) && value.equals(token.getValue());
	}

	public void setTipo(String value) {
		if (value.isEmpty()) {
			this.tipo = TipoToken.FIN;
		} else {

			switch (value) {
			case "*":
				this.tipo = TipoToken.CERRADURA_KLEENE;
				break;
			case "+":
				this.tipo = TipoToken.CERRADURA_KLEENE_POSITIVE;
				break;
			case "?":
				this.tipo = TipoToken.ALTERNATIVE;
				break;
			case "|":
				this.tipo = TipoToken.OR;
				break;
			case "(":
				this.tipo = TipoToken.PARENTESIS_LEFT;
				break;
			case ")":
				this.tipo = TipoToken.PARENTESIS_RIGTH;
				break;
			default:
				this.tipo = TipoToken.VALUE;
				break;
			}
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
