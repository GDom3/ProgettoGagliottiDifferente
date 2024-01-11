package UniDy.UninaDelivery;

public class CampoUsernameVuotoException extends Exception {

	private String messaggioErrore = "Campo USERNAME vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoUsernameVuotoException() {

	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
