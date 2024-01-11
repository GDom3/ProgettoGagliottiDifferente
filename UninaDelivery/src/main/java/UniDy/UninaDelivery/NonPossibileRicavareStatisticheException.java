package UniDy.UninaDelivery;

public class NonPossibileRicavareStatisticheException extends Exception {
	private String messaggioErrore= "Non è possibile ricavare le statistiche del report, la causa delle informazioni non presenti sul database";
	private String tipoErrore = "Errore";
	
	public NonPossibileRicavareStatisticheException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
