package UniDy.UninaDelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Persona {
	private String CodCliente;
	private ArrayList<Ordine> ordini = new ArrayList<Ordine>();
	
	protected Cliente(String codiceFiscale) {
		super();
		this.codiceFiscale = codiceFiscale;
	}


	public Cliente(String codice, String codfisc, String nome, String cognome) {
		CodCliente = codice;
		codiceFiscale = codfisc;
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public String toString() {
		return codiceFiscale;
	}

	protected void addOrdini(Ordine ordine) {
		this.ordini.add(ordine);
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

	protected String getCodCliente() {
		return CodCliente;
	}

}
