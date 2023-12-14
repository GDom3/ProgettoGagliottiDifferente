package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestoreFiltraggioOrdini {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	private FinestraVisualizzaDatiFiltrabili datiOrdiniWindow;
	private AppBrain Hal;
	
	public GestoreFiltraggioOrdini(AppBrain appBrain) {
		comunicazioneSQL = new ComunicaConDatabase();	
		Hal = appBrain;	 
	}
	
}
