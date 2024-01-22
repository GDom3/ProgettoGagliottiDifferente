package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Corriere extends Persona {
	private boolean disponibilità;
	private String patente;
	private int contratto;
	private int anniContributi;
	//private Corriere coordinatore;
	private String codiceFiscaleCordinatore;
	private ArrayList<Spedizione> spedizioni;

	protected Corriere(String CodFisc, String Nome,String Cognome, LocalDate dataNascita, String patenti, String email, String numCell, int contratto, int anni, String codCordinatore, boolean b) {
		setCodiceFiscale(CodFisc);
		setNome(Nome);
		setCognome(Cognome);
		setDataNascita(dataNascita);
		setPatente(patenti);
		setEmail(email);
		setNumeroCellulare(numCell);
		this.contratto = contratto;
		anniContributi = anni;
		codiceFiscaleCordinatore =codCordinatore;
		disponibilità = b;
	
		
	}


	protected boolean isDisponibilità() {
		return disponibilità;
	}

	protected String getPatente() {
		return patente;
	}

	protected int getContratto() {
		return contratto;
	}

	protected void setContratto(int contratto) {
		this.contratto = contratto;
	}

	protected int getAnniContributi() {
		return anniContributi;
	}

	protected void setAnniContributi(int anniContributi) {
		this.anniContributi = anniContributi;
	}

	protected String getCodiceFiscaleCordinatore() {
		return codiceFiscaleCordinatore;
	}

	protected void setCodiceFiscaleCordinatore(String codiceFiscaleCordinatore) {
		this.codiceFiscaleCordinatore = codiceFiscaleCordinatore;
	}

	protected ArrayList<Spedizione> getSpedizioni() {
		return spedizioni;
	}

	protected void setSpedizioni(ArrayList<Spedizione> spedizioni) {
		this.spedizioni = spedizioni;
	}

	protected void setDisponibilità(boolean disponibilità) {
		this.disponibilità = disponibilità;
	}

	protected void setPatente(String patente) {
		this.patente = patente;
	}
	
	
}
