package UniDy.UninaDelivery;

public class CampoEmailVuotoException extends Exception {
	private String messaggioErrore = "Campo E-MAIL vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoEmailVuotoException() {

	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
