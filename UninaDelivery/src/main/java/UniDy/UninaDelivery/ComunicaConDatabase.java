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
	private ResultSetMetaData risultatoMetaDati;
	
	
	public ComunicaConDatabase() {
		//Istanzio la classe o la recupero, per comunicare con il database;
		connectionDataBase = ConnessioneDataBase.getConnessioneDataBase(); 
	}
	
	
	//Passa alla prossima riga per andare a prendere la tupla successiva
	protected ResultSet prossimaRiga() throws SQLException { 
		try {
			risultato.next();
		} catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		
		return risultato;
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
			// Prova a creare la connessione
			creaConnessione();
		} catch (CreazioneStatementFallitaException e) {
			throw e;
		}catch (ConnessionNonRiuscitaException e ){
			e.setMessaggioErrore(e.getMessaggioErrore() + "\nDettagli : "+ connectionDataBase.getMessaggioErrore());
			throw e;
		}
		
		try { 
			//Provo a mandare la query
			mandaQuery(comando);
		} catch (RisultatoNonRicavabileException e) {
				throw e;
		}
					
		return risultato;
	}
	
	// crea la conessione e fa i giusti controlli
	private void creaConnessione() throws  ConnessionNonRiuscitaException,CreazioneStatementFallitaException{

		connessione = connectionDataBase.getConnection(); //Creo la connessione se è chiusa o non c'è mai stata
		
		if(connessione == null)
				throw new ConnessionNonRiuscitaException();
		try {
			trasportatore = connessione.createStatement();
		} catch (SQLException e) {
			throw new CreazioneStatementFallitaException();
		}
		
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
