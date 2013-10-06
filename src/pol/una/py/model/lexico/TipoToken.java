/**
 * 
 */
package pol.una.py.model.lexico;

/**
 * Clase que representa a los tipos de token permitidos.
 * 
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 02/10/2013
 */
public enum TipoToken {
	VALUE, FIN, // Token valido, Final de la expresion regular

	PARENTESIS_LEFT, PARENTESIS_RIGTH, // Parentesis

	CONCATENATE, OR, CERRADURA_KLEENE, CERRADURA_KLEENE_POSITIVE, ALTERNATIVE // Operaciones
}
