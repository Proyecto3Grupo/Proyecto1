package logica;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashMap;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

//TODO Y HAY MAS QUE IMPROTAR

/**
 * <!-- ACA VA LA DOCUMENTACION -->
 * TODO constructor, un monton, implementar las relaciones y demas cosas, los metodos etc. 
 * 
 *  PERO ES MEJOR que empiecen desde implementar el metodo crear reserva en cliente y empleado y ahi trabajen 
 *  con esta clase como quieran.-->
 */
//Esta se llamaria cuando se inicie la reserva desde cliente o desde empleado


public class Reserva implements Serializable
{
	
	private static final long serialVersionUID = 5L;
	public String codigoReserva;
	public ArrayList<String> listaConductoresAdicionales;
	public String tipoDeCarro; // yo sugiero cambiarlo a una enum, depende de si se pueden agregar mas tipos de vehiculos
	public String sedeRecogida;
	public String sedeEntrega;
	public String nuevaSedeEntrega;
	public String fechaHoraRecogida;
	public String fechaHoraEntrega;
	public String placaVehiculo;
	public String usernameCliente;
	public Tarifa tarifa;
	public Seguro seguro;
	public Vehiculo vehiculo;
	public String rutaImagenConductorAdiciones;
	public int calculoPrecioFinal;
	public int cantidadConductoresAdicionales;
	public int duracionPorDia;
	public String textoFactura;
	public Entrega estadoEntrega;
	
	/**
	 * <!-- CONSTRUCTOR -->
	 */
	public Reserva(String codigoReserva, ArrayList<String> listaConductoresAdicionales, String tipoDeCarro,
			String sedeRecogida,String sedeEntrega, String nuevaSedeEntrega,String fechaHoraRecogida, String fechaHoraEntrega,
			String placaVehiculo, String usernameCliente, String rutaImagenConductorAdiciones, int calculoPrecioFinal, int cantidadConductoresAdicionales,
			int duracionPorDia, String textoFactura, Entrega estadoEntrega) {
		super();
		this.codigoReserva = codigoReserva;
		this.listaConductoresAdicionales = listaConductoresAdicionales;
		this.tipoDeCarro = tipoDeCarro;
		this.sedeRecogida = sedeRecogida;
		this.sedeEntrega = sedeEntrega;
		this.nuevaSedeEntrega = nuevaSedeEntrega;
		this.fechaHoraRecogida = fechaHoraRecogida;
		this.fechaHoraEntrega = fechaHoraEntrega;
		this.placaVehiculo = placaVehiculo;
		this.usernameCliente = usernameCliente;
		this.rutaImagenConductorAdiciones = rutaImagenConductorAdiciones;
		this.calculoPrecioFinal = calculoPrecioFinal;
		this.cantidadConductoresAdicionales = cantidadConductoresAdicionales;
		this.duracionPorDia = duracionPorDia;
		this.textoFactura = textoFactura;
		this.estadoEntrega = estadoEntrega;
	}
	
	/**
	 * <!-- METODOS DE LA RESERVA -->
	 */
	
	// TODO ACA DEBERIA IR LO DE COMPROBAR EL USUARIO, 
			//si es empleado entonces si puede anadir esto, o si es usuario tambien (no se, miren documentacion)	
			//if usuario.cargo = ROLES.EMPLEADO ||  usuario.cargo = ROLES.CLIENTE
			// then crear el conductor adicional y guardarlo en la reserva, no se si este metodo deberia
			//ir aqui o la reserva, ahi miramos cuando lo implementemos
	public void addConductorAdicional(String rutaImagen) {
		listaConductoresAdicionales.add(rutaImagen);
		
		// TODO implement me
		//Va a pedir direccion de la imagen y se guarda como str en lista conductores adicionales
	}
	
