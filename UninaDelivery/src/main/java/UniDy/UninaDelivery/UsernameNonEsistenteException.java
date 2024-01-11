package UniDy.UninaDelivery;

public class UsernameNonEsistenteException extends Exception {
	private String messaggioErrore = "Username o email inseriti non registrate";
	private String tipoErrore = "Attenzione";
	
	public UsernameNonEsistenteException(){
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
