package pol.una.py;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
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

		Alfabeto alfabeto1 = new Alfabeto(DIGITOS);
		Alfabeto alfabeto2 = new Alfabeto(LETRAS);

		Map<String, Alfabeto> alfabetos = new HashMap<String, Alfabeto>();
		alfabetos.put("digito", alfabeto1);
		alfabetos.put("letra", alfabeto2);

		Map<String, Alfabeto> alfabetos2 = new HashMap<String, Alfabeto>();
		alfabetos2.put("digito", alfabeto1);

		ExpresionRegular expresion1 = new ExpresionRegular("((((a|c)*))a)");

		ProduccionBNF produccion1 = new ProduccionBNF("hola","identificador",
				expresion1);

		List<ProduccionBNF> producciones = new ArrayList<>();
		producciones.add(produccion1);
		// producciones.add(produccion2);

		BNF bnf = new BNF("Prueba", producciones, alfabeto2);
		System.out.println(bnf.toString());
		AnalizadorLexico analizar = new AnalizadorLexico(produccion1, alfabeto2);

		Thompson result = analizar.go();
		System.out.println(result.toString());

		GraphicHelper graph = new GraphicHelper();
		graph.graph(result);
	}

}
