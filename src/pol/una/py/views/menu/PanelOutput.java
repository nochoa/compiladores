package pol.una.py.views.menu;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.automatas.AFD;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Alfabeto;
import pol.una.py.model.lexico.BNF;
import pol.una.py.model.lexico.ProduccionBNF;

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
	private static final String SIMULAR = "Simular";
	private static final String GRAFICO = "Elegir produccion";
	private static final String PROCESAR = "Procesar";
	private JPanel panelGrafico;
	private BNF bnf;

	public ProduccionBNF produccion;

	public PanelOutput(BNF bnfpanel) throws AnalizadorLexicoException {
		bnf = bnfpanel;
		builPanels();
		this.add(panelGrafico);
	}

	@SuppressWarnings("serial")
	private void builPanels() {

		jScrollPane1 = new javax.swing.JScrollPane();
		tableAlf = new javax.swing.JTable();
		jScrollPane2 = new javax.swing.JScrollPane();
		tableExpr = new javax.swing.JTable();

		tableAlf.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Nombre", "Alfabeto" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		jScrollPane1.setViewportView(tableAlf);

		tableExpr.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Identificador",
						"Expresión Regular" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		jScrollPane2.setViewportView(tableExpr);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														725,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														725,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(616, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(36, 36, 36)
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 288,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(36, 36, 36)
						.addComponent(jScrollPane2,
								javax.swing.GroupLayout.PREFERRED_SIZE, 288,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(49, Short.MAX_VALUE)));

		DefaultTableModel modelAlfabeto = (DefaultTableModel) this.tableAlf
				.getModel();
		Object[] fila = new Object[2];
		if (bnf.isOneAlphabet()) {
			fila[0] = bnf.getAlphabet().getName();
			fila[1] = bnf.getAlphabet()	;
			modelAlfabeto.addRow(fila);
		} else {
			Map<String, Alfabeto> alfabetos = new HashMap<String, Alfabeto>();
			alfabetos = bnf.getAlphabets();

			for (Map.Entry<String, Alfabeto> entry : alfabetos.entrySet()) {
				fila[0] = entry.getKey();
				fila[1] = entry.getValue();
				modelAlfabeto.addRow(fila);
			}
		}

		DefaultTableModel modelo = (DefaultTableModel) this.tableExpr
				.getModel();
		List<ProduccionBNF> producciones = new ArrayList<>();
		producciones = bnf.productions;

		for (ProduccionBNF production : producciones) {
			fila[0] = production.getName();
			fila[1] = production.getExpresionRegular();
			modelo.addRow(fila);
		}

		bproc = new JButton(PROCESAR);
		bproc.setBounds(10, 25, 100, 30);
		bproc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int fila = tableExpr.getSelectedRow();
				int filas = tableExpr.getRowCount();
				if (filas == 0) {
					JOptionPane.showMessageDialog(null, "La tabla esta vacia",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else if (tableExpr.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una fila",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String este = (String) tableExpr.getValueAt(fila, 0);
					ProduccionBNF producion = bnf.getProduccion(este);
					AFN pre;
					pre = bnf.processProduction(producion);
					System.out.println(pre.toString());
					pre.paint();
					AFD sub = pre.generateAFD();
					sub.paint();

					sub.minimizar();

					// Pestanas hola = new Pestanas(pre);
					final PanelProcess proc = new PanelProcess(pre);

					proc.setVisible(true);
					proc.bvolver
							.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(
										java.awt.event.ActionEvent evt) {
									// this.setVisible(true);
									proc.setVisible(false);

								}
							});
				}
			}
		});

		// ////////////////////////////////////////////////////////////////////////////

		panelGrafico = new JPanel();
		panelGrafico.setBorder(BorderFactory.createTitledBorder(GRAFICO));
		panelGrafico.setBounds(760, 350, 150, 100);

		panelGrafico.add(bproc);

		bsimular = new javax.swing.JButton(SIMULAR);
		bsimular.setBounds(10, 25, 120, 30);

		// ///LIstener del boton Simular
		bsimular.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					probando(evt);
				} catch (AnalizadorLexicoException e) {
					e.printStackTrace();
				}
			}
		});
		panelGrafico.add(bsimular);

	}

	public void probando(ActionEvent e) throws AnalizadorLexicoException {

		// new PanelProcess().setVisible(true);
		// this.setVisible(false);
		// this.panelBNF.setVisible(false);
		// MenuOutput menuOutput = new MenuOutput();
		// System.out.println(bnf.toString());
		// menuOutput.build();
		// for (AFN afn : bnf.process()) {
		// System.out.println(afn.toString());
		// afn.paint();
		// Subconjunto subconjunto = new Subconjunto();
		// for (Cerradura cerradura : subconjunto.build(afn).getCerraduras()) {
		// System.out.println(cerradura.toString());
		// }
		//
		// }

	}

	private javax.swing.JButton bsimular;

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable tableAlf;
	private javax.swing.JTable tableExpr;
	private javax.swing.JButton bproc;

}