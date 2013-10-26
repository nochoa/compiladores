/**
 * 
 */
package pol.una.py.model.base;

/**
 * @author Nathalia Ochoa
 * 
 * @since 1.0
 * @version 1.0 Oct 25, 2013
 */
public enum Extension {
	JAVA(".java"), DOT(".dot"), PNG(".png");
	private String value;

	Extension(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
