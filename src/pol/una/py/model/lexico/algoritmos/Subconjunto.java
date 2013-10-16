/**
 * 
 */
package pol.una.py.model.lexico.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	/**
	 * Constructor por defecto.
	 */
	public Subconjunto() {
		cerraduras = new ArrayList<Cerradura>();
	}

	/**
	 * Aplica el algoritmo de construcción de subconjunto para obtener a partir
	 * de un AFN su AFD equivalente.
	 * 
	 * @param automata
	 * @return
	 */
	public Subconjunto build(AFN automata) {
		// Calculamos la cerradura ε para el estado inicial.
		Cerradura cerraduraInicial = new Cerradura(0,
				automata.getInitState(),
				buildCerradura(automata.getInitState()));
		cerraduras.add(cerraduraInicial);

		// Mientras existan cerraduras sin procesar.
		while (isProcess()) {

			for (String symbol : automata.getTable().getSymbols()) {
				if (!symbol.equals(ε)) {
					List<Estado> states = new ArrayList<>();
					for (Estado state : next().getEstadosAcanzables()) {

						merge(states,
								buildSubconjuntoBySymbol(state, symbol,
										new ArrayList<Estado>()));
					}
					if (!states.isEmpty()) {
						next().addSubconjunto(symbol, states);
					}
				}
			}
			for (pol.una.py.model.lexico.Cerradura.Subconjunto subconjunto : next()
					.getSubconjuntos()) {
				addCerradura(subconjunto.getStates(),
						buildCerradura(subconjunto.getStates()));

			}
			next().setProcess(true);
		}

		return this;

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
			if (valid.getCodigoUnico().equals(cerradura.getCodigoUnico())) {
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
}
