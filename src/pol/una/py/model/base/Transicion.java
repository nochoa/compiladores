package pol.una.py.model.base;

/**
 * Representa una transición desde un estado inicial a un estado siguiente.
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class Transicion {
	private Estado origin;
	private Estado destination;
	private String symbol;

	/**
	 * @param origin
	 * @param destination
	 * @param symbol
	 */
	public Transicion(Estado origin, Estado destination, String symbol) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.symbol = symbol;
	}

	public Estado getOrigin() {
		return origin;
	}

	public void setOrigin(Estado origin) {
		this.origin = origin;
	}

	public Estado getDestination() {
		return destination;
	}

	public void setDestination(Estado destination) {
		this.destination = destination;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
