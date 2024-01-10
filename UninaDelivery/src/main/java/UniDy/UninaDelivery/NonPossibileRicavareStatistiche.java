package UniDy.UninaDelivery;

public class NonPossibileRicavareStatistiche extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Errore";
	
	public NonPossibileRicavareStatistiche() {
		messaggioErrore = "Non è possibile ricavare le statistiche del report, la causa delle informazioni non presenti sul database";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
