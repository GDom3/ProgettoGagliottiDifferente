package UniDy.UninaDelivery;

public class CampoUsernameVuotoException extends Exception {

	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoUsernameVuotoException() {
		messaggioErrore = "Campo USERNAME vuoto, insetire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
