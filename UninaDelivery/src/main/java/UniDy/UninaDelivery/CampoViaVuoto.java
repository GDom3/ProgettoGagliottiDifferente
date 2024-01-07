package UniDy.UninaDelivery;

public class CampoViaVuoto extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public CampoViaVuoto() {
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
