/**
 * 
 */
package pol.una.py.model.automatas;

import java.util.ArrayList;
import java.util.List;

import pol.una.py.model.base.Estado;
import pol.una.py.model.lexico.algoritmos.Minimo;
import pol.una.py.views.grafos.GraphicHelper;

/**
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 17, 2013
 */
public class AFD extends AF {

	public AFD() {
		super();
	}

	/**
	 * Genera el grafico del AFD.
	 */
	public void paint() {
		GraphicHelper graph = new GraphicHelper();
		graph.graph(this, "AFD");
	}

	/**
	 * Retorna una lista con los estados no finales del automata finito.
	 * 
	 * @return
	 */
	public List<Estado> getNofinales() {
		List<Estado> noFinales = new ArrayList<Estado>();
		for (Estado state : getStates()) {
			if (!state.isAcceptation() && !state.isError()) {
				noFinales.add(state);
			}
		}
		return noFinales;

	}

	public Estado getError() {
		for (Estado state : getStates()) {
			if (state.isError()) {
				return state;
			}
		}
		return null;

	}

	/**
	 * Retorna una lista con los estados finales del automata finito.
	 * 
	 * @return
	 */
	public List<Estado> getFinales() {
		List<Estado> finales = new ArrayList<Estado>();
		for (Estado state : getStates()) {
			if (state.isAcceptation()) {
				finales.add(state);
			}
		}
		return finales;

	}

	public AFD minimizar() {
		Minimo minimo = new Minimo(this);
		AFD aRet = minimo.build();
		GraphicHelper graph = new GraphicHelper();
		graph.graph(aRet, "MIN");
		return aRet;
	}

}
