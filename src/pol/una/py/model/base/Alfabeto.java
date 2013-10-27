package pol.una.py.model.base;

import java.util.ArrayList;
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
	private String name;
	private List<String> symbols;
	public static final List<String> ROMANOS = new ArrayList<String>(
			Arrays.asList("I,II,III,IV,V,VI,VII,VIII,IX,X,XI,XII,XIII,XIV,XV,XVI,XVII,XVIII,XIX,XX,XXI,XXII,XXIII,XXIV,XXV,XXX"
					.split(",")));
	public static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	public static final String LETRAS_MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETRAS = LETRAS_MINUSCULAS + LETRAS_MAYUSCULAS;
	public static final String DIGITOS = "0123456789";

	public Alfabeto(String stream) {
		symbols = new ArrayList<String>();
		for (int i = 0; i < stream.length(); i++) {
			this.symbols.add(String.valueOf(stream.charAt(i)));
		}
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
	
	public String arrayByString() {
		StringBuilder sb = new StringBuilder();
		for (String symbol : symbols) {
			sb.append(symbol);
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
