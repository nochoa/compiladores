package pol.una.py.views.grafos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelConFondo extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagen;

    public JPanelConFondo() {
    }

    public JPanelConFondo(String nombreImagen) {
        if (nombreImagen != null) {
//            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
            ImageIcon imagenaux = new ImageIcon(nombreImagen);
            imagen = imagenaux.getImage();
        }
    }

    public JPanelConFondo(Image imagenInicial) {
        if (imagenInicial != null) {
            imagen = imagenInicial;
        }
    }

    public void setImagen(String nombreImagen) {
        if (nombreImagen != null) {
//            imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        	ImageIcon imagenaux = new ImageIcon(nombreImagen);
            imagen = imagenaux.getImage();
        } else {
            imagen = null;
        }

        repaint();
    }

    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }
}
