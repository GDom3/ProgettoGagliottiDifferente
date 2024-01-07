package UniDy.UninaDelivery;

public class CampoNumeroCivicoVuoto extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoNumeroCivicoVuoto() {
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
