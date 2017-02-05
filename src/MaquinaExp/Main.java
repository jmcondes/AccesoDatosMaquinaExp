package MaquinaExp;

import java.util.ArrayList;
/**
 * He metido en esta clase una serie de m�todos para sustituir a las llamadas a m�todos de otras clases
 * que hac�an los men�s. Adem�s creo el m�todo iniciarTodo, para crear todos los objetos iniciales del
 * programa que antes estaban en el m�todo main. De esta manera cuando cierro la ventana de administraci�n
 * puedo volver a crear la interfaz principal con los botones actualizados.
 * @author Jose Manuel Condes
 *
 */
public class Main {
	
	static Main m = null;
	private Visor v = null;
	private ConexionBBDD conn = null;
	private Interfaz interfaz = null;
	private Administrador adm = null;
	private ArrayList<Tecla> teclas = null;
	private Tecla teclaSeleccion = null;
	private Tecla retorno = null;
	private Clasificador c = null;
	private InterfazAdmin itfAdm = null;
	/**
	 * Crea todos los objetos, leyendo de la BBDD. Adem�s manda objetos a cada clase para comunicarse entre
	 * ellas.
	 */
	public void iniciarTodo(){
		
		v = new Visor();
		conn = new ConexionBBDD();
		conn.setVisor(v);
		adm = new Administrador();
		adm.setMain(m);
		adm.setConexionBBDD(conn);
		adm.setVisor(v);
		teclaSeleccion = new TeclaSeleccion(0,null);
		teclas = new ArrayList<Tecla>();
		Deposito [] depTemp = new Deposito[6];
		ArrayList<Dispensador> dispTemp = new ArrayList<Dispensador>();
		ArrayList<String> lectDepositos = conn.leerDepositos();
		ArrayList<String> lectDispensadores = conn.leerDispensadores();
		//Creamos un ArrayList de objetos deposito con la informaci�n del txt.
		for (int i = 0; i < lectDepositos.size(); i++) {
			String[] datos = lectDepositos.get(i).split("/");
			depTemp[i] = new Deposito(Integer.parseInt(datos[0]),Integer.parseInt(datos[1]));
		}
		//Creamos un ArrayList de objetos dispensador con la informaci�n del fichero.			
		for (int i = 0; i < lectDispensadores.size(); i++) {
			String[] datos = lectDispensadores.get(i).split("/");
			dispTemp.add(new Dispensador(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3])));
		}			
		//Creamos un clasificador a�adi�ndole los depositos y los dispensadores anteriores.
		c = new Clasificador(depTemp, dispTemp);
		interfaz = new Interfaz(c);
		v.setInterfaz(interfaz);
		interfaz.pack();
		interfaz.setLocationRelativeTo(null);
		interfaz.setResizable(false);
		interfaz.setVisible(true);
		interfaz.setMain(m);
		c.setMain(m);
		c.setVisor(v);
		adm.setClasificador(c);
		//Creamos las teclas
		retorno = new TeclaRetorno(c);
		for (int i = 0; i < c.getDispensadores().size(); i++) {
			teclas.add(new TeclaSeleccion(c.getDispensadores().get(i).getId(),c));
		}			
		v.mostrarBienvenida(c);
	}
	/**
	 * Este m�todo comienza la selecci�n del producto, seg�n el enunciado de la pr�ctica �ste se
	 * iniciaba en el main, mediante el objeto tecla seleccionado.
	 * @param teclaProducto
	 */
	public void realizarSeleccion(String teclaProducto){
		boolean agotado = false;
		boolean flagSeleccion = false;
		String entrada = teclaProducto;
		/*
		 * En el for buscamos un producto con una ID igual a la opci�n introducida por el usuario.
		 */
		for (int i = 0; i < c.getDispensadores().size(); i++) {
			//Si se encuentra una coincidencia, se comprueba si hay existencias del producto.
			if(Integer.toString(c.getDispensadores().get(i).getId()).equals(entrada)){
				if(c.getDispensadores().get(i).getCantidad() > 0){
					//Se llama al m�todo pulsar tecla con la tecla que tienen la ID igual a la del producto.
					flagSeleccion = ((TeclaSeleccion)teclaSeleccion).pulsar(teclas.get(i), c.getDispensadores().get(i));
					//Si el m�todo de la tecla devuelve true se actualiza el producto adquirido.
					if(flagSeleccion){						
						conn.actualizarDispensadores((i+1), c.getDispensadores().get(i).getCantidad());
						conn.actualizarDepositos(c.getDepositos());
					}						
				}
				else{
					agotado = true;
				}
			}
		}
		if(agotado){
			v.mostrarInformacion("El producto esta agotado. Puede seleccionar otro producto.");
		}
	}
	/**
	 * LLamada a la clase retorno, mediante el objeto tecla retorno.
	 */
	public void retornarDinero(){
		((TeclaRetorno)retorno).pulsar(retorno);
	}
	/**
	 * M�todo que llama al visor para mostrar informaci�n en pantalla. A este m�todo se llama desde
	 * las diferentes clases pas�ndole el String a imprimir. Adem�s se pasa el objeto clasificador
	 * para extraer la informaci�n en el m�todo de la clase visor.
	 * @param ventana
	 */
	public void mostrarInformacion(String ventana){
		v.mostrarInformacion(c, ventana);
	}
	/**
	 * Igual que el anterior pero para mostrar la informaci�n en el visor de administraci�n.
	 * @param ventana
	 */
	public void mostrarInformacionAdmin(String ventana){
		v.mostrarInformacion(c, ventana);
	}
	/**
	 * Inicializa la interfaz del login.
	 */
	public void loginAdministrador(){
		InterfazLogin itfLogin = new InterfazLogin();
		itfLogin.setAdministrador(adm);
		adm.setInterfazLogin(itfLogin);
	}
	/**
	 * Destruye la interfaz del login.
	 */
	public void cerrarInterfaz(){
		interfaz.dispose();
	}
	/**
	 * Inicia la interfaz del administrador.
	 */
	public void iniciarInterfazAdmin(){
		itfAdm = new InterfazAdmin(c);
		v.setInterfazAdmin(itfAdm);
		adm.setInterfazAdmin(itfAdm);
		itfAdm.setMain(m);
		itfAdm.pack();
		itfAdm.setLocationRelativeTo(null);
		itfAdm.setResizable(false);
		itfAdm.setVisible(true);
	}
	/**
	 * Llama al administrador para reponer las monedas.
	 * @param valor
	 */
	public void reponerMonedas(int valor){
		adm.addMoneda(valor);
	}
	/**
	 * LLama al adminsitrador para reponer los productos.
	 * @param id
	 * @param nomProducto
	 */
	public void reponerProductos(int id, String nomProducto){
		adm.addProducto(id, nomProducto);
	}
	/**
	 * Llama al m�todo del administrador que repone los productos.
	 */
	public void addNuevoProducto(){
		adm.addProductoNuevo();
	}
	/**
	 * Se destruye la interfaz administrador, de esta forma cuando se vuelva a abrir aparecer�n
	 * los botones nuevos.
	 */
	public void cerrarInterfazAdmin(){
		itfAdm.dispose();
	}
	/**
	 * Inicia el programa.
	 * @param args
	 */
	public static void main(String[] args) {
		
		m = new Main();
		m.iniciarTodo();
		
	}
}
