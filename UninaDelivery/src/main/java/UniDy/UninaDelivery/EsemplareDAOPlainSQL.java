package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EsemplareDAOPlainSQL implements EsemplareDAO {
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	
	
	public EsemplareDAOPlainSQL(ComunicaConDatabase comunicazioneSQL) {
		this.comunicazioneSQL = comunicazioneSQL;
	}


	@Override
	public ArrayList<Esemplare> dammiEsemplariNonvenduti () throws RisultatoNonRicavabileException, NonCiSonoEsemplariNonVendutiException {
		ArrayList<Esemplare> esemplari = new ArrayList<Esemplare>();
		Esemplare tempesemplare;
		String comando = "SELECT E.CodiceBarre, Mer.Nome FROM Esemplare AS E NATURAL JOIN Merce AS Mer WHERE E.CodOrdine IS NULL ORDER BY(E.CodiceBarre)";
		
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);
		
		try {
			while(comunicazioneSQL.prossimaRiga()) {
				tempesemplare = new Esemplare(risultato.getString(1),risultato.getString(2));
				esemplari.add(tempesemplare);
			}
		} catch (SQLException e) {
			throw new NonCiSonoEsemplariNonVendutiException();
		}
		
		
		return esemplari;
	}


	@Override
	public void inserisciEsemplareInOrdine(Ordine ordine, Esemplare esemplare) throws NonCiSonoOrdiniAttesiException {

		String comando = "UPDATE Esemplare SET CodOrdine = '"+ordine.getCodOrdine()+"' WHERE CodiceBarre = '"+esemplare.getCodiceBarre()+"';";
		
		try {
			comunicazioneSQL.mandaQDDL_DML(comando);
		} catch (OperazioneUpdateNonRiuscitaException e) {
			throw new NonCiSonoOrdiniAttesiException();
		}
		
		
	}


	@Override
	public void creaEsemplare(Esemplare esemplareTemp) throws OperazioneUpdateNonRiuscitaException {
		
		String comando = "INSERT INTO ESEMPLARE VALUES ('"+ esemplareTemp.getCodiceBarre()+"','"+esemplareTemp.getColore()+"',"+esemplareTemp.getCosto()+",'"+esemplareTemp.getGaranzia()+"','"
				+ esemplareTemp.getDescrizione()+"',(SELECT CodMerce FROM Merce WHERE Nome = '"+ esemplareTemp.getMerceRiferimento().getNome()+ "' AND "
						+ " Marca = '"+ esemplareTemp.getMerceRiferimento().getMarca()+ "' AND Anno = "+ esemplareTemp.getMerceRiferimento().getAnno() + " ),null,("
								+ "SELECT CodMagazzino FROM Magazzino WHERE Nome = '"+ esemplareTemp.getMagazzinoRiferimento().getNome()+"' AND "
										+ "NumeroCivico = '"+ esemplareTemp.getMagazzinoRiferimento().getNumeroCivico()+ "' AND "
												+ "Città = '"+ esemplareTemp.getMagazzinoRiferimento().getCitta() + "' AND Via = '"+ esemplareTemp.getMagazzinoRiferimento().getVia() + "' AND "
														+ "CAP = '"+ esemplareTemp.getMagazzinoRiferimento().getCAP() + "'));";

		
		comunicazioneSQL.mandaQDDL_DML(comando);
		
		
		
	}
	
}
