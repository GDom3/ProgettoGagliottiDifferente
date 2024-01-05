package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAOPlainSQL {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;


	protected OrdineDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	public Ordine trovaOrdine(String ordDaCercare) throws  RisultatoNonRicavabileException  {
		
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


	public String aggiornaStatoOrdine(Ordine ordineSelezionato) throws RisultatoNonRicavabileException {
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


	public ArrayList<Ordine> estraiOrdiniSenzaSpedOFalliti() throws  RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException  {
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		String comando = "SELECT CodOrdine,StatoOrdine FROM Ordine WHERE CodOrdine NOT IN (SELECT CodOrdine FROM Viaggio WHERE Corrente = true) ORDER BY (CodOrdine)";
		Ordine tempOrdine = null;
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempOrdine = new Ordine(risultato.getString(1),risultato.getString(2));
			ordini.add(tempOrdine);
			
		} catch (SQLException e) {
			throw new NonCiSonoOrdiniAttesiException();
		}
			
			
		if(comunicazioneSQL.prossimaRiga())	
			do{
			
				try {
					tempOrdine = new Ordine(risultato.getString(1),risultato.getString(2));
					ordini.add(tempOrdine);
				} catch (SQLException e) {
					throw new RisultatoNonRicavabileException();
				}	
			}while(comunicazioneSQL.prossimaRiga());
		
		return ordini;
	}


	
	

}
