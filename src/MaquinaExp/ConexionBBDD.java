package MaquinaExp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
/**
 * Clase que gestiona la conexi�n a MySQL adem�s de todos los m�todos necesarios
 * para leer de la BBDD y para actualizar las tablas.
 * @author Jose Manuel Condes Moreno
 *
 */
public class ConexionBBDD {
	
	private Visor visor;
	private final String rutaFile = "Config.ini";
	private String userMySQL;
	private String passwordMySQL;
	private String bd;
	private String url;
	private Connection conexion;
	private int contador = 3;
	
	public ConexionBBDD() {
		crearConexion();
	}
	
	public void setVisor(Visor visor){
		this.visor = visor;
	}
	/**
	 * Crea la conexi�n, al tercer intento fallido de conectar cierra el programa.
	 */
	public void crearConexion(){
		boolean conexionCreada = false;
		do{		
			leerConfig();
			try {			
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection((url + bd), userMySQL, passwordMySQL);
				System.out.println("     - Conexi�n con MySQL establecida -\n");
				conexionCreada = true;				
			} catch (Exception e) {
				if(contador == 0){
					System.out.println("El sistema ha detectado un intento de intrusi�n y se cerrar�.");
					System.exit(-1);				
				}
				System.out.println(" � Error de Conexi�n con MySQL - \n");
				System.out.println(" � Tiene " + this.contador + " intentos para introducir los datos. - \n");
				escribirConfig();
				leerConfig();
			}
		}while(!conexionCreada);
	}
	/**
	 * Lee el fichero de configuraci�n con getProperty()
	 */
	public void leerConfig(){
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try{
			File miFichero = new File(rutaFile);
			if(miFichero.exists()){
				entrada = new FileInputStream(miFichero);
				propiedades.load(entrada);
				this.url = propiedades.getProperty("BBDD_URL");
				this.userMySQL = propiedades.getProperty("BBDD_USER");
				this.passwordMySQL = propiedades.getProperty("BBDD_PASSWORD");
				this.bd = propiedades.getProperty("BBDD_BD");
			}
			else{
				System.out.println(" - No se ha encontrado el fichero ini, "
						+ "introduzca los par�metros para crearlo.");
				escribirConfig();
				leerConfig();
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Escribe el fichero de configuraci�n si no exixtiera, o bien si los par�metros de la conexi�n
	 * son err�neos.
	 */
	public void escribirConfig(){
		Properties propiedades = new Properties();
		OutputStream salida = null;
		PrintWriter pw = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			System.out.println(" - Introduzca el nombre de usuario: ");
			String usuario = br.readLine();
			System.out.println(" - Introduzca el password: ");
			String password = br.readLine();
			System.out.println(" - Introduzca el nombre de la BBDD: ");
			String bbdd = br.readLine();
			File miFichero = new File(rutaFile);
			pw = new PrintWriter(miFichero);
			pw.println("###########################");
			if(miFichero.exists()){
				salida = new FileOutputStream(miFichero);
				propiedades.setProperty("BBDD_URL","jdbc:mysql://localhost/");
				propiedades.setProperty("BBDD_USER",usuario);
				propiedades.setProperty("BBDD_PASSWORD",password);
				propiedades.setProperty("BBDD_BD",bbdd);
				propiedades.store(salida,"##### DATABASE CONFIG #####");
				this.contador--;
			}
			else{
				System.out.println("Fichero no encontrado");
			}						
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Lee de la tabla depositos de la BBDD y devuelve un ArrayList con las filas.
	 * @return
	 */
	public ArrayList<String> leerDepositos(){
		
		ArrayList<String> dep = new ArrayList<String>();
		String query = "SELECT CONCAT(valor,'/',cantidad) AS datosMonedas FROM maquinaexp.depositos";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()){
				dep.add(rset.getString("datosMonedas"));
			}
			rset.close();
			pstmt.close();
		} catch (SQLException s) {
			System.out.println("Ha habido un error al leer de la BBDD.");
			System.exit(-1);
		}		
		return dep;
	}
	/**
	 * Lee de la tabla dispensadores y devuelve un ArrayList de String con cada una de las filas
	 * extraidas.
	 * @return
	 */
	public ArrayList<String> leerDispensadores(){
		
		ArrayList<String> disp = new ArrayList<String>();
		String query = "SELECT CONCAT(id_producto,'/',producto,'/',precio,'/',cantidad) AS datosProductos"
						+ " FROM maquinaexp.dispensadores";
		try {
			PreparedStatement pstmt = conexion.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()){
				disp.add(rset.getString("datosProductos"));
			}
			rset.close();
			pstmt.close();
		} catch (SQLException s) {
			System.out.println("Ha habido un error al leer de la BBDD.");
		}		
		return disp;
	}
	/**
	 * M�todo para actualizar la tabla dep�sitos.
	 * @param dep
	 */
	public void actualizarDepositos(Deposito[] dep){
		String query = "UPDATE maquinaexp.depositos SET cantidad = ? WHERE valor = ?";
		PreparedStatement pstmt = null;
		int regActualizados = 0;
		try {
			for (int i = 0; i < dep.length; i++) {			
				pstmt = conexion.prepareStatement(query);
				pstmt.setInt(1, dep[i].getCantidad());
				pstmt.setInt(2, dep[i].getValor());
				regActualizados= pstmt.executeUpdate();
			}
			if (regActualizados > 0) {
				System.out.println("La tabla dep�sitos se ha actualizado");
			} else {
				System.out.println("Ha habido un error al actualizar la tabla dep�sitos.");
			}
			pstmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}		
	}
	/**
	 * M�todo que actualiza la tabla de productos de la BBDD.
	 * @param idProducto
	 * @param cantidad
	 */
	public void actualizarDispensadores(int idProducto, int cantidad){
		String query = "";
		PreparedStatement pstmt = null;
		int regActualizados = 0;
		try{
			query = "UPDATE maquinaexp.dispensadores SET cantidad = ? WHERE id_producto = ?";
			pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, idProducto);
			regActualizados= pstmt.executeUpdate();
			if (regActualizados > 0) {
				System.out.println("La tabla dispensadores se ha actualizado");
			} else {
				System.out.println("Ha habido un error al actualizar la tabla dispensadores.");
			}
			pstmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	/**
	 * M�todo que comprueba el login del administrador.
	 * @param usuario
	 * @param password
	 * @return
	 */
	public boolean comprobarAdmin(String usuario, String password){
		boolean admin = false;
		String query = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			query = "SELECT id_admin FROM maquinaexp.administrador WHERE usuario = ? AND password = ?";
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, usuario);
			pstmt.setString(2, password);
			rset = pstmt.executeQuery();
			if(rset.next()){
				admin = true;
			}
			rset.close();
			pstmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return admin;
	}
	/**
	 * M�todo para actualizar la tabla de monedas de la BBDD al reponer monedas.
	 * @param valor
	 * @param cantidad
	 */
	public void addMonedas(int valor, int cantidad){
		String query = "";
		PreparedStatement pstmt = null;
		int regActualizados = 0;
		try{
			query = "UPDATE maquinaexp.depositos SET cantidad = cantidad + ? WHERE valor = ?";
			pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, valor);
			regActualizados= pstmt.executeUpdate();
			if (regActualizados > 0) {
				visor.mostrarInformacionA("Se han a�adido " + cantidad + " monedas de " + valor + " c�ntimos.");
			} else {
				visor.mostrarInformacionA("Ha habido un error al a�adir las monedas.");
			}
			pstmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	/**
	 * M�todo para actualizar la tabla de productos de la BBDD al reponer un producto.
	 * @param id
	 * @param cantidad
	 * @param nomProducto
	 */
	public void reponerProducto(int id, int cantidad, String nomProducto){
		String query = "";
		PreparedStatement pstmt = null;
		int regActualizados = 0;
		try{
			query = "UPDATE maquinaexp.dispensadores SET cantidad = cantidad + ? WHERE id_producto = ?";
			pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, id);
			regActualizados= pstmt.executeUpdate();
			if (regActualizados > 0) {
				visor.mostrarInformacionA("Se han a�adido " + cantidad + " unidades de " + nomProducto + ".");
			} else {
				visor.mostrarInformacionA("Ha habido un error al a�adir los productos.");
			}
			pstmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	/**
	 * Este m�todo a�ade un producto a la BBDD.
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 */
	public void addProducto(String nombre, int precio, int cantidad){
		String query = "";
		PreparedStatement pstmt = null;
		int regActualizados = 0;
		try{
			query = "INSERT INTO maquinaexp.dispensadores (producto, precio, cantidad) VALUES(?, ?, ?)";
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nombre);
			pstmt.setInt(2, precio);
			pstmt.setInt(3, cantidad);
			regActualizados= pstmt.executeUpdate();
			if (regActualizados > 0) {
				visor.mostrarInformacionA("Se ha a�adido el nuevo producto.");
			} else {
				visor.mostrarInformacionA("Ha habido un error al a�adir el producto nuevo.");
			}
			pstmt.close();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}