package logica;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

/**
 * <!-- DOCUMENTACION AQUI PORFA -->
 * TODO: Solamente implementar los metodos de abajo
 * 
 * TODO: Tambien tienen que pensar como se va a formatear el horario de atencion y 
 * luego cambiarlo en el archivo de preuba  -->
 * 
 * 
 */



//TODO IMPLEMENTAR MAPA EN VEZX DE LISTA DE LOS VEHICULOS POR CATEGORIA
//TODO o si piensan mejor un mapa <KEY: CodigoCarro, Value: objeto Carro> esta bien tmb

//Se accederia tipodecarroquequiero
	//listadeesetipodecarro = sede.mapa.get(tipodecarroquequiero)
public class Sede implements Serializable
{
	private static final long serialVersionUID = 3L;
	public String codigoSede;
	public String ubicacion;
	public String horarioAtencion;
	
	public ArrayList<Vehiculo> listaVehiculos;
	
	public HashMap<String,Reserva> mapaReservas;
	public EmpresaAlquiler empresa;
	
	/**
	 * <!-- CONSTRUCTOR  -->
	 * @generated
	 */
	
	
	public Sede(String codigoSede, String ubicacion, String horarioAtencion, ArrayList<Vehiculo> listaVehiculos,
			HashMap<String, Reserva> mapaReservas, EmpresaAlquiler empresa) {
		super();
		this.codigoSede = codigoSede;
		this.ubicacion = ubicacion;
		this.horarioAtencion = horarioAtencion;
		this.listaVehiculos = listaVehiculos;
		this.mapaReservas = mapaReservas;
		this.empresa = empresa;
	}
	/**
	 * <!-- FUNCIONES DEL ADMIN  -->
	 */
	
	
	
	
	

	

	

    public void addVehiculo(String categoria, String placa, String marca, String codigoReservaActual, String modelo, String color, String tipoTransmision, Estados estados, String codigoSede) {
        Vehiculo nuevoVehiculo = new Vehiculo(categoria, placa, marca, codigoReservaActual, color, tipoTransmision, estados,modelo,codigoSede);

        listaVehiculos.add(nuevoVehiculo);
    }
    

	
	public void crearSeguros(String nombreSeguro, int tarifa ) {
		Seguro.registrarSeguro(codigoSede,tarifa);
	}
	
	/**
	 * <!-- GETTERS AND SETTERS -->
	 * @generated
	 */
	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public String getHorarioAtencion() {
		return horarioAtencion;
	}


	public void setHorarioAtencion(String horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}


	public ArrayList<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}


	public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public String getCodigoSede() {
		return codigoSede;
	}

	public void setCodigoSede(String codigoSede) {
		this.codigoSede = codigoSede;
	}



	public HashMap<String, Reserva> getMapaReservas() {
		return mapaReservas;
	}



	public void setMapaReservas(HashMap<String, Reserva> mapaReservas) {
		this.mapaReservas = mapaReservas;
	}
	
	


	
}

