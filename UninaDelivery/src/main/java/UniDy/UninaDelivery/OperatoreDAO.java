package UniDy.UninaDelivery;

public interface OperatoreDAO {

	Operatore provaAccesso(String username, String password)
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, UsernameNonEsistenteException, PasswordErrataException;

	void richiestaVerifica(String username)
			throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException;

	void verificaPassword(String password) throws RisultatoNonRicavabileException, PasswordErrataException;

	void verificaUsername(String username) throws UsernameNonEsistenteException, RisultatoNonRicavabileException;

}