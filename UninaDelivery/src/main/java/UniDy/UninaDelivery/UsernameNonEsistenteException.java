package UniDy.UninaDelivery;

public class UsernameNonEsistenteException extends Exception {
	private String messaggioErrore;
	private String tipoErrore = "Attenzione";
	
	public UsernameNonEsistenteException(){
		messaggioErrore = "Username o email inseriti non registrate";
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
