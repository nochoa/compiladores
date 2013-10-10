/**
 * 
 */
package pol.una.py.views.grafos;

import java.io.FileWriter;

import pol.una.py.model.automatas.AF;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Transicion;

/**
 * Helper que propociona las funcionalidades necesarias para generar el grafico
 * de un atomata.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 7, 2013
 */
public class GraphicHelper {
	// Directorio donde seran generados los graficos.
	private final static String PATH = "/home/nochoa/Documentos/desarrollo/graphviz/";
	private final static String DOT = ".dot";
	private final static String PNG = ".png";

	/**
	 * Genera el grafico de un automata finito.
	 * 
	 * @param automata
	 */
	public void graph(AF automata) {
		paint(automata);
	}

	/**
	 * Genera un arhivo con extension .dot y posteriormente utiliza dicho
	 * archivo para generar la imagen correspondiente ejecutando un comando en
	 * la terminal o consola.
	 * 
	 * @param automata
	 *            Automata a dibujar.
	 */
	private void paint(AF automata) {
		try {
			ProcessBuilder pbuilder;
			String pathDot = generatePathDot(automata.getProduccion()
					.getIdentificador());
			String pathPng = generatePathPng(automata.getProduccion()
					.getIdentificador());

			FileWriter fichero = new FileWriter(pathDot);
			fichero.write(generateDot(automata));
			fichero.close();

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
		sb.append(DOT);
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
		sb.append(PNG);
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
	private String generateDot(AF automata) {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph G");
		sb.append("\n{\n");
		sb.append(" rankdir=LR;\n");
		sb.append(buildNodes(automata));
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
	private String buildNodes(AF automata) {
		StringBuilder sb = new StringBuilder();
		sb.append(" edge [color=black];\n");

		for (Estado estado : automata.getTabla().getEstados()) {
			buildNode(sb, estado);
		}
		return sb.toString();

	}

	/**
	 * Genera las sentencias del archivo .dot que representa un estado en
	 * particular del automata y las transiciones que posee.
	 * 
	 * @param sb
	 * @param estado
	 *            Estado a representar.
	 */
	private void buildNode(StringBuilder sb, Estado estado) {
		if (estado.isAceptacion()) {
			sb.append(" " + estado.getValue());
			sb.append(" [shape=doublecircle];\n");
		} else {
			sb.append(" " + estado.getValue());
			sb.append(" [shape=circle];\n");
		}
		for (Transicion transicion : estado.getTransiciones()) {
			sb.append(" " + estado.getValue());
			sb.append(" ->");
			sb.append(transicion.getDestino().getValue());
			sb.append(" [label=" + transicion.getSimbolo() + "];\n");
		}
	}

}
