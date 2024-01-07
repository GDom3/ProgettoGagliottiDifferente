package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EsemplareDAOPlainSQL {
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	
	
	public EsemplareDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	public ArrayList<Esemplare> dammiEsemplariNonvenduti () throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException {
		ArrayList<Esemplare> esemplari = new ArrayList<Esemplare>();
		Esemplare tempesemplare;
		String comando = "SELECT E.CodiceBarre, Mer.Nome FROM Esemplare AS E NATURAL JOIN Merce AS Mer WHERE E.CodOrdine IS NULL ORDER BY(E.CodiceBarre)";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			while(comunicazioneSQL.prossimaRiga()) {
				tempesemplare = new Esemplare(risultato.getString(1),risultato.getString(2));
				esemplari.add(tempesemplare);
			}
		} catch (SQLException e) {
			throw new NonCiSonoEsemplariNonVendutiException();
		}
		
		
		return esemplari;
	}


	public void inserisciEsemplareInOrdine(Ordine ordine, Esemplare esemplare) throws NonCiSonoOrdiniAttesiException {

		String comando = "UPDATE Esemplare SET CodOrdine = '"+ordine.getCodOrdine()+"' WHERE CodiceBarre = '"+esemplare.getCodiceBarre()+"';";
		
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		} catch (OperazioneUpdateNonRiuscitaException e) {
			throw new NonCiSonoOrdiniAttesiException();
		}
		
		
	}
	
}
