/**
 * 
 */
package pol.una.py.model.lexico;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pol.una.py.model.automatas.AF;
import pol.una.py.model.automatas.AFD;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Extension;
import pol.una.py.model.base.Transicion;

/**
 * Clase utilizada para generar el codigo de un automata den particular para el
 * posterior reconocimiento de cadenas.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 25, 2013
 */
public class CodeGenerator {
	private AF automata;
	private List<String> imports = new ArrayList<String>();
	private static String ENTER = "\n";
	private static String END_OF_LINE = " ;" + ENTER;
	private static String TAB = "\t";
	private static String D_TAB = TAB + TAB;
	private static String T_TAB = D_TAB + TAB;
	private static String OPEN = "{" + ENTER;
	private static String CLOSE = "}" + ENTER;
	private static String CLASS = "public class NAME " + OPEN;
	private static String MAIN = "public static void main(String[] args) throws IOException";

	private final static String PATH = "/home/nochoa/devel/fp-una/TDS/src/pol/una/py/generations/";

	/**
	 * Constructor
	 */
	public CodeGenerator(AF af) {
		automata = af;
		imports.add("package pol.una.py.generations");
		imports.add("import java.io.BufferedReader");
		imports.add("import java.io.IOException");
		imports.add("import java.io.InputStreamReader");
	}

	public void generate() {
		StringBuilder code = new StringBuilder();
		for (String library : imports) {
			code.append(library);
			code.append(END_OF_LINE);
		}
		code.append(ENTER);
		code.append(CLASS.replaceAll("NAME", getNameClase()) + ENTER);

		// Obtenemos la cadena que se desea validar
		code.append(TAB + MAIN + OPEN);
		code.append(D_TAB + "String continuar = \"Y\"" + END_OF_LINE);
		code.append(D_TAB + "while (continuar.equals(\"Y\"))" + OPEN);
		code.append(T_TAB
				+ "System.out.println(\"Ingrese la cadena que desea validar\")"
				+ END_OF_LINE);
		code.append(T_TAB
				+ "InputStreamReader isr = new InputStreamReader(System.in)"
				+ END_OF_LINE);
		code.append(T_TAB + "BufferedReader buffer = new BufferedReader(isr)"
				+ END_OF_LINE);
		code.append(T_TAB + "String cadena = buffer.readLine()" + END_OF_LINE);
		code.append(T_TAB + "validate(cadena)" + END_OF_LINE);
		code.append(T_TAB + "System.out.println(\"Desea continuar: Y/N \")"
				+ END_OF_LINE);
		code.append(T_TAB + "cadena = buffer.readLine()" + END_OF_LINE);
		code.append(T_TAB + "if (cadena.equals(\"y\") || cadena.equals(\"Y\"))"
				+ OPEN);
		code.append(T_TAB + TAB + "continuar = \"Y\"" + END_OF_LINE);
		code.append(T_TAB + "}" + " else " + OPEN);
		code.append(T_TAB + TAB + "continuar = \"N\"" + END_OF_LINE);
		code.append(T_TAB + CLOSE);
		code.append(D_TAB + CLOSE);
		code.append(D_TAB
				+ "System.out.println(\"Gracias por utilizar el validador de expresiones.\")"
				+ END_OF_LINE);
		code.append(TAB + CLOSE);
		code.append(ENTER);

		// Validamos la cadena ingresada
		code.append(TAB + "public static void validate(String cadena)" + OPEN);
		code.append(D_TAB + "//Partimos del estado inicial" + END_OF_LINE);
		code.append(D_TAB + "int stateActual = " + getInitState() + END_OF_LINE);
		code.append(D_TAB + "//Recorremos la cadena ingresada" + END_OF_LINE);
		code.append(D_TAB + "for (int i = 0; i < cadena.length(); i++)" + OPEN);
		code.append(T_TAB
				+ "String character = String.valueOf(cadena.charAt(i))"
				+ END_OF_LINE);
		code.append(T_TAB + "stateActual = next(stateActual,character)"
				+ END_OF_LINE);
		code.append(T_TAB + "if(stateActual==-1)" + OPEN);
		code.append(T_TAB + TAB + "System.out.println(\"Cadena no valida\")"
				+ END_OF_LINE);
		code.append(T_TAB + TAB + "return" + END_OF_LINE);
		code.append(T_TAB + CLOSE);
		code.append(D_TAB + CLOSE);
		code.append(D_TAB
				+ "//Si el estado alcanzado es un estado final la cadena es valida"
				+ END_OF_LINE);
		code.append(getValidFinal());
		code.append(T_TAB + "System.out.println(\"Cadena valida\")"
				+ END_OF_LINE);
		code.append(D_TAB + "} else " + OPEN);
		code.append(T_TAB + "System.out.println(\"Cadena no valida\")"
				+ END_OF_LINE);
		code.append(D_TAB + CLOSE);
		code.append(TAB + CLOSE);
		code.append(ENTER);

		// Obtenemos el siguiente estado del automata
		code.append(TAB + "public static int next(int state, String actual)"
				+ OPEN);
		code.append(getSwitchState());
		code.append(TAB + CLOSE);

		code.append(CLOSE);

		FileWriter file;
		try {
			file = new FileWriter(getPath());
			file.write(code.toString());
			file.close();
		} catch (IOException e) {
			System.out.println("No se pudo generar el codigo del automata");

		}

	}

