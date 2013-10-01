/**
 * 
 */
package pol.una.py.model.construccion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.base.Operacion;

/**
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 30/09/2013
 */
public class ExpresionRegular {

	private String expresion;
	private HashMap<String, Alfabeto> alfabetos;

	/**
	 * Crea una expresión regular sin caracteres el blanco al principio y al
	 * final del objeto
	 * 
	 * @param expresion
	 *            Expresión regular
	 * @param alfabetos
	 *            Lista de afabetos asociados a la expresión regular
	 */
	public ExpresionRegular(String expresion,
			HashMap<String, Alfabeto> alfabetos) {

		this.expresion = expresion.trim();
		this.alfabetos = alfabetos;
	}

	/**
	 * Procesa la expresion regular y retorna la lista de tokens asociados a la
	 * misma
	 * 
	 * @return Lista de tokens
	 */
	public List<Token> procesar() {
		List<Token> tokens = new ArrayList<Token>();
		StringBuilder value = new StringBuilder();

		for (int i = 0; i < expresion.length(); i++) {
			value.append(expresion.charAt(i));

			// TODO validar parentesis concatenados
			if (expresion.charAt(i) == '(') {
				tokens.add(createToken(value, Operacion.PARENTESIS_LEFT));
				value = new StringBuilder();

				StringBuilder sb = new StringBuilder();
				boolean next = true;
				for (int j = i + 1; next == true; i++) {
					if (expresion.charAt(i) != ')') {
						sb.append(expresion.charAt(j));
					} else {
						tokens.add(createToken(sb, Operacion.PARENTESIS_RIGHT));
						next = false;
					}
				}
			}
			if (expresion.charAt(i) == '.') {
				tokens.add(createToken(value, Operacion.CONCATENATE));
				value = new StringBuilder();
			}
			if (expresion.charAt(i) == '|') {
				tokens.add(createToken(value, Operacion.ALTERNATIVE));
				value = new StringBuilder();
			}
			if (expresion.charAt(i) == '*') {
				tokens.add(createToken(value, Operacion.CERRADURA_KLEENE));
				value = new StringBuilder();
			}
			if (expresion.charAt(i) == '+') {
				tokens.add(createToken(value,
						Operacion.CERRADURA_KLEENE_POSITIVE));
				value = new StringBuilder();

			}
			if (expresion.charAt(i) == '?') {
				tokens.add(createToken(value, Operacion.ALTERNATIVE));
				value = new StringBuilder();
			}

		}
		// Agregamos el último token
		if (value.toString().length() > 0) {
			tokens.add(createToken(value, null));
		}
		return tokens;
	}

	/**
	 * Construye un token
	 * 
	 * @param value
	 *            Valor del token
	 * @param operacion
	 *            Operación encontrada al final del token
	 * @return Token formado correctamente
	 */
	private Token createToken(StringBuilder value, Operacion operacion) {
		Token token = new Token();
		value.deleteCharAt(value.length() - 1);
		token.setValue(value.toString());
		token.setOperacionSiguiente(operacion);
		return token;

	}

	public String getExpresion() {
		return expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}

	public HashMap<String, Alfabeto> getAlfabetos() {
		return alfabetos;
	}

	public void setAlfabetos(HashMap<String, Alfabeto> alfabetos) {
		this.alfabetos = alfabetos;
	}
}
