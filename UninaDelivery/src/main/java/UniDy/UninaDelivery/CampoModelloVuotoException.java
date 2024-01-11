package UniDy.UninaDelivery;

public class CampoModelloVuotoException extends Exception {
	private String messaggioErrore = "Campo MODELLO vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoModelloVuotoException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
