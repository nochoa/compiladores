/**
 * 
 */
package pol.una.py.model.lexico.algoritmos;

import java.util.ArrayList;
import java.util.List;

import pol.una.py.model.automatas.AFD;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Transicion;

/**
 * Clase que implementa el proceso de conversion de un AFD a su AFD minimo
 * equivalente. Busca los estados que realizan la misma funcion en el automata
 * para agruparlos (minimizacion) en un solo estado, para eso el algoritmo
 * realiza una particion de los estados, en la que dos estados estaran en la
 * misma clase de equivalencia si realizan la misma funcion.</br> <li>El
 * algoritmo realiza los siguientes pasos: </br>
 * 
 * <b>1. </b> Los estados finales por un lado y los no finales por otro. Esto es
 * debido a que los estados finales nunca seran equivalentes a los estados no
 * finales, ya que en los primeros se reconoce la palabra mientras que en los
 * segundos se rechaza. </br>
 * 
 * <b>2. </b> Por cada conjunto se realiza una comprobacion para ver si los
 * estados del conjunto son equivalentes, y los estados que no son equivalentes
 * se eliminan del conjunto y se agregan a la lista de conjuntos global.
 * 
 * <b>3. </b>Se repite el paso 2 hasta que todos los conjuntos sean no
 * equivalentes, es decir, hasta que los estados no puedan dividirse en otros
 * estados.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 21, 2013
 */
public class Minimo {
	private AFD min;
	private AFD origin;

	public Minimo(AFD automata) {
		this.origin = automata;
		this.min = new AFD();
		this.min.setProduction(automata.getProduction());
	}

	public AFD build() {
		// Lista de conjuntos inicial
		List<Conjunto> init = new ArrayList<Conjunto>();
		init.add(new Conjunto(0, origin.getFinales()));
		init.add(new Conjunto(1, origin.getNofinales()));

		// Si existe el estado de error, el mismo es agregado de forma separada,
		// en un tercer conjunto
		if (origin.getError() != null) {
			init.add(new Conjunto(2, origin.getError()));
		}

		List<Conjunto> next = new ArrayList<>();

		boolean process = true;
		resetIndex(init);
		while (process) {

			for (Conjunto elementos : init) {
				if (!elementos.isOne()) {
					List<Conjunto> result = split(init, elementos);
					next.addAll(result);
				}
			}
			resetIndex(next);
			// Procesamos los conjuntos hasta que todos los conjuntos sean
			// no equivalentes.
			if (init.size() == next.size() || next.isEmpty()) {
				process = false;
			} else {
				init = next;
				next = new ArrayList<Conjunto>();
			}

		}

		return buildAFDMin(init);
	}

	/**
	 * A partir de los conjuntos de estados no equivalentes genera el AFD
	 * asociado, es decir, el AFD minimo.
	 * 
	 * @param list
	 *            Lista de conjuntos equivalentes
	 * @return
	 */
	private AFD buildAFDMin(List<Conjunto> list) {
		for (Conjunto element : list) {
			if (!element.isError()) {
				Estado origen = getState(element);
				for (String symbol : origin.getTable().getSymbols()) {
					int groupIndex = getGroup(list,
							element.getStateBySymbol(symbol));

					origen.addTransition(new Transicion(origen,
							getStateByGroup(list, groupIndex), symbol));
				}
			}
		}
		return min;
	}

	/**
	 * Separa un determinado conjunto en estados no equivalentes entre si.
	 * 
	 * @param list
	 * @param conjunto
	 *            Conjunto
	 * @return
	 */
	private List<Conjunto> split(List<Conjunto> list, Conjunto conjunto) {
		List<Estado> toRemove = new ArrayList<>();
		List<Estado> toAdd = new ArrayList<Estado>();
		// Codigo utilizado como referencia para comparar los estados del
		// conjunto
		String codLast = "";

		for (Estado state : conjunto.getStates()) {
			String cod = "";
			for (Transicion transicion : state.getTransitions()) {
				cod = cod + getGroup(list, transicion.getDestination());
			}
			if (codLast.equals("")) {
				// Guardamos el codigo utilizado como referencia
				codLast = cod;
			}
			// Es un estado equivalente
			if (codLast.equals(cod)) {
				toAdd.add(state);
			} else {
				// El estado no es equivalente, debe ser eliminado del
				// conjunto
				toRemove.add(state);
			}

		}
		List<Conjunto> next = new ArrayList<Conjunto>();
		next.addAll(list);

		if (!toRemove.isEmpty()) {
			next.remove(conjunto);
			resetIndex(next);
			for (Estado state : toRemove) {
				// Agregamos el estado no equivalente a la lista global de
				// conjuntos
				next.add(new Conjunto(next.size(), state));
			}
			if (!toAdd.isEmpty()) {
				// Actualizamos el conjunto original, sin los estados no
				// equivalentes.
				next.add(new Conjunto(next.size(), toAdd));
			}

		}
		return next;

	}

