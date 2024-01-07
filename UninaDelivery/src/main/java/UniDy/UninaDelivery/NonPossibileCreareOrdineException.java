package UniDy.UninaDelivery;

public class NonPossibileCreareOrdineException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public NonPossibileCreareOrdineException() {
		messaggioErrore = "Non è possibile creare un'ordine con i valori inseriti, incoerenza tra parti";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
