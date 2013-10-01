package pol.una.py.model.base;

/**
 * Representa una tabla de transiciones
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class TablaTransicion {
	private Estado[] estados;
	private String[] simbolos;

	/**
	 * Crea una tabla de transiciones
	 * 
	 * @param cantEstados
	 *            Cantidad de estados
	 * @param cantSimbolos
	 *            Cantidad de simbolos
	 */
	public TablaTransicion(int cantEstados, int cantSimbolos) {
		estados = new Estado[cantEstados];
		simbolos = new String[cantSimbolos];
	}

	public String[] getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(String[] simbolos) {
		this.simbolos = simbolos;
	}

	public Estado[] getEstados() {
		return estados;
	}

	public void setEstados(Estado[] estados) {
		this.estados = estados;
	}

}
