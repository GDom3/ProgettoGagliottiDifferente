package UniDy.UninaDelivery;

public class CampoColoreVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo COLORE vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
