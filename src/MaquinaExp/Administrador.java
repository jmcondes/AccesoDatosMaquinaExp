package MaquinaExp;
/**
 * Esta clase gestiona el proceso de aministración de la máquina. Se comunica con la interfaz
 * de administrador y con el main, además de llamar al visor para mostrar mensajes en las ventanas.
 * También llama a los métodos de la clase conexión para actualizar las tablas.
 * @author Jose Manuel Condes Moreno.
 *
 */
public class Administrador {
	
	Main main = null;
	Clasificador clasificador = null;
	ConexionBBDD conexion= null;
	Visor visor = null;
	InterfazLogin itfLog = null;
	InterfazAdmin itfAdm = null;
	
	public void setMain(Main main){
		this.main = main;
	}
	
	public void setConexionBBDD(ConexionBBDD conexion){
		this.conexion = conexion;
	}
	
	public void setVisor(Visor visor){
		this.visor = visor;
	}
	
	public void setClasificador(Clasificador clasificador){
		this.clasificador = clasificador;
	}
	
	public void setInterfazLogin(InterfazLogin itfLog){
		this.itfLog = itfLog;
	}
	
	public void setInterfazAdmin(InterfazAdmin itfAdm){
		this.itfAdm = itfAdm;
	}
	
	public void iniciarAdmin(String usuario, String password){
		if(conexion.comprobarAdmin(usuario, password)){
			itfLog.cerrarVentana();
			main.cerrarInterfaz();
			main.iniciarInterfazAdmin();
		}
		else{
			itfLog.ventanaEmergente("Usuario o contraseña incorrectos.");
		}		
	}
	
	public void addMoneda(int valor){
		int cantidadMoneda = itfAdm.getCantidadRepMoneda();
		conexion.addMonedas(valor, cantidadMoneda);
	}
	
	public void addProducto(int id, String nomProducto){
		int idProducto = id;
		int cantidadProducto = itfAdm.getCantidadRepProducto();
		conexion.reponerProducto(idProducto, cantidadProducto, nomProducto);
	}
	
	public void addProductoNuevo(){
		String nomProducto = itfAdm.getNombreProducto();
		int precioProducto = itfAdm.getPrecioProducto();
		int cantidadInicial = itfAdm.getCantidadProducto();
		conexion.addProducto(nomProducto, precioProducto, cantidadInicial);		
	}
}
