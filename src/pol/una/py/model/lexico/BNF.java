package pol.una.py.model.lexico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Alfabeto;

/**
 * Representa la lista de producciones que constituyen la definición regular al
 * estilo BNF.
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class BNF {
	public List<ProduccionBNF> productions;
	private String name;
	private Alfabeto alphabet;
	private Map<String, Alfabeto> alphabets;
	private boolean oneAlphabet;

	/**
	 * Crea una definicion regular que posee una lista de alfabetos.
	 * 
	 * @param name
	 *            Nombre de la definicion regular
	 * @param productions
	 *            Lista de producciones
	 * @param alphabets
	 *            Lista de alfabetos
	 */
	public BNF(String name, List<ProduccionBNF> productions,
			Map<String, Alfabeto> alphabets) {
		super();
		this.name = name;
		this.productions = productions;
		this.alphabets = alphabets;
		this.oneAlphabet = false;
	}

	/**
	 * Crea una definicion regular con un solo alfabeto.
	 * 
	 * @param name
	 *            Nombre de la definicion regular
	 * @param productions
	 *            Lista de producciones
	 * @param alphabet
	 *            Alfabeto del BNF.
	 */
	public BNF(String name, List<ProduccionBNF> productions, Alfabeto alphabet) {
		super();
		this.name = name;
		this.productions = productions;
		this.alphabet = alphabet;
		this.oneAlphabet = true;
	}

	/**
	 * Valida si un determinado token pertenece al alfabeto/s asociado.
	 * 
	 * @param value
	 * @return
	 */
	public boolean isValid(String value) {
		// Si posee un solo alfabeto la validacion se realiza solbre un unico
		// alfabeto
		if (oneAlphabet) {
			return alphabet.pertenece(value);

		} else {
			// Si no la validacion se realiza por cada alfabeto asociado.
			return alphabets.containsKey(value);

		}

	}

	/**
	 * Procesa la definición regular y convierte cada producción a su AFN
	 * equivalente.
	 * 
	 * @return Lista de AFNs asociados a la definición regular.
	 * @throws AnalizadorLexicoException
	 */
	public List<AFN> process() throws AnalizadorLexicoException {
		List<AFN> aRet = new ArrayList<>();
		if (oneAlphabet) {
			for (ProduccionBNF production : productions) {
				aRet.add(new AFN(production, alphabet));
			}
		} else {
			for (ProduccionBNF production : productions) {
				aRet.add(new AFN(production, alphabets));
			}

		}
		return aRet;
	}

	public ProduccionBNF getProduccion(String name) {
		for (ProduccionBNF produccion : getProductions()) {
			if (produccion.getName().equals(name)) {
				return produccion;
			}
		}
		return null;
	}

	public AFN processProduction(ProduccionBNF production) {

		if (oneAlphabet) {
			try {
				return new AFN(production, alphabet);
			} catch (AnalizadorLexicoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		} else {
			try {
				return new AFN(production, alphabets);
			} catch (AnalizadorLexicoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOneAlphabet() {
		return oneAlphabet;
	}

	public Map<String, Alfabeto> getAlphabets() {
		return alphabets;
	}

	public void setAlphabetS(Map<String, Alfabeto> alphabets) {
		this.alphabets = alphabets;
	}

	/**
	 * @param alphabet
	 *            the alfabeto to set
	 */
	public void setAlphabet(Alfabeto alphabet) {
		this.alphabet = alphabet;
	}

	/**
	 * @return the alfabeto
	 */
	public Alfabeto getAlphabet() {
		return alphabet;
	}

	/**
	 * @param tieneUnAlfabeto
	 *            the tieneUnAlfabeto to set
	 */
	public void setOneAlphabet(boolean oneAlphabet) {
		this.oneAlphabet = oneAlphabet;
	}

	public List<ProduccionBNF> getProductions() {
		return productions;
	}

	public void setProductions(List<ProduccionBNF> productions) {
		this.productions = productions;
	}

	/**
	 * Impresion de la definicion regular.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BNF: ");
		sb.append(getName());
		sb.append("\n");
		sb.append("ALFABETO/S: ");
		if (oneAlphabet) {
			sb.append(alphabet);
		} else {
			for (Map.Entry<String, Alfabeto> alfabeto : alphabets.entrySet()) {
				sb.append(alfabeto.getKey());
				sb.append(" = ");
				sb.append(alfabeto.getValue());
				sb.append("; ");
			}
		}
		sb.append("\n");
		for (ProduccionBNF production : productions) {
			sb.append(production);
			sb.append("\n");
		}
		return sb.toString();
	}
}
