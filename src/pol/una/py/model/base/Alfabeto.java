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
	private List<String> symbols;

	/**
	 * @param symbols
	 *            Caracteres que pertenecen al alfabeto
	 */
	public Alfabeto(List<String> symbols) {
		super();
		this.symbols = symbols;
	}

	public Alfabeto(String stream) {
		this.symbols = Arrays.asList(stream.substring(1, stream.length() - 1)
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
		return symbols.contains(value);
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String symbol : symbols) {
			sb.append(symbol);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");

		return sb.toString();
	}

}
