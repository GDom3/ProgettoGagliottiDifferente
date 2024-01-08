package UniDy.UninaDelivery;

public class CampoCittàVuotoException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoCittàVuotoException() {
		messaggioErrore = "Devi inserire la città";
		nomeErrore = "Città vuota";
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
