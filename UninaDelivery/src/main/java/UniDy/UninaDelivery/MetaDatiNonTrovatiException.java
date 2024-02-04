package UniDy.UninaDelivery;

public class MetaDatiNonTrovatiException extends UninaDeliverySQLException {
	private String messaggioErrore = "Metadati non trovati, ovvero non è stato possibile ricavare come sia composto il risultato della query";
	private String tipoErrore = "Errore";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}

