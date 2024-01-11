package UniDy.UninaDelivery;

public class CampoPasswordVuotoException extends Exception {
	private String messaggioErrore = "Campo PASSWORD vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoPasswordVuotoException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
