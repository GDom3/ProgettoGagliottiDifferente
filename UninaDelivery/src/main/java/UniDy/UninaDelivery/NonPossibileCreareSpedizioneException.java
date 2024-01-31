package UniDy.UninaDelivery;

import java.sql.SQLException;

public class NonPossibileCreareSpedizioneException extends UninaDeliverySQLException {
	private String messaggioErrore= "Non è possibile creare una spedizione con i valori inseriti, incoerenza tra parti."
			+ "\nPossibilità : "
			+ "\n  - Possibile che il Corriere Selezionato non può guidare quel Mezzo Selezionato;"
			+ "\n  - Ordine Selezionato non trasportabile nel Mezzo Selezionato;";
	private String tipoErrore = "Errore";
	
	protected String getMessaggioErrore() {
		return messaggioErrore;
	}

	protected String getTipoErrore() {
		return tipoErrore;
	}

}
