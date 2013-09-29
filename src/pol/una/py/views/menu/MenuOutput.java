package pol.una.py.views.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import pol.una.py.views.Login;

/**
 * Representa el menu de salida de la aplicación
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 28, 2013
 * 
 */
public class MenuOutput {

	private static final String TITLE = "Traducto dirigido por la sintaxis (TDS)";
	private static final String REINICIAR = "Reiniciar";
	private static final String SALIR = "Salir";
	private JFrame menu;
	private PanelOutput panel;

	/**
	 * Construye el menu de salida
	 */
	public MenuOutput build() {
		menu = new JFrame();
		menu.setTitle(TITLE);
		menu.setBounds(200, 10, 1000, 720);
		menu.setResizable(false);

		panel = new PanelOutput();
		menu.add(panel);

		panel.add(builButtonReiniciar());
		panel.add(builButtonSalir());
		menu.setVisible(true);
		return this;
	}

	/**
	 * Reinicializa al traductor y vuelve a la pagina principal
	 * 
	 * @return
	 */
	private JButton builButtonReiniciar() {
		JButton button = new JButton(REINICIAR);
		button.setBounds(770, 665, 100, 25);
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				MenuInput menuInput = new MenuInput();
				menuInput.build();

			}
		};
		button.addActionListener(action);
		return button;

	}

	/**
	 * Cierra la sesión actual del traductor y vuelve a la interfaz de login
	 * 
	 * @return
	 */
	private JButton builButtonSalir() {
		JButton button = new JButton(SALIR);
		button.setBounds(880, 665, 100, 25);
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				Login.call();

			}
		};
		button.addActionListener(action);
		return button;

	}

}
