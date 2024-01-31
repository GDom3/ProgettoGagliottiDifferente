package UniDy.UninaDelivery;

public class CampoTargaVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo TARGA vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}