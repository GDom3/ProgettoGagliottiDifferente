package UniDy.UninaDelivery;

public class CampoNomeVuotoException extends Exception {
	private String messaggioErrore = "Campo NOME vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoNomeVuotoException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
