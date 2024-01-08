package UniDy.UninaDelivery;

public class NonCiSonoCorrieriException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoCorrieriException() {
		messaggioErrore = "Non ci sono CORRIERI da selezionare";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
