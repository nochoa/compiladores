/**
 * 
 */
package pol.una.py.model.automatas;

import java.util.Map;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.lexico.AnalizadorLexico;
import pol.una.py.model.lexico.ProduccionBNF;
import pol.una.py.model.lexico.Thompson;
import pol.una.py.views.grafos.GraphicHelper;

/**
 * Clase que representa un automata finito no determinista, esto es, a partir de
 * una expresión regular utiliza la construcción de Thompson para obtener el AFN
 * equivalente.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 30/09/2013
 * @version 2.0 13/10/2013
 */
public class AFN extends AF {
	/**
	 * Crea un AFN para una produccion con mas de un alfabeto.
	 * 
	 * @param produccion
	 * @param alfabetos
	 * @throws AnalizadorLexicoException
	 */
	public AFN(ProduccionBNF produccion, Map<String, Alfabeto> alfabetos)
			throws AnalizadorLexicoException {
		AnalizadorLexico analizador = new AnalizadorLexico(produccion,
				alfabetos);

		Thompson thompson = analizador.go();
		this.setProduccion(produccion);
		this.setTabla(thompson.getTabla());

	}

	/**
	 * Crea un AFN para una produccion con un solo alfabeto.
	 * 
	 * @param produccion
	 * @param alfabeto
	 * @throws AnalizadorLexicoException
	 */
	public AFN(ProduccionBNF produccion, Alfabeto alfabeto)
			throws AnalizadorLexicoException {
		AnalizadorLexico analizador = new AnalizadorLexico(produccion, alfabeto);

		Thompson thompson = analizador.go();
		this.setProduccion(produccion);
		this.setTabla(thompson.getTabla());
	}

	/**
	 * Genera el grafico del AFN.
	 */
	public void paint() {
		GraphicHelper graph = new GraphicHelper();
		graph.graph(this);
	}

}
