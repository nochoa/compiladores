package pol.una.py.views.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.base.Pila;
import pol.una.py.model.base.Reservadas;
import pol.una.py.model.lexico.BNF;
import pol.una.py.model.lexico.ExpresionRegular;
import pol.una.py.model.lexico.ProduccionBNF;

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
	private static final String PROCESAR = "Procesar";

	private JPanel panelAlfabeto;
	private JPanel panelExpresionRegular;

	Map<String, Alfabeto> alfabetos = new HashMap<String, Alfabeto>();
	
	List<ProduccionBNF> producciones = new ArrayList<>();


	public PanelInput() {
		builPanels();
		this.setLayout(null);
		this.add(panelAlfabeto);
		this.add(panelExpresionRegular);
		this.add(bprocesar);
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
				addExpression(evt);
			}
		});

		belimarExp = new javax.swing.JButton(ELIMINAREXP);
		belimarExp.setBounds(860, 25, 100, 30);
		belimarExp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteExpression(evt);
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
//		tablaExp.setCellSelectionEnabled(false);
		panelExpresionRegular.add(tablaExp);
		
		
		bprocesar = new JButton(PROCESAR);
		bprocesar.setBounds(880, 665, 100, 25);
		bprocesar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					buttonprocessBNF(evt);
				} catch (AnalizadorLexicoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		

	}
	
	public void buttonprocessBNF(ActionEvent e) throws AnalizadorLexicoException {
		
		MenuOutput menuOutput = new MenuOutput();
		if (alfabetos.size()>1){
		BNF bnf = new BNF("Prueba", producciones, alfabetos);
		menuOutput.build(bnf);
		}
		else{
			Alfabeto alfabeto = new Alfabeto("");
			for (Map.Entry<String, Alfabeto> entry : alfabetos.entrySet()) {
				alfabeto = entry.getValue();
				break;
			}
			BNF bnf = new BNF("Prueba", producciones, alfabeto);
			menuOutput.build(bnf);
			}	
		
	}
	
	private void addAlphabet(java.awt.event.ActionEvent evt) {
		String nomalfabeto = this.nalfabeto.getText();
		String alfab = this.talfabeto.getText();
		if (this.nalfabeto.getText().isEmpty() || this.talfabeto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (this.nalfabeto.getText().length() > 26) {
			JOptionPane.showMessageDialog(null,
					"El Alfabeto solo puede tener 26 caracteres.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else if (!this.talfabeto.getText().matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null,
					"El Alfabeto solo puede contener [a-z], [A-Z] ó [0-9].",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			
			this.talfabeto.setText("");
			this.nalfabeto.setText("");
			this.cbAlfabeto.setSelectedIndex(0);
			this.addRowAlphabet(nomalfabeto, alfab);
			
		}

	}

	public void addRowAlphabet(String nomalfabeto, String alfab) {

		DefaultTableModel modelo = (DefaultTableModel) this.tablaAlf.getModel();
		Object[] fila = new Object[2];
		fila[0] = nomalfabeto;
		fila[1] = alfab;
		int filas = modelo.getRowCount();
		int columnas = modelo.getColumnCount();
		if (filas == 0) {
			modelo.addRow(fila);
			Alfabeto alfabetotemp = new Alfabeto(alfab);
			alfabetos.put(nomalfabeto, alfabetotemp);
		} else {
			boolean agregar = true;
			for (int i = 0; i < columnas; i++) {
				for (int j = 0; j < filas; j++) {
					if (((modelo.getValueAt(j, i))).equals(fila[0])) {
						agregar = false;
						JOptionPane.showMessageDialog(null,
								"Ya tiene ese alfabeto", "Error",JOptionPane.ERROR_MESSAGE);
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
				Alfabeto alfabetotemp = new Alfabeto(alfab);
				alfabetos.put(nomalfabeto, alfabetotemp);
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
			this.nalfabeto.setText("Letras minusculas");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;
		case 3:
			this.talfabeto.setText(Alfabeto.LETRAS_MAYUSCULAS);
			this.nalfabeto.setText("Letras mayusculas");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;
		case 4:
			this.talfabeto.setText(Alfabeto.DIGITOS);
			this.nalfabeto.setText("Digitos");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;

		case 5:
			this.nalfabeto.setVisible(true);
			this.cbAlfabeto.setVisible(false);
			this.nalfabeto.setText("");
			this.talfabeto.setText("");
			this.talfabeto.setEnabled(true);
			this.nalfabeto.setEnabled(true);
			break;
		default:
			this.nalfabeto.setVisible(false);
			this.cbAlfabeto.setVisible(true);
			this.nalfabeto.setText("");
			this.talfabeto.setText("");
			this.talfabeto.setEnabled(false);
			this.nalfabeto.setEnabled(false);
			break;
		}
	}

	private void deleteAlphabet(java.awt.event.ActionEvent evt) {
		try {
			DefaultTableModel temp = (DefaultTableModel) this.tablaAlf
					.getModel();
			int filas = temp.getRowCount();
			if (filas == 0) {
				JOptionPane.showMessageDialog(null, "La tabla esta vacia", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (this.tablaAlf.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione una fila","Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int fila = this.tablaAlf.getSelectedRow();
				String keydel = (String) temp.getValueAt(fila, 0);
				temp.removeRow(fila);
				alfabetos.remove(keydel);
			}
			

		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
	}

	// /////////////////////////////Expresion

	private void addExpression(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

		String derecho = this.tladoder.getText();
		String izquierdo = this.tladoizq.getText();
		if (this.tladoizq.getText().isEmpty()
				|| this.tladoder.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete todos los campos",
					"Error", JOptionPane.ERROR_MESSAGE);
		} 
		else if(!balanced(derecho)){
			JOptionPane.showMessageDialog(null, "Parentesis desbalanceados","Error", JOptionPane.ERROR_MESSAGE);
		}
		else{
			this.tladoder.setText("");
			this.tladoizq.setText("");
			this.addRowExpression(izquierdo, derecho);
		}

	}

	public void addRowExpression(String izquierdo, String derecho) {

		DefaultTableModel modelo = (DefaultTableModel) this.tablaExp.getModel();
		Object[] fila = new Object[2];
		fila[0] = izquierdo;
		fila[1] = derecho;
		int filas = modelo.getRowCount();
		int columnas = modelo.getColumnCount();
		boolean nochar = true;
		for (int b = 0; b < derecho.length(); b++) {
			String value = Character.toString(derecho.charAt(b));
			if (value.equals("[")){
				String tok = new String();
				int inicio = b;
				int fin = derecho.indexOf("]", b);
				tok = derecho.substring(inicio + 1, fin);
				b = b + tok.length() + 1;
				if(!search(tok)){
					nochar = false;
					break;
				}
			}
			else if (!Reservadas.isValid(value)){
				if(!searchC(value)){
					nochar = false;
					break;
				}
			}
		}

		if (nochar){
			if (filas == 0) {
				modelo.addRow(fila);
				ExpresionRegular expresion1 = new ExpresionRegular(derecho);
				producciones.add(new ProduccionBNF(izquierdo, expresion1));
				this.tladoder.setText("");
				this.tladoizq.setText("");
			} else {
				boolean agregar = true;
				for (int i = 0; i < columnas; i++) {
					for (int j = 0; j < filas; j++) {
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
				if (agregar) {
					modelo.addRow(fila);
					ExpresionRegular expresion1 = new ExpresionRegular(derecho);
					producciones.add(new ProduccionBNF(izquierdo, expresion1));
					this.tladoder.setText("");
					this.tladoizq.setText("");
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"No se encuentra en el alfabeto", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteExpression(
			java.awt.event.ActionEvent evt) {
		try {
			DefaultTableModel temp = (DefaultTableModel) this.tablaExp.getModel();
			int filas = temp.getRowCount();
			if (filas == 0) {
				JOptionPane.showMessageDialog(null, "La tabla esta vacia", "Error", JOptionPane.ERROR_MESSAGE);
			} else if (this.tablaExp.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(null, "Seleccione una fila","Error", JOptionPane.ERROR_MESSAGE);
			} else {
				int fila = this.tablaExp.getSelectedRow();
				String keydel = (String) temp.getValueAt(fila, 0);
				temp.removeRow(fila);
				producciones.remove(fila);
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			;
		}
	}

	public boolean search( String tok){
		DefaultTableModel temp = (DefaultTableModel) tablaAlf.getModel();
		for (int i=0; i<temp.getRowCount();i++){
			if((((temp.getValueAt(i, 0))).equals(tok))){
				return true;
			}
		}
		return false;
	}
	
	public boolean searchC( String value){
		DefaultTableModel temp = (DefaultTableModel) tablaAlf.getModel();
		boolean ban = false;
		for (int a = 0; a < temp.getRowCount(); a++) {
			Alfabeto alfabeto = new Alfabeto ( (String) temp.getValueAt(a, 1));
			if(alfabeto.pertenece(value)){
				ban = true;
				break;
			}
		}
		if (ban)
			return true;
		else
			return false;
	}
	
	public boolean balanced( String ladoderecho ) {
        Pila pila1;  
    	pila1 = new Pila();    
    	String cadena = ladoderecho;
    	for (int f = 0 ; f < cadena.length() ; f++)
    	{
    	    if (cadena.charAt(f) == '(' || cadena.charAt(f) == '[' || cadena.charAt(f) == '{') {
    	    	pila1.insertar(cadena.charAt(f));
    	    } else {
    	    	if (cadena.charAt(f)==')') {
    	    	    if (pila1.extraer()!='(') {
    	    	        return false;
    	    	    }
    	     	} else {
    	    	    if (cadena.charAt(f)==']') {
    	    	        if (pila1.extraer()!='[') {
    	    	            return false;
    	    	        }
    	    	    } else {
    	    	        if (cadena.charAt(f)=='}') {
    	    	            if (pila1.extraer()!='{') {
    	    	                return false;
    	    	            }
    	    	        }
    	    	    }
    	        }
   	    }   
        }
    	if (pila1.vacia()) {
    	    return true;
    	} else {
   	    return false;
    	}
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
	
	public javax.swing.JButton bprocesar;
}
