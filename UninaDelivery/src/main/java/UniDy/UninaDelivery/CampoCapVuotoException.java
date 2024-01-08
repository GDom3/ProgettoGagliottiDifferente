package UniDy.UninaDelivery;

public class CampoCapVuotoException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoCapVuotoException() {
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
