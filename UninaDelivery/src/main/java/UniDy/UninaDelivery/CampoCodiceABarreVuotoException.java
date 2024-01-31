package UniDy.UninaDelivery;

public class CampoCodiceABarreVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo CODICE A BARRE vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
