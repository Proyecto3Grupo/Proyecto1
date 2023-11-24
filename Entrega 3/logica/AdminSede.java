package logica;

/**
 * <!-- ACA DOCUMENTACION -->
 * TODO Implementar los metodos. -->
 */

public class AdminSede extends Usuario {
	
	private static final long serialVersionUID = 2L;
	public String codigoSedeAdmin;
	
	public Sede sede;
	public EmpresaAlquiler empresa;
	
	/**
	 * <!-- CONSTRUCTOR -->
	 */

	
	public AdminSede(String username, String password, Roles rol, String codigoSedeAdmin, Sede sede,EmpresaAlquiler empresa) {
		super(username, password, rol);
		this.codigoSedeAdmin = codigoSedeAdmin;
		this.sede = sede;
		this.empresa = empresa;
	}
	
	
	/**
	 * <!-- METODOS DEL ADMIN DE SEDE -->
	 */
	
	

    /*public void addVehiculo(String categoria, String placa, String marca, String codigoReservaActual, String modelo, String color, String tipoTransmision, Estados estados) {
        Vehiculo nuevoVehiculo = new Vehiculo(categoria, placa, marca, codigoReservaActual, modelo, color, tipoTransmision, estados);

        sede.listaVehiculos.add(nuevoVehiculo);
    }*/
	
	public void addEmpleado(String username, String password, Roles cargo) {
		for (Sede sedes:empresa.listaSedes ) {
			if(sedes.codigoSede.equals(codigoSedeAdmin)) {
				
		
		Usuario u = new Empleado(username, password, codigoSedeAdmin, Roles.EMPLEADO, sedes);
		this.empresa.mapaUsuarios.put(username,u);
		
		}
	}
}
	public void deleteEmpleado(String username) {
    	for (Sede sedes:empresa.listaSedes ) {
			if(sedes.codigoSede.equals(username)) {
		empresa.mapaUsuarios.remove(username);		
		
		}
    	}
    }

        		
        /**
    	 * <!-- GETTERS  y SETTERS -->
    	 */

	public String getCodigoSede() {
		return codigoSedeAdmin;
	}

	public void setCodigoSede(String codigoSede) {
		this.codigoSedeAdmin = codigoSede;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	/**
	 * <!-- GETTERS SETTERS -->
	 */

	
	//NO se necesita getters ni setters para el cargo, ya que solo hay un admin general.
}

