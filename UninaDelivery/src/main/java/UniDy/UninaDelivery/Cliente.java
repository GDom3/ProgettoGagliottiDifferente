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
