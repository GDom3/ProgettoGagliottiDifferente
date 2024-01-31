package UniDy.UninaDelivery;

public class UsernameNonEsistenteException extends UninaDeliveryException {
	private String messaggioErrore = "Username o email inseriti non registrate";
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