	/**
	 * Obtiene el estado inicial del automata, debido a que si la generacion
	 * recibe un AFD minimo el estad inicial podria no ser el estado con valor
	 * 0.
	 * 
	 * @return
	 */
	private int getInitState() {
		if (automata instanceof AFD) {
			return ((AFD) automata).getInitMin();
		} else {
			return automata.getInitState().getValue();
		}
	}

	/**
	 * Crea una sentencia para cada uno de los estados finales del automata.
	 * 
	 * @return
	 */
	private String getValidFinal() {
		StringBuilder sb = new StringBuilder();
		sb.append(D_TAB + "if(");
		for (Estado state : automata.getFinales()) {
			sb.append("stateActual == " + state.getValue());
			sb.append(" || ");
		}
		sb.deleteCharAt(sb.toString().length() - 1);
		sb.deleteCharAt(sb.toString().length() - 1);
		sb.deleteCharAt(sb.toString().length() - 1);
		sb.append(")" + OPEN);
		return sb.toString();
	}

	/**
	 * Crea el switch para recorrer cada uno de los estados del automata.
	 * 
	 * @return
	 */
	private String getSwitchState() {
		StringBuilder sb = new StringBuilder();
		sb.append(D_TAB + "switch (state)" + OPEN);

		for (Estado state : automata.getStates()) {
			sb.append(T_TAB + "case(" + state.getValue() + "):" + ENTER);
			if (!state.isError()) {
				sb.append(T_TAB + TAB + "switch (actual)" + OPEN);
				for (Transicion transicion : state.getTransitions()) {
					sb.append(T_TAB + D_TAB + "case\"" + transicion.getSymbol()
							+ "\":");
					Estado stateAlcanzable = state.getStatesBySymbol(
							transicion.getSymbol()).get(0);
					if (stateAlcanzable.isError()) {
						sb.append("return -1" + END_OF_LINE);
					} else {
						sb.append("return " + stateAlcanzable.getValue()
								+ END_OF_LINE);
					}
				}
				sb.append(T_TAB + TAB + CLOSE);
			} else {
				sb.append(T_TAB + TAB + "return -1" + END_OF_LINE);
			}
		}
		sb.append(D_TAB + CLOSE);
		sb.append(T_TAB + "return -1" + END_OF_LINE);
		return sb.toString();
	}

	/**
	 * Genera el path donde sera generado el codigo.
	 * 
	 * @return
	 */
	private String getPath() {
		StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(getNameClase());
		sb.append(Extension.JAVA.getValue());
		return sb.toString();

	}

	/**
	 * Genera el nombre de la clase a ser utilizado para el codigo generado.
	 * 
	 * @return
	 */
	private String getNameClase() {
		StringBuilder name = new StringBuilder(automata.getProduction()
				.getName());
		String init = String.valueOf(name.charAt(0));
		name.deleteCharAt(0);
		return init.toUpperCase() + name;

	}

	public AF getAutomata() {
		return automata;
	}

	public void setAutomata(AF automata) {
		this.automata = automata;
	}

}
