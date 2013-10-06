package pol.una.py.model.lexico;

import java.util.Map;

import pol.una.py.excepciones.lexico.NoPerteneceAlAlfabeto;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.base.Reservadas;

/**
 * Clase utilizada para leer el flujo de caracteres de la expresion regular y
 * recolectar secuencias de caracteres en unidades significativas denominadas
 * tokens.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 03/10/2013
 */
public class HelperLexico {
	private ProduccionBNF produccion;
	private StringBuilder buffer;
	private Map<String, Alfabeto> alfabetos;
	private Alfabeto alfabeto;
	private boolean unAlfabeto;

	/**
	 * Crea un parseador para una produccion con mas de un alfabeto.
	 * 
	 * @param produccion
	 *            Produccion que se desea analizar.
	 * @param alfabetos
	 *            Lista de alfabetos.
	 */
	public HelperLexico(ProduccionBNF produccion,
			Map<String, Alfabeto> alfabetos) {
		this.produccion = produccion;
		this.alfabetos = alfabetos;
		this.unAlfabeto = false;
		this.buffer = new StringBuilder(produccion.getExpresionRegular()
				.getValue());

	}

	/**
	 * Crea un parseador para una produccion con un solo alfabeto.
	 * 
	 * @param produccion
	 *            Produccion que se desea analizar.
	 * @param alfabeto
	 *            Alfabeto asociado a la expresion regular.
	 */
	public HelperLexico(ProduccionBNF produccion, Alfabeto alfabeto) {
		this.produccion = produccion;
		this.alfabeto = alfabeto;
		this.unAlfabeto = true;
		this.buffer = new StringBuilder(produccion.getExpresionRegular()
				.getValue());

	}

	/**
	 * Retorna el siguiente token de la expresión regular que debe ser
	 * consumido.
	 * 
	 * @return Siguiente token.
	 * @throws NoPerteneceAlAlfabeto
	 */
	public Token next() throws NoPerteneceAlAlfabeto {
		Token token;
		String value = getNextValueToken();

		// Consumimos los espacion en blanco o tabuladores
		if (value.equalsIgnoreCase(" ") || value.equalsIgnoreCase("\t")) {
			token = next();

			// Fin de la expresion regular
		} else if (value.equals("")) {
			token = new Token("");
			// Si es una operacion o un token
		} else if (Reservadas.isValid(value) || isValid(value)) {
			token = new Token(value);

		} else {
			throw new NoPerteneceAlAlfabeto(value);
		}

		return token;
	}

	/**
	 * Obtiene el valor del siguiente token a procesar
	 * 
	 * @return
	 */
	public String getNextValueToken() {
		if (buffer.length() > 0) {
			String value = Character.toString(buffer.charAt(0));
			// Cada caracter es un token
			if (unAlfabeto) {
				buffer.deleteCharAt(0);
				return value;

			} else {
				if (value.equals("[")) {
					// Cada valor dentro de corchetes es un token.
					int init = buffer.indexOf("[");
					int fin = buffer.indexOf("]");
					value = buffer.substring(init + 1, fin);
					buffer.delete(init, fin + 1);
					return value;
				} else {
					buffer.deleteCharAt(0);
					return value;

				}

			}
		} else {
			// Fin de la expresion regular
			return "";
		}
	}

	/**
	 * Valida si los elementos de cada produccion no utilizan simbolos fuera del
	 * alfabeto.
	 * 
	 * @param value
	 * @return
	 */
	public boolean isValid(String value) {
		// Si posee un solo alfabeto la validacion se realiza solbre un unico
		// alfabeto
		if (unAlfabeto) {
			return alfabeto.pertenece(value);

		} else {
			// Si no la validacion se realiza por cada alfabeto asociado.
			for (Map.Entry<String, Alfabeto> entry : alfabetos.entrySet()) {
				if (entry.getKey().equals(value)) {
					return true;
				}
			}
			return false;

		}

	}

	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
	}

	public boolean isUnAlfabeto() {
		return unAlfabeto;
	}

	public void setUnAlfabeto(boolean unAlfabeto) {
		this.unAlfabeto = unAlfabeto;
	}

	public StringBuilder getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuilder buffer) {
		this.buffer = buffer;
	}

	public Map<String, Alfabeto> getAlfabetos() {
		return alfabetos;
	}

	public void setAlfabetos(Map<String, Alfabeto> alfabetos) {
		this.alfabetos = alfabetos;
	}

	public ProduccionBNF getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionBNF produccion) {
		this.produccion = produccion;
	}

}