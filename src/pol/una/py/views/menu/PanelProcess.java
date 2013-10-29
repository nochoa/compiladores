package pol.una.py.views.menu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import pol.una.py.model.automatas.AFD;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Transicion;
import pol.una.py.model.lexico.BNF;
import pol.una.py.model.lexico.CodeGenerator;

public class PanelProcess extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AFN automata;
	public BNF bnf;

	public PanelProcess(AFN afn, BNF definicionRegular) {
		automata = afn;
		bnf = definicionRegular;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		panelafn = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		tableAFN = new javax.swing.JTable();
		String pathPng = generatePathPng(automata.getProduction().getName()
				.concat("AFN"));
		grafoafn = new javax.swing.JPanel();// new javax.swing.JScrollPane();
		// grafoafn = new JPanelConFondo(pathPng);
		// grafoafn.setBorder(new javax.swing.border.LineBorder(new
		// java.awt.Color(0, 0, 0), 1, true));
		// grafoafn.setOpaque(false);
		// ((JPanelConFondo)
		// grafoafn).setImagen("/home/deysinalec/Documentos/graphviz/kAFN.png");

//		BufferedImage myPicture;
//		try {
//			myPicture = ImageIO.read(new File(pathPng));
//			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//			grafoafn.add(picLabel);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		 ImageIcon image = new ImageIcon(pathPng);
		 JLabel picLabel = new JLabel(image);
		 grafoafn.add(picLabel);

		// JScrollPane scroll2 = new JScrollPane(grafoafn);
		// grafoafn.setAutoscrolls(true);
		scrollGrafo = new javax.swing.JScrollBar();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		panelafd = new javax.swing.JPanel();
		panelafd.setAutoscrolls(true);
		jScrollPane3 = new javax.swing.JScrollPane();
		tableAFD = new javax.swing.JTable();
		grafoAFD = new JPanel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		panelmin = new javax.swing.JPanel();
		jScrollPane5 = new javax.swing.JScrollPane();
		tableMin = new javax.swing.JTable();
		grafoMin = new JPanel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		nproduccion = new javax.swing.JTextField();
		bvolver = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		bgenerarafn = new javax.swing.JButton();
		bgenerarafd = new javax.swing.JButton();
		bgenerarmin = new javax.swing.JButton();

		setTitle("Traductor dirigido por la sintaxis (TDS)");

		jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		tableAFN.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Estado", "Transiciones" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		DefaultTableModel moafn = (DefaultTableModel) this.tableAFN.getModel();
		Object[] fila = new Object[2];
		for (Estado estado : automata.getTable().getStates()) {
			fila[0] = estado.getValue();

			StringBuilder sb = new StringBuilder();
			for (Transicion transicion : estado.getTransitions()) {
				sb.append("(" + transicion.getSymbol() + ")"
						+ transicion.getDestination().getValue());
			}
			fila[1] = sb.toString();
			moafn.addRow(fila);
		}
		scrollGrafo.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
		// scroll2.setViewportView(scrollGrafo);
		// grafoafn.add(scrollGrafo);

		jScrollPane1.setViewportView(tableAFN);

		jLabel1.setText("Tabla de transiciones");

		jLabel2.setText("Grafo");

		bgenerarafn.setLabel("Generar codigo");
		bgenerarafn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CodeGenerator generator = new CodeGenerator(automata, bnf);
				generator.generate();
			}
		});

		javax.swing.GroupLayout panelafnLayout = new javax.swing.GroupLayout(
				panelafn);
		panelafn.setLayout(panelafnLayout);
		panelafnLayout
				.setHorizontalGroup(panelafnLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelafnLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelafnLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																panelafnLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				276,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				grafoafn,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				394,
																				Short.MAX_VALUE))
														.addGroup(
																panelafnLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				190,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel2)
																		.addGap(170,
																				170,
																				170)))
										.addContainerGap()));
		panelafnLayout
				.setVerticalGroup(panelafnLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelafnLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelafnLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jLabel2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												panelafnLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(grafoafn)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																359,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addContainerGap(13, Short.MAX_VALUE)));

		jTabbedPane1.addTab("AFN", panelafn);

		final AFD sub = automata.generateAFD();
		bgenerarafd.setLabel("Generar codigo");
		bgenerarafd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CodeGenerator generator = new CodeGenerator(sub, bnf);
				generator.generate();
			}
		});

		tableAFD.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Estado", "Transiciones" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		DefaultTableModel moafd = (DefaultTableModel) this.tableAFD.getModel();
		fila = new Object[2];
		for (Estado estado : sub.getTable().getStates()) {
			fila[0] = estado.getValue();
			StringBuilder sb = new StringBuilder();
			for (Transicion transicion : estado.getTransitions()) {
				sb.append("(" + transicion.getSymbol() + ")"
						+ transicion.getDestination().getValue());
			}
			fila[1] = sb.toString();
			moafd.addRow(fila);
		}

		String pathPngafd = generatePathPng(automata.getProduction().getName()
				.concat("AFD"));
		ImageIcon image2 = new ImageIcon(pathPngafd);
		JLabel picLabelafd = new JLabel(image2);// (new
												// ImageIcon(myPictureafd));
		grafoAFD.add(picLabelafd);

		jScrollPane3.setViewportView(tableAFD);

		jLabel4.setText("Tabla de transiciones");

		jLabel5.setText("Grafo");

		javax.swing.GroupLayout panelafdLayout = new javax.swing.GroupLayout(
				panelafd);
		panelafd.setLayout(panelafdLayout);
		panelafdLayout
				.setHorizontalGroup(panelafdLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelafdLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												276,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(grafoAFD)
										.addContainerGap())
						.addGroup(
								panelafdLayout
										.createSequentialGroup()
										.addGap(63, 63, 63)
										.addComponent(
												jLabel4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												190,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												276, Short.MAX_VALUE)
										.addComponent(jLabel5)
										.addGap(182, 182, 182))
						.addGroup(
								panelafdLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												bgenerarafd,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												161,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		panelafdLayout
				.setVerticalGroup(panelafdLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelafdLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelafdLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(jLabel5))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												panelafdLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(grafoAFD)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																359,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(bgenerarafd)
										.addContainerGap(13, Short.MAX_VALUE)));

		jTabbedPane1.addTab("AFD", panelafd);

		final AFD min = sub.minimizar();

		bgenerarmin.setLabel("Generar codigo");
		bgenerarmin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CodeGenerator generator = new CodeGenerator(min, bnf);
				generator.generate();
			}
		});

		tableMin.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Estado", "Transiciones" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		DefaultTableModel momin = (DefaultTableModel) this.tableMin.getModel();
		fila = new Object[2];
		for (Estado estado : min.getTable().getStates()) {
			fila[0] = estado.getValue();
			StringBuilder sb = new StringBuilder();
			for (Transicion transicion : estado.getTransitions()) {
				sb.append("(" + transicion.getSymbol() + ")"
						+ transicion.getDestination().getValue());
			}
			fila[1] = sb.toString();
			momin.addRow(fila);
		}

		String pathPngmin = generatePathPng(automata.getProduction().getName()
				.concat("MIN"));
		ImageIcon image3 = new ImageIcon(pathPngmin);
		JLabel picLabelmin = new JLabel(image3);// (new
												// ImageIcon(myPicturemin));
		grafoMin.add(picLabelmin);

		jScrollPane5.setViewportView(tableMin);

		jLabel6.setText("Tabla de transiciones");

		jLabel7.setText("Grafo");

		javax.swing.GroupLayout panelminLayout = new javax.swing.GroupLayout(
				panelmin);
		panelmin.setLayout(panelminLayout);
		panelminLayout
				.setHorizontalGroup(panelminLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelminLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelminLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																panelminLayout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				276,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				grafoMin)
																		.addContainerGap())
														.addGroup(
																panelminLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				190,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				276,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel7)
																		.addGap(182,
																				182,
																				182))
														.addGroup(
																panelminLayout
																		.createSequentialGroup()
																		.addComponent(
																				bgenerarmin,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				161,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)))));
		panelminLayout
				.setVerticalGroup(panelminLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								panelminLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												panelminLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(jLabel7))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												panelminLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(grafoMin)
														.addComponent(
																jScrollPane5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																359,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(bgenerarmin)
										.addContainerGap(13, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Mínimo", panelmin);

		nproduccion.setEnabled(false);
		nproduccion.setText(automata.getProduction().toString());

		bvolver.setLabel("Atrás");

		jLabel3.setText("Producción:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jTabbedPane1)
																.addContainerGap())
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel3)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										nproduccion,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										330,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(173,
																										173,
																										173))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										bvolver,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										94,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addContainerGap()))))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														nproduccion,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jTabbedPane1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(bvolver).addContainerGap()));

		pack();
	}

	public javax.swing.JButton bvolver;
	public javax.swing.JButton bgenerarafn;
	public javax.swing.JButton bgenerarafd;
	public javax.swing.JButton bgenerarmin;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JPanel grafoafn;// javax.swing.JScrollPane grafoafn; //=
										// new JPanel();
	private javax.swing.JScrollPane jScrollPane3;
	private JPanel grafoAFD;
	private javax.swing.JScrollPane jScrollPane5;
	private JPanel grafoMin;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTable tableAFN;
	private javax.swing.JTable tableAFD;
	private javax.swing.JTable tableMin;
	private javax.swing.JTextField nproduccion;
	private javax.swing.JPanel panelafd;
	private javax.swing.JPanel panelafn;
	private javax.swing.JPanel panelmin;
	private javax.swing.JScrollBar scrollGrafo;

	private String generatePathPng(String identificador) {
		StringBuilder sb = generatePath(identificador);
		sb.append(PNG);
		return sb.toString();

	}

	private StringBuilder generatePath(String identificador) {
		StringBuilder sb = new StringBuilder();
		sb.append(PATH);
		sb.append(identificador);
		return sb;

	}

	private final static String PATH = "/home/nochoa/Documentos/desarrollo/graphviz/";
	private final static String PNG = ".png";

}
