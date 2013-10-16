/**
 * 
 */
package pol.una.py.model.lexico;

import java.util.ArrayList;
import java.util.List;

import pol.una.py.model.base.Estado;

/**
 * Clase utilizada para representar cerraduras ε utilizadas para el algoritmo de
 * subconjunto.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 14, 2013
 */
public class Cerradura {
	private int value;
	private List<Estado> conjunto;
	private List<Estado> estadosAcanzables;
	// Para cada simbolo vemos cuales son los estados alcanzables
	private List<Subconjunto> subconjuntos;
	// Indica si la cerradura fue procesada o no.
	private boolean process;

	/**
	 * Constructor por defecto.
	 */
	public Cerradura(int value, List<Estado> conjunto,
			List<Estado> estadosAlcanzables) {
		this.value = value;
		this.conjunto = conjunto;
		this.estadosAcanzables = estadosAlcanzables;
		this.subconjuntos = new ArrayList<Subconjunto>();
		this.setProcess(false);
	}

	/**
	 * Contructor para la cerradura inicial, cuyo conjunto de estados es solo el
	 * estado inicial del automata.
	 * 
	 * @param value
	 * @param conjunto
	 * @param estadosAlcanzables
	 */
	public Cerradura(int value, Estado conjunto, List<Estado> estadosAlcanzables) {
		List<Estado> list = new ArrayList<Estado>();
		list.add(conjunto);

		this.value = value;
		this.conjunto = list;
		this.estadosAcanzables = estadosAlcanzables;
		this.subconjuntos = new ArrayList<Subconjunto>();
		this.setProcess(false);
	}

	public void addSubconjunto(String symbol, List<Estado> states) {
		subconjuntos.add(new Subconjunto(symbol, states));
	}

	public List<Estado> getConjunto() {
		return conjunto;
	}

	public void setConjunto(List<Estado> conjunto) {
		this.conjunto = conjunto;
	}

	/**
	 * @return the estadosAcanzables
	 */
	public List<Estado> getEstadosAcanzables() {
		return estadosAcanzables;
	}

	/**
	 * @param estadosAcanzables
	 *            the estadosAcanzables to set
	 */
	public void setEstadosAcanzables(List<Estado> estadosAcanzables) {
		this.estadosAcanzables = estadosAcanzables;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Subconjunto> getSubconjuntos() {
		return subconjuntos;
	}

	public void setSubconjuntos(List<Subconjunto> subconjuntos) {
		this.subconjuntos = subconjuntos;
	}

	public boolean isProcess() {
		return process;
	}

	public void setProcess(boolean process) {
		this.process = process;
	}

	/**
	 * Obtiene el codigo de la cerradura, se utiliza para comparar si dos
	 * cerraduras son iguales.
	 * 
	 * @return
	 */
	public String getCodigoUnico() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Estado estado : estadosAcanzables) {
			sb.append(estado.getValue());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Estado estado : conjunto) {
			sb.append(estado.getValue());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		sb.append(" - ");
		sb.append(getCodigoUnico());

		return sb.toString();
	}

	public class Subconjunto {
		private String symbol;
		private List<Estado> states;

		/**
		 * Constructor por defecto.
		 * 
		 * @param symbol
		 * @param states
		 */
		public Subconjunto(String symbol, List<Estado> states) {
			super();
			this.symbol = symbol;
			this.states = states;
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public List<Estado> getStates() {
			return states;
		}

		public void setStates(List<Estado> states) {
			this.states = states;
		}

	}

}
