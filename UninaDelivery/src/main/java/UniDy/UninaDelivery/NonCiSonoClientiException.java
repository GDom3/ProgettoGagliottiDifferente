package UniDy.UninaDelivery;

public class NonCiSonoClientiException extends UninaDeliveryException {
	private String messaggioErrore = "Non ci sono clienti esistenti";;
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
