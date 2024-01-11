package UniDy.UninaDelivery;

public class NonCiSonoMezziTrasportoDisponibiliException extends Exception {
	private String messaggioErrore= "Non ci sono mezzi di trasporto disponibili, ovvero tutti i mezzi sono adoperati in una spedizione";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoMezziTrasportoDisponibiliException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
