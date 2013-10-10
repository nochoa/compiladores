package pol.una.py.model.lexico;

import java.util.Map;

import pol.una.py.excepciones.lexico.ExpresionRegularMalFormada;
import pol.una.py.excepciones.lexico.MatchingFailure;
import pol.una.py.excepciones.lexico.NoPerteneceAlAlfabeto;
import pol.una.py.excepciones.lexico.ParentesisDerechoNoEncontrado;
import pol.una.py.excepciones.lexico.ParentesisIzquierdoNoEncontrado;
import pol.una.py.model.base.Alfabeto;

/**
 * Analizador que recibe una producción y genera el AFN resultante aplicando la
 * contrucción de Thompson. Se basa en el siguiente BNF para definir un lenguaje
 * de expresiones regulares.</br>
 * 
 * 1: expresion -> simple concatenate </br> 2: concatenate -> “|” simple
 * concatenate| Є </br> 3: simple -> basico t </br> 4: t -> basico t | Є </br>
 * 5: basico -> list op </br> 6: operacion -> * | + | ? | Є </br> 7: list ->
 * grupo |fin </br> 8: agrupacion -> “(” expresion “)” </br> 9: final ->
 * [alfabeto] </br>
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 02/10/2013
 */
public class AnalizadorLexico {
	private Thompson thompson;
	private ProduccionBNF produccion;
	private HelperLexico helper;
	private Token preAnalisis;

	/**
	 * Crea un analizador léxico para una determinada producción.
	 * 
	 * @param producción
	 *            producción la cual se desea generar el AFN
	 */
	public AnalizadorLexico(ProduccionBNF produccion,
			Map<String, Alfabeto> alfabetos) {
		this.produccion = produccion;
		this.helper = new HelperLexico(produccion, alfabetos);
		try {
			this.preAnalisis = helper.next();
			this.thompson = new Thompson();

		} catch (NoPerteneceAlAlfabeto e) {
			e.printStackTrace();
		}
	}

	public AnalizadorLexico(ProduccionBNF produccion, Alfabeto alfabeto) {
		this.produccion = produccion;
		this.helper = new HelperLexico(produccion, alfabeto);
		try {
			this.preAnalisis = helper.next();
			this.thompson = new Thompson();

		} catch (NoPerteneceAlAlfabeto e) {
			e.printStackTrace();
		}
	}

	public Thompson go() throws ExpresionRegularMalFormada, MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		this.thompson = expresion();

