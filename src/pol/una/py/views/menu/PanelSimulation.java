package pol.una.py.views.menu;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.model.automatas.AFD;
import pol.una.py.model.automatas.AFN;
import pol.una.py.model.lexico.ProduccionBNF;
import pol.una.py.views.grafos.GraphicHelper;

public class PanelSimulation extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AFD automata;
	public String[] cadena;
	public int cont;
	public int stateActual;

	public PanelSimulation(AFD min, String[] nv, int state) {
		cont = 0;
		cadena = nv;
		automata = min;
		stateActual = state;
		initComponents();
	}

	private void initComponents() {

		panelsimulate = new javax.swing.JTabbedPane();
		tabsim = new javax.swing.JPanel();
		bnext = new javax.swing.JButton();
		bnext.setEnabled(true);
		jLabel8 = new javax.swing.JLabel();
		cadenav = new javax.swing.JTextField();
		jPanel1 = new javax.swing.JPanel();
		panelimg = new javax.swing.JPanel();
		prodname = new javax.swing.JTextField();
		batras = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();

		setTitle("Traductor dirigido por la sintaxis (TDS)");

		panelsimulate.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		bnext.setLabel("Siguiente");
		bnext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					next(evt);
				} catch (AnalizadorLexicoException e) {
					e.printStackTrace();
				}
			}
		});

		String pathPng = generatePathPng(automata.getProduction().getName()
				.concat("SIMULATE"));
		ImageIcon imagensim = new ImageIcon(pathPng);
		JLabel picLabel = new JLabel(imagensim);
		panelimg.add(picLabel);

		jLabel8.setText("Cadena para validar: ");

		cadenav.setEnabled(false);
		StringBuilder sb = new StringBuilder();
		for (String symbol : cadena) {
			sb.append(symbol);
		}
		String palabra = sb.toString();
		cadenav.setText(palabra);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 251,
				Short.MAX_VALUE));

		javax.swing.GroupLayout panelimgLayout = new javax.swing.GroupLayout(
				panelimg);
		// panelimg.setLayout(panelimgLayout);
		panelimgLayout.setHorizontalGroup(panelimgLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));
		panelimgLayout.setVerticalGroup(panelimgLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));

		javax.swing.GroupLayout tabsimLayout = new javax.swing.GroupLayout(
				tabsim);
		tabsim.setLayout(tabsimLayout);
		tabsimLayout
				.setHorizontalGroup(tabsimLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tabsimLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												tabsimLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabsimLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8)
																		.addGap(27,
																				27,
																				27)
																		.addComponent(
																				cadenav,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				333,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				58,
																				Short.MAX_VALUE)
																		.addComponent(
																				bnext,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				120,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																panelimg,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addContainerGap()));
		tabsimLayout
				.setVerticalGroup(tabsimLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								tabsimLayout
										.createSequentialGroup()
										.addGap(21, 21, 21)
										.addGroup(
												tabsimLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																cadenav,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(bnext))
										.addGroup(
												tabsimLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																tabsimLayout
																		.createSequentialGroup()
																		.addGap(35,
																				35,
																				35)
																		.addComponent(
																				jPanel1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addGap(109,
																				109,
																				109))
														.addGroup(
																tabsimLayout
																		.createSequentialGroup()
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				panelimg,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addContainerGap()))));

		panelsimulate.addTab("Simulacion", tabsim);

		prodname.setEnabled(false);
		prodname.setText(automata.getProduction().toString());

		batras.setLabel("Atrás");

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
																		panelsimulate)
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
																										prodname,
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
																										batras,
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
														prodname,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3))
								.addGap(18, 18, 18)
								.addComponent(panelsimulate)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(batras).addContainerGap()));

		panelsimulate.getAccessibleContext().setAccessibleName("panel");

		pack();
	}

	public javax.swing.JButton bnext;
	public javax.swing.JButton batras;
	private javax.swing.JTextField cadenav;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTabbedPane panelsimulate;
	private javax.swing.JTextField prodname;
	public javax.swing.JPanel panelimg;
	private javax.swing.JPanel tabsim;
	private JLabel picLabel;

	public void next(ActionEvent e) throws AnalizadorLexicoException {
		cont++;
		if (cont < cadena.length) {
			GraphicHelper graphicHelper = new GraphicHelper();
			stateActual = graphicHelper.simulate(automata, cadena[cont],
					stateActual);
			if (stateActual == -1) {
				System.out.println("Cadena no valida");
				JOptionPane.showMessageDialog(null, "Cadena no valida.",
						"Error", JOptionPane.ERROR_MESSAGE);
				bnext.setEnabled(false);
			} else {
				String pathPng = generatePathPng(automata.getProduction()
						.getName().concat("SIMULATE"));
				ImageIcon image = new ImageIcon(pathPng);
				picLabel.setIcon(image);
//				JLabel picLabel = new JLabel(image);
//				panelimg.add(picLabel);
				
			}

		}
		if (cont == cadena.length) {
			bnext.setEnabled(false);
		}
	}

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
