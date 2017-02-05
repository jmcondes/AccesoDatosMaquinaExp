package MaquinaExp;

import java.util.ArrayList;
/**
 * Esta clase llama a las dos ventanas de interfaces para mostrar los mensajes.
 * @author Jose Manuel Condes Moreno.
 *
 */
public class Visor {
	
	private Interfaz interfaz;
	private InterfazAdmin itfAdm;
	
	public void setInterfaz(Interfaz interfaz){
		this.interfaz = interfaz;
	}
	
	public void setInterfazAdmin(InterfazAdmin itfAdm){
		this.itfAdm = itfAdm;
	}
	
	public void mostrarBienvenida(Clasificador cl){
		interfaz.setTextoVisor("¡¡¡Bienvenido¡¡¡\n\nIntroduzca dinero y seleccione un producto.");	
	}
	
	public void mostrarSaldo(Clasificador cl){
		interfaz.setTextoVisor("Su saldo actual es de " + ((double)cl.getSaldo())/100 + " €.");
	}
	
	public void mostrarPrecio(Dispensador d){
		interfaz.setTextoVisor("Su saldo actual es 0,00 Euros. El precio del producto es de " + ((double)d.getPrecio())/100 + " €.");
	}
	
	public void mostrarInformacion(String mensaje){
		interfaz.setTextoVisor(mensaje);
	}
	
	public void mostrarInformacionA(String mensaje){
		itfAdm.setTextoVisorAdmin(mensaje);
	}

	public void mostrarInformacion(Clasificador cl, String ventana){
		String textoPantalla = "Información de los depósitos de monedas:\n";
		for (int i = 0; i < cl.getDepositos().length; i++) {			
			textoPantalla += "\nHay "+ cl.getDepositos()[i].getCantidad() + " monedas de " + cl.getDepositos()[i].getValor();
		}
		textoPantalla += "\n\nInformación de los dispensadores de productos:\n";
		ArrayList<Dispensador> di = cl.getDispensadores();
		for (Dispensador d : di) {
			textoPantalla += "\nID: " + d.getId() + ", Nombre: " +d.getNombre() + ", Precio: " + d.getPrecio() + ", Cantidad: " + d.getCantidad();
		}
		textoPantalla += "\n\nEl saldo actual es " + cl.getSaldo() + ".\n";
		if(ventana.equals("VentanaPrincipal")){
			interfaz.setTextoVisor(textoPantalla);
		}
		else if(ventana.equals("VentanaAdmin")){
			itfAdm.setTextoVisorAdmin(textoPantalla);
		}
	}	
}
