package MaquinaExp;

public class TeclaSeleccion extends Tecla{
		
	public TeclaSeleccion(int idTecla, Clasificador clasificador){
		this.idTecla = idTecla;
		this.clasificador = clasificador;
	}

	public boolean pulsar(Tecla t, Dispensador d){
		System.out.println("Ha seleccionado " + d.getNombre() + ".");
		boolean flagSelec = t.getClasificador().seleccionarProducto(d);
		return flagSelec;
	}
}
