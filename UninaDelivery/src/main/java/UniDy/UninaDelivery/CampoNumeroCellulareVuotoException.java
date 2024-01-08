package UniDy.UninaDelivery;

public class CampoNumeroCellulareVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoNumeroCellulareVuotoException() {
		messaggioErrore = "Campo NUMERO CELLULARE vuoto, inserire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
