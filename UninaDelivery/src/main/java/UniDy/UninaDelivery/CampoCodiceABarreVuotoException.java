package UniDy.UninaDelivery;

public class CampoCodiceABarreVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoCodiceABarreVuotoException() {
		messaggioErrore = "Campo CODICE A BARRE vuoto, inserire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
