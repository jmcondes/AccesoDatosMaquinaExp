package MaquinaExp;


public class Deposito {

	private int valor;
	private int cantidad;
	
	public Deposito(int valor, int cantidad){
		this.valor = valor;
		this.cantidad = cantidad;
	}
	
	public int getValor(){
		return this.valor;
	}
	
	public void setValor(int valor){
		this.valor = valor;
	}
	
	public int getCantidad(){
		return this.cantidad;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad += cantidad;
	}
	
	public void anadir(){
		this.cantidad++;
	}
	
	public void restar(){
		this.cantidad--;
	}
	
}
