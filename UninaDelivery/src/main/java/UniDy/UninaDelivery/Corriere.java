package UniDy.UninaDelivery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Corriere extends Persona {

	private String codCorriere;
	private boolean disponibilità;
	private String patente;
	private int contratto;
	private int anniContributi;
	private Corriere cordinatore;
	private ArrayList<Spedizione> spedizioni;
	
	@Override
	public String toString() {
		return codCorriere;
	}

	protected Corriere(String codCorriere, boolean disponibilità, String patente, String Nome, String Cognome) {
		super();
		this.codCorriere = codCorriere;
		this.disponibilità = disponibilità;
		this.patente = patente;
		nome = Nome;
		cognome = Cognome;
	}

	protected Corriere(String codCorriere, String Nome, String Cognome) {
		super();
		nome = Nome;
		cognome = Cognome;
		this.codCorriere = codCorriere;
		
		
		
	}
	
	protected Corriere(String CodFisc, String Nome, String Cognome, LocalDate dataNascita, String patenti, String email, String numCell, int contratto, int anni, String codCordinatore) {
		codiceFiscale = CodFisc;
		nome = Nome;
		cognome = Cognome;
		DataNascita = dataNascita;
		patente = patenti;
		this.email = email;
		numeroCellulare = numCell;
		this.contratto = contratto;
		anniContributi = anni;
		cordinatore = new Corriere(codCordinatore,null,null);
		disponibilità = true;
	
		
	}

	

	protected String getCodCorriere() {
		return codCorriere;
	}

	protected boolean isDisponibilità() {
		return disponibilità;
	}

	protected String getPatente() {
		return patente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corriere other = (Corriere) obj;
		return Objects.equals(codCorriere, other.codCorriere);
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

	protected void setCodCorriere(String codCorriere) {
		this.codCorriere = codCorriere;
	}

	protected Corriere getCordinatore() {
		return cordinatore;
	}

	protected void setCordinatore(Corriere cordinatore) {
		this.cordinatore = cordinatore;
	}

	

	
	
	
}
