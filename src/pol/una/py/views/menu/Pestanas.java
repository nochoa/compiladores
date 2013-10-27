package pol.una.py.views.menu;

/////////////////////////////////////////////////////////////////////////////// 

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import pol.una.py.model.automatas.AFN;
import pol.una.py.model.base.Estado;
import pol.una.py.model.base.Transicion;
import pol.una.py.model.lexico.ProduccionBNF;

public class Pestanas extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPane panelConFichas = new JTabbedPane();// crando herramienta de
													// fichas
	ImageIcon icon = new ImageIcon("cerrar.png");// declarando los icono para
													// luegos ponerlos en las
													// pestañas

	public Pestanas(AFN afnproc) throws IOException {

		super("Produccion");
		
		setVisible(true);
		setSize(500, 500);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);

		// empieza la primera pestaña
		JPanel p1 = new JPanel();
		JLabel lbl1 = new JLabel("panel uno11");
		lbl1.setBounds(100, 100, 80, 70);
		p1.add(lbl1);

		tablaAlf = new javax.swing.JTable();

		tablaAlf.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Estado", "Transiciones" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		tablaAlf.setBounds(10, 10, 725, 200);

		DefaultTableModel mo = (DefaultTableModel) this.tablaAlf.getModel();
		Object[] fila = new Object[2];
		for (Estado estado : afnproc.getTable().getStates()) {
			fila[0] = estado.getValue();

			StringBuilder sb = new StringBuilder();
			for (Transicion transicion : estado.getTransitions()) {
				sb.append("(" + transicion.getSymbol() + ")"
						+ transicion.getDestination().getValue());
			}
			fila[1] = sb.toString();
			mo.addRow(fila);
		}

		p1.add(tablaAlf);

		panelConFichas.addTab("AFN", null, p1, "Tabla de la produccion");
		// termina la primera pestaña le puedo agregar mas cosas pero no tengo
		// tengo ganas ,total es una prueba

		//
		// empieza la segunda pestaña
		JPanel p2 = new JPanel();
		JLabel lbl2 = new JLabel("pestaña 2");
		p1.setLayout(new BorderLayout());
		p2.add(lbl2, BorderLayout.CENTER);
		panelConFichas.addTab("Grafo", null, p2, "Grafo de la produccion");

		// AFN pre = bnf.processProduction(produccion);

		jTextArea1 = new javax.swing.JTextArea();
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setText(afnproc.toString());
		jTextArea1.disable();
		// jTextArea1.setText(bnf);
		p2.add(jTextArea1);

		// ////termina la pestaña dos

		String pathPng = generatePathPng(afnproc.getProduction().getName().concat("AFN"));

		BufferedImage myPicture = ImageIO.read(new File(pathPng));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		p2.add(picLabel);

		// agregar objeto JTabbedPane al contenedor
		getContentPane().add(panelConFichas);

	}

	private javax.swing.JTable tablaAlf;
	private javax.swing.JTextArea jTextArea1;

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

	private final static String PATH = "/home/deysinalec/Documentos/graphviz/";
	private final static String PNG = ".png";
}
