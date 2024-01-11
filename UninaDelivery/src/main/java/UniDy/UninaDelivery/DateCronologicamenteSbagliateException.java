package UniDy.UninaDelivery;

public class DateCronologicamenteSbagliateException extends Exception {
	private String messaggioErrore = "le date inserite non sono valide, il periodo di tempo deve essere coerente";
	private String nomeErrore = "Data fine maggiore data inizio";
	
	public DateCronologicamenteSbagliateException() {
		
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
