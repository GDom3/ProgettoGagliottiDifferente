package UniDy.UninaDelivery;

public class CampoPatentiVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoPatentiVuotoException() {
		messaggioErrore = "Campo PATENTI vuoto, inserire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
