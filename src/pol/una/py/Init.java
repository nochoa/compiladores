package pol.una.py;

import java.io.File;

import pol.una.py.excepciones.lexico.AnalizadorLexicoException;
import pol.una.py.views.menu.MenuInput;

/**
 * Punto de entrada a la aplicación
 * 
 * @author Nathalia Ochoa
 * @since 1.0
 * @version 1.0 Set 27, 2013
 * 
 */
public class Init {
	public static void main(String[] args) throws AnalizadorLexicoException {
		removeFiles();
		// Login.call();
		MenuInput menu = new MenuInput();
		menu.build();

	}

	private static void removeFiles() {
		String PATH = "/home/nochoa/Documentos/desarrollo/graphviz";

		File directorio = new File(PATH);
		File file;
		if (directorio.isDirectory()) {
			String[] files = directorio.list();
			if (files.length > 0) {
				for (String archivo : files) {
					file = new File(PATH + File.separator + archivo);
					file.delete();
					file.deleteOnExit();

				}
			}
			System.out
					.println("Cantidad de archivos borrados: " + files.length);
		}

	}
}
