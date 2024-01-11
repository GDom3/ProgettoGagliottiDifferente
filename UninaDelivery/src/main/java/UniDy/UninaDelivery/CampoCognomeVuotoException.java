package UniDy.UninaDelivery;

public class CampoCognomeVuotoException extends Exception {
	private String messaggioErrore = "Campo COGNOME vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoCognomeVuotoException() {

	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
