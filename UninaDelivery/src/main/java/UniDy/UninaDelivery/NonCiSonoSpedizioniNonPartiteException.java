package UniDy.UninaDelivery;

public class NonCiSonoSpedizioniNonPartiteException extends UninaDeliverySQLException {
	private String messaggioErrore= "Impossibile procedere all'aggiunta, in quanto tutte le spedizioni sono partite o concluse";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
