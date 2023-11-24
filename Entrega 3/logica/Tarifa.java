package logica;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <!-- DOCUMENTACION -->
 * TODO Nada solo implementar los metodos y relacionarlo desde reserva  -->
 * @generated
 */

public class Tarifa implements Serializable
{
	private static final long serialVersionUID = 1L;
	public String categoria;
	public int tarifaPorDia;
	public int valorExtraConductorAdicional;
	public int valorPorEntregaOtraSede;
	public HashMap<String,Tarifa> mapaTarifa;
	
	/**
	 * <!- CONSTURCTOR -->
	 */
	
	public Tarifa(String categoria, int tarifaPorDia, int valorExtraConductorAdicional, int valorPorEntregaOtraSede,HashMap<String,Tarifa>mapaTarifa) {
		super();
		this.categoria = categoria;
		this.tarifaPorDia = tarifaPorDia;
		this.valorExtraConductorAdicional = valorExtraConductorAdicional;
		this.valorPorEntregaOtraSede = valorPorEntregaOtraSede;
		this.mapaTarifa=mapaTarifa;
	}

	/**
	 * <!-- METODOS DE LA TARIFA -->
	 */
	
	
	public void registrarTarifa(String categoria, int tarifaPorDia, int valorExtraConductorAdicional, int valorPorEntregaOtraSede,HashMap<String,Tarifa>mapaTarifa) {
		
		// TODO implement me
		Tarifa nuevaTarifa= new Tarifa(categoria, tarifaPorDia, valorExtraConductorAdicional, valorPorEntregaOtraSede, mapaTarifa);
		mapaTarifa.put(categoria,nuevaTarifa);
		
	}
	
	
	/**
	 * <!-- GETTERS SETTERS -->
	 */

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getTarifaPorDia() {
		return tarifaPorDia;
	}

	public void setTarifaPorDia(int tarifaPorDia) {
		this.tarifaPorDia = tarifaPorDia;
	}

	public int getValorExtraConductorAdicional() {
		return valorExtraConductorAdicional;
	}

	public void setValorExtraConductorAdicional(int valorExtraConductorAdicional) {
		this.valorExtraConductorAdicional = valorExtraConductorAdicional;
	}

	public int getValorPorEntregaOtraSede() {
		return valorPorEntregaOtraSede;
	}

	public void setValorPorEntregaOtraSede(int valorPorEntregaOtraSede) {
		this.valorPorEntregaOtraSede = valorPorEntregaOtraSede;
	}

	public HashMap<String, Tarifa> getMapaTarifa() {
		return mapaTarifa;
	}

	public void setMapaTarifa(HashMap<String, Tarifa> mapaTarifa) {
		this.mapaTarifa = mapaTarifa;
	}
	
}

