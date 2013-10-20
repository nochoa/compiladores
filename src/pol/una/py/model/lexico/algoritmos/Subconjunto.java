/**
 * 
 */
package pol.una.py.model.lexico.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pol.una.py.model.automatas.AFD;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Comparable;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Transicion;
import pol.una.py.model.lexico.Cerradura;

/**
 * Clase que proporciona las funcionalidades necesarias para el algoritmo de
 * construcción de subconjuntos. Dado un AFN arbitrario, construye un AFD
 * equivalente, eliminando tanto las transiciones ε (construyendo cerraduras)
 * como las transiciones múltiples de un estado en un carácter de entrada simple
 * (construyendo subconjuntos).
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 14, 2013
 */
public class Subconjunto {
	private static final String ε = "ε";
	private List<Cerradura> cerraduras;
	private AFD automata;

	/**
	 * Constructor por defecto.
	 */
	public Subconjunto() {
		cerraduras = new ArrayList<Cerradura>();
		automata = new AFD();
	}

	/**
	 * Aplica el algoritmo de construcción de subconjunto para obtener a partir
	 * de un AFN su AFD equivalente.
	 * 
	 * @param afn
	 * @return
	 */
	public AFD build(AFN afn) {
		automata.setProduction(afn.getProduction());
		// Calculamos la cerradura ε para el estado inicial.
		Cerradura cerraduraInicial = new Cerradura(0, afn.getInitState(),
				buildCerradura(afn.getInitState()));
		cerraduras.add(cerraduraInicial);

		// Mientras existan cerraduras sin procesar.
		while (isProcess()) {
			// Hallamos el subconjunto para cada simbolo
			for (String symbol : afn.getSymbols()) {
				if (!symbol.equals(ε)) {
					List<Estado> states = new ArrayList<>();
					for (Estado state : next().getEstadosAcanzables()) {

						merge(states,
								buildSubconjuntoBySymbol(state, symbol,
										new ArrayList<Estado>()));
					}
					next().addSubconjunto(symbol, states);

				}
			}
			// Para cada subconjunto calculamos la cerradura
			for (pol.una.py.model.lexico.Cerradura.Subconjunto subconjunto : next()
					.getSubconjuntos()) {
				if (!subconjunto.getStates().isEmpty()) {
					addCerradura(subconjunto.getStates(),
							buildCerradura(subconjunto.getStates()));
				}

			}
			// Finalizamos el proceso de la cerradura actual.
			next().setProcess(true);
		}

		return buildAFD();

	}

	/**
	 * Construye la cerradura ε de un estado en particular. Advierta que la
	 * cerradura ε de un estado siempre contiene al estado mismo.
	 * 
	 * @param state
	 * @return Cerradura ε
	 */
	public List<Estado> buildCerradura(Estado state) {
		// Agregamos el estado en cuestion, ya que la cerradura siempre contiene
		// al estado mismo.
		List<Estado> estadosAlcanzables = new ArrayList<Estado>();
		estadosAlcanzables.add(state);

		// Construimos la cerradura y ordenamos los estados alcanzables.
		estadosAlcanzables = getEstadosAlcanzables(state, estadosAlcanzables);
		Collections.sort(estadosAlcanzables, new Comparable());
		return estadosAlcanzables;
	}

	/**
	 * Construye la cerradura ε para un conjunto en particular.
	 * 
	 * @param conjunto
	 *            Conjunto de estados de los cuales se desea obtener la
	 *            cerradura ε.
	 * @return Cerradura ε
	 */
	public List<Estado> buildCerradura(List<Estado> conjunto) {
		List<Estado> estadosAlcanzables = new ArrayList<Estado>();
		// Por cada estado del conjunto contruimos la cerradura y actualizamos
		// la lista de estados.
		for (Estado estado : conjunto) {
			merge(estadosAlcanzables, buildCerradura(estado));

		}
		Collections.sort(estadosAlcanzables, new Comparable());
		return estadosAlcanzables;
	}

	/**
	 * Obtiene los estados alcanzables desde un estado en particular con
	 * transiciones ε.
	 * 
	 * @param state
	 *            Estado inicial
	 * @param states
	 *            Lista de estados alcanzables
	 * 
	 */
	private List<Estado> getEstadosAlcanzables(Estado state, List<Estado> states) {
		for (Transicion transicion : state.getTransitions()) {
			if (transicion.getSymbol().equals(ε)) {
				states.add(transicion.getDestination());
				getEstadosAlcanzables(transicion.getDestination(), states);

			}
		}
		return states;
	}

	/**
	 * Obtiene los estados alcanzables desde un estado con transiciones ε y un
	 * símbolo en particular.
	 * 
	 * @param state
	 *            Estado inicial
	 * @param symbol
	 *            Transicion
	 * @param states
	 *            Lista de estados alcanzables
	 */
	public List<Estado> buildSubconjuntoBySymbol(Estado state, String symbol,
			List<Estado> states) {
		for (Transicion transition : state.getTransitions()) {
			if (transition.getSymbol().equals(ε)) {
				buildSubconjuntoBySymbol(transition.getDestination(), symbol,
						states);
			} else {
				if (transition.getSymbol().equals(symbol)) {

					states.add(transition.getDestination());

				}
			}
		}
		return states;
	}

