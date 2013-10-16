package pol.una.py.model.lexico.algoritmos;

import pol.una.py.model.automatas.AF;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.TablaTransicion;
import pol.una.py.model.base.Transicion;
import pol.una.py.model.lexico.Token;

/**
 * Clase que proporciona las funcionalidades necesarias para el algoritmo de
 * construcción de Thompson. Utiliza transiciones ε para unir las máquinas de
 * cada segmento de una expresión regular con el fin de formar una máquina que
 * corresponde con la expresión completa.
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public class Thompson extends AF {
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
		this.setTable(new TablaTransicion());

		Estado origin = new Estado(0);
		Estado destination = new Estado(1);

		origin.addTransition(new Transicion(origin, destination, token.getValue()));

		this.getTable().addEstado(origin);
		this.getTable().addEstado(destination);
	}

	public void or(Thompson thompson) {
		Estado newInit = new Estado(0);
		Estado newFinal = new Estado(this.size() + thompson.size() + 1);

		resetStates(1);
		thompson.resetStates(this.size() + 1);

		// Conectamos el nuevo estado inicial con ambos automatas.
		newInit.addTransition(new Transicion(newInit, getInitState(),
				ε));
		newInit.addTransition(new Transicion(newInit, thompson
				.getInitState(), ε));

		// Conectamos ambos automatas con el nuevo estado final.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), newFinal, ε));
		thompson.getEndState().addTransition(
				new Transicion(thompson.getEndState(), newFinal, ε));

		// Agregamos los estados del automata2 en el automata1.
		for (Estado estado : thompson.getTable().getStates()) {
			this.getTable().addEstado(estado);
		}

		this.getTable().addEstado(newInit);
		this.getTable().addEstado(newFinal);

	}

	public void concatenate(Thompson thompson) {
		// Si la cadena vacia aun no esta en la lista de simbolos la agregamos.
		// Esto es debido a que los simbolos se agregan cuando de agrega un
		// estado pero en esta operacion no se agrega ningun estado.
		this.getTable().addSymbolEmpty();

		thompson.resetStates(this.size());

		// Conectamos ambos automatas con una transicion ε.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), thompson
						.getInitState(), ε));

		// Agregamos los estados del automata2 en el automata1.
		for (Estado state : thompson.getTable().getStates()) {
			this.getTable().addEstado(state);
		}

	}

	public void cerradura_kleene() {
		Estado newInit = new Estado(0);
		Estado newFinal = new Estado(this.size() + 1);

		resetStates(1);

		// Conectamos el nuevo estado inicial con el estado inicial del
		// automata.
		newInit.addTransition(new Transicion(newInit, getInitState(),
				ε));
		// Agregamos la transicion ε para la cadena vacia.
		newInit.addTransition(new Transicion(newInit, newFinal, ε));

		// Conectamos el estado final del automata con el nuevo estado final.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), newFinal, ε));

		// Agregamos la transicion ε para la repeticion.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), this.getInitState(),
						ε));

		this.getTable().addEstado(newInit);
		this.getTable().addEstado(newFinal);

	}

	public void cerradura_kleene_positive() {
		Estado newInit = new Estado(0);
		Estado newFinal = new Estado(this.size() + 1);

		resetStates(1);

		// Conectamos el nuevo estado inicial con el estado inicial del
		// automata.
		newInit.addTransition(new Transicion(newInit, getInitState(),
				ε));

		// Conectamos el estado final del automata con el nuevo estado final.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), newFinal, ε));

		// Agregamos la transicion ε para la repeticion.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), newFinal, ε));

		getTable().addEstado(newInit);
		getTable().addEstado(newFinal);

	}

	public void alternative() {
		Estado newInit = new Estado(0);
		Estado newFinal = new Estado(this.size() + 1);

		resetStates(1);

		// Conectamos el nuevo estado inicial con el estado inicial del
		// automata.
		newInit.addTransition(new Transicion(newInit, getInitState(),
				ε));
		newInit.addTransition(new Transicion(newInit, newFinal, ε));

		// Conectamos el estado final del automata con el nuevo estado final.
		this.getEndState().addTransition(
				new Transicion(this.getEndState(), newFinal, ε));

		getTable().addEstado(newInit);
		getTable().addEstado(newFinal);

	}

}
