package pol.una.py.model.base;

import java.util.Comparator;

/**
 * Comparator utilizado para ordenar una lista de Estados.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 5, 2013
 */
public class Comparable implements Comparator<Estado> {

	/**
	 * Realiza la comparacion entre dos estados para realizar la ordenacion de
	 * manera ascendente.
	 */
	@Override
	public int compare(Estado estado1, Estado estado2) {
		return (estado1.getValue() > estado2.getValue() ? 1 : (estado1
				.getValue() == estado2.getValue() ? 0 : -1));
	}
}