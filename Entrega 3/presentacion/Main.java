package presentacion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import logica.AdminGeneral;
import logica.AdminSede;
import logica.Cliente;
import logica.Empleado;
import logica.EmpresaAlquiler;
import logica.Estados;
import logica.Roles;
import logica.Usuario;
import persistencia.PersistenciaEmpresaAlquiler;

public class Main {
	
	private EmpresaAlquiler empresa;

	public Main() {
		
        this.empresa = PersistenciaEmpresaAlquiler.cargarEmpresaAlquiler();
        
        try {
			if (empresa.getMapaUsuarios().isEmpty()) {
				this.empresa.leerArchivos();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int op = 0;
		
		boolean esEmpleado = esFuncionario();
		
		if (!esEmpleado) { // si es un cliente, le puede dar la opcion crear cliente
			
		do {
			System.out.println("Digite:\n" + "" + 
								"0. Salir\n" + 
								"1. Iniciar Sesion" + 
								"2. Crear Usuario cliente"); 																										
			
			try {
				op = Integer.parseInt(reader.readLine());
				
				if (op == 1) {
					iniciarSesion(this.empresa, reader);
				} else if (op == 2) {
					crearCuenta(this.empresa, reader);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (op != 0);
		
		try {
			// Al cerrar el programa, se guardan los datos de la empresa en un archivo binario.
	        PersistenciaEmpresaAlquiler.guardarEmpresaAlquiler(this.empresa);
	        reader.close();
		} catch (IOException e) {
			e.printStackTrace(); }
		}
		
		else {
			do {
				System.out.println("Digite:\n" + "" + 
									"0. Salir\n" + 
									"1. Iniciar Sesion como funcionario" );
				
				try {
					op = Integer.parseInt(reader.readLine());
					
					if (op == 1) {
						iniciarSesion(this.empresa, reader);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (op != 0);
			
			try {
				// Al cerrar el programa, se guardan los datos de la empresa en un archivo binario.
		        PersistenciaEmpresaAlquiler.guardarEmpresaAlquiler(this.empresa);
		        reader.close();
			} catch (IOException e) {
				e.printStackTrace(); }
			}
		}

	private boolean esFuncionario() {
		// TODO Auto-generated method stub
		
		System.out.println("Digite:\n" + "" + 
				"1. Funcionario empresa\n" + 
				"2. Cliente");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int op = 0;
		boolean retorno = true;
		
		try {
		op = Integer.parseInt(reader.readLine());
		
		if (op == 1) {
			retorno = true;
		} else if (op == 2) {
			retorno = false;
		}
		} catch (IOException e) {
		e.printStackTrace();
		}
		return retorno;
	}

	private void iniciarSesion(EmpresaAlquiler empresa, BufferedReader reader) {
		boolean accesoApp = false;
		
		while (!accesoApp) {
			try {
				System.out.print("Ingrese su nombre de usuario: ");
				String name = reader.readLine();
				System.out.print("Ingrese su contraseña: ");
				String password = reader.readLine();
				Usuario accesoGeneral = this.empresa.mapaUsuarios.get(name);
				
				if (accesoGeneral != null && accesoGeneral.getPassword().equals(password)) {
					accesoApp = true;

					if (accesoGeneral.getRol() == Roles.ADMINISTRADORGENERAL) {
						AdminGeneral accesoAdmiGeneral = (AdminGeneral) this.empresa.mapaUsuarios.get(name);
						MenuAdministradorGeneral(accesoAdmiGeneral);
						
						if ("admin".equals(name)) {
							System.out.print("Ingrese su nuevo nombre de usuario: ");
							String nuevoUser = reader.readLine();
							System.out.print("Ingrese su contraseña: ");
							String nuevoPassword = reader.readLine();
							accesoAdmiGeneral.cambiarContraseña(name, nuevoUser, nuevoPassword, Roles.ADMINISTRADORGENERAL);
						}
					}

					if (accesoGeneral.getRol() == Roles.ADMINISTRADORSEDE) {
						AdminSede accesoAdminSede = (AdminSede) this.empresa.mapaUsuarios.get(name);
						MenuAdministradorSede(accesoAdminSede);
					}

					if (accesoGeneral.getRol() == Roles.EMPLEADO) {
						Empleado accesoEmpleado = (Empleado) this.empresa.mapaUsuarios.get(name);
						MenuEmpleado(accesoEmpleado);
					}

					if (accesoGeneral.getRol() == Roles.CLIENTE) {
						Cliente accesoCliente = (Cliente) this.empresa.mapaUsuarios.get(name);
						MenuCliente(accesoCliente);
					}
				} else {
					System.out.println("Contraseña Incorrecta");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void crearCuenta(EmpresaAlquiler empresa, BufferedReader reader) {
		try {
			System.out.println("Crear un cliente:");
	
			System.out.print("Ingrese el nombre de usuario: ");
			String nuevoUsername = reader.readLine();
			System.out.print("Ingrese la contraseña: ");
			String newPassword = reader.readLine();
	
			System.out.print("Ingrese el nombre del cliente: ");
			String nombreCliente = reader.readLine();
	
			System.out.print("Ingrese el email: ");
			String email = reader.readLine();
	
			System.out.print("Ingrese el telefono: ");
			String telefono = reader.readLine();
	
			System.out.print("Ingrese su fecha de nacimiento: ");
			String fechaNacimiento = reader.readLine();
	
			System.out.print("Ingrese su nacionalidad: ");
			String nacionalidad = reader.readLine();
	
			System.out.print("Ingrese la foto de su cedula: ");
			String imagenCedula = reader.readLine();
	
			System.out.print("Ingrese la imagen de su licencia: ");
			String imagenLicencia = reader.readLine();
	
			System.out.print("Ingrese su metodo de pago: ");
			String metodoDePago = reader.readLine();
	
			System.out.print("Ingrese su numero de tarjeta: ");
			String numeroTarjeta = reader.readLine();
	
			System.out.print("Ingrese la fecha de vencimiento de su tarjeta: ");
			String fechaVencimiento = reader.readLine();
			empresa.crearCliente(nuevoUsername, newPassword, Roles.CLIENTE, nombreCliente, email, telefono,
					fechaNacimiento, nacionalidad, imagenCedula, imagenLicencia, metodoDePago, numeroTarjeta,
					fechaVencimiento);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    private void MenuAdministradorGeneral(AdminGeneral accesoAdmiGeneral) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int op = 0;

        do {
            try {
                System.out.println("Menu Administrador General");
                System.out.println("Digite:\n"
                        + "0. Salir\n"
                        + "1. Crear Nuevo Administrador de la sede\n"
                        + "2. Eliminar Administrador de la sede\n"
                        + "3. Añadir nuevo Vehiculo\n"
                        + "4. Borrar Vehiculo\n"
                        + "5. Añadir nueva Sede\n"
                        + "6. Eliminar Sede\n"
                        + "7. Modificar Seguros\n");
                op = Integer.parseInt(reader.readLine());

                if (op == 1) {
                    System.out.println("Ingrese el nombre de usuario del nuevo administrador de la sede: ");
                    String userNuevoAdminSede = reader.readLine();
                    System.out.println("Ingrese la contraseña del nuevo administrador de la sede: ");
                    String passwordNuevoAdminSede = reader.readLine();
                    System.out.println("Ingrese el código de la sede: ");
                    String CodSede = reader.readLine();

                    accesoAdmiGeneral.crearAdminSede(userNuevoAdminSede, passwordNuevoAdminSede, CodSede, Roles.ADMINISTRADORSEDE);

                } else if (op == 2) {
                    System.out.println("Ingrese el username del administrador que desea borrar: ");
                    String userBorrarAdminSede = reader.readLine();
                    System.out.println("Ingrese el código de la sede a la que pertenece el administrador que desea borrar: ");
                    String codigoSedeBorrarAdminSede = reader.readLine();
                    accesoAdmiGeneral.eliminarAdminSede(userBorrarAdminSede, codigoSedeBorrarAdminSede);

                } else if (op == 3) {
                    System.out.print("Registrar nuevo vehículo: ");

                    System.out.print("Ingrese la categoría del vehículo: ");
                    String categoria = reader.readLine();

                    System.out.print("Ingrese la placa del vehículo: ");
                    String placa = reader.readLine();

                    System.out.print("Ingrese la marca del vehículo: ");
                    String marca = reader.readLine();

                    System.out.print("Ingrese el modelo del vehículo: ");
                    String modelo = reader.readLine();

                    System.out.print("Ingrese el color del vehículo: ");
                    String color = reader.readLine();

                    System.out.print("Ingrese el tipo de transmisión del vehículo: ");
                    String tipoTransmision = reader.readLine();

                    System.out.print("Ingrese el código de sede: ");
                    String codigoSede = reader.readLine();

                    accesoAdmiGeneral.nuevoVehiculo(categoria, placa, marca, "", modelo, color, tipoTransmision, Estados.DISPONIBLE, codigoSede);

                } else if (op == 4) {
                    System.out.print("Ingrese la placa del vehículo que desea eliminar: ");
                    String placa = reader.readLine();

                    System.out.print("Ingrese el código de la sede: ");
                    String codigoSede3 = reader.readLine();

                    accesoAdmiGeneral.borrarVehiculo(placa, codigoSede3);

                } else if (op == 5) {
                    System.out.print("Ingrese el código de la sede: ");
                    String codigoSede = reader.readLine();

                    System.out.print("Ingrese la ubicación de la sede: ");
                    String ubicacion = reader.readLine();

                    System.out.print("Ingrese el horario de atención de la sede: ");
                    String horarioAtencion = reader.readLine();
                    accesoAdmiGeneral.addSede(codigoSede, ubicacion, horarioAtencion);

                } else if (op == 6) {
                    System.out.print("Ingrese el código de la sede que desea eliminar: ");
                    String codigoSede = reader.readLine();

                    accesoAdmiGeneral.eliminarSedes(codigoSede);

                } else if (op == 7) {
                    System.out.print("Ingrese el nombre del seguro que desea modificar: ");
                    String nombreSeguro = reader.readLine();
                    System.out.print("Ingrese el nombre del seguro nuevamente o escriba uno nuevo si desea cambiar el nombre: ");
                    String nuevoNombreSeguro = reader.readLine();
                    System.out.print("Ingrese la nueva tarifa del seguro: ");
                    int tarifaSeguro = Integer.parseInt(reader.readLine());
                    accesoAdmiGeneral.modificarSeguro(nombreSeguro, nuevoNombreSeguro, tarifaSeguro);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (op != 0);
    }

    private void MenuAdministradorSede(AdminSede adminSede) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int op = 0;

        do {
            try {
                System.out.println("Menu Administrador Sede");
                System.out.println("0. Salir\n"
                        + "1. Crear nuevo empleado\n"
                        + "2. Eliminar un empleado\n");
                op = Integer.parseInt(reader.readLine());

                if (op == 1) {
                    System.out.println("Agregar un empleado:");
                    System.out.print("Ingrese el nombre de usuario: ");
                    String nuevoUsername = reader.readLine();
                    System.out.print("Ingrese la contraseña: ");
                    String newPassword = reader.readLine();

                    adminSede.addEmpleado(nuevoUsername, newPassword, Roles.EMPLEADO);
                    System.out.println("Empleado agregado con éxito.");

                }
                if (op == 2) {
                    System.out.println("Eliminar un empleado:");
                    System.out.print("Ingrese el nombre de usuario del empleado a eliminar: ");
                    String usernameAEliminar = reader.readLine();

                    adminSede.deleteEmpleado(usernameAEliminar);
                    System.out.println("Empleado eliminado con éxito.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (op != 0);
    }

    private void MenuEmpleado(Empleado empleado) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;

        do {
            try {
                System.out.println("Menú de Empleado:");
                System.out.println("1. Mandar vehículo a mantenimiento");
                System.out.println("2. Crear un cliente");
                System.out.println("3. Iniciar una reserva");
                System.out.println("4. Cerrar y guardar reserva");
                System.out.println("5. Generar reporte de vehículos en mantenimiento");
                System.out.println("6. Generar entrega");
                System.out.println("7. Añadir Conductor adicional");
                System.out.println("8. Salir");
                System.out.print("Elija una opción: ");
                opcion = Integer.parseInt(reader.readLine());

                if (opcion == 1) {
                    System.out.println("Mandar vehículo a mantenimiento:");
                    System.out.println("Ingrese la placa del vehículo");
                    String placaVehiculo = reader.readLine();
                    empleado.mandarMantenimiento(placaVehiculo);
                } else if (opcion == 2) {
                    System.out.println("Crear un cliente:");
                    System.out.print("Ingrese el nombre de usuario: ");
                    String username = reader.readLine();

                    System.out.print("Ingrese la contraseña: ");
                    String password = reader.readLine();

                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombreCliente = reader.readLine();

                    System.out.print("Ingrese el email: ");
                    String email = reader.readLine();

                    System.out.print("Ingrese el teléfono: ");
                    String telefono = reader.readLine();

                    System.out.print("Ingrese la fecha de nacimiento: ");
                    String fechaNacimiento = reader.readLine();

                    System.out.print("Ingrese la nacionalidad: ");
                    String nacionalidad = reader.readLine();

                    System.out.print("Ingrese la imagen de la cédula: ");
                    String imagenCedula = reader.readLine();

                    System.out.print("Ingrese la imagen de la licencia: ");
                    String imagenLicencia = reader.readLine();

                  
                  

                    System.out.print("Ingrese la fecha de vencimiento de la tarjeta: ");
                    String fechaVencimiento = reader.readLine();
                    
                    
                    empresa.crearCliente(username,password,Roles.CLIENTE,nombreCliente,email,telefono,fechaNacimiento,nacionalidad,imagenCedula,imagenLicencia,"","",fechaVencimiento);
                    
                } else if (opcion == 3) {
                    System.out.println("Iniciar una reserva:");
                   
                    System.out.print("Ingrese el código de sede: ");
                    String codigoSede = reader.readLine();

                    System.out.print("Ingrese el tipo de carro: ");
                    String tipoDeCarro = reader.readLine();

                    System.out.print("Ingrese la sede de recogida: ");
                    String sedeRecogida = reader.readLine();

                    System.out.print("Ingrese la sede de entrega: ");
                    String sedeEntrega = reader.readLine();

                    

                    System.out.print("Ingrese la fecha y hora de recogida en el formato Año/Mes/Día/Hora/Min/Seg: ");
                    String fechaHoraRecogida = reader.readLine();

                    System.out.print("Ingrese la fecha y hora de entrega en el formato Año/Mes/Día/Hora/Min/Seg: ");
                    String fechaHoraEntrega = reader.readLine();

                    System.out.print("Ingrese la placa del vehículo: ");
                    String placaVehiculo = reader.readLine();

                    System.out.print("Ingrese el nombre de usuario del cliente: ");
                    String usernameCliente = reader.readLine();

                  

                    

                  
                    
                
                    
                    
                    empleado.iniciarReserva(codigoSede,"", tipoDeCarro, sedeRecogida, sedeEntrega,"",
    						fechaHoraRecogida, fechaHoraEntrega, placaVehiculo, usernameCliente,
    						0,0,0,"");
                } else if (opcion == 4) {
                    System.out.println("Cerrar y guardar reserva:");
                    empleado.cerrarGuardarReserva();
                } else if (opcion == 5) {
                    System.out.println("Generar reporte de vehículos en mantenimiento:");
                    empleado.reporteCarrosMantenimiento();
                } else if (opcion == 6) {
                    System.out.println("Generar entrega:");
                    System.out.print("Ingrese el código de reserva: ");
                    String codigoReserva = reader.readLine();
                    empleado.generarEntrega(codigoReserva);
                } else if (opcion == 7) {
                    System.out.println("Añadir Conductor adicional:");
                    System.out.print("Ingrese la ruta de la imagen: ");
                    String rutaImagen = reader.readLine();
                    System.out.print("Ingrese el código de la sede donde se realizó la reserva: ");
                    String codigoSede2 = reader.readLine();
                    System.out.print("Ingrese el código de reserva: ");
                    String codigoReserva = reader.readLine();
                    empleado.addConductorAdicional(rutaImagen, codigoSede2, codigoReserva);
                } else if (opcion == 8) {
                    System.out.println("Saliendo del menú de Empleado...");
                } else {
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (opcion != 8);
    }

    private void MenuCliente(Cliente cliente) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;

        do {
            try {
                System.out.println("Menú de Cliente:");
                System.out.println("1. Iniciar Reserva");
                System.out.println("2. Cerrar y Guardar Reserva");
                System.out.println("3. Ver Método de Pago");
                System.out.println("4. Salir");
                System.out.print("Elija una opción: ");
                opcion = Integer.parseInt(reader.readLine());

                if (opcion == 1) {
                    System.out.println("Iniciar Reserva:");
                    System.out.println("Iniciar una reserva:");
                    System.out.println("Iniciar una reserva:");
                    
                    System.out.print("Ingrese el código de sede: ");
                    String codigoSede = reader.readLine();

                    System.out.print("Ingrese el tipo de carro: ");
                    String tipoDeCarro = reader.readLine();

                    System.out.print("Ingrese la sede de recogida: ");
                    String sedeRecogida = reader.readLine();

                    System.out.print("Ingrese la sede de entrega: ");
                    String sedeEntrega = reader.readLine();

                   

                    System.out.print("Ingrese la fecha y hora de recogida en el formato Año/Mes/Día/Hora/Min/Seg: ");
                    String fechaHoraRecogida = reader.readLine();

                    System.out.print("Ingrese la fecha y hora de entrega en el formato Año/Mes/Día/Hora/Min/Seg: ");
                    String fechaHoraEntrega = reader.readLine();

                    System.out.print("Ingrese la placa del vehículo: ");
                    String placaVehiculo = reader.readLine();

                    System.out.print("Ingrese el nombre de usuario del cliente: ");
                    String usernameCliente = reader.readLine();

                  

                    

                  
                    cliente.iniciarReserva(codigoSede,"", tipoDeCarro, sedeRecogida, sedeEntrega,"",
    						fechaHoraRecogida, fechaHoraEntrega, placaVehiculo, usernameCliente,
    						"", 0,0,0,"");
                } else if (opcion == 2) {
                    System.out.println("Cerrar y Guardar Reserva:");
                    cliente.getCerrarSesion();
                } else if (opcion == 3) {
                    System.out.println("Método de Pago:");
                    cliente.getMetodoDePago();
                } else if (opcion == 4) {
                    System.out.println("Saliendo del menú de Cliente...");
                } else {
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        new Main();
    }
}

