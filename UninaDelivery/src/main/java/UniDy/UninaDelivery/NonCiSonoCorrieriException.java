package UniDy.UninaDelivery;

public class NonCiSonoCorrieriException extends UninaDeliveryException {
	private String messaggioErrore = "Non ci sono CORRIERI da selezionare";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
