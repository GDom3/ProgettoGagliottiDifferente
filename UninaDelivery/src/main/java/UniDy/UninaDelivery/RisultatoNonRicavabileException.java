package UniDy.UninaDelivery;

import java.sql.SQLException;

public class RisultatoNonRicavabileException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public RisultatoNonRicavabileException() {
		messaggioErrore = "Errore durante la query, precisamente durante la richiesta di eseguire una query";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}

