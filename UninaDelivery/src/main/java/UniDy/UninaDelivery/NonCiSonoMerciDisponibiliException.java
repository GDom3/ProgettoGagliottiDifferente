package UniDy.UninaDelivery;

public class NonCiSonoMerciDisponibiliException extends Exception {
	private String messaggioErrore= "Non ci sono Merci disponibili, ovvero nessuna merce è presente";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoMerciDisponibiliException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
