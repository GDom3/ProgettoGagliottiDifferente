package UniDy.UninaDelivery;

public class CampoPesoVuotoException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public CampoPesoVuotoException() {
		messaggioErrore = "Campo Peso vuoto, inserire un valore per poter proseguire";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}