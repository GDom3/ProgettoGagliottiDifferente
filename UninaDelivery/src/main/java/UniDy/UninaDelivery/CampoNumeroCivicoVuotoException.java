package UniDy.UninaDelivery;

public class CampoNumeroCivicoVuotoException extends Exception {
	private String messaggioErrore = "Devi inserire il numero civico";
	private String nomeErrore = "Numero Civico vuota";
	
	public CampoNumeroCivicoVuotoException() {
		
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
