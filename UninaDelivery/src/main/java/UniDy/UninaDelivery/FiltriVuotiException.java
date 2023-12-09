package UniDy.UninaDelivery;

public class FiltriVuotiException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public FiltriVuotiException(FinestraVisualizzaDatiFiltrabili finestra) {

		messaggioErrore = "Devi inserire l'utente o le date su cui filtrare ";
		nomeErrore = "Utente e date vuote";
		finestra.setCliente(null);
		finestra.setDataFine(null);
		finestra.setDataInizio(null);
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
