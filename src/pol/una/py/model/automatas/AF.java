/**
 * 
 */
package pol.una.py.model.automatas;

import java.util.Collections;

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
	private ProduccionBNF produccion;
	private TablaTransicion tabla;

	public AF() {
		this.tabla = new TablaTransicion();
	}

	/**
	 * Retorna la cantidad de estados que posee el automata
	 * 
	 * @return Cantidad de estados
	 */
	public int size() {
		return getTabla().getEstados().size();
	}

	/**
	 * Actualiza la numeración de los estados del automata.
	 * 
	 * @param cant
	 *            Cantidad de estados que fueron agregados al inicio, es
	 *            utilizado para saber en cuento hay que correr la enumeración
	 *            de los estados.
	 */
	public void reiniciarEstados(int cant) {
		int index = cant;
		Collections.sort(getTabla().getEstados(), new Comparable());
		for (Estado estado : getTabla().getEstados()) {
			estado.setValue(index);
			index++;
		}

	}

	/**
	 * @return the estadoInicial
	 */
	public Estado getEstadoInicial() {
		return getTabla().getEstados().get(0);
	}

	public Estado getEstadoFinal() {
		return getTabla().getEstados().get(this.size() - 1);
	}

	public TablaTransicion getTabla() {
		return tabla;
	}

	public void setTabla(TablaTransicion tabla) {
		this.tabla = tabla;
	}

	public ProduccionBNF getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionBNF produccion) {
		this.produccion = produccion;
	}

	/**
	 * Impresion del automata.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Produccion: ");
		sb.append(produccion.toString());
		sb.append("\n");
		sb.append(tabla.toString());
		return sb.toString();
	}

}
