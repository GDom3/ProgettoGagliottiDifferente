package UniDy.UninaDelivery;

import java.sql.*;
import java.time.LocalDate;

import javax.swing.JFrame;


public class AppBrain {
	
	FinestraLogin loginWindow;
	FinestraMenu menuWindow;
	FinestraReportStatistico datiStatisticiWindow;
	FinestraVisualizzaDatiFiltrabili datiOrdiniWindow;
	
	ComunicaConDatabase comunicazioneSQL;
	
	public static void main(String[] args) {
		
		AppBrain gestoreApplicazione = new AppBrain();
		
	}

	public AppBrain() {
		
		comunicazioneSQL = new ComunicaConDatabase(); 
		loginWindow = new FinestraLogin(this);
		menuWindow = new FinestraMenu(this);
		datiStatisticiWindow = new FinestraReportStatistico(this);
		datiOrdiniWindow = new FinestraVisualizzaDatiFiltrabili(this);
		
		
		datiOrdiniWindow.setVisible(true); 
		//menuWindow.setVisible(true); 
		//loginWindow.setVisible(true);
	}

	protected void accesso(String username, String password) throws  SQLException{
		String operatore = "Utente";
	
		//La select necessaria per vedere se posso trovare l'utente e se ha inserito i valori corretti
		String comando = "SELECT O.nome, O.cognome, O.password FROM operatore AS O WHERE ( O.username = '"+ username + "' OR O.email = '" + username.toLowerCase() + "')";
		
		//Mando il comando e prendo il risultato della query
		ResultSet risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);  
				
		try {
			//Vado alla prima riga
			comunicazioneSQL.prossimaRiga(); 
			
			// mi estraggo il nome e il cognome
			operatore = risultato.getString(1) + " " + risultato.getString(2); 
		} catch (SQLException e) {
			// nel caso non si trova nessun valore significa che l'operatore non esiste nel database
			throw new EstrazioneCampiFallitaException("Username o email non registrate"); 
		}
		
		//prendo la password e la comparo alla password corretta
		if(!password.equals(risultato.getString(3))) 
			throw new EstrazioneCampiFallitaException("Password errata");
		
		//Chiudo tutto
		comunicazioneSQL.chiudiComunicazioneDatabase();
		
		//Do il benvenuto al nuovo operatore
		menuWindow.impostaOperatore(operatore);
		// Vado nel menu
		ritornaMenu(loginWindow); 
	}
	

	protected void logout() {
		//Funzione che gestisce il logout
		loginWindow.impostaPassword("Password");
		menuWindow.setVisible(false);
		loginWindow.setVisible(true);
		
	}

	protected void ritornaMenu(JFrame finestra) {
		//Funzione che 'chiude' la finestra data in input e 'apre' il menu
		finestra.setVisible(false);
		menuWindow.setVisible(true);
	}

	protected void mostraFinestraVisualizza() {
		//'chiude' il menu e 'apre' il visuliazza dati finesta 
		menuWindow.setVisible(false);
		datiOrdiniWindow.setVisible(true);
	}

	protected void filtraPerUtenteData(String cliente, LocalDate dataInizio, LocalDate dataFine) {
		// TODO Auto-generated method stub
		
	}

	protected void filtraPerDate(LocalDate dataInizio, LocalDate dataFine) {
		// TODO Auto-generated method stub
		
	}

	protected void filtraPerCliente(String cliente) {
		// TODO Auto-generated method stub
		
	}

	public void visualizzaTutti() {
		String comando = "";
		
	}
}


