package MaquinaExp;

public abstract class Tecla {
	
	protected int idTecla;
	protected Clasificador clasificador;
	
	public int getIdTecla(){
		return this.idTecla;
	}
	
	public Clasificador getClasificador(){
		return this.clasificador;
	}
	
}
