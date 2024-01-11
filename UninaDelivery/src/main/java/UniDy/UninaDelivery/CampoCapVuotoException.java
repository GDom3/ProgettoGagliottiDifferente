package UniDy.UninaDelivery;

public class CampoCapVuotoException extends Exception {
	private String messaggioErrore  = "Devi inserire il CAP";;
	private String nomeErrore = "CAP vuoto";
	
	public CampoCapVuotoException() {
	
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
