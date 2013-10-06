package pol.una.py.model.base;

import java.util.ArrayList;
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
	private boolean visitado;
	private List<Transicion> transiciones;

	/**
	 * Crea un estado sin transiciones.
	 * 
	 * @param value
	 */
	public Estado(int value) {
		super();
		this.value = value;
		this.aceptacion = false;
		this.transiciones = new ArrayList<>();
	}

	/**
	 * Crea un estado con una lista de transiciones.
	 * 
	 * @param value
	 * 
	 * @param transiciones
	 */
	public Estado(int value, List<Transicion> transiciones) {
		super();
		this.value = value;
		this.transiciones = transiciones;
		this.aceptacion = false;
	}

	/**
	 * Obtiene los estados alcanzables con un determinado simbolo.
	 * 
	 * @param simbolo
	 * @return Lista de estados alcanzables
	 */
	public List<Estado> getEstadosBySimbol(String simbolo) {
		List<Estado> list = new ArrayList<>();
		for (Transicion transicion : transiciones) {
			if (transicion.getSimbolo().equals(simbolo)) {
				list.add(transicion.getDestino());
			}
		}
		return list;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

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

	public void addTransicion(Transicion transicion) {
		if (transiciones == null) {
			transiciones = new ArrayList<>();
		}
		this.transiciones.add(transicion);
	}

	public void setTransiciones(List<Transicion> transiciones) {
		this.transiciones = transiciones;
	}

}
