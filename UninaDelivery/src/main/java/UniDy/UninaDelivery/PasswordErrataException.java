package UniDy.UninaDelivery;

public class PasswordErrataException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public PasswordErrataException(){
		messaggioErrore = "Password inserita è errata";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
