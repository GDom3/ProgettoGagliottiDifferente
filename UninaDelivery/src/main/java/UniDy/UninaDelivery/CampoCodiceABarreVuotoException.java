package UniDy.UninaDelivery;

public class CampoCodiceABarreVuotoException extends Exception {
	private String messaggioErrore = "Campo CODICE A BARRE vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoCodiceABarreVuotoException() {
	
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
