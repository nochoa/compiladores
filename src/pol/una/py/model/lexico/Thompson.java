package pol.una.py.model.lexico;

import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.TablaTransicion;
import pol.una.py.model.base.Transicion;

/**
 * Clase que proporciona las funcionalidades necesarias para la construcción de
 * Thompson. La cual construye a partir de una expresion regular un diagrama
 * AFN.
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class Thompson extends AFN {
	private static final String ε = "ε";

	/**
	 * Constructor por defecto.
	 */
	public Thompson() {
		super();
	}

	/**
	 * Crea un AFN para un token normal, sin operacion alguna asociada.
	 * 
	 * @param token
	 *            Token del cual se desea crear el AFN.
	 * 
	 */
	public Thompson(Token token) {
		this.setTabla(new TablaTransicion());

		Estado origen = new Estado(0);
		Estado destino = new Estado(1);

		origen.addTransicion(new Transicion(origen, destino, token.getValue()));

		this.getTabla().addEstado(origen);
		this.getTabla().addEstado(destino);
	}

	public void OR(Thompson thompson) {
		Estado newInicial = new Estado(0);
		Estado newFinal = new Estado(this.size() + thompson.size() + 1);

		reiniciarEstados(1);
		thompson.reiniciarEstados(this.size() + 1);

		// Conectamos el nuevo estado inicial con ambos automatas.
		newInicial.addTransicion(new Transicion(newInicial, getEstadoInicial(),
				ε));
		newInicial.addTransicion(new Transicion(newInicial, thompson
				.getEstadoInicial(), ε));

		// Conectamos ambos automatas con el nuevo estado final.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), newFinal, ε));
		thompson.getEstadoFinal().addTransicion(
				new Transicion(thompson.getEstadoFinal(), newFinal, ε));

		// Agregamos los estados del automata2 en el automata1.
		for (Estado estado : thompson.getTabla().getEstados()) {
			this.getTabla().addEstado(estado);
		}

		this.getTabla().addEstado(newInicial);
		this.getTabla().addEstado(newFinal);

	}

	public void CONCATENATE(Thompson thompson) {
		// Si la cadena vacia aun no esta en la lista de simbolos la agregamos.
		// Esto es debido a que los simbolos se agregan cuando de agrega un
		// estado pero en esta operacion no se agrega ningun estado.
		this.getTabla().addSimbolEmpty();

		thompson.reiniciarEstados(this.size());

		// Conectamos ambos automatas con una transicion ε.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), thompson
						.getEstadoInicial(), ε));

		// Agregamos los estados del automata2 en el automata1.
		for (Estado estado : thompson.getTabla().getEstados()) {
			this.getTabla().addEstado(estado);
		}

	}

	public void CERRADURA_KLEENE() {
		Estado newInicial = new Estado(0);
		Estado newFinal = new Estado(this.size() + 1);

		reiniciarEstados(1);

		// Conectamos el nuevo estado inicial con el estado inicial del
		// automata.
		newInicial.addTransicion(new Transicion(newInicial, getEstadoInicial(),
				ε));
		// Agregamos la transicion ε para la cadena vacia.
		newInicial.addTransicion(new Transicion(newInicial, newFinal, ε));

		// Conectamos el estado final del automata con el nuevo estado final.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), newFinal, ε));

		// Agregamos la transicion ε para la repeticion.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), this.getEstadoInicial(),
						ε));

		this.getTabla().addEstado(newInicial);
		this.getTabla().addEstado(newFinal);

	}

	public void CERRADURA_KLEENE_POSITIVE() {
		Estado newInicial = new Estado(0);
		Estado newFinal = new Estado(this.size() + 1);

		reiniciarEstados(1);

		// Conectamos el nuevo estado inicial con el estado inicial del
		// automata.
		newInicial.addTransicion(new Transicion(newInicial, getEstadoInicial(),
				ε));

		// Conectamos el estado final del automata con el nuevo estado final.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), newFinal, ε));

		// Agregamos la transicion ε para la repeticion.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), newFinal, ε));

		getTabla().addEstado(newInicial);
		getTabla().addEstado(newFinal);

	}

	public void ALTERNATIVE() {
		Estado newInicial = new Estado(0);
		Estado newFinal = new Estado(this.size() + 1);

		reiniciarEstados(1);

		// Conectamos el nuevo estado inicial con el estado inicial del
		// automata.
		newInicial.addTransicion(new Transicion(newInicial, getEstadoInicial(),
				ε));
		newInicial.addTransicion(new Transicion(newInicial, newFinal, ε));

		// Conectamos el estado final del automata con el nuevo estado final.
		this.getEstadoFinal().addTransicion(
				new Transicion(this.getEstadoFinal(), newFinal, ε));

		getTabla().addEstado(newInicial);
		getTabla().addEstado(newFinal);

	}

}
