package pol.una.py.views.menu;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Representa el panel principal de menu
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class PanelInput extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String ALFABETO = "Alfabeto";
	private static final String EXPRESION = "Expresión";
	private JPanel panelAlfabeto;
	private JPanel panelExpresionRegular;

	public PanelInput() {
		builPanels();
		this.setLayout(null);
		this.add(panelAlfabeto);
		this.add(panelExpresionRegular);
	}

	private void builPanels() {
		panelAlfabeto = new JPanel();
		panelAlfabeto.setBorder(BorderFactory.createTitledBorder(ALFABETO));
		panelAlfabeto.setBounds(10, 10, 980, 325);

		JLabel hola = new JLabel("debe permitir ingresar el alfabeto",
				JLabel.CENTER);
		panelAlfabeto.add(hola);

		panelExpresionRegular = new JPanel();
		panelExpresionRegular.setBorder(BorderFactory
				.createTitledBorder(EXPRESION));
		panelExpresionRegular.setBounds(10, 335, 980, 325);
		JLabel hola2 = new JLabel("debe permitir ingresar las expresiones",
				JLabel.CENTER);
		panelExpresionRegular.add(hola2);
	}

}