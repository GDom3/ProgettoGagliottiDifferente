package UniDy.UninaDelivery;

public class NonPossibileCreareClienteException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public NonPossibileCreareClienteException() {
		messaggioErrore = "Non è possibile creare un cliente con i valori inseriti, incoerenza tra parti";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
