package pol.una.py.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pol.una.py.views.menu.MenuInput;

/**
 * Representa la interfaz de login para el ingreso a la aplicación
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 27, 2013
 * 
 */
public class Login {

	private static final String TITLE = "Inicio de sesion";
	private static final String LOGIN_FAILURE = "El usuario o contraseña son incorrectos";
	private static final String USER_LABEL = "Usuario";
	private static final String PASS_LABEL = "Password";
	private static final String LOGIN = "Ingresar";
	private static final String USER = "admin";
	private static final String PASS = "admin";

	public static void call() {
		final JFrame windows = new JFrame();
		windows.setTitle(TITLE);
		windows.setBounds(500, 250, 300, 160);
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windows.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		windows.add(panel);

		final JLabel message = new JLabel(LOGIN_FAILURE);
		message.setBounds(7, 2, 300, 25);
		message.setForeground(Color.RED);
		message.setVisible(false);
		panel.add(message);

		JLabel userLabel = new JLabel(USER_LABEL);
		userLabel.setBounds(10, 30, 80, 25);
		panel.add(userLabel);

		final JTextField userText = new JTextField(20);
		userText.setBounds(100, 30, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel(PASS_LABEL);
		passwordLabel.setBounds(10, 60, 80, 25);
		panel.add(passwordLabel);

		final JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 60, 160, 25);
		panel.add(passwordText);

		JButton loginButton = new JButton(LOGIN);
		loginButton.setBounds(100, 90, 100, 25);
		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateUser(userText.getText())
						&& validatePassword(passwordText.getPassword())) {
					message.setVisible(false);
					windows.setVisible(false);
					MenuInput menu = new MenuInput();
					menu.build();
				} else {
					message.setVisible(true);
				}

			}
		};
		loginButton.addActionListener(action);
		panel.add(loginButton, BorderLayout.CENTER);

		windows.setVisible(true);

	}

	/**
	 * Valida el usuario ingresado
	 * 
	 * @param user
	 *            Usuario ingresado
	 * @return
	 */
	private static boolean validateUser(String user) {
		if (user.equals(USER)) {
			return true;
		}
		return false;
	}

	/**
	 * Valida la contraseña ingresada
	 * 
	 * @param password
	 *            Contraseña ingresada
	 * @return
	 */
	private static boolean validatePassword(char[] password) {
		if (new String(password).equals(PASS)) {
			return true;
		}
		return false;
	}
}
