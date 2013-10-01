package pol.una.py.model.base;

import java.util.List;


/**
 * Representa un determinado estado dentro del automata
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class Estado {
	private int value;
	private boolean aceptacion;
	private List<Transicion> transiciones;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isAceptacion() {
		return aceptacion;
	}

	public void setAceptacion(boolean aceptacion) {
		this.aceptacion = aceptacion;
	}

	public List<Transicion> getTransiciones() {
		return transiciones;
	}

	public void setTransiciones(List<Transicion> transiciones) {
		this.transiciones = transiciones;
	}

}
