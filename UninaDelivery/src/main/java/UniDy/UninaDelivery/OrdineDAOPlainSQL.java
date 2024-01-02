package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdineDAOPlainSQL {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;


	protected OrdineDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	public Ordine trovaOrdine(String ordDaCercare) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException  {
		
		String comando = "SELECT CodOrdine, StatoOrdine FROM Ordine WHERE CodOrdine = '" + ordDaCercare + "';";
		
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		
		Ordine ordEstratto;
		
		try {
			comunicazioneSQL.prossimaRiga();
			ordEstratto = new Ordine(risultato.getString(1),null,0, 0);
			ordEstratto.setStatoOrdine(risultato.getString(2));
		}catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		
		
		return ordEstratto;
	}


	public String aggiornaStatoOrdine(Ordine ordineSelezionato) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException {
		String comando = "UPDATE Ordine SET StatoOrdine = '" +  ordineSelezionato.getStatoOrdine() + "' WHERE CodOrdine = '" + ordineSelezionato.getCodOrdine()+"' ;";
		int buonfine; 
		String StatoSpedizione = "OK";
		
		
		try {
			buonfine = comunicazioneSQL.mandaQDDL_DML(comando);
			
		} catch (OperazioneUpdateNonRiuscitaException e) {	
			comando = "SELECT S.StatoSpedizione FROM Viaggio AS V NATURAL JOIN Spedizione AS S WHERE V.Corrente = true AND V.CodOrdine = '" + ordineSelezionato.getCodOrdine()+"' ;";
			risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
			try {
				comunicazioneSQL.prossimaRiga();
				StatoSpedizione = risultato.getString(1);
			} catch (SQLException e1) {
				throw new RisultatoNonRicavabileException();
			}
			
			return StatoSpedizione;
		}
		
		return "OK";
	}


	
	

}
