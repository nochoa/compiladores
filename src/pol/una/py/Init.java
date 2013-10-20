package pol.una.py;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.automatas.AFD;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.lexico.BNF;
import pol.una.py.model.lexico.ExpresionRegular;
import pol.una.py.model.lexico.ProduccionBNF;

/**
 * Punto de entrada a la aplicación
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 27, 2013
 * 
 */
public class Init {
	public static void main(String[] args) throws AnalizadorLexicoException {
		// Login.call();
		// MenuInput menu = new MenuInput();
		// menu.build();

		Map<String, Alfabeto> alfabetos = new HashMap<String, Alfabeto>();
		alfabetos.put("digito", new Alfabeto(Alfabeto.DIGITOS));
		alfabetos.put("letra", new Alfabeto(Alfabeto.LETRAS_MINUSCULAS));
		// a(c|b)*b?(a|c)*
		ExpresionRegular expresion1 = new ExpresionRegular("a(c|b)*b?(a|c)*");
		// ExpresionRegular expresion2 = new ExpresionRegular("[letra]*");

		List<ProduccionBNF> producciones = new ArrayList<>();
		producciones.add(new ProduccionBNF("identificador", expresion1));
		// producciones.add(new ProduccionBNF("letra", expresion2));

		BNF bnf = new BNF("Prueba", producciones, new Alfabeto(
				Alfabeto.LETRAS_MINUSCULAS));
		System.out.println(bnf.toString());

		for (AFN afn : bnf.process()) {
			System.out.println(afn.toString());
			afn.paint();

			AFD sub = afn.generateAFD();
			System.out.println(sub.toString());
			sub.paint();

		}

	}
}
