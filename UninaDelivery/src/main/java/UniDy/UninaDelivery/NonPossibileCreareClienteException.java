package UniDy.UninaDelivery;

public class NonPossibileCreareClienteException extends UninaDeliveryException {
	private String messaggioErrore = "Non è possibile creare un cliente con i valori inseriti, incoerenza tra parti.\nDettagli: \n  - Controlla la correttezza del codice fiscale (16 cifre) \n  - Controlla che il numero di telefono contenga solo numeri\n  - Oppure che non esisti già";
	private String tipoErrore = "Errore";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
