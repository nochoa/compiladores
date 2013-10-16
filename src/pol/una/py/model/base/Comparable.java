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
	public int compare(Estado state1, Estado state2) {
		return (state1.getValue() > state2.getValue() ? 1 : (state1
				.getValue() == state2.getValue() ? 0 : -1));
	}
}