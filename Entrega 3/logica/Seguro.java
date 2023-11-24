package logica;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <!-- DOCUMENTACION -->
 * TODO nada solo implementar el metodo y relacionarlo desde reserva
 */

public class Seguro implements Serializable
{
	
	private static final long serialVersionUID = 7L;
	public String nombreSeguro;
	public int tarifaPorDia;
	public TipoSeguro tipoSeguro;
	public static HashMap<String,Seguro> mapaSeguro;
	
	/**
	 * <!-- CONSTRUCTOR -->
	 */
	public Seguro(String nombreSeguro,int tarifaPorDia) {
		super();
		this.nombreSeguro = nombreSeguro;
		this.tarifaPorDia =tarifaPorDia;
	}

	/**
	 * <!-- METODOS DEL CONSTRUCTOR -->
	 */
	
	public static void registrarSeguro(String nombreSeguro,int tarifaPorDia) {
		Seguro nuevoseguro= new Seguro(nombreSeguro, tarifaPorDia);
		mapaSeguro.put(nombreSeguro,nuevoseguro);
		
		// TODO implement me
		
		
		
	}
	public void modificarTarifa(String nombreSeguro,int nuevaTarifa ) {
		
		// TODO implement me
		Seguro res=mapaSeguro.get(nombreSeguro);
		res.tarifaPorDia= nuevaTarifa;
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * <!-- GETTERS SETTERS -->
	 */
	
	public String getNombreSeguro() {
		return nombreSeguro;
	}

	public void setNombreSeguro(String nombreSeguro) {
		this.nombreSeguro = nombreSeguro;
	}

	public int getTarifaPorDia() {
		return tarifaPorDia;
	}

	public void setTarifaPorDia(int tarifaPorDia) {
		this.tarifaPorDia = tarifaPorDia;
	}
	
}
