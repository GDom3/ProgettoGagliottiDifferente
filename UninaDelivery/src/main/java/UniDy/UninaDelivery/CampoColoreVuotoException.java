package UniDy.UninaDelivery;

public class CampoColoreVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoColoreVuotoException() {
		messaggioErrore = "Campo COLORE vuoto, inserire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
