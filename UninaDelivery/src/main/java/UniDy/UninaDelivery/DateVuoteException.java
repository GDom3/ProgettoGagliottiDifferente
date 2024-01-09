package UniDy.UninaDelivery;

public class DateVuoteException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public DateVuoteException() {
		messaggioErrore = "Devi inserire anche le date";
		nomeErrore = "Date vuote";
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
