package UniDy.UninaDelivery;

public class NonCiSonoMerciDisponibiliException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoMerciDisponibiliException() {
		messaggioErrore = "Non ci sono Merci disponibili, ovvero nessuna merce è presente";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
