package MaquinaExp;

public class Dispensador {

	private int id;
	private String nombre;
	private int precio;
	private int cantidad;
	
	public Dispensador(int id, String nombre, int precio, int cantidad){
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public int getPrecio(){
		return this.precio;
	}
	
	public int getCantidad(){
		return this.cantidad;
	}
	
	public void restarProducto(){
		this.cantidad--;
	}
	
	public void dispensar(Dispensador d, Visor v){		
		restarProducto();
	}
}
