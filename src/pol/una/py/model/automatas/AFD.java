/**
 * 
 */
package pol.una.py.model.automatas;

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
	 * Genera el grafico del AFD. Antes de graficar indicamos cual es el estado
	 * inicial de manera a que el mismo pueda ser diferenciado de los demas
	 * estados.
	 */
	public void paint() {
		this.getState(0).setInit(true);
		GraphicHelper graph = new GraphicHelper();
		graph.graph(this, "AFD");
	}

	public AFD minimizar() {
		Minimo minimo = new Minimo(this);
		AFD aRet = minimo.build();
		GraphicHelper graph = new GraphicHelper();
		graph.graph(aRet, "MIN");
		return aRet;
	}

	/**
	 * Metodo utilizado solo para la simulacion del AFD minimo, debido a que es
	 * el unico caso donde es estado <b>0</b> podria no ser el estado inicial,
	 * motivo por el cual al elaborar el minimo se indica con el atributo
	 * <b>init</b> cual es el estado inicial.
	 * 
	 * @return
	 */
	public int getInitMin() {
		for (Estado state : getStates()) {
			if (state.isInit()) {
				return state.getValue();
			}
		}
		return -1;
	}

}
