package UniDy.UninaDelivery;

import java.sql.SQLException;

public class NonPossibileCreareSpedizioneException extends SQLException {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public NonPossibileCreareSpedizioneException() {
		messaggioErrore = "Non è possibile creare una spedizione con i valori inseriti, incoerenza tra parti";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
