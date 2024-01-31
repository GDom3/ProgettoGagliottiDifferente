package UniDy.UninaDelivery;

public class DateCronologicamenteSbagliateException extends UninaDeliveryException {
	private String messaggioErrore = "le date inserite non sono valide, il periodo di tempo deve essere coerente";
	private String tipoErrore = "Data fine maggiore data inizio";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
