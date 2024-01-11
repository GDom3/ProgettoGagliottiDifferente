package UniDy.UninaDelivery;

public class NonPossibileCreareClienteException extends Exception {
	private String messaggioErrore= "Non è possibile creare un cliente con i valori inseriti, incoerenza tra parti";
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
