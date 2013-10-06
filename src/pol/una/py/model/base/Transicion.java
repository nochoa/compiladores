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
	private Estado origen;
	private Estado destino;
	private String simbolo;

	/**
	 * @param origen
	 * @param destino
	 * @param simbolo
	 */
	public Transicion(Estado origen, Estado destino, String simbolo) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.simbolo = simbolo;
	}

	public Estado getOrigen() {
		return origen;
	}

	public void setOrigen(Estado origen) {
		this.origen = origen;
	}

	public Estado getDestino() {
		return destino;
	}

	public void setDestino(Estado destino) {
		this.destino = destino;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}
