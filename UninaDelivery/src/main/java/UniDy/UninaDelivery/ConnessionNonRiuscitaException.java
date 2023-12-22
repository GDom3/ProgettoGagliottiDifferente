package UniDy.UninaDelivery;

import java.sql.SQLException;

public class ConnessionNonRiuscitaException extends SQLException {


	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public ConnessionNonRiuscitaException() {
		messaggioErrore = "Connessione al database non riuscita";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
	
	protected void aggiungiDettagli(String messaggioErrore) {
		this.messaggioErrore.concat("\n Dettagli").concat(messaggioErrore);
	}
}
