/**
 * 
 */
package pol.una.py.views.grafos;

import java.io.FileWriter;
import java.util.List;

import pol.una.py.model.automatas.AF;
import pol.una.py.model.automatas.AFD;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Extension;
import pol.una.py.model.base.Transicion;

/**
 * Helper que propociona las funcionalidades necesarias para generar el gráfico
 * de un automata.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 7, 2013
 */
public class GraphicHelper {
	// Directorio donde seran generados los graficos.
	private final static String PATH = "/home/nochoa/Documentos/desarrollo/graphviz/";

	/**
	 * Genera el gráfico de un automata finito.
	 * 
	 * @param automata
	 */
	public void graph(AF automata, String type) {
		paint(automata, type);
	}

	/**
	 * Simula la ejecucion de un automata.
	 * 
	 * @param automata
	 * @param input
	 *            Character de entrada
	 * @param actual
	 *            Estado actual de la simulacion
	 * @return
	 */
	public int simulate(AF automata, String input, int actual) {
		// Pintamos el estado actual
		Estado stateActual = automata.getState(actual);
		stateActual.setFilled(true);

		List<Estado> alcanzables = stateActual.getStatesBySymbol(input);
		if (!alcanzables.isEmpty()) {
			// Obtenemos el siguiente estado
			Estado stateNext = alcanzables.get(0);
			stateNext.setFilled(true);

			// Generamos la imagen de la simulacion
			GraphicHelper gh = new GraphicHelper();
			gh.graph(automata, "SIMULATE");

			// Si el estado alcanzable es un estado de error, la cadena no es
			// valida
			if (stateNext.isError()) {
				return -1;
			} else {
				return stateNext.getValue();
			}
		} else {
			// Si no hay una transicion para el caracter de entrada la cadena no
			// es valida
			return -1;
		}

	}

	/**
	 * Genera un arhivo con extension .dot y posteriormente utiliza dicho
	 * archivo para generar la imagen correspondiente ejecutando un comando en
	 * la terminal o consola.
	 * 
	 * @param automata
	 *            Automata a dibujar.
	 * @param type
	 */
	private void paint(AF automata, String type) {
		try {
			ProcessBuilder pbuilder;
			String pathDot = generatePathDot(automata.getProduction().getName()
					.concat(type));
			String pathPng = generatePathPng(automata.getProduction().getName()
					.concat(type));

			FileWriter file = new FileWriter(pathDot);
			file.write(generateDot(automata, type));
			file.close();

			// Realiza la construccion del comando en la linea de comandos esto
			// es: dot -Tpng -o archivo.png archivo.dot
			pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", pathPng,
					pathDot);
			pbuilder.redirectErrorStream(true);

			// Ejecuta el proceso
			pbuilder.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Genera el path donde seran generados los archivos necesarios para los
	 * graficos con el identificador del automata.
	 * 
	 * @param identificador
	 *            Identificador del automata.
	 * @return Directorio donde seran generados los archivos
	 */
	private StringBuilder generatePath(String identificador) {
		StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(identificador);
		return sb;

	}

	/**
	 * Genera el path del archivo con extension .dot que representa la
	 * definicion del grafico del automata.
	 * 
	 * @param identificador
	 *            Identificador del automata.
	 * @return
	 */
	private String generatePathDot(String identificador) {
		StringBuilder sb = generatePath(identificador);
		sb.append(Extension.DOT.getValue());
		return sb.toString();

	}

	/**
	 * Genera el path del archivo con extension .png que representa el grafico
	 * del automata.
	 * 
	 * @param identificador
	 *            Identificador del automata.
	 * @return
	 */
	private String generatePathPng(String identificador) {
		StringBuilder sb = generatePath(identificador);
		sb.append(Extension.PNG.getValue());
		return sb.toString();

	}

	/**
	 * Genera el archivo con extension .dot que sera utilizado para generar la
	 * imagen del grafico del automata.
	 * 
	 * @param automata
	 *            Automata a dibujar
	 * @return
	 */
	private String generateDot(AF automata, String type) {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph G");
		sb.append("\n{\n");
		sb.append(" rankdir=LR;\n");
		sb.append(buildNodes(automata, type));
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Genera las sentencias del archivo .dot que representan los estados del
	 * automata.
	 * 
	 * @param automata
	 *            Automata a dibujar.
	 * @return
	 */
	private String buildNodes(AF automata, String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(" edge [color=black];\n");

		for (Estado estado : automata.getTable().getStates()) {
			buildNode(automata, sb, estado, type);
		}
		return sb.toString();

	}

	/**
	 * Genera las sentencias del archivo .dot que representa un estado en
	 * particular del automata y las transiciones que posee.
	 * 
	 * @param sb
	 * @param state
	 *            Estado a representar.
	 */
	private void buildNode(AF automata, StringBuilder sb, Estado state,
			String type) {

		sb.append(getNode(state.getValue(), type));
		sb.append("[");
		if (state.isFilled()) {
			;
			sb.append("style=filled,color=green,");
		}
		// Pintamos el estado inicial y la figura es especial
		if (state.getValue() == getInitState(automata, type)) {
			sb.append(" shape=Mcircle, color= pink];\n");
		} else {
			// Si es un estado de aceptacion debe ser una figura especial
			if (state.isAcceptation()) {
				sb.append(" shape=doublecircle];\n");
			} else {
				// Si es un estado de error tambien debe ser una figura especial
				if (state.isError()) {
					sb.append(" shape=diamond, color= red];\n");
				} else {
					sb.append(" shape=circle];\n");
				}
			}
		}

		for (Transicion transicion : state.getTransitions()) {
			sb.append(getNode(state.getValue(), type));
			sb.append(" ->");
			sb.append(getNode(transicion.getDestination().getValue(), type));
			sb.append(" [label=" + transicion.getSymbol() + "];\n");
		}
	}

	/**
	 * Si el automata es un AFN imprime el valor del estado, si es un AFD
	 * obtiene la letra equivalente al valor del estado.
	 * 
	 * @param value
	 *            Valor del estado
	 * @param type
	 *            Tipo del automada
	 * @return
	 */
	private String getNode(int value, String type) {
		if (type.equals("AFD")) {
			return " " + Alfabeto.LETRAS_MAYUSCULAS.charAt(value);
		} else {
			if (type.equals("MIN") || type.equals("SIMULATE")) {
				return " " + Alfabeto.ROMANOS.get(value);
			} else {
				return " " + value;
			}
		}

	}

	/**
	 * Obtiene el estado inicial del automata, esto es debido a que si es un
	 * AFD, ADF minimo o una simulacion(que se hace sobre el AFD minimo) el
	 * estado con valor <b>0</b> no necesariamente es el estado inicial como en
	 * el AFN.
	 * 
	 * @param automata
	 * @param type
	 * @return
	 */
	private int getInitState(AF automata, String type) {
		if (type.equals("AFN")) {
			return automata.getInitState().getValue();

		} else {
			return ((AFD) automata).getInitMin();
		}
	}

}
