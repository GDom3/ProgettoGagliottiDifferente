package UniDy.UninaDelivery;

import java.sql.SQLException;

public class OperazioneUpdateNonRiuscitaException extends UninaDeliverySQLException {
	private String messaggioErrore= "L'Operazione DML non è andata a buon fine a causa dei valori inseriti; \nSuggerimenti : "
			+ "\n  • Si controlli che l'oggetto Nnn esista già;"
			+ "\n  • Si controlli la coerenza tra i valori inseriti;"
			+ "\n  • Si controlli la giusta formattazione dei dati;"
			+ "\n    In paricolar modo:"
			+ "\n      - Rispettando le Lunghezze;"
			+ "\n      - Rispettando i tipi di dati;";
	private String tipoErrore = "Attenzione";

	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}
}
