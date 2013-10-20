/**
 * 
 */
package pol.una.py.model.automatas;

import java.util.Collections;
import java.util.List;

import pol.una.py.model.base.Comparable;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.TablaTransicion;
import pol.una.py.model.lexico.ProduccionBNF;

/**
 * Clase que representa un automata finito.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 02/10/2013
 */
public class AF {
	private ProduccionBNF production;
	private TablaTransicion table;

	public AF() {
		this.table = new TablaTransicion();
	}

	/**
	 * Agrega un estado a la tabla de transiciones.
	 * 
	 * @param state
	 *            Estado a agregar.
	 */
	public void addEstado(Estado state) {
		getTable().addEstado(state);
	}

	public boolean containState(int value) {
		return getTable().containState(value);
	}

	/**
	 * Verifica si un estado se encuentra en la tabla de transicion, de ser asi
	 * retorna dicho estado.
	 * 
	 * @param value
	 * @return
	 */
	public Estado getState(int value) {
		return getTable().getState(value);
	}

	public List<String> getSymbols() {
		return getTable().getSymbols();
	}

	/**
	 * Retorna la cantidad de estados que posee el automata
	 * 
	 * @return Cantidad de estados
	 */
	public int size() {
		return getTable().getStates().size();
	}

	/**
	 * Actualiza la numeración de los estados del automata.
	 * 
	 * @param cant
	 *            Cantidad de estados que fueron agregados al inicio, es
	 *            utilizado para saber en cuento hay que correr la enumeración
	 *            de los estados.
	 */
	public void resetStates(int cant) {
		int index = cant;
		Collections.sort(getTable().getStates(), new Comparable());
		for (Estado estado : getTable().getStates()) {
			estado.setValue(index);
			index++;
		}

	}

	/**
	 * Verifica si todos los estados del automata han sido visitados.
	 * 
	 * @return <b>true</b> Si todos los estados han sido visitados.</br>
	 *         <b>false</b> Si al menos un detalle aun no ha sido visitado.
	 */

	public boolean visitAll() {
		for (Estado estado : table.getStates()) {
			if (estado.isVisited()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return the estadoInicial
	 */
	public Estado getInitState() {
		return getTable().getStates().get(0);
	}

	public Estado getEndState() {
		return getTable().getStates().get(this.size() - 1);
	}

	public TablaTransicion getTable() {
		return table;
	}

	public List<Estado> getStates() {
		return getTable().getStates();
	}

	public void setTable(TablaTransicion table) {
		this.table = table;
	}

	public ProduccionBNF getProduction() {
		return production;
	}

	public void setProduction(ProduccionBNF production) {
		this.production = production;
	}

	/**
	 * Impresion del automata.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Produccion: ");
		sb.append(production.toString());
		sb.append("\n");
		sb.append(table.toString());
		return sb.toString();
	}

}
