package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAOPlainSQL implements OrdineDAO {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	private ArrayList<Ordine> ordini;

	protected OrdineDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	@Override
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


	@Override
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


	@Override
	public ArrayList<Ordine> estraiOrdiniSenzaSpedOFalliti() throws  RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException  {
		ordini = new ArrayList<Ordine>();
		String comando = "SELECT CodOrdine,StatoOrdine FROM Ordine WHERE CodOrdine NOT IN (SELECT CodOrdine FROM Viaggio WHERE Corrente = true) ORDER BY (CodOrdine)";
		return estraiOrdini(comando);
	}
	
	
	private ArrayList<Ordine> estraiOrdini(String comando)  throws  RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException {
		
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

	@Override
	public ArrayList<Ordine> dammiOrdiniNonPartitiOFalliti() throws  RisultatoNonRicavabileException, NonCiSonoOrdiniAttesiException{

		ordini = new ArrayList<Ordine>();
		String comando = "SELECT CodOrdine,StatoOrdine FROM Ordine WHERE StatoOrdine <> 'Spedito' AND  StatoOrdine <> 'In Consegna' AND StatoOrdine <> 'Consegnato'  ORDER BY (CodOrdine)";
		return estraiOrdini(comando);
		
		
	}


	@Override
	public void creaOrdine(Ordine nuovoOrd) throws RisultatoNonRicavabileException, NonPossibileCreareOrdineException {
		
		String comando = "INSERT INTO Ordine SELECT MAX(CodOrdine)+1,'Presa In Carico',"+nuovoOrd.getCostoSpedizione()+",'"+nuovoOrd.getDataE()+"','"
                +nuovoOrd.getDataConsegna()+"','"+nuovoOrd.getIndirizzo().getNumeroCivico()+"','"+nuovoOrd.getIndirizzo().getCittà()+"','"
                +nuovoOrd.getIndirizzo().getVia()+"','"+nuovoOrd.getIndirizzo().getCAP()+"',"+""
                		+ "(SELECT CodCliente FROM Cliente WHERE CodiceFiscale = '"+ nuovoOrd.getAcquirente().getCodiceFiscale()+"') FROM Ordine;";



        comando = comando + " UPDATE ESEMPLARE SET CodOrdine = (SELECT CodOrdine FROM Ordine ORDER BY CodOrdine DESC LIMIT 1) WHERE CodiceBarre = '" + nuovoOrd.getArticoliVenduti(0).getCodiceBarre() +"';";
		
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		}catch (SQLException e) {
			throw new NonPossibileCreareOrdineException();
		}
		
	}
	
	@Override
	public int[] numeroMedioOrdini(int anno) throws RisultatoNonRicavabileException{
		int vet[] = new int[12];
		int valore;
		int valoreVecchio = 0;
		int mese = 1;
		int i = 0;
		String comando = "SELECT Count(CodOrdine),date_part('month', DataE) FROM Ordine WHERE date_part('year', DataE) = '"+anno+"' GROUP BY (date_part('month', DataE)) ORDER BY (date_part('month', DataE)); ";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		if(comunicazioneSQL.prossimaRiga())	
			do{
				try {
					valore = risultato.getInt(1);
					mese = risultato.getInt(2);
					
					vet[mese-1] = valore;
					
					
				} catch (SQLException e) {
					throw new RisultatoNonRicavabileException();
				}	
			}while(comunicazioneSQL.prossimaRiga());
		
		
		return vet;
	}
	
	@Override
	public Ordine ordineConMaggiorProdotti(int anno) throws RisultatoNonRicavabileException {
		Ordine ord = null;
		
		String comando = "SELECT CodOrdine,StatoOrdine,COUNT(CodiceBarre) AS num "
				+ " FROM ESEMPLARE NATURAL JOIN Ordine "
				+ " WHERE CodOrdine IS NOT NULL AND  date_part('year', DataE) = '"+anno+"' "
						+ " GROUP BY(CodOrdine,StatoOrdine) ORDER BY (num) DESC LIMIT 1;";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			comunicazioneSQL.prossimaRiga();
			ord = new Ordine(risultato.getString(1), risultato.getString(2));
		} catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		
		
		return ord;
	}
	
	@Override
	public Ordine ordineConMinorProdotti(int anno) throws RisultatoNonRicavabileException {
		Ordine ord = null;
		
		String comando = "SELECT CodOrdine,StatoOrdine,COUNT(CodiceBarre) AS num "
				+ " FROM ESEMPLARE NATURAL JOIN Ordine "
				+ " WHERE CodOrdine IS NOT NULL AND  date_part('year', DataE) = '"+anno+"' "
						+ " GROUP BY(CodOrdine,StatoOrdine) ORDER BY (num) LIMIT 1;";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			comunicazioneSQL.prossimaRiga();
			ord = new Ordine(risultato.getString(1), risultato.getString(2));
		} catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		
		
		return ord;
	}


	
	

}
