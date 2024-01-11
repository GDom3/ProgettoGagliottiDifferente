package UniDy.UninaDelivery;

public class CampoMarcaVuotoException extends Exception {
	private String messaggioErrore = "Campo MARCA vuoto, inserire un valore per poter proseguire";;
	private String tipoErrore = "Attenzione";
	
	public CampoMarcaVuotoException() {
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
