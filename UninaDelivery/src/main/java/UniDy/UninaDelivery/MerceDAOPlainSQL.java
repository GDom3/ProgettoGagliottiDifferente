package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MerceDAOPlainSQL implements MerceDAO {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;

	
	public MerceDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}
	
	@Override
	public void creaNuovaMerce(Merce nuovaMerce) throws NonPossibileCreareMerceException {
		
		
		String comando = "INSERT INTO Merce SELECT MAX(CodMerce)+1, '"+nuovaMerce.getNome() + "',"+ nuovaMerce.getPeso() + ",'"+ nuovaMerce.getMarca() + "',"+ nuovaMerce.getAnno()+",0,"+ "( "
				+ "SELECT CodAzienda FROM Azienda WHERE PartitaIva = '"+ nuovaMerce.getAzienda().getPartitaIVA()+"')" + " FROM Merce;";
		
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		}catch (SQLException e) {
			throw new NonPossibileCreareMerceException();
		}
		
		
		
	}
	
	@Override
	public ArrayList<Merce> estraiMerce() throws RisultatoNonRicavabileException, NonCiSonoMerciDisponibiliException  {
		ArrayList<Merce> merci = new ArrayList<Merce>();
		String comando = "SELECT Nome, Peso, Marca, Anno, CodAzienda FROM MERCE ORDER BY(CodMerce)";
		Merce tempMerce;
		Fornitore tempFornitore;
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		
		try {
			comunicazioneSQL.prossimaRiga();
			tempFornitore = new Fornitore(null,risultato.getString(5));
			tempMerce = new Merce(risultato.getString(1),risultato.getFloat(2),risultato.getString(3),risultato.getInt(4),tempFornitore);
			merci.add(tempMerce);
			
		} catch (SQLException e) {
			throw new NonCiSonoMerciDisponibiliException();
		}
		
		if(comunicazioneSQL.prossimaRiga())	
		do{
			try {
				tempFornitore = new Fornitore(null,risultato.getString(5));
				tempMerce = new Merce(risultato.getString(1),risultato.getFloat(2),risultato.getString(3),risultato.getInt(4),tempFornitore);
				merci.add(tempMerce);
			} catch (SQLException e) {
				throw new RisultatoNonRicavabileException();
			}
			
			
		}while(comunicazioneSQL.prossimaRiga());
				
	
		return merci;
		
	}
	
	
	

}
