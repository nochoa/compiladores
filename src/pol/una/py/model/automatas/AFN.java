/**
 * 
 */
package pol.una.py.model.automatas;

import java.util.Map;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.lexico.AnalizadorLexico;
import pol.una.py.model.lexico.ProduccionBNF;
import pol.una.py.model.lexico.algoritmos.Thompson;
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
	 * @param production
	 * @param alphabets
	 * @throws AnalizadorLexicoException
	 */
	// TODO VER PARA RECIBIR EL THOMPSON DE MANERA A MANEJAR LAS EXCEPCIONES EN
	// UN NIVEL SUPERIOR.
	public AFN(ProduccionBNF production, Map<String, Alfabeto> alphabets)
			throws AnalizadorLexicoException {
		AnalizadorLexico analizador = new AnalizadorLexico(production,
				alphabets);

		Thompson thompson = analizador.go();
		this.setProduction(production);
		this.setTable(thompson.getTable());

	}

	/**
	 * Crea un AFN para una produccion con un solo alfabeto.
	 * 
	 * @param production
	 * @param alphabet
	 * @throws AnalizadorLexicoException
	 */
	public AFN(ProduccionBNF production, Alfabeto alphabet)
			throws AnalizadorLexicoException {
		AnalizadorLexico analizador = new AnalizadorLexico(production, alphabet);

		Thompson thompson = analizador.go();
		this.setProduction(production);
		this.setTable(thompson.getTable());
	}

	/**
	 * Genera el grafico del AFN.
	 */
	public void paint() {
		GraphicHelper graph = new GraphicHelper();
		graph.graph(this);
	}

}
