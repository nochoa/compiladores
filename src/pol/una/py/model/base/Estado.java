package pol.una.py.model.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un determinado estado dentro del automata
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class Estado {
	private int value;
	private boolean init;
	private boolean acceptation;
	private boolean error;
	private List<Transicion> transitions;
	private boolean filled;

	/**
	 * Crea un estado sin transiciones.
	 * 
	 * @param value
	 */
	public Estado(int value) {
		super();
		this.value = value;
		this.acceptation = false;
		this.error = false;
		this.transitions = new ArrayList<Transicion>();
	}

	/**
	 * Crea un estado con una lista de transiciones.
	 * 
	 * @param value
	 * 
	 * @param transitions
	 */
	public Estado(int value, List<Transicion> transitions) {
		super();
		this.value = value;
		this.acceptation = false;
		this.error = false;
		this.transitions = transitions;
	}

	/**
	 * Obtiene los estados alcanzables con un determinado simbolo.
	 * 
	 * @param symbol
	 * @return Lista de estados alcanzables
	 */
	public List<Estado> getStatesBySymbol(String symbol) {
		List<Estado> list = new ArrayList<Estado>();
		for (Transicion transition : transitions) {
			if (transition.getSymbol().equals(symbol)) {
				list.add(transition.getDestination());
			}
		}
		return list;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isAcceptation() {
		return acceptation;
	}

	public void setAcceptation(boolean acceptation) {
		this.acceptation = acceptation;
	}

	public List<Transicion> getTransitions() {
		return transitions;
	}

	public void addTransition(Transicion transition) {
		if (transitions == null) {
			transitions = new ArrayList<>();
		}
		this.transitions.add(transition);
	}

	public void setTransitions(List<Transicion> transitions) {
		this.transitions = transitions;
	}

	public static String toStringList(List<Estado> states) {
		StringBuilder sb = new StringBuilder();
		if (states.isEmpty()) {
			return sb.append("{}").toString();
		}
		sb.append("{");
		for (Estado state : states) {
			sb.append(state.getValue());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

}
