package UniDy.UninaDelivery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ComunicaConDatabase {
	
	private Connection connessione = null;
	private ConnessioneDataBase connectionDataBase = null;
	private Statement trasportatore = null;
	private ResultSet risultato;
	private int BuonFine;
	private ResultSetMetaData risultatoMetaDati;
	
	
	protected ComunicaConDatabase() throws ConnessionNonRiuscitaException, CreazioneStatementFallitaException  {
		//Istanzio la classe o la recupero, per comunicare con il database;
		connectionDataBase = ConnessioneDataBase.getConnessioneDataBase(); 
		
		
		this.creaConnessione();
		
		
	}
	
	
	//Passa alla prossima riga per andare a prendere la tupla successiva
	protected boolean prossimaRiga() throws RisultatoNonRicavabileException  { 
		try {
			return risultato.next();
		} catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		
		
	}
	
	
	//Chiude tutto
	protected void chiudiComunicazioneDatabase() throws ChiusturaComunicazioneFallitaException{
		
		try {
			risultato.close();
			trasportatore.close();
			connessione.close();
		}catch (SQLException e) {
			throw new ChiusturaComunicazioneFallitaException();
		}
	
		
	}
	
	//Manda la quary e la esegue riportando il risultato e fa i dovuti controlli
	protected ResultSet comunicaConDatabaseQuery(String comando) throws  CreazioneStatementFallitaException , ConnessionNonRiuscitaException ,RisultatoNonRicavabileException{
		
		try { 
			//Provo a mandare la query
			mandaQuery(comando);
		} catch (RisultatoNonRicavabileException e) {
				throw e;
		}
					
		return risultato;
	}
	
	
	protected int mandaQDDL_DML(String comando) throws OperazioneUpdateNonRiuscitaException {
		
		try {
			BuonFine = trasportatore.executeUpdate(comando);
		} catch (SQLException e) {
			throw new OperazioneUpdateNonRiuscitaException();
		}
		
		return BuonFine;
		
	}


	// crea la conessione e fa i giusti controlli
	protected Connection creaConnessione() throws  ConnessionNonRiuscitaException,CreazioneStatementFallitaException{
		
		connessione = connectionDataBase.getConnection(); //Creo la connessione se è chiusa o non c'è mai stata
		
		
		if(connessione == null)
				throw new ConnessionNonRiuscitaException();
		try {
			trasportatore = connessione.createStatement();
		} catch (SQLException e) {
			throw new CreazioneStatementFallitaException();
		}
		
		return connessione;
		
	}
	
	//Mando la query al db
	private void mandaQuery(String comando) throws RisultatoNonRicavabileException {
		try {
			risultato = trasportatore.executeQuery(comando);
		} catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
	}
	
	//Prendo i Campi
	private void gestioneMetaDati() throws MetaDatiNonTrovatiException  {
		
		try {
			risultatoMetaDati = risultato.getMetaData();
		} catch (SQLException e) {
			throw new MetaDatiNonTrovatiException();
		}
		
		
	}

}
