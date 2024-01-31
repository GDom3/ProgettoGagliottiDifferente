package UniDy.UninaDelivery;

public class NonCiSonoMerciDisponibiliException extends UninaDeliveryException {
	private String messaggioErrore= "Non ci sono Merci disponibili, ovvero nessuna merce è presente";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
