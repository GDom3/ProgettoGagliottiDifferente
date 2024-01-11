package UniDy.UninaDelivery;

public class PasswordErrataException extends Exception {
	private String messaggioErrore= "Password inserita è errata";
	private String tipoErrore = "Attenzione";
	
	public PasswordErrataException(){
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
