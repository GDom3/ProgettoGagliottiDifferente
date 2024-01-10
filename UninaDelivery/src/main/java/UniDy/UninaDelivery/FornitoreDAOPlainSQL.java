package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornitoreDAOPlainSQL implements FornitoreDAO {
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;

	
	public FornitoreDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	@Override
	public ArrayList<Fornitore> dammiTuttiFornitori() throws RisultatoNonRicavabileException, NonCiSonoFornitoriException {
		ArrayList<Fornitore> fornitori = new ArrayList<Fornitore>();
		Fornitore tempFornitore = null;
		
		String comando = "SELECT Nome,PartitaIva FROM Azienda WHERE IsFornitore = true ORDER BY (CodAzienda)";

		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempFornitore = new Fornitore(risultato.getString(1),risultato.getString(2));
			fornitori.add(tempFornitore);
			
		} catch (SQLException e) {
			throw new NonCiSonoFornitoriException();
		}
			
			
		if(comunicazioneSQL.prossimaRiga())	
			do{
			
				try {
					tempFornitore = new Fornitore(risultato.getString(1),risultato.getString(2));
					fornitori.add(tempFornitore);
				} catch (SQLException e) {
					throw new RisultatoNonRicavabileException();
				}	
			}while(comunicazioneSQL.prossimaRiga());
		
		
		
		
		return fornitori;
	}
	
	
	
}
