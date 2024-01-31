package UniDy.UninaDelivery;

public class NonPossibileCreareMerceException extends UninaDeliveryException {
	
	private String messaggioErrore= "Non è possibile creare una merce con i valori inseriti, incoerenza tra parti";
	private String tipoErrore = "Errore";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}


}