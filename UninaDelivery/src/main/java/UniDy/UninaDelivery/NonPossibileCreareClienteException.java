package UniDy.UninaDelivery;

public class NonPossibileCreareClienteException extends Exception {
	private String messaggioErrore = "Non è possibile creare un cliente con i valori inseriti, incoerenza tra parti.\n Dettagli: \n  - Controlla la correttezza del codice fiscale (16 cifre) \n - controlla che il numero di telefono contenga solo numeri";
	private String tipoErrore = "Errore";
	
	public NonPossibileCreareClienteException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
