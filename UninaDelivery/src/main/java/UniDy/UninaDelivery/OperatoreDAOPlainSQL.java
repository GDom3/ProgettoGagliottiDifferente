package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatoreDAOPlainSQL implements OperatoreDAO  {
	private String usernameCorretto = null;
	private String passwordCorretto = null;
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	private Operatore utenteOperatore;
	
	public OperatoreDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}

	@Override
	public Operatore provaAccesso(String username, String password)
			throws RisultatoNonRicavabileException, UsernameNonEsistenteException, PasswordErrataException {
			
			utenteOperatore = new Operatore(usernameCorretto, password);
			
			richiestaVerifica(username);

			verificaUsername(username);
			usernameCorretto = username;
			verificaPassword(password);

			
			
			return utenteOperatore;
	}

	@Override
	public void richiestaVerifica(String username)
			throws RisultatoNonRicavabileException {
		// La select necessaria per vedere se posso trovare l'utente e se ha inserito i
		// valori corretti
		String comando = "SELECT O.nome, O.cognome, O.password FROM operatore AS O WHERE ( O.username = '" + username
				+ "' OR O.email = '" + username.toLowerCase() + "')";

		// Mando il comando e prendo il risultato della query
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);

	}

	@Override
	public void verificaPassword(String password) throws RisultatoNonRicavabileException, PasswordErrataException {
		// prendo la password e la comparo alla password corretta

		try {
			passwordCorretto = risultato.getString(3);
		} catch (SQLException e) {
			throw new RisultatoNonRicavabileException();
		}
		if (!password.equals(passwordCorretto))
			throw new PasswordErrataException();

	}
	

	@Override
	public void verificaUsername(String username) throws UsernameNonEsistenteException, RisultatoNonRicavabileException {
		// Vado alla prima riga
		comunicazioneSQL.prossimaRiga();

		try {
			// mi estraggo il nome e il cognome
			utenteOperatore.setNome(risultato.getString(1));
			utenteOperatore.setCognome(risultato.getString(2));
		} catch (SQLException e) {
			// nel caso non si trova nessun valore significa che l'operatore non esiste nel
			// database
			throw new UsernameNonEsistenteException();
		}

	}

}
