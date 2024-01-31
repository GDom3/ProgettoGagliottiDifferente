package UniDy.UninaDelivery;

public class CampoModelloVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo MODELLO vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
