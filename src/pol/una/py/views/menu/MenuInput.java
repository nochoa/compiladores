package pol.una.py.views.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Representa el menu de entrada de la aplicación
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 27, 2013
 * 
 */
public class MenuInput {

	private static final String TITLE = "Traducto dirigido por la sintaxis (TDS)";
	private static final String PROCESAR = "Procesar";
	private JFrame menu;
	private PanelInput panel;

	/**
	 * Construye el menu de la interfaz principal
	 */
	public MenuInput build() {
		menu = new JFrame();
		menu.setTitle(TITLE);
		menu.setBounds(200, 10, 1000, 720);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setResizable(false);

		panel = new PanelInput();
		menu.add(panel);

		panel.add(builButton());
		menu.setVisible(true);
		return this;

	}

	/**
	 * Invoca al proceso de traducción y renderiza la salida del traductor
	 * 
	 * @return
	 */
	private JButton builButton() {
		JButton button = new JButton(PROCESAR);
		button.setBounds(880, 665, 100, 25);
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				MenuOutput menuOutput = new MenuOutput();
				menuOutput.build();

			}
		};
		button.addActionListener(action);
		return button;

	}

}
