package UniDy.UninaDelivery;

public class CampoPesoVuotoException extends Exception {
	private String messaggioErrore = "Campo Peso vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	public CampoPesoVuotoException() {

		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}