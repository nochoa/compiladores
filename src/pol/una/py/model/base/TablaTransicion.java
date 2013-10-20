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
	private List<Estado> states;
	private List<String> symbols;

	public TablaTransicion() {
		states = new ArrayList<Estado>();
		symbols = new ArrayList<String>();
	}

	/**
	 * Agrega un estado a la tabla de transiciones.
	 * 
	 * @param state
	 *            Estado a agregar.
	 */
	public void addEstado(Estado state) {
		states.add(state);
		addSymbols(state);
	}

	private void addSymbols(Estado state) {
		for (Transicion transition : state.getTransitions()) {
			if (!symbols.contains(transition.getSymbol())) {
				symbols.add(transition.getSymbol());
			}
		}
	}

	/**
	 * Verifica si un estado se encuentra en la tabla de transicion, de ser asi
	 * retorna dicho estado.
	 * 
	 * @param value
	 *            Valor del estado
	 * @return <b>Estado</b>Estado </br><b>null</b>Si el estado no se encuentra
	 *         en la tabla.
	 */
	public Estado getState(int value) {
		if (containState(value)) {
			for (Estado state : states) {
				if (state.getValue() == value) {
					return state;
				}
			}
		}
		return null;
	}

	/**
	 * Verifica si un determinado estado se encuentra en la tabla de transición.
	 * 
	 * @param value
	 *            Valor del estado
	 * @return <b>true</b> Si el estado se encuentra en la tabla de
	 *         transición</br> <b>false</b> Caso contrario.
	 */
	public boolean containState(int value) {
		for (Estado state : states) {
			if (state.getValue() == value) {
				return true;
			}
		}
		return false;
	}

	public void addSymbolEmpty() {
		if (!symbols.contains("ε")) {
			symbols.add("ε");
		}
	}

	public List<Estado> getStates() {
		Collections.sort(states, new Comparable());
		return states;
	}

	public void setStates(List<Estado> states) {
		this.states = states;
	}

	public List<String> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
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
		Collections.sort(states, new Comparable());
		for (Estado estado : states) {
			if (!estado.isError()) {
				sb.append("  " + estado.getValue() + "    - ");
				for (Transicion transicion : estado.getTransitions()) {
					sb.append("(" + transicion.getSymbol() + ")"
							+ transicion.getDestination().getValue());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
