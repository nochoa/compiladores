package pol.una.py.model.base;

/**
 * Representa las operaciones definidas como válidas para las expresiones
 * regulares
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 30, 2013
 * 
 */
public enum Operacion {
	PARENTESIS_LEFT, 
	PARENTESIS_RIGHT, 
	CONCATENATE, 
	OR, 
	CERRADURA_KLEENE, 
	CERRADURA_KLEENE_POSITIVE, 
	ALTERNATIVE

}