		if (!preAnalisis.getTipo().equals(TipoToken.FIN)) {
			throw new ExpresionRegularMalFormada(preAnalisis.getValue());
		}
		thompson.getEstadoFinal().setAceptacion(true);
		return thompson;
	}

	/**
	 * <b>Primera producción:</b> Analizador inicial de la expresion regular,
	 * punto de entrada para analizar las distintas operaciones o estructuras
	 * que pueden ser representadas en la expresion.
	 * 
	 * @return
	 * @throws MatchingFailure
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 */
	private Thompson expresion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		Thompson automata1 = null, automata2;

		automata1 = this.procesarExpresion();
		automata2 = this.concatenate();
		if (automata2 != null) {
			automata1.or(automata2);
		}

		automata1.setProduccion(produccion);
		return automata1;
	}

	/**
	 * <b>Segunda producción:</b>producción recursiva que permite analizar las
	 * expresiones que poseen el operador "|" que representa la operacion de
	 * concatenacion.
	 * 
	 * @return
	 * @throws MatchingFailure
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 */
	private Thompson concatenate() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		Token or = new Token("|");
		if (preAnalisis.isEquals(or)) {
			this.match("|");
			return expresion();
		} else {
			return null;
		}

	}

	/**
	 * <b>Tercera producción:</b> Verifica la estructura de preanalisis actual,
	 * la cual puede ser final de la expresion, una de las operaciones
	 * permitidas (+,* o ?) o una expresion agrupada entre parentesis.
	 * 
	 * @return
	 * @throws MatchingFailure
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 */
	private Thompson procesarExpresion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		Thompson automata1 = this.procesarOperacion();
		Thompson automata2 = this.procesarExpresionSiguiente();
		if (automata2 != null) {
			automata1.concatenate(automata2);
		}

		return automata1;
	}

	/**
	 * <b>Cuarta producción:</b> Permite el analisis recursivo de la expresion.
	 * 
	 * @return
	 * @throws MatchingFailure
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 */
	private Thompson procesarExpresionSiguiente() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		String current = preAnalisis.getValue();
		Thompson result = null;
		if ((preAnalisis.getTipo() != TipoToken.FIN) && helper.isValid(current)
				|| current.equals("(")) {
			result = procesarExpresion();
		}
		return result;
	}

	/**
	 * <b>Quinta producción:</b> De acuerdo a la operacion asociada a
	 * preanalisis, invoca a la correspondiente contruccion de Thompson.
	 * 
	 * @return
	 * @throws MatchingFailure
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 */
	private Thompson procesarOperacion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {
		Thompson automata1 = checkAgrupacionOrFin();

		if (automata1 != null) {
			char operator = operacion();
			switch (operator) {
			case '*':
				automata1.cerradura_kleene();
				break;
			case '+':
				automata1.cerradura_kleene_positive();
				break;
			case '?':
				automata1.alternative();
				break;
			case 'E':
				break;
			}
		}
		return automata1;
	}

	/**
	 * <b>Sexta producción:</b>Obtiene la operacion asociada al token actual, es
	 * decir el siguiente token, las operaciones posibles son: * | + | ?
	 * 
	 * @return Operacion.
	 * @throws MatchingFailure
	 */
	private char operacion() throws MatchingFailure {
		char operador = 'E';

		if (preAnalisis.getValue().compareTo("") != 0) {
			operador = preAnalisis.getValue().charAt(0);

			switch (operador) {
			case '*':
				this.match("*");
				break;
			case '+':
				this.match("+");
				break;
			case '?':
				this.match("?");
				break;
			default:
				return 'E';
			}
		}
		return operador;
	}

	/**
	 * <b>Septima producción:</b>Verifica si preanalisis se corresponde con una
	 * expresion entre parentesis o si es el fin de la expresion.
	 * 
	 * @return
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 * @throws MatchingFailure
	 */
	private Thompson checkAgrupacionOrFin()
			throws ParentesisDerechoNoEncontrado,
			ParentesisIzquierdoNoEncontrado, MatchingFailure {

		Token grupofirst = new Token("(");

		if (preAnalisis.isEquals(grupofirst)) {
			return this.agrupacion();
		} else {
			return this.checkFin();
		}
	}

	/**
	 * <b>Octava producción:</b>Analiza una expresion que esta agrupada entre
	 * parentesis.
	 * 
	 * @return
	 * @throws ParentesisDerechoNoEncontrado
	 * @throws ParentesisIzquierdoNoEncontrado
	 * @throws MatchingFailure
	 */
	private Thompson agrupacion() throws ParentesisDerechoNoEncontrado,
			ParentesisIzquierdoNoEncontrado, MatchingFailure {
		this.match("(");

		Thompson Aux1 = expresion();

		this.match(")");

		return Aux1;
	}

	/**
	 * <b>Novena producción:</b>Si la expresion se ha analizado totalmente, es
	 * decir si preanalisis es el fin de la expresion, retorna un automata nulo,
	 * caso contrario contruye un automata simple y consume el token actual.
	 * 
	 * @return
	 * @throws MatchingFailure
	 */
	private Thompson checkFin() throws MatchingFailure {
		Thompson nuevo = null;

		if (preAnalisis.getTipo() != TipoToken.FIN) {
			nuevo = new Thompson(preAnalisis);
			this.match(preAnalisis.getValue());
		}

		return nuevo;
	}

	private void match(String simbolo) throws MatchingFailure {

		Token temporal = new Token(simbolo);
		if (getPreAnalisis().isEquals(temporal)) {
			try {
				this.setPreAnalisis(helper.next());
			} catch (NoPerteneceAlAlfabeto e) {
				e.printStackTrace();
			}
		} else {
			throw new MatchingFailure();
		}
	}

	public Thompson getThompson() {
		return thompson;
	}

	public void setThompson(Thompson thompson) {
		this.thompson = thompson;
	}

	public ProduccionBNF getExpresionRegular() {
		return produccion;
	}

	public void setExpresionRegular(ProduccionBNF expresionRegular) {
		this.produccion = expresionRegular;
	}

	public HelperLexico getHelper() {
		return helper;
	}

	public void setHelper(HelperLexico helper) {
		this.helper = helper;
	}

	public Token getPreAnalisis() {
		return preAnalisis;
	}

	public void setPreAnalisis(Token preAnalisis) {
		this.preAnalisis = preAnalisis;
	}

}
