package UniDy.UninaDelivery;

public class RisultatoNonRicavabileException extends UninaDeliverySQLException {
	private String messaggioErrore= "Errore durante la query, precisamente quando provo ad estrarre il risultato una query";;
	private String tipoErrore = "Errore";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}

