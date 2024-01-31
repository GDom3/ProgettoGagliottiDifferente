package UniDy.UninaDelivery;

import java.sql.SQLException;

public class ConnessionNonRiuscitaException extends UninaDeliverySQLException {

	private String messaggioErrore = "Connessione al database non riuscita";
	private String tipoErrore = "Errore";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
	
	protected void aggiungiDettagli(String messaggioErrore) {
		this.messaggioErrore.concat("\n Dettagli : ").concat(messaggioErrore);
	}
}
