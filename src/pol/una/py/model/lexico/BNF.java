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
	private List<ProduccionBNF> producciones;
	private String name;

	private Alfabeto alfabeto;
	private Map<String, Alfabeto> alfabetos;
	private boolean unAlfabeto;

	/**
	 * Crea una definicion regular que posee una lista de alfabetos.
	 * 
	 * @param name
	 *            Nombre de la definicion regular
	 * @param producciones
	 *            Lista de producciones
	 * @param alfabetos
	 *            Lista de alfabetos
	 */
	public BNF(String name, List<ProduccionBNF> producciones,
			Map<String, Alfabeto> alfabetos) {
		super();
		this.name = name;
		this.producciones = producciones;
		this.alfabetos = alfabetos;
		this.unAlfabeto = false;
	}

	/**
	 * Crea una definicion regular con un solo alfabeto.
	 * 
	 * @param name
	 *            Nombre de la definicion regular
	 * @param producciones
	 *            Lista de producciones
	 * @param alfabeto
	 *            Alfabeto del BNF.
	 */
	public BNF(String name, List<ProduccionBNF> producciones, Alfabeto alfabeto) {
		super();
		this.name = name;
		this.producciones = producciones;
		this.alfabeto = alfabeto;
		this.unAlfabeto = true;
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
		if (unAlfabeto) {
			return alfabeto.pertenece(value);

		} else {
			// Si no la validacion se realiza por cada alfabeto asociado.
			return alfabetos.containsKey(value);

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
		if (unAlfabeto) {
			for (ProduccionBNF produccion : producciones) {
				aRet.add(new AFN(produccion, alfabeto));
			}
		} else {
			for (ProduccionBNF produccion : producciones) {
				aRet.add(new AFN(produccion, alfabetos));
			}

		}
		return aRet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUnAlfabeto() {
		return unAlfabeto;
	}

	public Map<String, Alfabeto> getAlfabetos() {
		return alfabetos;
	}

	public void setAlfabetos(Map<String, Alfabeto> alfabetos) {
		this.alfabetos = alfabetos;
	}

	/**
	 * @param alfabeto
	 *            the alfabeto to set
	 */
	public void setAlfabeto(Alfabeto alfabeto) {
		this.alfabeto = alfabeto;
	}

	/**
	 * @return the alfabeto
	 */
	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	/**
	 * @param tieneUnAlfabeto
	 *            the tieneUnAlfabeto to set
	 */
	public void setUnAlfabeto(boolean unAlfabeto) {
		this.unAlfabeto = unAlfabeto;
	}

	/**
	 * @return the tieneUnAlfabeto
	 */
	public boolean unAlfabeto() {
		return unAlfabeto;
	}

	public List<ProduccionBNF> getProducciones() {
		return producciones;
	}

	public void setProducciones(List<ProduccionBNF> producciones) {
		this.producciones = producciones;
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
		sb.append(" ");
		if (unAlfabeto) {
			sb.append(alfabeto.toString());
		} else {
			for (Map.Entry<String, Alfabeto> alfabeto : alfabetos.entrySet()) {
				sb.append(alfabeto.getKey());
				sb.append(" = ");
				sb.append(alfabeto.getValue().toString());
				sb.append("; ");
			}
		}
		sb.append("\n");
		for (ProduccionBNF produccion : producciones) {
			sb.append(produccion.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
