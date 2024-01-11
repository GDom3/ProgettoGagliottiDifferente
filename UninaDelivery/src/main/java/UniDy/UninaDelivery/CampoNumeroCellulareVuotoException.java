package UniDy.UninaDelivery;

public class CampoNumeroCellulareVuotoException extends Exception {
	private String messaggioErrore  = "Campo NUMERO CELLULARE vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoNumeroCellulareVuotoException() {
	
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
