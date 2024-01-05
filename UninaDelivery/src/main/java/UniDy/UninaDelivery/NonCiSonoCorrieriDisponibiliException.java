package UniDy.UninaDelivery;

public class NonCiSonoCorrieriDisponibiliException extends Exception {

	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoCorrieriDisponibiliException() {
		messaggioErrore = "Non ci sono corrieri disponibili, ovvero tutti i corriere stanno consegnado già in una spedizione";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
	
	
}
