package UniDy.UninaDelivery;

import java.sql.SQLException;

public class CampoInserimentoVuotoException extends SQLException {

	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoInserimentoVuotoException(String tipoCampo) {
		messaggioErrore = "Campo " + tipoCampo + " vuoto, insetire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
