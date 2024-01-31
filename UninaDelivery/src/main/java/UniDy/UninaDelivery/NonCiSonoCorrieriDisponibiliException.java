package UniDy.UninaDelivery;

public class NonCiSonoCorrieriDisponibiliException extends UninaDeliveryException {

	private String messaggioErrore = "Non ci sono corrieri disponibili, ovvero tutti i corriere stanno consegnado già in una spedizione";
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
	
	
}
