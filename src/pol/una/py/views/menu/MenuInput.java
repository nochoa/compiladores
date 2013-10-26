package pol.una.py.views.menu;

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
	private JFrame menu;
	private PanelInput panel;

	/**
	 * Construye el menu de la interfaz principal
	 */
	public MenuInput build( ) {
		menu = new JFrame();
		menu.setTitle(TITLE);
		menu.setBounds(200, 10, 1000, 720);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setResizable(false);

		panel = new PanelInput();
		menu.add(panel);
		panel.bprocesar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				menu.setVisible(false);
			}
		});

		menu.setVisible(true);
		return this;

	}
	
}
