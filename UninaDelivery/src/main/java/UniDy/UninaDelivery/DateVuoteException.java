package UniDy.UninaDelivery;

public class DateVuoteException extends Exception {
	private String messaggioErrore = "Devi inserire anche le date";
	private String nomeErrore = "Date vuote";
	
	public DateVuoteException() {

		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
