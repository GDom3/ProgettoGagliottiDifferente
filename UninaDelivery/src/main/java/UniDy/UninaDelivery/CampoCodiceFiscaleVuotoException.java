package UniDy.UninaDelivery;

public class CampoCodiceFiscaleVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoCodiceFiscaleVuotoException() {
		messaggioErrore = "Campo CODICE FISCALE vuoto, inserire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
