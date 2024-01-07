package UniDy.UninaDelivery;

public class NonCiSonoClientiException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoClientiException() {
		messaggioErrore = "Non ci sono clienti esistenti";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
