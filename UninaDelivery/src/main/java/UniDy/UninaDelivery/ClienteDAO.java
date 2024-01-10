package UniDy.UninaDelivery;

import java.util.ArrayList;

public interface ClienteDAO {

	ArrayList<Cliente> dammiTuttiClienti() throws NonCiSonoClientiException, RisultatoNonRicavabileException;

	void registraCliente(Cliente clienteTemp) throws NonPossibileCreareClienteException;

}