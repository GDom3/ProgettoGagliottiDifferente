package UniDy.UninaDelivery;

public class CampoNumeroCivicoVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Devi inserire il numero civico";
	private String tipoErrore = "Numero Civico vuota";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
