package pol.una.py.model.base;

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
	private List<String> simbolos;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSimbolos(List<String> simbolos) {
		this.simbolos = simbolos;
	}

	public List<String> getSimbolos() {
		return simbolos;
	}
}
