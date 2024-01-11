package UniDy.UninaDelivery;

public class CampoCittàVuotoException extends Exception {
	private String messaggioErrore = "Devi inserire la città";
	private String nomeErrore = "Città vuota";
	
	public CampoCittàVuotoException() {
	
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