	/**
	 * Verifica si un estado en particular se encuentra en una lista de estados.
	 * 
	 * @param state
	 *            Estado a analizar.
	 * @param states
	 *            Lista de estados
	 * @return <b>true</b> Si el estado ya se encuentra en la lista.</br>
	 *         <b>false</b> Si el estado no se encuentra en la lista.
	 */
	private boolean contain(Estado state, List<Estado> states) {
		for (Estado valid : states) {
			if (valid.getValue() == state.getValue()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Mezcla dos listas de estados, esto es, agrega a la primera lista solo
	 * aquellos estados de la segunda lista que aun no se encuentran en la
	 * primera lista.
	 * 
	 * @param states1
	 *            Primera lista.
	 * @param states2
	 *            Segunda lista.
	 */
	private void merge(List<Estado> states1, List<Estado> states2) {
		for (Estado state : states2) {
			if (!contain(state, states1)) {
				states1.add(state);
			}

		}
	}

	/**
	 * Retorna la siguiente cerradura a ser procesada.
	 * 
	 * @return <b>cerradura</b> La siguiente cerradura a ser procesada.</br>
	 *         <b>null</b> Si todas las cerraduras han sido procesadas.
	 */
	public Cerradura next() {
		for (Cerradura cerradura : cerraduras) {
			if (!cerradura.isProcess()) {
				return cerradura;
			}
		}
		return null;
	}

	/**
	 * Verifica si el algoritmo de construcción de Subconjunto aun no ha
	 * analizado todas las cerraduras.
	 * 
	 * @return <b>true</b> Sí existe una o más cerradura/s sin procesar.</br>
	 *         <b>false</b> Sí todas las cerraduras han sido procesadas.
	 */
	public boolean isProcess() {
		return next() != null;
	}

	/**
	 * Agrega una cerradura al subconjunto si y solo si la cerradura aun no ha
	 * sido registrada.
	 * 
	 * @param conjunto
	 *            Conjunto de estados de la cerradura.
	 * @param estadosAlcanzables
	 *            Estados alcanzables de la cerradura.
	 */
	public void addCerradura(List<Estado> conjunto,
			List<Estado> estadosAlcanzables) {
		Cerradura cerradura = new Cerradura(size(), conjunto,
				estadosAlcanzables);
		boolean band = true;
		for (Cerradura valid : cerraduras) {
			if (valid.getCodCerradura().equals(cerradura.getCodCerradura())) {
				band = false;
				break;
			}

		}
		// Solo si la cerradura aun no ha sido registrada la agrego
		if (band) {
			cerraduras.add(cerradura);
		}
	}

	/**
	 * Contruye el AFD equivalente
	 * 
	 * @return <b>AFD</b> Automata finito equivalente al AFN recibido
	 */
	private AFD buildAFD() {
		for (Cerradura cerradura : cerraduras) {
			Estado origen = getState(cerradura);
			Estado destino = null;

			for (pol.una.py.model.lexico.Cerradura.Subconjunto conjunto : cerradura
					.getSubconjuntos()) {
				String codigoUnico = conjunto.getCodCerradura();
				// Conjunto vacio
				if (codigoUnico.equals("{}")) {
					destino = getStateError();
				} else {
					destino = getState(getCerradura(codigoUnico));

				}
				origen.addTransition(new Transicion(origen, destino, conjunto
						.getSymbol()));

			}
			automata.addEstado(origen);
		}
		return automata;
	}

	/**
	 * Obtiene el estado relacionado a la cerradura, esto es, si el estado ya se
	 * encuentra en el automata retorna dicho estado, caso contrario crea un
	 * nuevo estado.
	 * 
	 * @param cerradura
	 *            Cerradura de la cual se desea obtener el estado asociado.
	 * @return Estado asociado a la cerradura
	 */
	private Estado getState(Cerradura cerradura) {
		Estado state = null;
		// Si el estado aun no esta en el automata
		if (!automata.containState(cerradura.getValue())) {
			state = new Estado(cerradura.getValue());
			state.setAcceptation(cerradura.isAcceptation());
		} else {
			// Si el estado ya esta en el automata
			state = automata.getState(cerradura.getValue());
		}
		return state;
	}

	/**
	 * Verifica si el aumata ya posee un estado de error y si no lo tiene crea
	 * un estado de error y lo retorna.
	 * 
	 * @return
	 */
	private Estado getStateError() {
		Estado state = null;
		if (!automata.containState(cerraduras.size())) {
			state = new Estado(cerraduras.size());
			state.setError(true);
			automata.addEstado(state);
		} else {
			state = automata.getState(cerraduras.size());
		}
		return state;
	}

	private Cerradura getCerradura(String codigo) {
		for (Cerradura cerradura : cerraduras) {
			if (cerradura.getCodConjunto().equals(codigo)) {
				return cerradura;
			}
		}
		return null;

	}

	/**
	 * Cantidad de cerraduras del subconjunto.
	 * 
	 * @return
	 */
	private int size() {
		return cerraduras.size();
	}

	public List<Cerradura> getCerraduras() {
		return cerraduras;
	}

	public void setCerraduras(List<Cerradura> cerraduras) {
		this.cerraduras = cerraduras;
	}

	public AFD getAutomata() {
		return automata;
	}

	public void setAutomata(AFD automata) {
		this.automata = automata;
	}
}
