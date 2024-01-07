package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Corriere extends Persona {

	private String codCorriere;
	private boolean disponibilità;
	private String patente;
	private ArrayList<Spedizione> spedizioni = new ArrayList<Spedizione>();
	
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
	

	
	
	
}
