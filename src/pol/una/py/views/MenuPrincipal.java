package pol.una.py.views;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Representa el menu principal de la aplicación
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 27, 2013
 * 
 */
public class MenuPrincipal {
	private static final String TITLE = "Traducto dirigido por la sintaxis (TDS)";

	/**
	 * Construye la interfaz principal
	 */
	public static void build() {
		JFrame windows = new JFrame();

		windows.setTitle(TITLE);
		windows.setBounds(200, 50, 1000, 600);
		windows.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		windows.add(panel);
		windows.setVisible(true);

	}
}
