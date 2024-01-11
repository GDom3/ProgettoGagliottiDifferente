package UniDy.UninaDelivery;

public class NonCiSonoClientiException extends Exception {
	private String messaggioErrore = "Non ci sono clienti esistenti";;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoClientiException() {
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