	public String iniciarReserva() {
		//inicializaciones:
		
		
		
		HashMap<String, Tarifa> mapaTarifa = tarifa.getMapaTarifa();
		Tarifa valuesMapaTarifa=mapaTarifa.get(tarifa.categoria);
		
		
		// Formato Fecha:YY//MM//DD//HH//MIN//SEG
		
		//String EjemploFecha = "23/10/25/13/34/01";
		
		//CalculoFecha:
		String[] partesRecogida = fechaHoraRecogida.split("/");
		String[] partesEntrega = fechaHoraEntrega.split("/");
		
		
		LocalDate startDate = LocalDate.of(Integer.parseInt(partesRecogida[0]),Integer.parseInt(partesRecogida[1]),Integer.parseInt(partesRecogida[2]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(partesEntrega[0]),Integer.parseInt(partesEntrega[1]),Integer.parseInt(partesEntrega[2]));
        Period period = Period.between(startDate, endDate);
        this.duracionPorDia = period.getDays();
        Duration duracionTotal = Duration.between(startDate, endDate);
		
        
        
        
		//cantidad_listaConductoresAdd:
		this.cantidadConductoresAdicionales=listaConductoresAdicionales.size();
		//Tarifa por entregar en otra sede:
		//Si nuevaSedeEntrega es igual a 0 es por que se entrega en la misma no hay cambio de sede
		if (nuevaSedeEntrega=="") {
			valuesMapaTarifa.valorPorEntregaOtraSede=0;
		}
		
		
		//Calculo Total Precio
		this.calculoPrecioFinal=(duracionPorDia*valuesMapaTarifa.tarifaPorDia)+(cantidadConductoresAdicionales*valuesMapaTarifa.valorExtraConductorAdicional)+valuesMapaTarifa.valorPorEntregaOtraSede;
		
		//añadir reserva
		
		
		
			
			
			
			
		ocuparVehiculo(duracionPorDia);
		
		String factura30Porciento=generarFactura(30);
		
		String retorno="Felicidades!!! "+ usernameCliente+ "Usted ha reservado el vehiculo con placa"+placaVehiculo+"\n."
		+"Su codigo de reserva es: "
		+codigoReserva+"\n."
		
		+"Usted registro"+listaConductoresAdicionales.size()+"conductores adicionales"+"\n."
		
		+"Su vehiculo es del tipo: "+"\n."
		+tipoDeCarro+"\n."
		
		+"Su vehiculo sera recogido en la sede: "
		+sedeRecogida+"\n."
		
		+"En la fecha: "
		+fechaHoraRecogida+"\n."
		
		+"Su vehiculo sera entregado en la sede: "
		+sedeEntrega+"\n."
		
		+"Por un total de: "
		+duracionPorDia
		+" dias."
		+"Duracion general de: "
		+duracionTotal
		+"En la fecha: "
		+fechaHoraEntrega+"\n."
		+"Recuerde que debe pagar el 30% del valor del alquiler \n. "
		+"Su factura por el 30% es"
		+factura30Porciento;
		
		

		;
		return retorno;
		
		
		
		
		
		
		
		//ESTO NO SE PASA A CODIGO HASTA QUE resuelvan el formato de fechas y todo eso.
		//duracion = fechaHoraEntrega - fechaHoraRecogida
		//ocuparVehiculo(duracion);
	
		
	} //imprimir la factura al final de pronto osea llamar metodo generarfactura, la fecha y todo lo demas.
	
	
	// al terminar la reserva ya se deberia hacer eso automaticamente
	
	void entregaACliente() {
		generarFactura(70);
		estadoEntrega=Entrega.ENTREGADOACIENTE;
		
		
		
	}
	
	
	private void ocuparVehiculo(int duracion) {
		// TODO implement me	
		//ACA SOLO SE CAMBIARIA EL ESTADO USANDO EL ENUM
		
		vehiculo.setEstados(Estados.NODISPONIBLE);
		
	}
	
	//importantisimo, con los getters y setters sale, solo es que formateen el texto
	public String generarFactura(int porcentaje) {
		// TODO implement me	
		porcentaje=porcentaje/100;
		String PrintConductoresAdicionales="";
		String PrintEntregarOtraSede="";
		
		
		HashMap<String, Tarifa> mapaTarifa = tarifa.getMapaTarifa();
		Tarifa valuesMapaTarifa=mapaTarifa.get(tarifa.categoria);
		
		
		
		//	logica Print conductores addicionales
		if (cantidadConductoresAdicionales!=0) {
				PrintConductoresAdicionales="Tarifa por conductor adicional: "+ 
				(porcentaje*valuesMapaTarifa.valorExtraConductorAdicional)+" $"
				+" x "+cantidadConductoresAdicionales + " conductores adicionales.\n" ;
			
		}
		
		if (valuesMapaTarifa.valorPorEntregaOtraSede!=0) {
			PrintEntregarOtraSede="Tarifa por Entregar en otra sede: "+ 
			(porcentaje*valuesMapaTarifa.valorPorEntregaOtraSede)+" $.\n";}
			
		
	
		return 	"Tarifa por dia: "
				+(porcentaje*valuesMapaTarifa.tarifaPorDia)+" $"+" x "+duracionPorDia+" Dias/n"
				+"/n"
				//Print conductores addicionales
				+ PrintConductoresAdicionales
				+ "\n"
				+ PrintEntregarOtraSede
				+ "\n"
				+ "Total: \n"
				+ (porcentaje*calculoPrecioFinal)
				+" $.";
	}
	
	 
	
	/**
	 * <!-- GETTERS SETTERS -->
	 */

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public ArrayList<String> getListaConductoresAdicionales() {
		return listaConductoresAdicionales;
	}

	public void setListaConductoresAdicionales(ArrayList<String> listaConductoresAdicionales) {
		this.listaConductoresAdicionales = listaConductoresAdicionales;
	}

	public String getTipoDeCarro() {
		return tipoDeCarro;
	}

	public void setTipoDeCarro(String tipoDeCarro) {
		this.tipoDeCarro = tipoDeCarro;
	}

	

	public String getSedeRecogida() {
		return sedeRecogida;
	}













	public void setSedeRecogida(String sedeRecogida) {
		this.sedeRecogida = sedeRecogida;
	}













	public String getSedeEntrega() {
		return sedeEntrega;
	}













	public void setSedeEntrega(String sedeEntrega) {
		this.sedeEntrega = sedeEntrega;
	}













	public String getFechaHoraRecogida() {
		return fechaHoraRecogida;
	}

	public void setFechaHoraRecogida(String fechaHoraRecogida) {
		this.fechaHoraRecogida = fechaHoraRecogida;
	}

	public String getFechaHoraEntrega() {
		return fechaHoraEntrega;
	}

	public void setFechaHoraEntrega(String fechaHoraEntrega) {
		this.fechaHoraEntrega = fechaHoraEntrega;
	}

	public String getCodigoVehiculo() {
		return placaVehiculo;
	}

	public void setCodigoVehiculo(String codigoVehiculo) {
		this.placaVehiculo = codigoVehiculo;
	}

	public String getUsernameCliente() {
		return usernameCliente;
	}

	public void setUsernameCliente(String usernameCliente) {
		this.usernameCliente = usernameCliente;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	
	
	
}