	private void resetIndex(List<Conjunto> list) {
		int index = 0;
		for (Conjunto element : list) {
			element.setIndex(index);
			index++;
		}
	}

	private int getGroup(List<Conjunto> list, Estado state) {
		for (Conjunto elemento : list) {
			if (elemento.contain(state)) {
				return elemento.getIndex();
			}
		}
		return -1;
	}

	/**
	 * Retorna el estado asociado a un determinado conjunto y si dicho estado
	 * aun no se encuentra en el AFD minimo es agregado al AFD.
	 * 
	 * @param list
	 *            Lista global de conjuntos
	 * @param index
	 *            Identificador del conjunto
	 * @return
	 */
	private Estado getStateByGroup(List<Conjunto> list, int index) {
		Estado state = null;
		if (!min.containState(list.get(index).getIndex())) {
			state = new Estado(list.get(index).getIndex());
			state.setAcceptation(list.get(index).isAcceptation());
			state.setError(list.get(index).isError());
			min.addEstado(state);

		} else {
			// Si el estado ya esta en el automata
			state = min.getState(list.get(index).getIndex());
		}

		return state;
	}

	private Estado getState(Conjunto element) {
		Estado state = null;
		if (!min.containState(element.getIndex())) {
			state = new Estado(element.getIndex());
			state.setAcceptation(element.isAcceptation());
			state.setError(element.isError());
			min.addEstado(state);
		} else {
			state = min.getState(element.getIndex());
		}
		return state;
	}

	/**
	 * @return the minimo
	 */
	public AFD getAutomata() {
		return min;
	}

	/**
	 * @param minimo
	 *            the minimo to set
	 */
	public void setAutomata(AFD minimo) {
		this.min = minimo;
	}

	public AFD getOrigin() {
		return origin;
	}

	public void setOrigin(AFD origin) {
		this.origin = origin;
	}

	/**
	 * Clase utilizada para representar una lista de estado con un identificador
	 * unico.
	 * 
	 * @author Nathalia Ochoa
	 * 
	 * @since 1.0
	 * @version 1.0 Oct 25, 2013
	 */
	public class Conjunto {
		private List<Estado> states;
		private int index;

		/**
		 * Constructor
		 * 
		 * @param elementos
		 *            Lista de estados.
		 * @param index
		 *            Identificador de la lista.
		 */
		public Conjunto(int index, List<Estado> elementos) {
			super();
			this.states = elementos;
			this.index = index;
		}

		/**
		 * Constructor
		 * 
		 * @param index
		 *            Identificador de la lista.
		 * @param state
		 *            Unico estado de la lista.
		 */
		public Conjunto(int index, Estado state) {
			super();
			List<Estado> list = new ArrayList<Estado>();
			list.add(state);
			this.states = list;
			this.index = index;
		}

		public List<Estado> getStates() {
			return states;
		}

		public void setStates(List<Estado> elementos) {
			this.states = elementos;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		/**
		 * Verfica si un determinado estado se encuentra en la lista de estados.
		 * 
		 * @param value
		 *            Estado
		 * @return <b>true</b> Si el estado se encuentra en la lista</br>
		 *         <b>false</b> Caso contrario
		 */
		public boolean contain(Estado value) {
			for (Estado state : states) {
				if (state.getValue() == value.getValue()) {
					return true;
				}
			}
			return false;
		}

		public void remove(Estado state) {
			states.remove(state);
		}

		/**
		 * Verifica si el conjunto posee un solo elemento en la lista de
		 * estados.
		 * 
		 * @return
		 */
		public boolean isOne() {
			return states.size() == 1;
		}

		/**
		 * Verifica si el conjunto es un conjunto de aceptacion.
		 * 
		 * @return <b>true</b> Si al menos uno de los estados de la lista es un
		 *         estado de aceptacion.</br><b>false</b> Caso contrario.
		 */
		public boolean isAcceptation() {
			for (Estado state : states) {
				if (state.isAcceptation()) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Verifica si el conjunto es un conjunto de error.
		 * 
		 * @return <b>true</b> Si al menos uno de los estados de la lista es un
		 *         estado de error.</br><b>false</b> Caso contrario.
		 */
		public boolean isError() {
			for (Estado state : states) {
				if (state.isError()) {
					return true;
				}
			}
			return false;
		}

		public Estado getStateBySymbol(String symbol) {
			return states.get(0).getStatesBySymbol(symbol).get(0);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Indice: " + index);
			sb.append("{");
			for (Estado state : states) {
				sb.append(state.getValue());
			}
			sb.append("}");
			return sb.toString();
		}

	}

}
