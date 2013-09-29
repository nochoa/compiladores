package pol.una.py.views.menu;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Representa la vista donde se visualizan los datos ingresados por el usuario
 * ademas de la salida del traductor
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class PanelOutput extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String ALFABETO = "Alfabeto/s";
	private static final String EXPRESION = "Expresión/es";
	private static final String GRAFICO = "Gráfico";
	private JPanel panelAlfabeto;
	private JPanel panelExpresionRegular;
	private JPanel panelGrafico;

	public PanelOutput() {
		builPanels();
		this.setLayout(null);
		this.add(panelAlfabeto);
		this.add(panelExpresionRegular);
		this.add(panelGrafico);

	}

	private void builPanels() {
		panelAlfabeto = new JPanel();
		panelAlfabeto.setBorder(BorderFactory.createTitledBorder(ALFABETO));
		panelAlfabeto.setBounds(10, 10, 980, 200);
		JLabel hola = new JLabel("mostrar los alfabetos ingresados",
				JLabel.CENTER);
		panelAlfabeto.add(hola);

		panelExpresionRegular = new JPanel();
		panelExpresionRegular.setBorder(BorderFactory
				.createTitledBorder(EXPRESION));
		panelExpresionRegular.setBounds(10, 210, 980, 200);
		JLabel hola2 = new JLabel("mostrar expresiones ingresadas",
				JLabel.CENTER);
		panelExpresionRegular.add(hola2);

		panelGrafico = new JPanel();
		panelGrafico.setBorder(BorderFactory.createTitledBorder(GRAFICO));
		panelGrafico.setBounds(10, 410, 980, 250);
		JLabel hola3 = new JLabel("mostrar el grafico de salida", JLabel.CENTER);
		panelGrafico.add(hola3);
	}

}