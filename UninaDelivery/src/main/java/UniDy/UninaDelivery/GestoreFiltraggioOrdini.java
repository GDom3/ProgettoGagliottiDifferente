package UniDy.UninaDelivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestoreFiltraggioOrdini {
	
	private ComunicaConDatabase comunicazioneSQL;
	private ResultSet risultato;
	private FinestraVisualizzaDatiFiltrabili datiOrdiniWindow;
	private AppBrain Hal;

	public GestoreFiltraggioOrdini(AppBrain appBrain) {
		comunicazioneSQL = new ComunicaConDatabase();	
		Hal = appBrain;
		
		 
	}
	
	protected void filtraPerUtenteData(String cliente, LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
		String addonsFiltro =  " WHERE (";
		if(datiOrdiniWindow.IsDataEsecuzioneSelezionato())
			addonsFiltro = addonsFiltro + "DataE ";
		else
			addonsFiltro = addonsFiltro + "DataConsegna ";
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"')";
		
		addonsFiltro = addonsFiltro + " AND (Cl.CodCliente = "+ Integer.valueOf(cliente)+" OR "
				+ "Cl.CodiceFiscale = '"+cliente+"' OR "
						+ "Cl.email = '"+cliente+"' OR "
								+ "Cl.NumeroCellulare = '"+cliente+"')";	
		
		visualizzaOrdini(addonsFiltro);
		
		if(datiOrdiniWindow.IsTabellaVuota())
			throw new EstrazioneCampiFallitaException("Non esiste nessun ordine del (ID,Codicefiscale,email,Numero di Telefono) utente nel intervallo di tempo selezionato"); 
		
	}

	protected void filtraPerDate(LocalDate dataInizio, LocalDate dataFine) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
		String addonsFiltro =  " WHERE ";
		if(datiOrdiniWindow.IsDataEsecuzioneSelezionato())
			addonsFiltro = addonsFiltro + "DataE ";
		else
			addonsFiltro = addonsFiltro + "DataConsegna ";
		
		addonsFiltro = addonsFiltro + "BETWEEN '"+dataInizio+"' AND '"+ dataFine +"'";
		
		
		visualizzaOrdini(addonsFiltro);
		
		if(datiOrdiniWindow.IsTabellaVuota())
			throw new EstrazioneCampiFallitaException("Non esiste nessun ordine nell'intervallo di tempo dato"); 
			
	}
	

	protected void filtraPerCliente(String cliente) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
	
		String addonsFiltro = " WHERE Cl.CodCliente = "+ Integer.valueOf(cliente)+" OR "
				+ "Cl.CodiceFiscale = '"+cliente+"' OR "
						+ "Cl.email = '"+cliente+"' OR "
								+ "Cl.NumeroCellulare = '"+cliente+"'";	
		
		visualizzaOrdini(addonsFiltro);
		
		if(datiOrdiniWindow.IsTabellaVuota())
			throw new EstrazioneCampiFallitaException("Non esiste nessun ordine del (ID,Codicefiscale,email,Numero di Telefono) utente"); 
	}

	protected void visualizzaOrdini(String addonsPerFiltrare) throws CreazioneStatementFallitaException, ConnessionNonRiuscitaException, RisultatoNonRicavabileException, EstrazioneCampiFallitaException {
		
		datiOrdiniWindow = Hal.getDatiOrdiniWindow();
		
		String comando = "SELECT Cl.CodiceFiscale, O.CodOrdine, count(E.CodiceBarre) AS NumMerci, sum(E.Costo) AS Totale, V.CodSpedizione "
				+ "FROM Cliente Cl NATURAL JOIN Ordine O NATURAL JOIN ESEMPLARE E NATURAL JOIN Viaggio V "
				 + addonsPerFiltrare + "GROUP BY (Cl.CodiceFiscale, O.CodOrdine,V.CodSpedizione)";
		
		datiOrdiniWindow.svuotaTabella();

		//Mando il comando e prendo il risultato della query
		risultato = comunicazioneSQL.comunicaConDatabaseQuery(comando);  	
		
		
		

		while(comunicazioneSQL.prossimaRiga()) {
			
			try {
				datiOrdiniWindow.aggiungiTupla(risultato.getString(1), risultato.getString(2), risultato.getInt(3), risultato.getFloat(4), risultato.getString(5));
			} catch (SQLException e) {
				throw new EstrazioneCampiFallitaException("Nessun Valore Trovato Nella Tabella"); 
			}
			
			
		}
	
	}
	
	

}
