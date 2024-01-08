package UniDy.UninaDelivery;

public class CampoViaVuotoException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoViaVuotoException() {
		messaggioErrore = "Devi inserire la via/viale";
		nomeErrore = "Via/viale vuota";
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
