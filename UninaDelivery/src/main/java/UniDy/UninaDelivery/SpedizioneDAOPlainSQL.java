package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SpedizioneDAOPlainSQL implements SpedizioneDAO {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	private ArrayList<Spedizione> spedizioni;
	private Ordine tempOrdine;
	private Cliente tempCliente;
	private ArrayList<Ordine> ordiniDiUnaSpedizione;
	private Spedizione tempSpedizione = null;
	private String CodiceSpedizioneMomentaneo;
	
	public SpedizioneDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}
	
	@Override
	public ArrayList<Spedizione> ricavaSpedizioni(String addonsSQL) throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		

		String comando = "SELECT Cl.CodiceFiscale, O.CodOrdine, count(E.CodiceBarre) AS NumMerci, sum(E.Costo) AS Totale, V.CodSpedizione "
				+ "FROM Cliente Cl NATURAL JOIN Ordine O NATURAL JOIN ESEMPLARE E NATURAL JOIN Viaggio V WHERE V.Corrente = true " + addonsSQL 
				  + " GROUP BY (Cl.CodiceFiscale, O.CodOrdine,V.CodSpedizione) ORDER BY(V.CodSpedizione)";


		
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
	public ArrayList<Spedizione> ricavaSpedizioniPerCliente(String cliente) throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro; 
		
		try {
			// caso in cui sia un intero
			addonsFiltro = " AND Cl.CodCliente = "+ Integer.valueOf(cliente)+" OR ";
		} catch (Exception e) {
			addonsFiltro = " AND ";
		}
		
		//Restante parte del filtro
		addonsFiltro = addonsFiltro
				+ "Cl.CodiceFiscale = '"+cliente+"' OR "
						+ "Cl.email = '"+cliente+"' OR "
								+ "Cl.NumeroCellulare = '"+cliente+"'";	
		
		
		return ricavaSpedizioni(addonsFiltro);
	}


	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerDateE(LocalDate dataInizio, LocalDate dataFine) throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " AND ";
		
		addonsFiltro = addonsFiltro + "DataE "; // caso in cui si fa con la data di esecuzione
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"'";
		
		return ricavaSpedizioni(addonsFiltro);
	}


	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerDateConsegna(LocalDate dataInizio, LocalDate dataFine) throws  RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " AND ";
		
		addonsFiltro = addonsFiltro + "DataConsegna ";// caso in cui si fa con la data di consegna
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"'";
		
		return ricavaSpedizioni(addonsFiltro);
	}


	@Override
	public ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateE(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  " AND (";
		
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
	public ArrayList<Spedizione> ricavaSpedizioniPerUtenteEDateConsegna(String cliente, LocalDate dataInizio, LocalDate dataFine) throws RisultatoNonRicavabileException, DatiTrovatiDopoIlFiltraggioVuotiException {
		//Rappresenta la selezione da fare il filtro
		String addonsFiltro =  "  AND (";
		
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
	
	public Spedizione trovaSpedizione(String codSpedizione) throws RisultatoNonRicavabileException{
		String comando = "SELECT CodSpedizione, StatoSpedizione FROM Spedizione WHERE CodSpedizione = '" + codSpedizione + "';";
		
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
				
		
		Spedizione SpedEstratta;
		
		try {
			comunicazioneSQL.prossimaRiga();
			SpedEstratta = new Spedizione(codSpedizione, null); 
			SpedEstratta.setStatoSpedizione(risultato.getString(2));
		}catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		
		
		return SpedEstratta;
		
		
		
		
	}

	
	public String aggiornaStatoSpedizione(Spedizione spedizioneSelezionata) throws RisultatoNonRicavabileException {
		String comando = "UPDATE Spedizione SET StatoSpedizione = '" +  spedizioneSelezionata.getStatoSpedizione() + "' WHERE CodSpedizione = '" + spedizioneSelezionata.getCodSpedizione()+"' ;";
		int buonfine; 
		String StatoSpedizione = "OK";
		
		
		try {
			buonfine = comunicazioneSQL.mandaQDDL_DML(comando);
		} catch (OperazioneUpdateNonRiuscitaException e) {		
			comando = "SELECT S.StatoSpedizione FROM Viaggio AS V NATURAL JOIN Spedizione AS S WHERE V.Corrente = true AND V.CodSpedizione = '" + spedizioneSelezionata.getCodSpedizione()+"' ;";
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
	public void creaNuovaSpedizione(Spedizione nuovaSpedizione) throws OperazioneUpdateNonRiuscitaException, RisultatoNonRicavabileException,NonPossibileCreareSpedizioneException {
		
		
		String comando = "INSERT INTO Spedizione SELECT CodSpedizione+1, 'Presa In Carico', "+nuovaSpedizione.getKM() + ", 0, 'Base Centrale',"+ nuovaSpedizione.getCorriere().getCodCorriere() + ","+ nuovaSpedizione.getMezzoUtilizzato().getCodMezzo() +" FROM Spedizione ORDER BY CodSpedizione DESC LIMIT 1;"
				+ "INSERT INTO Viaggio VALUES (true,"+ nuovaSpedizione.getCodOrdineIndex(0).getCodOrdine() + ",(SELECT CodSpedizione FROM Spedizione ORDER BY CodSpedizione DESC LIMIT 1) )";
		
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		}catch (SQLException e) {
			throw new NonPossibileCreareSpedizioneException();
		}
		
		
		
	}

	@Override
	public ArrayList<Spedizione> dammiSpedizioniNonPartite() throws NonCiSonoSpedizioniNonPartite, RisultatoNonRicavabileException {
		
		ArrayList<Spedizione> spedizioniNonPartite = new ArrayList<Spedizione>();
		Spedizione tempSpedizione;
		String comando = "SELECT CodSpedizione FROM SPEDIZIONE WHERE StatoSpedizione <> 'Partita' AND StatoSpedizione <> 'Conclusa' ORDER BY (CodSpedizione);";
		
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempSpedizione = new Spedizione(risultato.getString(1), null);
			spedizioniNonPartite.add(tempSpedizione);
		} catch (SQLException e) {
			throw new NonCiSonoSpedizioniNonPartite();
		}
		
		if(comunicazioneSQL.prossimaRiga())	
		do {
			try {
				tempSpedizione = new Spedizione(risultato.getString(1), null);
			} catch (SQLException e) {
				throw new RisultatoNonRicavabileException();
			}
			spedizioniNonPartite.add(tempSpedizione);
			
		}while(comunicazioneSQL.prossimaRiga());
		
		
		
		
		
		return spedizioniNonPartite;
	}

	@Override
	public void inserisciOrdineInSpedizione(Spedizione spedizione, Ordine ordine) throws OperazioneUpdateNonRiuscitaException {
		
		String comando = "INSERT INTO Viaggio VALUES (true,"+ordine.getCodOrdine()+","+spedizione.getCodSpedizione()+");";
		
		comunicazioneSQL.mandaQDDL_DML(comando);
		
	
	}
	
	
}
