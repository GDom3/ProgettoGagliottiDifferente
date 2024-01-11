package UniDy.UninaDelivery;

import java.sql.SQLException;

public class ConnessionNonRiuscitaException extends SQLException {


	private String messaggioErrore = "Connessione al database non riuscita";
	private String tipoErrore = "Errore";
	
	public ConnessionNonRiuscitaException() {

		
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
