package UniDy.UninaDelivery;

public class NonCiSonoFornitoriException extends Exception {
	private String messaggioErrore= "Non ci sono Fornitori disponibili, ovvero nessun fornitore è presente";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoFornitoriException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
