package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Operatore {

	private String usernameCorretto = null;
	private String passwordCorretto = null;
	private ComunicaConDatabase comunicazioneSQL;
	private String nomeOperatore = "Utente";
	private ResultSet risultato;

	protected Operatore() {
		comunicazioneSQL = new ComunicaConDatabase();		
	}
	
	
	protected String getNomeOperatore() {
		return nomeOperatore;
	}


	protected void provaAccesso(String username, String password) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException, ChiusturaComunicazioneFallitaException{
	
		
		if(usernameCorretto == null || passwordCorretto == null || !username.equals(usernameCorretto) || !password.equals(passwordCorretto)) {
			
			richiestaVerifica(username);
				
			verificaUsername(username);
			usernameCorretto = username;
			verificaPassword(password);
			
			
		
			//Chiudo tutto
			comunicazioneSQL.chiudiComunicazioneDatabase();
		}
		
		
		
		
	}
		private void richiestaVerifica(String username) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException {
			//La select necessaria per vedere se posso trovare l'utente e se ha inserito i valori corretti
			String comando = "SELECT O.nome, O.cognome, O.password FROM operatore AS O WHERE ( O.username = '"+ username + "' OR O.email = '" + username.toLowerCase() + "')";
		
			//Mando il comando e prendo il risultato della query
			risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);  	
			
		}
		
		
		private void verificaPassword(String password) throws EstrazioneCampiFallitaException, RisultatoNonRicavabileException {
			//prendo la password e la comparo alla password corretta
			
			try {
				passwordCorretto = risultato.getString(3);
			}catch(SQLException e ) {
				throw new RisultatoNonRicavabileException();	
			}
			if(!password.equals(passwordCorretto)) 
					throw new EstrazioneCampiFallitaException("Password errata");
			
		}

		private void verificaUsername(String username) throws RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
				//Vado alla prima riga
				comunicazioneSQL.prossimaRiga(); 
						
			try {
				// mi estraggo il nome e il cognome
				nomeOperatore = risultato.getString(1) + " " + risultato.getString(2); 
			} catch (SQLException e) {
				// nel caso non si trova nessun valore significa che l'operatore non esiste nel database
				throw new EstrazioneCampiFallitaException("Username o email non registrate"); 
			}
			
			
		}
	

}
