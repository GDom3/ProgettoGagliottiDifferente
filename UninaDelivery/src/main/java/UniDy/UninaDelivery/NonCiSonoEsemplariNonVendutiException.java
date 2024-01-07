package UniDy.UninaDelivery;

public class NonCiSonoEsemplariNonVendutiException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public NonCiSonoEsemplariNonVendutiException() {
		messaggioErrore = "Non ci sono esemplari disponibili, ovvero tutti gli esemplari sono stati venduti";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
