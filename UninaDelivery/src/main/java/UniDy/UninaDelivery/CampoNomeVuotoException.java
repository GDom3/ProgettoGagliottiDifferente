package UniDy.UninaDelivery;

public class CampoNomeVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo NOME vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
