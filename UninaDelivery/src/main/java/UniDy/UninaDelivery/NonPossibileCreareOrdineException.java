package UniDy.UninaDelivery;

public class NonPossibileCreareOrdineException extends UninaDeliveryException {
	private String messaggioErrore= "Non è possibile creare un'ordine con i valori inseriti, incoerenza tra parti";
	private String tipoErrore = "Errore";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
