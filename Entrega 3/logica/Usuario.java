package logica;
import java.io.Serializable;
/**
 * <!-- DOCUMENTACION -->
 * <!--  La clase padre, almacenada en un mapa en empresa alquiler.  -->
 * TODO Creo que no necesitamos mas los metodos abastractos, mas bien cambiarlos por metodos fijo de iniciar sesion?
 * TODO Falta lo de inciar sesion, no se si aqui o en empresa alquiler.
 */

public abstract class Usuario implements Serializable
{
	private static final long serialVersionUID = 2L;
	public String username;
	public String password;
	public Roles rol;
	/**
	 * <!-- CONSTRUCTOR -->
	 */

	public Usuario(String username, String password,Roles rol) {
		super();
		this.username = username;
		this.password = password;
		this.rol=rol ;
	}

	/**
	 * <!-- GETTERS SETTERS -->
	 */

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	//No pusimos getters para contrasena ya que no habra metodo cambiarContrasena()
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Roles getRol() {
		return rol;
	}
	
}

