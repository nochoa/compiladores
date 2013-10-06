package pol.una.py.model.base;

import java.util.Arrays;
import java.util.List;

/**
 * Representa un alfabeto del lenguaje
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class Alfabeto {
	private List<String> simbolos;

	/**
	 * @param simbolos
	 *            Caracteres que pertenecen al alfabeto
	 */
	public Alfabeto(List<String> simbolos) {
		super();
		this.simbolos = simbolos;
	}

	public Alfabeto(String stream) {
		this.simbolos = Arrays.asList(stream.substring(1, stream.length() - 1)
				.split(","));

	}

	/**
	 * Valida si una cadena pertenece al alfabeto
	 * 
	 * @param value
	 *            Valor que se desea validar
	 * @return
	 */
	public boolean pertenece(String value) {
		return simbolos.contains(value);
	}

	public void setSimbolos(List<String> simbolos) {
		this.simbolos = simbolos;
	}

	public List<String> getSimbolos() {
		return simbolos;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String symbol : simbolos) {
			sb.append(symbol);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");

		return sb.toString();
	}

}
