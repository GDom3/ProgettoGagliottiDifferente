package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SpedizioneDAOPlainSQL implements SpedizioneDAO {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	private AppBrain gestoreApplicazione;
	private ArrayList<Spedizione> spedizioni;
	private Ordine tempOrdine;
	private Cliente tempCliente;
	private ArrayList<Ordine> ordiniDiUnaSpedizione;
	private Spedizione tempSpedizione = null;
	private String CodiceSpedizioneMomentaneo;
	
	
	public SpedizioneDAOPlainSQL(ComunicaConDatabase comunicazioneSQL, AppBrain gestoreApplicazione) {
		this.comunicazioneSQL = comunicazioneSQL;
		this.gestoreApplicazione = gestoreApplicazione;
	}
	
	@Override
	public ArrayList<Spedizione> ricavaSpedizioni(String addonsSQL) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		

		String comando = "SELECT Cl.CodiceFiscale, O.CodOrdine, count(E.CodiceBarre) AS NumMerci, sum(E.Costo) AS Totale, V.CodSpedizione "
				+ "FROM Cliente Cl NATURAL JOIN Ordine O NATURAL JOIN ESEMPLARE E NATURAL JOIN Viaggio V " + addonsSQL 
				  + "GROUP BY (Cl.CodiceFiscale, O.CodOrdine,V.CodSpedizione) ORDER BY(V.CodSpedizione)";
		
		
		 

		//Mando il comando e prendo il risultato della query
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);  	
		
		comunicazioneSQL.prossimaRiga();
		
		//Prendo Il primo risultato
		try {
			casoAppartieneAdNuovaSpedizione();	
		} catch (SQLException e) {
			//Nel caso non esiste vuol dire che i dati inseriti dall'utente non corrispondono a nessun ordine
			throw new DatiTrovatiDopoIlFiltraggioVuotiException();
		}
		
		//Aggiungo l'ordine al cliente
		tempCliente.addOrdini(tempOrdine);
		
		//Alloco l'array per il return
		spedizioni = new ArrayList<Spedizione>();
		
		//Per tutte le righe successive
		while(comunicazioneSQL.prossimaRiga()) {
			
			try {
				//Se la spedizione coincide con la precedente
				if(CodiceSpedizioneMomentaneo.equals(risultato.getString(5)))
					//Semplicemente prendo il nuovo cliente e ordine  e li aggiungo rispettivamente
					casoAppartieneAllaSpedizionePrecedente();		
				else{
					//Altrimenti vuol dire che una spedizione è finita, quindi salvo tutti i dati di essa
					inseriscoSpedizione();
					// e parto da nuovo con la nuova
					casoAppartieneAdNuovaSpedizione();	
			
				}
			} catch (SQLException e) {
				//Caso in cui c'è un errore con il db
				throw new RisultatoNonRicavabileException();
			}
		}
		//Salvo l'ultima spedizione
		inseriscoSpedizione();
		
		return spedizioni;
	}
	
	//Spiegata su
	private void casoAppartieneAdNuovaSpedizione() throws SQLException {
		CodiceSpedizioneMomentaneo = risultato.getString(5);
		tempCliente = new Cliente(risultato.getString(1));
		tempOrdine = new Ordine(risultato.getString(2),tempCliente,risultato.getFloat(4),risultato.getInt(3));
		ordiniDiUnaSpedizione = new ArrayList<Ordine>(tempOrdine.getNumMerci());
		ordiniDiUnaSpedizione.add(tempOrdine);
	
	}
	
	//Spiegata su
	private void casoAppartieneAllaSpedizionePrecedente() throws SQLException {
		tempCliente = new Cliente(risultato.getString(1));
		tempOrdine = new Ordine(risultato.getString(2),tempCliente,risultato.getFloat(4),risultato.getInt(3));
		ordiniDiUnaSpedizione.add(tempOrdine);
		tempCliente.addOrdini(tempOrdine);
		
	}
	
	//Spiegata su
	private void inseriscoSpedizione(){
		tempSpedizione = new Spedizione(CodiceSpedizioneMomentaneo, ordiniDiUnaSpedizione);
		for(Ordine ord : ordiniDiUnaSpedizione)
			ord.addSpedizioni(tempSpedizione);
		
		spedizioni.add(tempSpedizione);	
	}
	

	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerCliente(String cliente) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro; 
		
		try {
			// caso in cui sia un intero
			addonsFiltro = " WHERE Cl.CodCliente = "+ Integer.valueOf(cliente)+" OR ";
		} catch (Exception e) {
			addonsFiltro = "WHERE ";
		}
		
		//Restante parte del filtro
		addonsFiltro = addonsFiltro
				+ "Cl.CodiceFiscale = '"+cliente+"' OR "
						+ "Cl.email = '"+cliente+"' OR "
								+ "Cl.NumeroCellulare = '"+cliente+"'";	
		
		
		return ricavaSpedizioni(addonsFiltro);
	}


	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerDateE(LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " WHERE ";
		
		addonsFiltro = addonsFiltro + "DataE "; // caso in cui si fa con la data di esecuzione
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"'";
		
		return ricavaSpedizioni(addonsFiltro);
	}


	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerDateConsegna(LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " WHERE ";
		
		addonsFiltro = addonsFiltro + "DataConsegna ";// caso in cui si fa con la data di consegna
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"'";
		
		return ricavaSpedizioni(addonsFiltro);
	}


	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateE(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " WHERE (";
		
		addonsFiltro = addonsFiltro + "DataE ";   // caso in cui si fa con la data di esecuzione
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"')";
		
		try {
			// caso in cui sia un intero
			addonsFiltro = addonsFiltro + " AND Cl.CodCliente = "+ Integer.valueOf(cliente)+" OR ";
		} catch (Exception e) {
			addonsFiltro = addonsFiltro + " AND ";
		}
		
		//Restante parte del filtro
		addonsFiltro = addonsFiltro
				+ "Cl.CodiceFiscale = '"+cliente+"' OR "
						+ "Cl.email = '"+cliente+"' OR "
								+ "Cl.NumeroCellulare = '"+cliente+"'";	
		
		return ricavaSpedizioni(addonsFiltro);
	}
	
	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateConsegna(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " WHERE (";
		
		addonsFiltro = addonsFiltro + "DataConsegna ";// caso in cui si fa con la data di consegna
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"')";
		
		try {
			// caso in cui sia un intero
			addonsFiltro = addonsFiltro + " AND Cl.CodCliente = "+ Integer.valueOf(cliente)+" OR ";
		} catch (Exception e) {
			addonsFiltro = addonsFiltro + " AND ";
		}
		
		//Restante parte del filtro
		addonsFiltro = addonsFiltro
				+ "Cl.CodiceFiscale = '"+cliente+"' OR "
						+ "Cl.email = '"+cliente+"' OR "
								+ "Cl.NumeroCellulare = '"+cliente+"'";	
		
		return ricavaSpedizioni(addonsFiltro);
	}
	
	
	
	
}
