package UniDy.UninaDelivery;

public class CampoPesoVuotoException extends UninaDeliveryException {
	private String messaggioErrore = "Campo Peso vuoto, inserire un valore per poter proseguire";
	private String tipoErrore = "Attenzione";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}