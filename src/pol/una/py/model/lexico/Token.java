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
	private TipoToken type;
	private String value;

	/**
	 * Recibe un simbolo válido y lo convierte en un token.
	 * 
	 * @param value
	 */
	public Token(String value) {
		this.value = value;
		setType(value);
	}

	public TipoToken getType() {
		return type;
	}

	public boolean isEquals(Token token) {
		return type.equals(token.getType()) && value.equals(token.getValue());
	}

	public void setType(String value) {
		if (value.isEmpty()) {
			this.type = TipoToken.FIN;
		} else {

			switch (value) {
			case "*":
				this.type = TipoToken.CERRADURA_KLEENE;
				break;
			case "+":
				this.type = TipoToken.CERRADURA_KLEENE_POSITIVE;
				break;
			case "?":
				this.type = TipoToken.ALTERNATIVE;
				break;
			case "|":
				this.type = TipoToken.OR;
				break;
			case "(":
				this.type = TipoToken.PARENTESIS_LEFT;
				break;
			case ")":
				this.type = TipoToken.PARENTESIS_RIGTH;
				break;
			default:
				this.type = TipoToken.VALUE;
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
