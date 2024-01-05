package UniDy.UninaDelivery;

public class NonCiSonoMezziTrasportoDisponibiliException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoMezziTrasportoDisponibiliException() {
		messaggioErrore = "Non ci sono mezzi di trasporto disponibili, ovvero tutti i mezzi sono adoperati in una spedizione";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
