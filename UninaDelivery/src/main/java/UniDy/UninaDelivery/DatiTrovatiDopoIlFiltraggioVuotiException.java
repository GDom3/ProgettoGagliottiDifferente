package UniDy.UninaDelivery;

public class DatiTrovatiDopoIlFiltraggioVuotiException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public DatiTrovatiDopoIlFiltraggioVuotiException() {
		messaggioErrore = "Il Risultato del filtraggio ha dato un esito vuoto, ovvero non esistono spedizioni con queste caratteristiche";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
