package UniDy.UninaDelivery;

public class CampoViaVuotoException extends Exception {
	private String messaggioErrore = "Devi inserire la via/viale";
	private String nomeErrore = "Via/viale vuota";
	
	public CampoViaVuotoException() {
		
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
