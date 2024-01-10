package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazzinoDAOPlainSQL implements MagazzinoDAO {

	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;

	
	public MagazzinoDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	@Override
	public ArrayList<Magazzino> dammiTutteMagazzini() throws NonCiSonoMagazziniDisponibiliException, RisultatoNonRicavabileException {
		
		ArrayList<Magazzino> magazzini = new ArrayList<Magazzino>();
		Magazzino tempMagazzino;
		String comando = "SELECT nome,NumeroCivico,Città,Via,CAP FROM Magazzino ORDER BY (CodAzienda);";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempMagazzino = new Magazzino(risultato.getString(1),risultato.getString(2),risultato.getString(3),risultato.getString(4),risultato.getString(5),0);
			magazzini.add(tempMagazzino);
			
		} catch (SQLException e) {
			throw new NonCiSonoMagazziniDisponibiliException();
		}
		
		if(comunicazioneSQL.prossimaRiga())	
		do{
			try {
				tempMagazzino = new Magazzino(risultato.getString(1),risultato.getString(2),risultato.getString(3),risultato.getString(4),risultato.getString(5),0);
				magazzini.add(tempMagazzino);
			} catch (SQLException e) {
				throw new RisultatoNonRicavabileException();
			}
			
			
		}while(comunicazioneSQL.prossimaRiga());
		
		return magazzini;
	
	}
}
