package UniDy.UninaDelivery;

public class NonCiSonoFornitoriException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoFornitoriException() {
		messaggioErrore = "Non ci sono Fornitori disponibili, ovvero nessun fornitore è presente";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
