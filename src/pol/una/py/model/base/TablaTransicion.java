package pol.una.py.model.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una tabla de transiciones.
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class TablaTransicion {
	private List<Estado> estados;
	private List<String> simbolos;

	public TablaTransicion() {
		estados = new ArrayList<Estado>();
		simbolos = new ArrayList<String>();
	}

	/**
	 * Agrega un estado a la tabla de transiciones.
	 * 
	 * @param estado
	 *            Estado a agregar.
	 */
	public void addEstado(Estado estado) {
		estados.add(estado);
		addSimbolos(estado);
	}

	public void addSimbolos(Estado estado) {
		for (Transicion transicion : estado.getTransiciones()) {
			if (!simbolos.contains(transicion.getSimbolo())) {
				simbolos.add(transicion.getSimbolo());
			}
		}
	}

	public void addSimbolEmpty() {
		if (!simbolos.contains("ε")) {
			simbolos.add("ε");
		}
	}

	public List<Estado> getEstados() {
		Collections.sort(estados, new Comparable());
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<String> getSimbolos() {
		return simbolos;
	}

	public void setSimbolos(List<String> simbolos) {
		this.simbolos = simbolos;
	}

	/**
	 * Imprision de la tabla de transiciones.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("----------Tabla de transiciones----------");
		sb.append("\n");
		sb.append("Estado - Transiciones \n");
		Collections.sort(estados, new Comparable());
		for (Estado estado : estados) {
			sb.append("  " + estado.getValue() + "    - ");
			for (Transicion transicion : estado.getTransiciones()) {
				sb.append("(" + transicion.getSimbolo() + ")"
						+ transicion.getDestino().getValue());
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
