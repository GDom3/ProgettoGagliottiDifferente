package UniDy.UninaDelivery;

public class FiltriVuotiException extends UninaDeliveryException {
	
	private String messaggioErrore = "Devi inserire l'utente o le date su cui filtrare, poichè ambedue i campi sono vuoti";
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
