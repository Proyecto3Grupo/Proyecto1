package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class LectorArchivo {

	public static ArrayList<String> leer(String archivo) {
		try {
			ArrayList<String> lineas = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			String linea;
			while((linea=br.readLine()) != null) {
				lineas.add(linea);
			}
			br.close();
			return lineas;
		} catch (Exception e) {
			return null;
		}		
	}
}
