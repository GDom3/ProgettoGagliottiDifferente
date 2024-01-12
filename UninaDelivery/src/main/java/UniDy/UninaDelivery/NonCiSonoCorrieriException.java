package UniDy.UninaDelivery;

public class NonCiSonoCorrieriException extends Exception {
	private String messaggioErrore = "Non ci sono CORRIERI da selezionare";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoCorrieriException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
