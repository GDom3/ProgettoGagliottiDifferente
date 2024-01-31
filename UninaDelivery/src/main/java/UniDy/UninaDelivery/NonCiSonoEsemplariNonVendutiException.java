package UniDy.UninaDelivery;

public class NonCiSonoEsemplariNonVendutiException extends UninaDeliveryException {
	private String messaggioErrore = "Non ci sono esemplari disponibili, ovvero tutti gli esemplari sono stati venduti";;
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
