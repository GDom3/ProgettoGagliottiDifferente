package UniDy.UninaDelivery;

public interface OperatoreDAO {

	Operatore provaAccesso(String username, String password) throws RisultatoNonRicavabileException, UsernameNonEsistenteException, PasswordErrataException;

	void richiestaVerifica(String username) throws RisultatoNonRicavabileException;

	void verificaPassword(String password) throws RisultatoNonRicavabileException, PasswordErrataException;

	void verificaUsername(String username) throws UsernameNonEsistenteException, RisultatoNonRicavabileException;

}