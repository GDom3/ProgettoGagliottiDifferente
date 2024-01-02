package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {
	
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String email;
	private String numeroCellulare;
	private ArrayList<Ordine> ordini = new ArrayList<Ordine>();
	
	@Override
	public String toString() {
		return codiceFiscale;
	}

	protected void addOrdini(Ordine ordine) {
		this.ordini.add(ordine);
	}

	protected Cliente(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	protected String getCodiceFiscale() {
		return codiceFiscale;
	}
	
	protected void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	protected String getNome() {
		return nome;
	}

	protected void setNome(String nome) {
		this.nome = nome;
	}

	protected String getCognome() {
		return cognome;
	}

	protected void setCognome(String cognome) {
		this.cognome = cognome;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected String getNumeroCellulare() {
		return numeroCellulare;
	}

	protected void setNumeroCellulare(String numeroCellulare) {
		this.numeroCellulare = numeroCellulare;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(codiceFiscale, other.codiceFiscale);
	}

}
