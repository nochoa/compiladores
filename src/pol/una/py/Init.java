package pol.una.py;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.lexico.AnalizadorLexico;
import pol.una.py.model.lexico.BNF;
import pol.una.py.model.lexico.ExpresionRegular;
import pol.una.py.model.lexico.ProduccionBNF;
import pol.una.py.model.lexico.Thompson;
import pol.una.py.views.grafos.GraphicHelper;

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
		String DIGITOS = "{0,1,2,3,4,5,6}";
		String LETRAS = "{a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,s}";

		Map<String, Alfabeto> alfabetos = new HashMap<String, Alfabeto>();
		alfabetos.put("digito", new Alfabeto(DIGITOS));
		alfabetos.put("letra", new Alfabeto(LETRAS));

		ExpresionRegular expresion1 = new ExpresionRegular(
				"([letra]|[digito])*");
		ExpresionRegular expresion2 = new ExpresionRegular("[letra]*");

		List<ProduccionBNF> producciones = new ArrayList<>();
		producciones.add(new ProduccionBNF("identificador", expresion1));
		producciones.add(new ProduccionBNF("letra", expresion2));

		BNF bnf = new BNF("Prueba", producciones, alfabetos);
		System.out.println(bnf.toString());

		for (AFN afn : bnf.process()) {
			System.out.println(afn.toString());
			afn.paint();

		}

	}
}
