/**
 * 
 */
package pol.una.py.model.automatas;

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

	public AFD minimizar() {
		Minimo minimo = new Minimo(this);
		AFD aRet = minimo.build();
		GraphicHelper graph = new GraphicHelper();
		graph.graph(aRet, "MIN");
		return aRet;
	}

}