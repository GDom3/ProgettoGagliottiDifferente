package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorriereDAOPlainSQL {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	 
	
	protected CorriereDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
		
	}


	public ArrayList<Corriere> estraiCorrieriSenzaSped() throws RisultatoNonRicavabileException, NonCiSonoCorrieriDisponibiliException  {
		ArrayList<Corriere> corrieri = new ArrayList<Corriere>();
		Corriere tempCorriere;
		String comando = "SELECT CodCorriere,Disponibilità,Patente,nome,cognome FROM Corriere WHERE Disponibilità = true ORDER BY (CodCorriere);";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempCorriere = new Corriere(risultato.getString(1),risultato.getBoolean(2),risultato.getString(3),risultato.getString(4),risultato.getString(5));
			corrieri.add(tempCorriere);
			
		} catch (SQLException e) {
			throw new NonCiSonoCorrieriDisponibiliException();
		}
		
		if(comunicazioneSQL.prossimaRiga())	
		do{
			try {
				tempCorriere = new Corriere(risultato.getString(1),risultato.getBoolean(2),risultato.getString(3),risultato.getString(4),risultato.getString(5));
				corrieri.add(tempCorriere);
			} catch (SQLException e) {
				throw new RisultatoNonRicavabileException();
			}
			
			
		}while(comunicazioneSQL.prossimaRiga());
		
		return corrieri;
	}
	
	
	
	
	
	
	
	
	
	
}
