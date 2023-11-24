package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

import persistencia.LectorArchivo;

/**
 * <!-- ACA VA DOCUMENTACION -->
 *  Se usa la interfaz Serializable para que los objetos pueden ser convertidos en una secuencia de bytes 
 *  y luego reconstruidos nuevamente a partir de esa secuencia de bytes, para la persistencia. -->
 */

public class EmpresaAlquiler implements Serializable
{
	private static final long serialVersionUID = 1L; //este es el contrato que cumple del implement
	public HashMap<String, Usuario> mapaUsuarios;
	public ArrayList<Sede> listaSedes; //TODO QUITAR ESTATICO
	
	/**
	 * <!-- CONSTRUCTOR -->
	 */
	
	public EmpresaAlquiler() {
		this.listaSedes = new  ArrayList<Sede>();
		this.mapaUsuarios = new HashMap<String, Usuario>();
	}
	
	/**
	 * <!-- METODO PARA LA CONSOLA -->
	 */

	public void leerArchivos() {
		
		Usuario u= new AdminGeneral("0","0",Roles.ADMINISTRADORGENERAL, this);
		this.mapaUsuarios.put("0",u);
		
		//TODO IMPLEMENTAR SEDES
		ArrayList<String> lineas;
		lineas = LectorArchivo.leer("sedes.dat");
		for(String linea : lineas) {
			String []datos = linea.split(";");
			ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
			HashMap<String,Reserva> mapaReserva= new HashMap<String,Reserva>();
			
			Sede sede = new Sede(datos[0], datos[1], datos[2],listaVehiculos,mapaReserva,this); 
			listaSedes.add(sede);
		}
		
		
		lineas = LectorArchivo.leer("empleados.dat");
		for(String linea : lineas) {
			String []datos = linea.split(";");
			
			String codigoSedeBuscada = datos[2]; // Código de la sede que buscamos
			Sede sedeEspecifica = null;
			
			//algoritmo para buscar la sede
			for (Sede sede : listaSedes) {
			    if (sede.getCodigoSede().equals(codigoSedeBuscada)) {
			        sedeEspecifica = sede;
			    }
			}
			
			Usuario user = new Empleado((datos[0]), datos[1], (datos[2]), Roles.EMPLEADO, sedeEspecifica);
			Empleado userEmpleado = new Empleado((datos[0]), datos[1], (datos[2]), Roles.EMPLEADO, sedeEspecifica);//SE LLAMA AL CONSTRUCTOR DE EMPLEADO 		
			this.mapaUsuarios.put(datos[0], user);
			
				//SE GUARDA EN EL MAPA EL USERNAME Y EL USUARIO 		
		}//ACA HAY QUE GUARDARLL EN LA SEDE QUE PERTENECE.


		lineas = LectorArchivo.leer("administradores.dat"); 
		for(String linea : lineas) {
			String []datos = linea.split(";");
			for(Sede sedes : this.listaSedes) {
				if (sedes.codigoSede==datos[2]) {
					{
			Usuario user = new AdminSede(datos[0], datos[1],Roles.ADMINISTRADORSEDE,(datos[2]),sedes,this); // Polimorfismo porque creamos un usuario U pero como un adminsitrador Sede
			this.mapaUsuarios.put(datos[0], user);			
		}}}}
	
//valuesMapa.Usuarios.Cliente.nombre
		
		//TODO IMPLEMENTAR
		lineas = LectorArchivo.leer("clientes.dat");
		for(String linea : lineas) {
			String []datos = linea.split(";");
			Usuario user = new Cliente(datos[0], datos[1], Roles.CLIENTE, datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8], datos[9], datos[10], datos[11]); 
			this.mapaUsuarios.put(datos[0], user);		
		}
		
		
			
		
	}
		
	public String infoCarroEspecifico(String placa) {
		String retorno="";
		for (Sede sedes : this.listaSedes) {
			for (Vehiculo vehiculo : sedes.listaVehiculos) {
				if (vehiculo.placa == placa) {
					retorno=(String.format("el carro de placa %s, esta en la sede%s.", placa, vehiculo.getCodigoSede()));
				} else {
					retorno="no existe este vehiculo";
				}
			}
		}
		// Busquedas, guardan variables
		return retorno;

	}
		
		public void crearCliente(String username, String password, Roles cargo, String nombreCliente, String email, String telefono, String fechaNacimiento, String nacionalidad, String imagenCedula,String imagenLicencia, String metodoDePago, String numeroTarjeta, String fechaVencimiento) {
			Usuario u = new Cliente(username,password,cargo,nombreCliente,email,telefono,fechaNacimiento,nacionalidad,imagenCedula,imagenCedula,metodoDePago,numeroTarjeta,fechaVencimiento);
			this.mapaUsuarios.put(username, u);
			// TODO implement me
				
		}

		
	/**
	 * <!-- GETTERS SETTERS -->
	 */

	public ArrayList<Sede> getListaSedes() {
		return listaSedes;
	}

	public void setListaSedes(ArrayList<Sede> listaSedes) {
		this.listaSedes = listaSedes;
	}

	public HashMap<String, Usuario> getMapaUsuarios() {
		return mapaUsuarios;
	}

	public void setMapaUsuarios(HashMap<String, Usuario> mapaUsuarios) {
		this.mapaUsuarios = mapaUsuarios;
	}

	
	
}
	


