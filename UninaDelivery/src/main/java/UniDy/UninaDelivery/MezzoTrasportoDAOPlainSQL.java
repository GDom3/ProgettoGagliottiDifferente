package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MezzoTrasportoDAOPlainSQL {

	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	 
	
	protected MezzoTrasportoDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
		
	}


	public ArrayList<MezzoTrasporto> estraiMezziSenzaSped() throws RisultatoNonRicavabileException, NonCiSonoMezziTrasportoDisponibiliException {
		
		ArrayList<MezzoTrasporto> mezzi = new ArrayList<MezzoTrasporto>();
		MezzoTrasporto tempMezzo;
		
		String Comando = "SELECT Codmezzo,targa FROM MezzoTrasporto WHERE Disponibilità = TRUE ORDER BY (CodMezzo);";
		
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(Comando);
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempMezzo = new MezzoTrasporto(risultato.getString(1),risultato.getString(2));
			mezzi.add(tempMezzo);
			
		} catch (SQLException e) {
			throw new NonCiSonoMezziTrasportoDisponibiliException();
		}
		if(comunicazioneSQL.prossimaRiga())	
		do{
			try {
				tempMezzo = new MezzoTrasporto(risultato.getString(1),risultato.getString(2));
				mezzi.add(tempMezzo);
			} catch (SQLException e) {
				throw new RisultatoNonRicavabileException();
			}
			
			
		}while(comunicazioneSQL.prossimaRiga());
		
		return mezzi;
	}
	
	public void registraMezzo(MezzoTrasporto mezzo) throws OperazioneUpdateNonRiuscitaException  {
	    String comando = "INSERT INTO MezzoTrasporto " +
	                     "SELECT  MAX(CodMezzo) + 1, '" + mezzo.getTarga() + "', " + mezzo.getCapienza() +
	                     ", '" + mezzo.getMarca() + "', '" + mezzo.getModello() + "', " + mezzo.getAssicurazione() +
	                     ", true, '" + mezzo.getPatentiNecessarie() + "', 1 " +
	                     " FROM MezzoTrasporto;";
	    

	    
	    comunicazioneSQL.mandaQDDL_DML(comando);
	}
}

