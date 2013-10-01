package pol.una.py.model.construccion;

import java.util.List;

/**
 * Representa la lista de producciones que contituyen la definición regular
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class BNF {
	private List<ProduccionBNF> producciones;

	public List<ProduccionBNF> getProducciones() {
		return producciones;
	}

	public void setProducciones(List<ProduccionBNF> producciones) {
		this.producciones = producciones;
	}
}
