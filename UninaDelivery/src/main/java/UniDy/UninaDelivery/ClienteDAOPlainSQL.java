package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAOPlainSQL implements ClienteDAO {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	
	
	public ClienteDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	@Override
	public ArrayList<Cliente> dammiTuttiClienti() throws NonCiSonoClientiException, RisultatoNonRicavabileException {
		ArrayList<Cliente> clientela = new ArrayList<Cliente>();
		Cliente tempCliente;
		String comando = "SELECT CodiceFiscale, nome , cognome FROM Cliente ORDER BY(CodCliente)";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			while(comunicazioneSQL.prossimaRiga()) {
				tempCliente = new Cliente(risultato.getString(1),risultato.getString(2),risultato.getString(3),null,null,null,null);
				clientela.add(tempCliente);
			}
		} catch (SQLException e) {
			throw new NonCiSonoClientiException();
		}
		
		
		return clientela;
	}


	@Override
	public void registraCliente(Cliente clienteTemp) throws NonPossibileCreareClienteException {
		String comando = "INSERT INTO Cliente SELECT MAX(codCliente)+1,'"+clienteTemp.getCodiceFiscale()+"','"+clienteTemp.getNome()+"','"+clienteTemp.getCognome()+"','"+clienteTemp.getEmail()
				+ "','"+clienteTemp.getNumeroCellulare()+"','"+ clienteTemp.getDataNascita()+"','"+clienteTemp.getPreferenzaContatto()+"' "
				+ " FROM Cliente;";
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		}catch (SQLException e) {
			throw new NonPossibileCreareClienteException();
		}
		
		
	}
	
	
	

}
