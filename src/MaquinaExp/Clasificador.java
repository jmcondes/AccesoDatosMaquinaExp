package MaquinaExp;

import java.util.ArrayList;
/**
 * Clase que contiene los clasificadores, en esta clase se realiza el proceso de selecci�n
 * de productos, as� como la devoluci�n del cambio de monedas, y la inserci�n de monedas en
 * la m�quina.
 * @author Jose Manuel Condes Moreno
 *
 */
public class Clasificador {

	private int saldo;
	private Deposito depositos[];
	private ArrayList<Dispensador> dispensadores;
	Visor v = null;
	Main main = null;
	ArrayList<Deposito> depRetorno;
	int[] cambioTemp;
	
	public Clasificador(Deposito[] depositos, ArrayList<Dispensador> dispensadores){
		this.saldo = 0;
		this.depositos = depositos;
		this.dispensadores = dispensadores;
		depRetorno = new ArrayList<Deposito>();
		cambioTemp = new int[6];
	}
	
	public void setVisor(Visor visor){
		this.v = visor;
	}
	
	public void setMain(Main main){
		this.main = main;
	}
	/**
	 * Devuelve el array de dep�sitos.
	 * @return
	 */
	public Deposito[] getDepositos(){
		return this.depositos;
	}
	/**
	 * M�todo que devuelve el saldo actual.
	 * @return
	 */
	public int getSaldo(){
		return this.saldo;
	}
	/**
	 * M�todo para modificar el saldo.
	 * @param saldo
	 */
	public void setSaldo(int saldo){
		this.saldo += saldo;
	}
	/**
	 * Devuelve el ArryList de dispensadores.
	 * @return
	 */
	public ArrayList<Dispensador> getDispensadores(){
		return this.dispensadores;
	}
	/**
	 * A�ade objetos dep�sitos cuando el usuario inserta las monedas.
	 * @param cl
	 * @param valorMoneda
	 */
	public void insertarMoneda(Clasificador cl, String valorMoneda){
		String entrada = valorMoneda;

		int entradaNum = Integer.parseInt(entrada);
		cl.setSaldo(entradaNum);
		for (int i = 0; i < depositos.length; i++) {
			if(depositos[i].getValor()==entradaNum){
				depositos[i].anadir();
			}
		}
		v.mostrarSaldo(cl);
		if(!entrada.equals("0")){
			depRetorno.add(new Deposito(Integer.parseInt(entrada),1));
		}

	}
	/**
	 * Resta las monedas de los depositos al retornar el dinero.
	 */
	public void retornarMoneda(){
		String mensajeDevolucion = "";
		if(depRetorno.size() > 0){
			mensajeDevolucion += "Se han devuelto:\n";
			for (int i = 0; i < depRetorno.size(); i++) {
				for (int j = 0; j < depositos.length; j++) {
					if(depositos[j].getValor()==depRetorno.get(i).getValor()){
						mensajeDevolucion += "\n1 moneda de " + depositos[j].getValor();
						depositos[j].restar();
					}
				}
			}
			this.saldo = 0;
			mensajeDevolucion += "\n";
			v.mostrarInformacion(mensajeDevolucion);
			depRetorno.clear();	
		}
		else{
			v.mostrarInformacion("Error al retornar dinero, no dispone de saldo.\n");
		}	
	}
	/**
	 * En el m�todo se comprueban los casos del enunciado de la pr�ctica para el saldo.
	 * @param d
	 * @return
	 */
	public boolean seleccionarProducto(Dispensador d){
		boolean flagTerminar = false;
		int dineroDevolucion = this.saldo - d.getPrecio();
		if(this.saldo == 0){
			v.mostrarPrecio(d);			
		}
		else if(dineroDevolucion < 0){
			v.mostrarInformacion("El saldo es insuficiente. El precio del producto es de " + d.getPrecio() + " c�ntimos de �.");
		}
		else{
			if(calculaCambio(dineroDevolucion) > 0){
				v.mostrarInformacion("No hay cambio disponible. Puede seleccionar otro producto.");
			}
			else{
				String vueltas ="";
				if(dineroDevolucion != 0){
					vueltas += "Se han devuelto: ";
					for (int i = 0; i < cambioTemp.length; i++) {
						if(cambioTemp[i] != 0){
							vueltas += "\n" + cambioTemp[i] + " monedas de " + depositos[i].getValor() + " c�ntimos";
						}
						depositos[i].setCantidad(-cambioTemp[i]);
						cambioTemp[i] = 0;
					}
				}
				vueltas += "\nDisfrute de su " + d.getNombre() + ".";
				vueltas += "\n��Gracias por su compra, hasta la vista!!!";
				v.mostrarInformacion(vueltas);
				d.dispensar(d, v);
				this.saldo = 0;
				flagTerminar = true;
				//Se reinicia el ArrayList temporal que almacena el dinero introducido.
				depRetorno.clear();
			}
		}
		return flagTerminar;
	}
	/**
	 * M�todo que calcula el cambio.
	 * @param cambio
	 * @return
	 */
	public int calculaCambio(int cambio){
		v.mostrarInformacion("El cambio a devolver es de " + cambio + " c�ntimos de euro.");
		for (int i = depositos.length - 1; i >= 0 && cambio > 0; i--) {
			if(cambio >= depositos[i].getValor() && depositos[i].getCantidad()>0){
				//Se comprueba que no se quitan m�s monedas de las que hay en los dep�sitos.
				if(cambio <= (depositos[i].getCantidad()*depositos[i].getValor())){
					//Se van a�adiendo el n�mero de monedas resultantes a un array temporal de int.
					cambioTemp[i]= cambio/(depositos[i].getValor());
					cambio = cambio%(depositos[i].getValor());
				}
				else{
					cambio = cambio - (depositos[i].getCantidad()*depositos[i].getValor());
					cambioTemp[i] = depositos[i].getCantidad();
				}
			}
		}
		return cambio;
	}
	
}
