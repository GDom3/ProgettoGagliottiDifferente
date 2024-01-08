package UniDy.UninaDelivery;

public class FiltriVuotiException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public FiltriVuotiException() {

		messaggioErrore = "Devi inserire l'utente o le date su cui filtrare ";
		nomeErrore = "Utente e date vuote";
	
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
