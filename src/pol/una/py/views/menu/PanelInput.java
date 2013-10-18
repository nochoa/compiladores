package pol.una.py.views.menu;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import pol.una.py.model.base.Alfabeto;

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
	private static final String AGREGARALF = "Agregar";
	private static final String ELIMINARALF = "Eliminar";
	private static final String AGREGAREXP = "Agregar";
	private static final String ELIMINAREXP = "Eliminar";

	private JPanel panelAlfabeto;
	List<Alfabeto> alfabetos = new ArrayList<Alfabeto>();

	private JPanel panelExpresionRegular;

	public PanelInput() {
		builPanels();
		this.setLayout(null);
		this.add(panelAlfabeto);
		this.add(panelExpresionRegular);
	}

	@SuppressWarnings("unchecked")
	private void builPanels() {
		// org.jdesktop.application.ResourceMap resourceMap =
		// org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Definiciones.class);

		panelAlfabeto = new JPanel();
		panelAlfabeto.setBorder(BorderFactory.createTitledBorder(ALFABETO));
		panelAlfabeto.setLayout(null);
		panelAlfabeto.setBounds(10, 10, 980, 325);

		String[] Alfabetos = { "Elegir Alfabeto", "Letras",
				"Letras minusculas", "Letras mayusculas", "Digitos",
				"Personalizado", };

		cbAlfabeto = new JComboBox(Alfabetos);
		cbAlfabeto.setSelectedIndex(0);
		cbAlfabeto.setBounds(10, 25, 200, 30);
		cbAlfabeto.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelAlfabeto.add(cbAlfabeto);
		cbAlfabeto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onChangeAlphabet(evt);
			}
		});

		baddAlfabeto = new JButton(AGREGARALF);
		baddAlfabeto.setBounds(750, 25, 100, 30);
		panelAlfabeto.add(baddAlfabeto);
		baddAlfabeto.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addAlphabet(evt);
			}
		});

		talfabeto = new javax.swing.JTextField();
		talfabeto.enable(false);
		talfabeto.setBounds(220, 25, 515, 30);
		talfabeto.setEnabled(false);
		panelAlfabeto.add(talfabeto);

		nalfabeto = new javax.swing.JTextField();
		nalfabeto.setBounds(10, 25, 200, 30);
		nalfabeto.setEnabled(false);
		panelAlfabeto.add(nalfabeto);

		belimarAlf = new javax.swing.JButton(ELIMINARALF);
		belimarAlf.setBounds(750, 70, 100, 30);
		belimarAlf.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteAlphabet(evt);
			}
		});
		panelAlfabeto.add(belimarAlf);

		tablaAlf = new javax.swing.JTable();

		tablaAlf.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Nombre", "Alfabeto" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		tablaAlf.setBounds(10, 70, 725, 200);

		// Create the scroll pane and add the table to it.
		jScrollPane1 = new JScrollPane();

		// Add the scroll pane to this window.
		jScrollPane1.setViewportView(tablaAlf);
		// getRootPane().add(scrollPane, BorderLayout.CENTER);

		panelAlfabeto.add(tablaAlf);

		// //////////////////////////////////////////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////////////////////////////////////////

		// Expresion Regular
		panelExpresionRegular = new JPanel();
		panelExpresionRegular.setBorder(BorderFactory
				.createTitledBorder(EXPRESION));
		panelExpresionRegular.setBounds(10, 335, 980, 325);
		// JLabel hola2 = new JLabel("debe permitir ingresar las expresiones",
		// JLabel.CENTER);
		// panelExpresionRegular.add(hola2);

		tladoizq = new javax.swing.JTextField();
		tladoizq.setBounds(10, 25, 360, 30);
		panelExpresionRegular.add(tladoizq);

		tladoder = new javax.swing.JTextField();
		tladoder.setBounds(380, 25, 360, 30);
		panelExpresionRegular.add(tladoder);

		baddExpr = new JButton(AGREGAREXP);
		baddExpr.setBounds(750, 25, 100, 30);
		panelExpresionRegular.setLayout(null);
		panelExpresionRegular.add(baddExpr);

		baddExpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBaddExprActionPerformed(evt);
			}
		});

		belimarExp = new javax.swing.JButton(ELIMINAREXP);
		belimarExp.setBounds(860, 25, 100, 30);
		belimarExp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonEliminarExpActionPerformed(evt);
			}
		});
		panelExpresionRegular.add(belimarExp);

		tablaExp = new javax.swing.JTable();

		tablaExp.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Expresion", "Regular" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		tablaExp.setBounds(10, 70, 725, 200);
		// tablaAlf.setPreferredScrollableViewportSize(new Dimension(500, 70));

		// Create the scroll pane and add the table to it.
		jScrollPane2 = new JScrollPane();

		// Add the scroll pane to this window.
		jScrollPane2.setViewportView(tablaExp);
		// getRootPane().add(scrollPane, BorderLayout.CENTER);

		panelExpresionRegular.add(tablaExp);

	}

	private void addAlphabet(java.awt.event.ActionEvent evt) {
		String nomalfabeto = this.nalfabeto.getText();
		String alfab = this.talfabeto.getText();
		if (this.nalfabeto.getText().isEmpty()
				|| this.talfabeto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete todos los campos",
					"Error", JOptionPane.ERROR_MESSAGE);
		} else if (this.nalfabeto.getText().length() > 26) {
			JOptionPane.showMessageDialog(null,
					"El Alfabeto solo puede tener 26 caracteres.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (!this.talfabeto.getText().matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null,
					"El Alfabeto solo puede contener [a-z], [A-Z] ó [0-9].",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			this.cbAlfabeto.setVisible(true);
			this.nalfabeto.setVisible(false);
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			this.talfabeto.setText("");
			this.nalfabeto.setText("");
			this.cbAlfabeto.setSelectedIndex(0);
			this.addRowAlphabet(nomalfabeto, alfab);
			System.out.println("eurecaaaaaaa " + alfab);
			Alfabeto alfabetotemp = new Alfabeto(alfab);
			alfabetos.add(alfabetotemp);
			System.out.println("siiiiiiii " + alfabetotemp);
		}

	}

	public void addRowAlphabet(String expNombre2, String expr) {

		DefaultTableModel modelo = (DefaultTableModel) this.tablaAlf.getModel();
		Object[] fila = new Object[2];
		fila[0] = expNombre2;
		fila[1] = expr;
		int filas = modelo.getRowCount();
		int columnas = modelo.getColumnCount();
		if (filas == 0) {
			modelo.addRow(fila);
		} else {
			boolean agregar = true;
			for (int i = 0; i < columnas; i++) {
				for (int j = 0; j < filas; j++) {
					Object o = modelo.getValueAt(j, i);
					if (((modelo.getValueAt(j, i))).equals(fila[0])) {
						agregar = false;
						JOptionPane.showMessageDialog(null,
								"Ya tiene ese alfabeto", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				if (agregar == false) {
					break;
				}
			}
			if (agregar == true) {
				modelo.addRow(fila);
				this.talfabeto.setText("");
				this.nalfabeto.setText("");
			}
		}
	}

	private void onChangeAlphabet(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ClasesActionPerformed

		switch (this.cbAlfabeto.getSelectedIndex()) {
		case 1:
			this.talfabeto.setText(Alfabeto.LETRAS);
			this.nalfabeto.setText("Letras");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;
		case 2:
			this.talfabeto.setText(Alfabeto.LETRAS_MINUSCULAS);
			this.nalfabeto.setText("Letras");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;
		case 3:
			this.talfabeto.setText(Alfabeto.LETRAS_MAYUSCULAS);
			this.nalfabeto.setText("Letras");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;
		case 4:
			this.talfabeto.setText(Alfabeto.DIGITOS);
			this.nalfabeto.setText("Digitos");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;

		default:
			this.nalfabeto.setVisible(true);
			this.cbAlfabeto.setVisible(false);
			this.nalfabeto.setText("");
			this.talfabeto.setText("");
			this.talfabeto.setEnabled(true);
			this.nalfabeto.setEnabled(true);
		}
	}

	private void deleteAlphabet(java.awt.event.ActionEvent evt) {
		try {
			DefaultTableModel temp = (DefaultTableModel) this.tablaAlf
					.getModel();
			int filas = temp.getRowCount();
			if (filas == 0) {
				JOptionPane.showMessageDialog(null, "La tabla esta vacia",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else if (this.tablaAlf.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione una fila",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int fila = this.tablaAlf.getSelectedRow();
				temp.removeRow(fila);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
	}

	// /////////////////////////////Expresion

	private void jBaddExprActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

		String derecho = this.tladoder.getText();
		String izquierdo = this.tladoizq.getText();
		if (this.tladoizq.getText().isEmpty()
				|| this.tladoder.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete todos los campos",
					"Error", JOptionPane.ERROR_MESSAGE);
		} else {
			this.tladoder.setText("");
			this.tladoizq.setText("");
			this.agregarFilaExpr(izquierdo, derecho);
		}

	}

	public void agregarFilaExpr(String izquierdo, String derecho) {

		DefaultTableModel modelo = (DefaultTableModel) this.tablaExp.getModel();
		Object[] fila = new Object[2];
		fila[0] = izquierdo;
		fila[1] = derecho;
		int filas = modelo.getRowCount();
		int columnas = modelo.getColumnCount();
		boolean nochar = true;
		for (int i = 0; i < alfabetos.size(); i++) {
			System.out.println("aaaaaaaaa " + alfabetos.get(i));
			if (!alfabetos.get(i).pertenece(derecho)) {
				nochar = false;

				JOptionPane.showMessageDialog(null,
						"No se encuentra en el alfabeto", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}

		}

		if (filas == 0) {
			if (nochar) {
				modelo.addRow(fila);
			}

		} else {
			boolean agregar = true;
			for (int i = 0; i < columnas; i++) {
				for (int j = 0; j < filas; j++) {
					Object o = modelo.getValueAt(j, i);
					if (((modelo.getValueAt(j, i))).equals(fila[0])) {
						agregar = false;
						JOptionPane.showMessageDialog(this, "Ya existe");
						break;
					}
				}
				if (agregar == false) {
					break;
				}
			}
			if (agregar && nochar) {
				modelo.addRow(fila);
				this.tladoder.setText("");
				this.tladoizq.setText("");
			}
		}
	}

	private void jButtonEliminarExpActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			DefaultTableModel temp = (DefaultTableModel) tablaExp.getModel();
			temp.removeRow(tablaExp.getSelectedRow());

		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
	}

	// ////Para procesar la entrada
	public void procesarEntrada() {

		// DefaultTableModel modelo = (DefaultTableModel)
		// this.tablaExp.getModel();
		// Object[] fila = new Object[2];
		// fila[0] = izquierdo;
		// fila[1] = derecho;
		// int filas = modelo.getRowCount();
		// int columnas = modelo.getColumnCount();
		// if (filas == 0) {
		// modelo.addRow(fila);
		// } else {
		// boolean agregar = true;
		// for (int i = 0; i < columnas; i++) {
		// for (int j = 0; j < filas; j++) {
		// Object o = modelo.getValueAt(j, i);
		// if (((modelo.getValueAt(j, i))).equals(fila[0])) {
		// agregar = false;
		// JOptionPane.showMessageDialog(this, "Ya existe");
		// break;
		// }
		// }
		// if (agregar == false) {
		// break;
		// }
		// }
		// if (agregar == true) {
		// modelo.addRow(fila);
		// this.tladoder.setText("");
		// this.tladoizq.setText("");
		// }
		// }

	}

	// Panel Alfabeto
	public javax.swing.JComboBox cbAlfabeto;
	private javax.swing.JButton baddAlfabeto;
	private javax.swing.JButton belimarAlf;
	private javax.swing.JTable tablaAlf;
	private javax.swing.JScrollPane jScrollPane1;
	public javax.swing.JTextField nalfabeto;
	private javax.swing.JTextField talfabeto;

	// Panel Expresiones
	private javax.swing.JButton baddExpr;
	private javax.swing.JTextField tladoizq;
	private javax.swing.JTextField tladoder;
	private javax.swing.JTable tablaExp;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton belimarExp;

}