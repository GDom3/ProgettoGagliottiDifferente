package UniDy.UninaDelivery;

public class FiltriVuotiException extends Exception {
	
	private String messaggioErrore = "Devi inserire l'utente o le date su cui filtrare ";
	private String nomeErrore = "Utente e date vuote";
	
	public FiltriVuotiException() {

		
	
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
