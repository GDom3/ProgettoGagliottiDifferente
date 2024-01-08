package UniDy.UninaDelivery;

public class CampoNumeroCivicoVuotoException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoNumeroCivicoVuotoException() {
		messaggioErrore = "Devi inserire il numero civico";
		nomeErrore = "Numero Civico vuota";
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
