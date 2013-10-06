/**
 * 
 */
package pol.una.py.model.base;

/**
 * Representa los caracteres especiales utilizados
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public enum Reservadas {
	CONCATENATE("."), OR("|"), CERRADURA_KLEENE("*"), CERRADURA_KLEENE_POSITIVE(
			"+"), ALTERNATIVE("?"), PARENTESIS_LEFT("("), PARENTESIS_RIGTH(")"), ;
	private String value;

	Reservadas(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	/**
	 * Valida si el valor pasado como parametro es un caracter especial.
	 * 
	 * @param value
	 *            Valor a validar
	 * @return
	 */
	public static boolean isValid(String value) {
		if (value != null) {
			for (Reservadas caracter : Reservadas.values()) {
				if (value.equalsIgnoreCase(caracter.value)) {
					return true;
				}
			}
		}
		return false;
	}

}
