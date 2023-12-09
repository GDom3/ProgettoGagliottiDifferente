package UniDy.UninaDelivery;

public class DateCronologicamenteSbagliateException extends Exception {
	private String messaggioErrore;
	private String nomeErrore;
	
	public DateCronologicamenteSbagliateException(FinestraVisualizzaDatiFiltrabili finestra) {
		messaggioErrore = "le date inserite non sono valide, il periodo di tempo deve essere coerente";
		nomeErrore = "Data fine maggiore data inizio";
		finestra.setDataFine(null);
		finestra.setDataInizio(null);
		
	}

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getNomeErrore() {
		return nomeErrore;
	}
}
