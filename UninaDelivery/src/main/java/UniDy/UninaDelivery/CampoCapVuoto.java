package UniDy.UninaDelivery;

public class CampoCapVuoto extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoCapVuoto() {
		messaggioErrore = "Devi inserire il CAP";
		nomeErrore = "CAP vuoto";
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
