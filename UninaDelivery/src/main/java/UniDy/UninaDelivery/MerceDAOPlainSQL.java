package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MerceDAOPlainSQL {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;

	
	public MerceDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}
	
	protected void creaNuovaMerce(Merce nuovaMerce) throws NonPossibileCreareMerceException {
		
		
		String comando = "INSERT INTO Merce SELECT MAX(CodMerce)+1, '"+nuovaMerce.getNome() + "',"+ nuovaMerce.getPeso() + ",'"+ nuovaMerce.getMarca() + "',"+ nuovaMerce.getAnno()+",0,"+ nuovaMerce.getAzienda().getCodFornitore() + " FROM Merce;";
		
		
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		}catch (SQLException e) {
			throw new NonPossibileCreareMerceException();
		}
		
		
		
	}

	
	

}
