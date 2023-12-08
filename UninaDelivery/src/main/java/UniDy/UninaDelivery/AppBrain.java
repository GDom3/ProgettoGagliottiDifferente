package UniDy.UninaDelivery;

import java.sql.*;


public class AppBrain {
	
	FinestraLogin loginWindow;
	FinestraMenu menuWindow;
	ComunicaConDatabase comunicazioneSQL;
	
	public static void main(String[] args) {
		
		AppBrain gestoreApplicazione = new AppBrain();

	}

	public AppBrain() {
		
		comunicazioneSQL = new ComunicaConDatabase(); 
		loginWindow = new FinestraLogin(this);
		menuWindow = new FinestraMenu(this);
		
		
		
		//menuWindow.setVisible(true);
		loginWindow.setVisible(true);
	}

	protected void accesso(String username, String password) throws  SQLException{
		String operatore = "Utente";
		//La select necessaria per vedere se posso trovare l'utente e se ha inserito i valori corretti
		String comando = "SELECT O.nome, O.cognome FROM operatore AS O WHERE ( O.username = '"+ username + "' OR O.email = '" + username + "') AND O.password = '"+password+ "'";
			
		ResultSet risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando); 
				
		try {
			comunicazioneSQL.prossimaRiga();
			operatore = risultato.getString(1) + " " + risultato.getString(2);
		
		} catch (SQLException e) {
			esisteUtenteInserito(username);
			
		}
		
		comunicazioneSQL.chiudiComunicazioneDatabase();
		
		menuWindow.impostaOperatore(operatore);
		menuWindow.setVisible(true);
		loginWindow.setVisible(false);
	}
	
	
	private void esisteUtenteInserito(String username) throws SQLException {
		//Select per vedere se esiste l'utente
		String comando = "SELECT 1 FROM operatore AS O WHERE ( O.username = '"+ username + "' OR O.email = '" + username + "')";
		String errore = "Errore";
		
	
		ResultSet risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		try {
			comunicazioneSQL.prossimaRiga();
	
			if(risultato.getInt(1) == 1) // se lo trova invece significa che la password è quella sbagliata
				errore = "Password errata";
					
		} catch (SQLException ErroreUtenteNonTrovato) { //Se non trova l'utente vuol dire che non esiste
			errore = "Username non registrato";
		}
	
		throw new EstrazioneCampiFallitaException(errore); // e quindi gli do l'errore corretto 
			
	
	}

	public void logout() {
		menuWindow.setVisible(false);
		loginWindow.impostaPassword("Password");
		loginWindow.setVisible(true);
		
	}
}


