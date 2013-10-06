/**
 * 
 */
package pol.una.py.model.lexico;

import java.util.Map;

import pol.una.py.excepciones.lexico.ExpresionRegularMalFormada;
import pol.una.py.excepciones.lexico.MatchingFailure;
import pol.una.py.excepciones.lexico.NoPerteneceAlAlfabeto;
import pol.una.py.excepciones.lexico.ParentesisDerechoNoEncontrado;
import pol.una.py.excepciones.lexico.ParentesisIzquierdoNoEncontrado;
import pol.una.py.model.base.Alfabeto;

/**
 * Analizador que recibe una produccion y genera el AFN resultante aplicando la
 * contrucción de Thompson.
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
	 * Crea un analizador lexico para una determinada produccion.
	 * 
	 * @param produccion
	 *            Produccion la cual se desea generar el AFN
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

		this.thompson = primeraProduccion();

		if (!preAnalisis.getTipo().equals(TipoToken.FIN)) {
			throw new ExpresionRegularMalFormada(preAnalisis.getValue());
		}
		return thompson;
	}

	private Thompson primeraProduccion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		Thompson automata1 = null, automata2;

		automata1 = this.terceraProduccion();
		automata2 = this.segundaProduccion();
		if (automata2 != null) {
			automata1.OR(automata2);
		}

		automata1.setProduccion(produccion);
		return automata1;
	}

	private Thompson segundaProduccion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		Token or = new Token("|");
		if (preAnalisis.isEquals(or)) {
			this.match("|");
			return primeraProduccion();
		} else {
			return null;
		}

	}

	private Thompson terceraProduccion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		Thompson automata1 = this.quintaProduccion();
		Thompson automata2 = this.cuartaProduccion();
		if (automata2 != null) {
			automata1.CONCATENATE(automata2);
		}

		return automata1;
	}

	private Thompson cuartaProduccion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {

		String current = preAnalisis.getValue();
		Thompson result = null;
		if ((preAnalisis.getTipo() != TipoToken.FIN) && helper.isValid(current)
				|| current.equals("(")) {
			result = terceraProduccion();
		}
		return result;
	}

	private Thompson quintaProduccion() throws MatchingFailure,
			ParentesisDerechoNoEncontrado, ParentesisIzquierdoNoEncontrado {
		Thompson automata1 = septimaProduccion();

		if (automata1 != null) {
			char operator = sextaProduccion();
			switch (operator) {
			case '*':
				automata1.CERRADURA_KLEENE();
				break;
			case '+':
				automata1.CERRADURA_KLEENE_POSITIVE();
				break;
			case '?':
				automata1.ALTERNATIVE();
				break;
			case 'E':
				break;
			}
		}
		return automata1;
	}

	private char sextaProduccion() throws MatchingFailure {
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

	private Thompson septimaProduccion() throws ParentesisDerechoNoEncontrado,
			ParentesisIzquierdoNoEncontrado, MatchingFailure {

		Token grupofirst = new Token("(");

		if (preAnalisis.isEquals(grupofirst)) {
			return this.octavaProduccion();
		} else {
			return this.novenaProduccion();
		}
	}

	private Thompson octavaProduccion() throws ParentesisDerechoNoEncontrado,
			ParentesisIzquierdoNoEncontrado, MatchingFailure {
		this.match("(");

		Thompson Aux1 = primeraProduccion();

		this.match(")");

		return Aux1;
	}

	private Thompson novenaProduccion() throws MatchingFailure {
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
