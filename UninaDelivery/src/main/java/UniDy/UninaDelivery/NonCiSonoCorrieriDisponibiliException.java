package UniDy.UninaDelivery;

public class NonCiSonoCorrieriDisponibiliException extends Exception {

	private String messaggioErrore = "Non ci sono corrieri disponibili, ovvero tutti i corriere stanno consegnado già in una spedizione";
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoCorrieriDisponibiliException() {
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
	
	
}
