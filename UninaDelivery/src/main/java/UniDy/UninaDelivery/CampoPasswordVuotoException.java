package UniDy.UninaDelivery;

public class CampoPasswordVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoPasswordVuotoException() {
		messaggioErrore = "Campo PASSWORD vuoto, insetire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
