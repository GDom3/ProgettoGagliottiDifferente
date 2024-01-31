package UniDy.UninaDelivery;

public class CampoNumeroCellulareVuotoException extends UninaDeliveryException {
	private String messaggioErrore  = "Campo NUMERO CELLULARE vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
