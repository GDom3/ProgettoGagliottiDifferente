package UniDy.UninaDelivery;

public class NonCiSonoMagazziniDisponibiliException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoMagazziniDisponibiliException() {
		messaggioErrore = "Non ci sono Magazzini disponibili, ovvero nessun magazzino è presente";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}