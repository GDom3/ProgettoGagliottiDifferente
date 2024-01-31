package UniDy.UninaDelivery;

public class NonPossibileRicavareStatisticheException extends UninaDeliveryException {
	private String messaggioErrore= "Non è possibile ricavare le statistiche del report, la causa delle informazioni non presenti sul database";
	private String tipoErrore = "Errore";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
