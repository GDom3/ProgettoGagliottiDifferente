package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorriereDAOPlainSQL implements CorriereDAO {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	 
	
	protected CorriereDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
		
	}


	@Override
	public ArrayList<Corriere> estraiCorrieriSenzaSped() throws RisultatoNonRicavabileException, NonCiSonoCorrieriDisponibiliException  {
		ArrayList<Corriere> corrieri = new ArrayList<Corriere>();
		Corriere tempCorriere;
		String comando = "SELECT CodiceFiscale,nome,cognome,Patente,Disponibilità FROM Corriere WHERE Disponibilità = true ORDER BY (CodCorriere);";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempCorriere = new Corriere(risultato.getString(1),risultato.getString(2),risultato.getString(3),null,risultato.getString(4),null,null,0,0,null,risultato.getBoolean(5));
			corrieri.add(tempCorriere);
			
		} catch (SQLException e) {
			throw new NonCiSonoCorrieriDisponibiliException();
		}
		
		if(comunicazioneSQL.prossimaRiga())	
		do{
			try {
				tempCorriere = new Corriere(risultato.getString(1),risultato.getString(2),risultato.getString(3),null,risultato.getString(4),null,null,0,0,null,risultato.getBoolean(5));
				corrieri.add(tempCorriere);
			} catch (SQLException e) {
				throw new RisultatoNonRicavabileException();
			}
			
			
		}while(comunicazioneSQL.prossimaRiga());
		
		return corrieri;
	}
	
	
	@Override
	public ArrayList<Corriere> estraiTuttiCorrieri() throws RisultatoNonRicavabileException , NonCiSonoCorrieriException {
		ArrayList<Corriere> corriere = new ArrayList<Corriere>();
		Corriere tempCorriere;
		String comando = "SELECT CodiceFiscale, nome , cognome FROM Corriere ORDER BY(CodCorriere)";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			while(comunicazioneSQL.prossimaRiga()) {
				tempCorriere = new Corriere(risultato.getString(1),risultato.getString(2),risultato.getString(3),null,null,null,null,0,0,null,true);
				corriere.add(tempCorriere);
			}
		} catch (SQLException e) {
			throw new NonCiSonoCorrieriException();
		}
		
		
		return corriere;
	}


	@Override
	public void assumiCorriere(Corriere corriere) throws OperazioneUpdateNonRiuscitaException {
		String comando = "INSERT INTO Corriere SELECT MAX(codCorriere)+1,'"+ corriere.getCodiceFiscale()+"','"+corriere.getNome()+"','"
				+ corriere.getCognome()+"','"+corriere.getEmail()+"','"+corriere.getNumeroCellulare()+"','"+corriere.getDataNascita()+"',"
						+ corriere.getContratto()+","+corriere.getAnniContributi()+",true,'"+corriere.getPatente()+"',"+"("
								+ "SELECT CodCorriere FROM Corriere WHERE CodiceFiscale = '"+corriere.getCoordinatore().getCodiceFiscale()+"'),1 FROM Corriere;";
	
		comunicazioneSQL.mandaQDDL_DML(comando);
		
		
		
	}
	
	
	
	
	
	
	
	
}
