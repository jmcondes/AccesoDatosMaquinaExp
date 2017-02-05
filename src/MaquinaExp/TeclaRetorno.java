package MaquinaExp;

public class TeclaRetorno extends Tecla{
	
	public TeclaRetorno(Clasificador clasificador){
		this.clasificador = clasificador;
	}
	
	public void pulsar(Tecla t){
		t.getClasificador().retornarMoneda();
	}

}
