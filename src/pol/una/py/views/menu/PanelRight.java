package pol.una.py.views.menu;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Representa el panel derecho de la vista donde se visualiza la salida del
 * traductor, especificamente la seccion del grafico
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class PanelRight extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String FIELDSET = "Salida";
	private static final String GRAFO_LABEL = "Dibujo";

	public PanelRight() {
		this.setLayout(new GridLayout(2, 1));
		this.setBorder(BorderFactory.createTitledBorder(FIELDSET));
		JLabel lenguaje = new JLabel(GRAFO_LABEL, JLabel.CENTER);
		this.add(lenguaje);
	}

}
