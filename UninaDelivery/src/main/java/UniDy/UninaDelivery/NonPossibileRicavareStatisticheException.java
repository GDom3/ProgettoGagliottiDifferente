package UniDy.UninaDelivery;

public class NonPossibileRicavareStatisticheException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public NonPossibileRicavareStatisticheException() {
		messaggioErrore = "Non è possibile ricavare le statistiche del report, la causa delle informazioni non presenti sul database";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
