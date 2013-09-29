package pol.una.py.views.menu;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Representa el panel izquierdo de la vista donde se visualiza la salida del
 * traducto, especificamente la sección de los datos ingresados
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class PanelLeft extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String FIELDSET = "Datos ingresados";

	JTextField lenguajeText;
	JLabel lenguajeLabel;
	JTextField expresionRegularText;
	JLabel expresionRegularLabel;

	public PanelLeft() {
		this.setLayout(new GridLayout(2, 1));
		this.setBorder(BorderFactory.createTitledBorder(FIELDSET));

		lenguajeText = new JTextField(20);
		lenguajeLabel = new JLabel("Lenguaje", JLabel.LEFT);
		lenguajeLabel.setLabelFor(lenguajeText);
		this.add(lenguajeLabel);

		this.add(lenguajeText);

		expresionRegularText = new JTextField(20);
		expresionRegularLabel = new JLabel("Expresion regular", JLabel.LEFT);
		expresionRegularLabel.setLabelFor(expresionRegularText);
		this.add(expresionRegularLabel);
		this.add(expresionRegularText);
	}

}
