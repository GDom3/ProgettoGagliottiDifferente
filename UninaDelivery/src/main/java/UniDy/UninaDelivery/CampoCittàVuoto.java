package UniDy.UninaDelivery;

public class CampoCittàVuoto extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoCittàVuoto() {
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
