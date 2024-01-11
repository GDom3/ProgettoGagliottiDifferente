package UniDy.UninaDelivery;

import java.sql.SQLException;

public class RisultatoNonRicavabileException extends SQLException {
	private String messaggioErrore= "Errore durante la query, precisamente quando provo ad estrarre il risultato una query";;
	private String tipoErrore = "Errore";
	
	public RisultatoNonRicavabileException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}

