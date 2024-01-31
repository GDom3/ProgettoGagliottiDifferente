package UniDy.UninaDelivery;

public class FiltriVuotiException extends UninaDeliveryException {
	
	private String messaggioErrore = "Devi inserire l'utente o le date su cui filtrare ";
	private String tipoErrore = "Utente e date vuote";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
