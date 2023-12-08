package UniDy.UninaDelivery;

import java.sql.SQLException;

public class EstrazioneCampiFallitaException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public EstrazioneCampiFallitaException(String errore) {
		messaggioErrore = errore;
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
