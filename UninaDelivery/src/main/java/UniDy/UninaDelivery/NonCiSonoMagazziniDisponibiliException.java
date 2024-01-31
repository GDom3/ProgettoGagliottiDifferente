package UniDy.UninaDelivery;

public class NonCiSonoMagazziniDisponibiliException extends UninaDeliveryException {
	private String messaggioErrore= "Non ci sono Magazzini disponibili, ovvero nessun magazzino è presente";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}