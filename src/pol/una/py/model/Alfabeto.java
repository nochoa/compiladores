package pol.una.py.model;

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
	private List<String> language;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLanguage(List<String> language) {
		this.language = language;
	}

	public List<String> getLanguage() {
		return language;
	}
}
